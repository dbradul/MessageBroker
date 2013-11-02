/******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 * 
 ******************************************************************************
 *
 * @file    MessageRouterTest.java
 * @author  DBradul
 ******************************************************************************/
package org.bench.messagebroker.messaging;

import static org.bench.messagebroker.generated.types.SGPimMgrTypes.Result.PIMMGR_RESULT_OK;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.bench.messagebroker.generated.api.SGPimMgrProxy;
import org.bench.messagebroker.messaging.GCFMessage;
import org.bench.messagebroker.messaging.MessageBroker;
import org.bench.messagebroker.messaging.MessageBrokerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.onemoresunday.gcfparser.GCFEnum;
import org.onemoresunday.gcfparser.GCFList;
import org.onemoresunday.gcfparser.GCFNumber;
import org.onemoresunday.gcfparser.GCFString;

/******************************************************************************
 * Tests for MessageRouter
 * 
 * @author DBradul
 *****************************************************************************/
public class MessageBrokerTest
{
   private MessageBroker mMsgBroker;

   @Before
   public void setUp()
   {
      mMsgBroker = MessageBroker.getInstance();
      if (!mMsgBroker.isStarted())
      {
         mMsgBroker.start();
      }
   }

   @After
   public void tearDown() throws MessageBrokerException, InterruptedException
   {
      if (mMsgBroker != null)
      {
         mMsgBroker.shutdown();
      }
   }
      

   @Test
   public void testResponseDispatching() throws Exception
   {
      final CountDownLatch latch = new CountDownLatch(1);
      final GCFString timeoutExpired = new GCFString("FALSE");

      mMsgBroker.registerStub(SGPimMgrStubImpl.getInstance());

      SGPimMgrProxy proxy = new SGPimMgrProxy()
      {
         @Override
         protected void refreshResponse(GCFEnum result) throws MessageBrokerException
         {
            if (result.equals(PIMMGR_RESULT_OK))
            {
               timeoutExpired.setValue("TRUE");
            }
            latch.countDown();
         }
      };

      proxy.refreshRequest(new GCFNumber("1"), new GCFList("type_list"));

      // wait till the count reaches zero (true) or waiting timeout expires (false)
      assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
      assertTrue(timeoutExpired.getBooleanValue());
   }
      

   @Test
   public void testEventsDispatching() throws Exception
   {
      final CountDownLatch latch = new CountDownLatch(1);
      final GCFString result = new GCFString("FALSE");

      SGPimMgrStubImpl stub = SGPimMgrStubImpl.getInstance();
      mMsgBroker.registerStub(stub);

      final Set<Integer> resultSet = Collections.synchronizedSet(new TreeSet<Integer>());

      SGPimMgrProxy proxy = new SGPimMgrProxy()
      {
         @Override
         protected void eventStatusChanged(GCFNumber pimmgr_id, GCFEnum status,
               GCFEnum device_status, GCFList params) throws MessageBrokerException
         {
            resultSet.remove(pimmgr_id.getIntValue());

            if (resultSet.size() == 0)
            {
               latch.countDown();
               result.setValue("TRUE");
            }
         }
      };

      proxy.registerForEvents();

      for (int i = 0; i < 10; i++)
      {
         resultSet.add(i);
         stub.eventStatusChanged(new GCFNumber("" + i), new GCFEnum("PIMMGR_OPEN"),
               new GCFEnum("PIMMGR_DEVICE_DETACHED"), new GCFList());
      }

      // wait till the count reaches zero (true) or waiting timeout expires (false)
      assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
      assertTrue(result.getBooleanValue());
   }


   @Test
   public void testTimeout() throws Exception
   {
      final CountDownLatch latch = new CountDownLatch(1);
      final GCFString timeoutExpired = new GCFString("FALSE");

      // intentionally suppress request processing to enforce timeout exception
      SGPimMgrStubImpl stub = new SGPimMgrStubImpl()
      {
         @Override
         public void refreshRequest(GCFNumber pimmgrId, GCFList objTypes)
         {
            //
         }
      };

      mMsgBroker.registerStub(stub);

      SGPimMgrProxy proxy = new SGPimMgrProxy()
      {
         @Override
         protected void refreshResponse(GCFEnum result) throws MessageBrokerException
         {
            latch.countDown();
         }

         @Override
         public void onTimeoutExpired(GCFMessage message)
         {
            timeoutExpired.setValue("TRUE");
            latch.countDown();
         }
      };

      proxy.setTimeout(1000);
      proxy.refreshRequest(new GCFNumber("1"), new GCFList("type_list"));

      // wait till the count reaches zero (true) or waiting timeout expires (false)
      assertTrue(latch.await(2000, TimeUnit.MILLISECONDS));
      assertTrue(timeoutExpired.getBooleanValue());
   }

}
