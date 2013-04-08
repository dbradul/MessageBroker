/*******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 * 
********************************************************************************
*
* @file          SGPimMgrStubImpl.java
* @author        GCFAPICodegen
*/
package org.onemoresunday.messagebroker.messaging;

import org.onemoresunday.gcfparser.*;
import org.onemoresunday.messagebroker.generated.api.SGPimMgrStub;

import org.onemoresunday.messagebroker.messaging.GCFMessage;
import org.onemoresunday.messagebroker.messaging.MessageBrokerException;
import org.onemoresunday.messagebroker.messaging.GCFMessage.Type;

/***************************************************************************
 * SGPimMgrStubImpl
 *
 * This file provides dummy implementation for SGPimMgr interface
 * This implementation is used for testing purposes only
 *
 *
 **************************************************************************/
public class SGPimMgrStubImpl extends SGPimMgrStub
{
   
   /***************************************************************************
    * Constructor.
    */
   protected SGPimMgrStubImpl()
   {
      this("");
   }

   /****************************************************************************
    * Constructor.
    */
   protected SGPimMgrStubImpl(String instanceName)
   {
      super(instanceName);
   }

   /****************************************************************************
    * Get instance of SGPimMgrStubImplDummy
    */
   public synchronized static SGPimMgrStubImpl getInstance(String instanceName)
   {
      if (!mNameInstanceMap.containsKey(instanceName))
      {
         mNameInstanceMap.put(instanceName, new SGPimMgrStubImpl(instanceName));
      }
      
      return (SGPimMgrStubImpl)mNameInstanceMap.get(instanceName);
   }

   /*****************************************************************************
    * Get instance
    */
   public synchronized static SGPimMgrStubImpl getInstance()
   {
      return getInstance("");
   }
   
   /*************************************************************************
    * refreshRequest
    */
   @Override
   public void refreshRequest(GCFNumber pimmgr_id, GCFList obj_types)
   {
      String paramsStr = "";
      paramsStr += (" result=" + "PIMMGR_RESULT_OK");

      String messageStr = String.format("RESP APP_ID:%d PimMgr_Refresh%s;", mCallId, paramsStr);

      GCFMessage message = new GCFMessage( messageStr );
      try
      {
         mMessageBroker.sendMessage(message, null);
      }
      catch (MessageBrokerException ex)
      {
          ex.printStackTrace();
      }
   }
   
   public void eventStatusChanged(GCFNumber pimmgr_id, GCFEnum status, GCFEnum device_status, GCFList params) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_STATUS_CHANGED", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      status.setName("status");
      device_status.setName("device_status");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( status );
      message.addParam( device_status );
      message.addParam( params );
      
      mMessageBroker.sendMessage(message, null);
   }

   @Override
   protected void openRequest(GCFEnum pim_access_ctrl, GCFString database_filename, GCFEnum sync_mode, GCFList obj_types, GCFList open_props) 
   {
       String paramsStr = "";
       paramsStr += (" result=" + "PIMMGR_RESULT_SYSTEM_ERROR");
       paramsStr += (" pimmgr_id=" + "42");

       String messageStr = String.format("RESP APP_ID:%d PimMgr_Open%s;", mCallId, paramsStr);

       GCFMessage message = new GCFMessage( messageStr );
       try
       {
          mMessageBroker.sendMessage(message, null);
       }
       catch (MessageBrokerException ex)
       {
           ex.printStackTrace();
       }
   }

   @Override
   protected void closeRequest(GCFNumber pimmgr_id)
   {
      String paramsStr = "";
      paramsStr += (" result=" + "PIMMGR_RESULT_SYSTEM_ERROR");

      String messageStr = String.format("RESP APP_ID:%d PimMgr_Close%s;", mCallId, paramsStr);

      GCFMessage message = new GCFMessage( messageStr );
      try
      {
         mMessageBroker.sendMessage(message, null);
      }
      catch (MessageBrokerException ex)
      {
          ex.printStackTrace();
      }
   }


  
}