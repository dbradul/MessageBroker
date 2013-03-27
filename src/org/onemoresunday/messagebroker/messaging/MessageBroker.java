/*****************************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************************
 *
 * @file    MessageRouter.java
 * @author  DBradul
 *****************************************************************************************/
package org.onemoresunday.messagebroker.messaging;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.onemoresunday.messagebroker.messaging.GCFMessage.Type;
import org.onemoresunday.messagebroker.common.AbstractProxy;
import org.onemoresunday.messagebroker.common.AbstractStub;

/****************************************************************************************
 * MessageRouter is used to provides dispatching mechanism for sending and receiving GCFMessages
 * 
 * @author DBradul
 ****************************************************************************************/
public class MessageBroker
{
   private static final String               MESSAGE_SHUTDOWN     = "SHUTDOWN";
   private static final int                  WATCHDOG_PERIOD      = 100; //milliseconds
   private static MessageBroker mInstance;

   private List<MessageSenderEntry>          mWaitForResponseList = new LinkedList<MessageSenderEntry>();
   private List<MessageSenderEntry>          mWaitForEventsList   = new LinkedList<MessageSenderEntry>();
   private Map<String, AbstractStub>         mCallHandlersList    = new HashMap<String, AbstractStub>();
   private LinkedBlockingQueue<GCFMessage>   mIncomingMsgQueue    = new LinkedBlockingQueue<GCFMessage>();

   private Thread mMessageDispatcherThread;
   
   private int mLastCallID;
   
   private boolean mIsStarted;

   /***************************************************************************************
    * Constructor.
    */
   private MessageBroker()
   {
   }

   /***************************************************************************************
    * Return the single instance of the class
    */
   public static synchronized MessageBroker getInstance()
   {
      if (null == mInstance)
      {
         mInstance = new MessageBroker();
      }
      
      return mInstance;
   }

   /***************************************************************************************
    * Get the next CallID. This method is used to ensure that two calls in one session don't have the same CallID.
    * 
    * @return int - the last CallID increased by one
    */
   public synchronized int generateNextCallID()
   {
      return ++mLastCallID;
   }

   /****************************************************************************************
    * Add event subscriber
    * 
    * @param proxy - subscriber to add
    * @param event - event for which the proxy is subscribed
    */
   public void addEventSubscriber(AbstractProxy proxy, GCFMessage event)
   {
      synchronized (mWaitForEventsList)
      {
         mWaitForEventsList.add(new MessageSenderEntry(event, proxy));
      }
   }

   /****************************************************************************************
    * Remove event subscriber
    * 
    * @param proxy - subscriber to remove
    */
   public void removeEventSubscriber(AbstractProxy proxy)
   {
      synchronized (mWaitForEventsList)
      {
         for (Iterator<MessageSenderEntry> it = mWaitForEventsList.iterator(); it.hasNext();)
         {
            MessageSenderEntry entry = it.next();
            if (proxy == entry.getSender())
            {
               it.remove();
            }
         }
      }
   }
   
   /*************************************************************************************
    * Add call handler that associates an implementor with the call name
    * 
    * @param stub - object that implements the call handling
    * @param call name - call name for which the handling is added
    */
   public void addCallHandler(AbstractStub stub, GCFMessage callName)
   {
      synchronized (mCallHandlersList)
      {
         mCallHandlersList.put(callName.getDestAddress(), stub);
      }
   }

   /*************************************************************************************
    * Remove call handler 
    * If the message addressed to this handler is received afterward, it won't be dispatched
    * 
    * @param stub - object that implements call handling
    */
   public void removeCallHandler(AbstractStub stub)
   {
      synchronized (mCallHandlersList)
      {
         Iterator<AbstractStub> mapIt = mCallHandlersList.values().iterator();
         for (; mapIt.hasNext();)
         {
            AbstractStub stubEntry = mapIt.next();
            if (stub == stubEntry)
            {
               mapIt.remove();
            }
         }
      }
   }
   
   /*************************************************************************************
    * Remove response handler from the broker.
    * If the message addressed to this handler is received afterward, it won't be dispatched
    * 
    * @param proxy - object that implements response handling
    */
   public void removeResponseHandler(AbstractProxy proxy)
   {
      synchronized (mWaitForResponseList)
      {
         for (Iterator<MessageSenderEntry> it = mWaitForResponseList.iterator(); it.hasNext();)
         {
            MessageSenderEntry entry = it.next();
            if (proxy == entry.getSender())
            {
               it.remove();
            }
         }
      }
   }
   
   /*************************************************************************************
    * Register call handler in the broker
    * 
    * @param stub - stub to register in router 
    */
   public void registerStub(AbstractStub stub)
   {
      stub.setBroker(this);
      stub.registerCalls();
   }

   /*************************************************************************************
    * Send a message to the broker
    * 
    * @param proxy      calling instance of AbstractProxy
    * @param message    message to send 
    * @throws InterruptedException 
    */
   public void sendMessage(GCFMessage message, AbstractProxy proxy) throws MessageBrokerException
   {
      try
      {
         MessageSenderEntry entry = new MessageSenderEntry(message, proxy);
         
         // there are cases when we aren't interested in a response
         // => don't add a sender into the waiting list
         // E.g.: EVNT and CTRL
         if (null != proxy)
         {
            message.setTimestamp(System.currentTimeMillis(), proxy.getTimeout());
            
            synchronized (mWaitForResponseList)
            {
               mWaitForResponseList.add(mWaitForResponseList.size(), entry);
            }
         }
         
         mIncomingMsgQueue.put(entry.getMessage());
      }
      catch (InterruptedException e)
      {
         // rethrow with IOException to simplify client interface
         throw new MessageBrokerException("Connection is not ready!");
      }
   }

   protected MessageSenderEntry findAndRemoveMessageSender(GCFMessage message)
   {
      MessageSenderEntry entry = null;
      for (Iterator<MessageSenderEntry> it2 = mWaitForResponseList.iterator(); it2.hasNext();)
      {
         entry = it2.next();
          
         if (message.matches(entry.getMessage()))
         {
            it2.remove();
            break;
         }
      }

      return entry;
   }
   
   /*************************************************************************************
    * Main "message loop" function that dispatches received messages among 
    * waiting for responses senders (AbstractProxies), 
    * responsible for processing handlers (AbstractStub), etc
    *
    */
   private void startDispatchThread()
   {
      final String THREAD_NAME = "MessageDispatcherThread";
      
      mMessageDispatcherThread = new Thread( new Runnable()
      {
         @Override
         public void run()
         {
            try
            {
               GCFMessage message = null;
               
               while (null != (message = mIncomingMsgQueue.take()))
               {
                  // dispatch the response to the corresponding proxy
                  if (Type.RESP == message.getType())
                  {
                     synchronized (mWaitForResponseList)
                     {
                        MessageSenderEntry entry = findAndRemoveMessageSender(message);

                        if (entry != null)
                        {
                           try
                           {
                              entry.getSender().dispatchResponse( message );
                           }
                           catch (Exception e)
                           {
                              System.out.println("MessageRouter::dispatchThread(): " + e.getMessage());
                              entry.getSender().onRouterException(message, e);
                           }
                        }
                     }
                  }

                  // dispatch the event to all its subscribers
                  else if (Type.EVNT == message.getType())
                  {
                     List<AbstractProxy> senderList = new LinkedList<AbstractProxy>();
                     
                     synchronized (mWaitForEventsList)
                     {
                        for (Iterator<MessageSenderEntry> it2 = mWaitForEventsList.iterator(); it2.hasNext();)
                        {
                           MessageSenderEntry entry = it2.next();

                           if( message.getSourceAddress().equals( entry.getMessage().getSourceAddress() ) && 
                               message.getDestAddress().equals( entry.getMessage().getDestAddress() ) )
                           {
                              senderList.add( entry.getSender() );
                           }
                        }
                     }
                     
                     // we put event dispatching out of synchronized(...) scope to allow a user to modify
                     // event subscribers list (unregisterFromEvents) within event handler function call
                     // (i.e. in MessageRouter thread)
                     for (AbstractProxy sender : senderList)
                     {
                        try
                        {
                           sender.dispatchEvent(message);
                        }
                        catch (Exception e)
                        {
                           System.out.println("MessageRouter::DispatchThread(): " + e.getMessage());
                           sender.onRouterException(message, e);
                        }
                     }
                  }

                  // dispatch the call to its stub
                  else if (Type.CALL == message.getType())
                  {
                     synchronized (mCallHandlersList)
                     {
                        AbstractStub stub = mCallHandlersList.get(message.getDestAddress());
                        if (stub != null)
                        {
                           stub.dispatchRequest(message);
                        }
                        else
                        {
                           MessageSenderEntry entry = findAndRemoveMessageSender(message);
                           
                           if (entry != null)
                           {
                              entry.getSender().onRouterException(
                                    message,
                                    new MessageBrokerException("Stub is not implemented for the call: " +
                                                               message.toString()));
                           }
                        }
                     }
                  }
                     
                  // dispatch the abort call to its stub
                  else if (Type.ABRT == message.getType())
                  {
                     synchronized (mCallHandlersList)
                     {
                        AbstractStub stub = mCallHandlersList.get(message.getDestAddress());
                        if (stub != null)
                        {
                           stub.dispatchAbort(message);
                        }
                        else
                        {
                           MessageSenderEntry entry = findAndRemoveMessageSender(message);
                           
                           if (entry != null)
                           {
                              entry.getSender().onRouterException(
                                    message,
                                    new MessageBrokerException("Stub is not implemented for the call: " +
                                                               message.toString()));
                           }
                        }
                     }
                  }
                     
                  // apply the corresponding actions for the CTRL message
                  else if ( Type.CTRL == message.getType() )
                  {
                     if( message.getDestAddress().equals(MESSAGE_SHUTDOWN) )
                     {
                        break;
                     }
                  }
               }// while
            }// try
            
            catch (InterruptedException e)
            {
               System.out.println( THREAD_NAME + " was interrupted: " + e.getMessage() );
               e.printStackTrace();
            }
            catch (Exception e)
            {
               System.out.println( THREAD_NAME + " was interrupted: " + e.getMessage() );
               e.printStackTrace();
            }
         }
      },
      
      THREAD_NAME);
      
      mMessageDispatcherThread.start();
   }

   /*************************************************************************************
    * Watchdog thread checks for expired messages and notifies a sender about them
    * 
    */
   private void startWatchDogThread()
   {
      final String THREAD_NAME = "WatchDogThread";
      
      new Thread ( new Runnable()
      {
         @Override
         public void run()
         {
            boolean bExited = false;
            while (!bExited)
            {
               if (!mMessageDispatcherThread.isAlive())
               {
                  startDispatchThread();
               }
               
               else
               {
                  synchronized (mWaitForResponseList)
                  {
                     for (Iterator<MessageSenderEntry> it = mWaitForResponseList.iterator(); it.hasNext();)
                     {
                        MessageSenderEntry waitForRespEntry = it.next();

                        if (Type.CTRL == waitForRespEntry.getMessage().getType())
                        {
                           if( waitForRespEntry.getMessage().getDestAddress().equals(MESSAGE_SHUTDOWN) )
                           {
                              bExited = true;
                              break;
                           }
                        }
                        else if (waitForRespEntry.getMessage().getTimestamp() < System.currentTimeMillis())
                        {
                           waitForRespEntry.getSender().onTimeoutExpired(waitForRespEntry.getMessage());
                           it.remove(); //TODO: do it outside of iterating and synchronized ("alien" method)
                        }
                     }
                  }
               }
               
               try
               {
                  Thread.sleep(WATCHDOG_PERIOD);
               }
               catch (InterruptedException e)
               {
                  System.out.println( THREAD_NAME + " was interrupted: " + e.getMessage() );
                  e.printStackTrace();
               }
            }
         }
      },
      THREAD_NAME

      ).start();
   }
   
   /*************************************************************************************
    * Check if router was already started.
    * 
    * @return true if started
    */
   public boolean isStarted()
   {
      return mIsStarted;
   }

   /*************************************************************************************
    * Start message router
    */
   public void start()
   {
      if (!mIsStarted)
      {
         startDispatchThread();
         startWatchDogThread();
         
         mIsStarted = true;
      }
      else
      {
         throw new IllegalStateException("MessageRouter was already started");
      }
   }

   /*************************************************************************************
    * Stop message router
    */
   public void shutdown() throws MessageBrokerException, InterruptedException
   {
      sendMessage(new GCFMessage(null, MESSAGE_SHUTDOWN, null, Type.CTRL), new AbstractProxy()
      {
         @Override
         public void registerForEvents()
         {
            // dummy proxy
         }

         @Override
         public void unregisterFromEvents()
         {
            // dummy proxy
         }

         @Override
         public void dispatchResponse(GCFMessage response) throws MessageBrokerException
         {
            // dummy proxy
         }

         @Override
         public void dispatchEvent(GCFMessage event) throws MessageBrokerException
         {
            // dummy proxy
         }
      });
      
      // let the watchdog and dispatch threads to be finished
      Thread.sleep( WATCHDOG_PERIOD );
      
      mIsStarted = false;
   }
}

/****************************************************************************************
 * This class holds an association between a message and its sender.
 * This association is required to dispatch received response to the corresponding proxy
 * 
 * @author DBradul
 ****************************************************************************************/
class MessageSenderEntry
{
   private GCFMessage      mMessage;
   private AbstractProxy   mSender;
   
   public MessageSenderEntry(GCFMessage message, AbstractProxy sender)
   {
      mMessage = message;
      mSender = sender;
   }
   
   public GCFMessage getMessage() 
   { 
      return mMessage; 
   }

   public AbstractProxy getSender() 
   { 
      return mSender; 
   }
}
