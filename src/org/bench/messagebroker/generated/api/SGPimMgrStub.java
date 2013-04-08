/******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************
*
* @file          SGPimMgrProxy.java
* @author        GCFAPICodegen
*/
package org.bench.messagebroker.generated.api;

import java.util.HashMap;
import java.util.Map;

import org.bench.messagebroker.common.AbstractStub;
import org.bench.messagebroker.messaging.GCFMessage;
import org.bench.messagebroker.messaging.MessageBrokerException;
import org.bench.messagebroker.messaging.GCFMessage.Type;
import org.onemoresunday.gcfparser.GCFEnum;
import org.onemoresunday.gcfparser.GCFList;
import org.onemoresunday.gcfparser.GCFNumber;
import org.onemoresunday.gcfparser.GCFString;



/****************************************************************************************
 * SGPimMgrStub
 *
 * This class is used as a base class for the real SGPimMgr implementations
 *
 ****************************************************************************************/
public abstract class SGPimMgrStub extends AbstractStub
{
   protected static Map<String, AbstractStub>  mNameInstanceMap = new HashMap<String, AbstractStub>();
   
   /*****************************************************************
    * Constructor.
    */
   protected SGPimMgrStub()
   {
      this("");
   }

   /*****************************************************************
    * Constructor.
    *
    * @param instanceName 
    */
   protected SGPimMgrStub(String instanceName)
   {
      super(instanceName);
   }
   
   /*****************************************************************
    * Register calls
    */
   @Override
   public void registerCalls()
   {
      if (mMessageBroker == null)
      {
         throw new IllegalStateException("Message broker is not initialized");
      }
      
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Open", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Close", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Refresh", null));
   }


   /************************************************************************
    * Dispatch received GCF-request
    *
    * @param call
    */
   @Override
   public void dispatchRequest(GCFMessage request)
   {
      setRawMessage(request);
      if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Open"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         openRequest( 
            (GCFEnum)request.getParamByName("pim_access_ctrl"), 
            (GCFString)request.getParamByName("database_filename"), 
            (GCFEnum)request.getParamByName("sync_mode"), 
            (GCFList)request.getParamByName("obj_types"), 
            (GCFList)request.getParamByName("open_props"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Close"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         closeRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Refresh"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         refreshRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFList)request.getParamByName("obj_types"));
      }
   }
   
   /************************************************************************
    * Dispatch received GCF-abort
    *
    * @param call
    */
   @Override
   public void dispatchAbort(GCFMessage request)
   {
      setRawMessage(request);
   }

   /*--------------------------------- Events -----------------------------------------*/

   protected void eventStatusChanged(GCFNumber pimmgr_id, GCFEnum status, GCFEnum device_status, GCFList params) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_STATUS_CHANGED", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      status.setName("status");
      message.addParam(status);
      device_status.setName("device_status");
      message.addParam(device_status);
      params.setName("params");
      message.addParam(params);
      
      mMessageBroker.sendMessage(message, null);
   }

   /*---------------------------------- Requests ---------------------------------------*/
   
   protected abstract void openRequest(GCFEnum pim_access_ctrl, GCFString database_filename, GCFEnum sync_mode, GCFList obj_types, GCFList open_props);
   protected abstract void closeRequest(GCFNumber pimmgr_id);
   protected abstract void refreshRequest(GCFNumber pimmgr_id, GCFList obj_types);


   /*---------------------------------- Abort calls ---------------------------------------*/
}
