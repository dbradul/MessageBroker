/******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************
 *
 * @file          AbstractProxy.java
 * @author        DBradul
 * 
 ****************************************************************************************/
package org.onemoresunday.messagebroker.common;

import java.io.IOException;

import org.onemoresunday.messagebroker.messaging.GCFMessage;
import org.onemoresunday.messagebroker.messaging.MessageBroker;
import org.onemoresunday.messagebroker.messaging.MessageBrokerException;

/****************************************************************************************
 * 
 * Base class for all proxies.
 * 
 * Proxy is the class used on a client side to communicate with server (see AbstractStub description).
 * Proxy classes derived from this class are generated on the basis of interfaces defined in XML-files (.HBGA). 
 * These interfaces contain definitions for supported methods, events, user-defined data types and other relevant 
 * information. See org.onemoresunday.generated.api.SGPimMgrProxy as an example.
 * 
 * @see org.onemoresunday.common.AbstractStub
 * @author DBradul
 *
 ****************************************************************************************/
public abstract class AbstractProxy
{
   /** Unique value to identify call-response pair within current session */
   protected int mCallId = -1;

   /**
    * Timeout for the sent call. 
    * -1 (default) means "no timeout"
    * When timeout is expired the method timeoutExpired() is called from the MessageRouter thread
    * 
    * @see timeoutExpired(GCFMessage message)
    * */
   protected long mTimeout = -1; // in milliseconds

   protected MessageBroker mMessageRouter;

   protected GCFMessage mRawMessage = null;

   protected String mInstName = "";

   protected String mSenderSuffix = "";

   public abstract void registerForEvents();

   public abstract void unregisterFromEvents();
   

   public AbstractProxy()
   {
   }

   /*******************************************************************************
    * This method dispatches the received message to a concrete overridden method
    * 
    * @param response - response to be dispatched
    * @throws IOException - exception is thrown when the client code makes an error.
    *         Reason it is not handled on the client is to simplify the client code and 
    *         to deliver all errors in one uniformed way.
    * @see    errorOccurred(GCFMessage, String)
    */
   public abstract void dispatchResponse(GCFMessage response) throws MessageBrokerException;
   

   /*******************************************************************************
    * Dispatch the received event to a concrete overridden method
    * 
    * @param event - event to be dispatched
    * @throws MessageBrokerException 
    */
   public abstract void dispatchEvent(GCFMessage event) throws MessageBrokerException;


   /*******************************************************************************
    * Fired by watchdog thread when the message timeout is expired.
    * Should be overridden in those proxies that are interested in this event (presumably all)
    * 
    * @param message - message for which the timeout has been expired
    */
   public void onTimeoutExpired(GCFMessage message)
   {
      System.out.println("Timeout has been expired for the message: '" + message.toString() + "'");
   }


   /*******************************************************************************
    * Fired by dispatch thread when an exception is thrown at dispatching response.
    * Should be overridden in those proxies that are interested in this event
    * 
    * @param message - message for which the error has occurred
    * @param message - error message description
    */
   public void onRouterException(GCFMessage message, Exception exception)
   {
      System.out.println("Exception is thrown for the message: '" + message + "'");
      exception.printStackTrace();
   }
   
   /*******************************************************************************
    * Set the timeout for the current call. If not set, default value is used.
    * 
    * @param timeout in milliseconds
    */
   public void setTimeout( long timeout )
   {
      mTimeout = timeout;
   }

   /*******************************************************************************
    * Get the timeout for the current call. If not set, default value is used.
    * 
    * @return timeout in milliseconds
    */
   public long getTimeout()
   {
      return mTimeout;
   }
   
   /*******************************************************************************
    * Get callId. CallId is the unique, auto-incremented value that is used to
    * differentiate one call (request) from another until response is received. 
    * This allows for different calls to be executed in parallel.
    * 
    * @return callId
    */
   public int getCallId()
   {
      return mCallId;
   }

   /*******************************************************************************
    * Set callId. CallId is the unique, auto-incremented value that is used to
    * differentiate one call (request) from another until response is received. 
    * This allows for different calls to be executed in parallel.
    * 
    * @param callId - callId to set
    */
   public void setCallId( int callId )
   { 
      mCallId = callId;
   }
   
   /*******************************************************************************
    * Set router.
    * Router is used for routing (sending+dispatching) messages from proxies to stubs
    * 
    * @param broker - message broker to set
    */
   public void setBroker(MessageBroker broker)
   {
      mMessageRouter = broker;
   }
   
   /*******************************************************************************
    * Get router.
    * Router is used for routing (sending+dispatching) messages from proxies to stubs
    * 
    * @return message broker
    */
   public MessageBroker getBroker()
   {
      return mMessageRouter;
   }
   
   /*************************************************************************************
    * Return currently received GCF-message
    * Valid only within the current overridden 'XXXresponse' method call
    * 
    * @return gcfMessage - raw GCF-message
    */
   public GCFMessage getRawMessage()
   {
      return mRawMessage;
   }

   /*************************************************************************************
    * Set current GCF-message
    * 
    * @param rawMessage - GCF-message to set
    */
   public void setRawMessage(GCFMessage rawMessage)
   {
      mRawMessage = rawMessage;
   }
   
   /*************************************************************************************
    * Get instance name.
    * This name is prepended as prefix for all GCF-commands
    * The notion of instance name is to differentiate different instances of the same interface.
    * Similar to hash() value for different objects of the same class.
    * 
    * @see    setInstanceName()
    * @return instanceName
    */
   public String getInstanceName()
   {
      return mInstName;
   }

   /*************************************************************************************
    * Set instance name.
    * This name is prepended as prefix for all GCF-commands
    * The notion of instance name is to differentiate different instances of the same interface.
    * Similar to hash() value for different objects of the same class.
    * 
    * @see    getInstanceName()
    * @return instanceName
    */
   public void setInstanceName(String instance)
   {
      mInstName = instance;
   }
   
   /*************************************************************************************
    * Get sender suffix
    * This value is appended as suffix for all source addresses in GCF-commands
    * The notion of suffix is to differentiate source addresses sent to the same interface.
    * 
    * @see    setSenderSuffix()
    * @return instanceName
    */
   public String getSenderSuffix()
   {
      return mSenderSuffix;
   }

   /*************************************************************************************
    * Set instance name.
    * This value is appended as suffix for all source addresses in GCF-commands
    * The notion of suffix is to differentiate source addresses sent to the same interface.
    * 
    * @see    getSenderSuffix()
    * @return instanceName
    */
   public void setSenderSuffix(String senderSuffix)
   {
      mSenderSuffix = senderSuffix;
   }

}
