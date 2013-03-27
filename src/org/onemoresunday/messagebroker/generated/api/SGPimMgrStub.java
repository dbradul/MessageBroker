/******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************
*
* @file          SGPimMgrProxy.java
* @author        GCFAPICodegen
*/
package org.onemoresunday.messagebroker.generated.api;

import java.util.HashMap;
import java.util.Map;

import org.onemoresunday.gcfparser.GCFEnum;
import org.onemoresunday.gcfparser.GCFList;
import org.onemoresunday.gcfparser.GCFNumber;
import org.onemoresunday.gcfparser.GCFString;
import org.onemoresunday.gcfparser.GCFStruct;

import org.onemoresunday.messagebroker.messaging.GCFMessage;
import org.onemoresunday.messagebroker.messaging.GCFMessage.Type;
import org.onemoresunday.messagebroker.messaging.MessageBrokerException;

import org.onemoresunday.messagebroker.common.AbstractStub;

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
      this(null);
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
         throw new IllegalStateException("Message router is not initialized");
      }
      
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Open", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Close", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetStatus", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Synchronize", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeExt", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeAbort", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeAbortExt", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeAbortAll", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeContinue", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SynchronizeSuspend", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Refresh", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SetTimeZoneAndDST", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetTimeZoneAndDST", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetTimeZoneAndDSTRecalcStatus", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_RecalcTimeZoneAndDST", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_AttachDevice", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_DetachDevice", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetDeviceInfo", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_DeleteDevice", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_Authenticate", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_AuthenticateExt", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_ControlDevice", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_ControlObject", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetObjectInstances", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SetObjectNotifications", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_ClearObjectNotifications", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_GetSubfolders", null));
      mMessageBroker.addCallHandler(this, new GCFMessage(null, getInstanceName() + "PimMgr_SetEventDestination", null));
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
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetStatus"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getStatusRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Synchronize"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("renew"), 
            (GCFList)request.getParamByName("obj_types"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeExt"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeExtRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("renew"), 
            (GCFNumber)request.getParamByName("queue"), 
            (GCFList)request.getParamByName("obj_types"), 
            (GCFList)request.getParamByName("sync_props"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbort"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeAbortRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbortExt"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeAbortExtRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFNumber)request.getParamByName("queue"), 
            (GCFList)request.getParamByName("abort_props"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbortAll"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeAbortAllRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFList)request.getParamByName("abort_props"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeContinue"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeContinueRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeSuspend"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         synchronizeSuspendRequest( 
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
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         setTimeZoneAndDSTRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFString)request.getParamByName("timezone_offset"), 
            (GCFNumber)request.getParamByName("dst_rule_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getTimeZoneAndDSTRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetTimeZoneAndDSTRecalcStatus"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getTimeZoneAndDSTRecalcStatusRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_RecalcTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         recalcTimeZoneAndDSTRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_AttachDevice"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         attachDeviceRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFNumber)request.getParamByName("device_id"), 
            (GCFList)request.getParamByName("device_props"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_DetachDevice"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         detachDeviceRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFNumber)request.getParamByName("device_id"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetDeviceInfo"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getDeviceInfoRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFString)request.getParamByName("info_key"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_DeleteDevice"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         deleteDeviceRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFList)request.getParamByName("params"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Authenticate"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         authenticateRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFString)request.getParamByName("service"), 
            (GCFList)request.getParamByName("params"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_AuthenticateExt"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         authenticateExtRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFNumber)request.getParamByName("queue"), 
            (GCFString)request.getParamByName("service"), 
            (GCFList)request.getParamByName("params"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ControlDevice"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         controlDeviceRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFList)request.getParamByName("params"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ControlObject"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         controlObjectRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("obj_type"), 
            (GCFStruct)request.getParamByName("instance"), 
            (GCFString)request.getParamByName("operation"), 
            (GCFList)request.getParamByName("params"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetObjectInstances"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getObjectInstancesRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFList)request.getParamByName("object_types"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetObjectNotifications"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         setObjectNotificationsRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("obj_type"), 
            (GCFStruct)request.getParamByName("instance"), 
            (GCFList)request.getParamByName("notifications"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ClearObjectNotifications"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         clearObjectNotificationsRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("obj_type"), 
            (GCFStruct)request.getParamByName("instance"), 
            (GCFList)request.getParamByName("notifications"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetSubfolders"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         getSubfoldersRequest( 
            (GCFNumber)request.getParamByName("pimmgr_id"), 
            (GCFEnum)request.getParamByName("obj_type"), 
            (GCFStruct)request.getParamByName("instance"), 
            (GCFString)request.getParamByName("folder"));
      }
      else if (request.getDestAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetEventDestination"))
      {
         mCallId = Integer.parseInt(request.getCallId());
         mCurrentSourceAddress = request.getSourceAddress();
         setEventDestinationRequest( 
            (GCFString)request.getParamByName("destination"));
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

   protected void eventSynchronizeProgress(GCFNumber pimmgr_id, GCFEnum obj_type, GCFNumber obj_count, GCFEnum state, GCFList obj_type_props) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_SYNCHRONIZE_PROGRESS", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      obj_type.setName("obj_type");
      message.addParam(obj_type);
      obj_count.setName("obj_count");
      message.addParam(obj_count);
      state.setName("state");
      message.addParam(state);
      obj_type_props.setName("obj_type_props");
      message.addParam(obj_type_props);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventSynchronizeProgressExt(GCFNumber pimmgr_id, GCFNumber queue, GCFEnum obj_type, GCFNumber obj_count, GCFEnum state, GCFList obj_type_props) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_SYNCHRONIZE_PROGRESS_EXT", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      queue.setName("queue");
      message.addParam(queue);
      obj_type.setName("obj_type");
      message.addParam(obj_type);
      obj_count.setName("obj_count");
      message.addParam(obj_count);
      state.setName("state");
      message.addParam(state);
      obj_type_props.setName("obj_type_props");
      message.addParam(obj_type_props);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventAuthenticationRequired(GCFNumber pimmgr_id, GCFString service, GCFList params) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_AUTHENTICATION_REQUIRED", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      service.setName("service");
      message.addParam(service);
      params.setName("params");
      message.addParam(params);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventAuthenticationRequiredExt(GCFNumber pimmgr_id, GCFNumber queue, GCFString service, GCFList params) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_AUTHENTICATION_REQUIRED_EXT", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      queue.setName("queue");
      message.addParam(queue);
      service.setName("service");
      message.addParam(service);
      params.setName("params");
      message.addParam(params);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventArchivingProgress(GCFNumber pimmgr_id, GCFEnum archiving_state, GCFList obj_types) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_ARCHIVING_PROGRESS", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      archiving_state.setName("archiving_state");
      message.addParam(archiving_state);
      obj_types.setName("obj_types");
      message.addParam(obj_types);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventObjectNotification(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFEnum notification, GCFList notification_params) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_OBJ_NOTIFICATION", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      obj_type.setName("obj_type");
      message.addParam(obj_type);
      instance.setName("instance");
      message.addParam(instance);
      notification.setName("notification");
      message.addParam(notification);
      notification_params.setName("notification_params");
      message.addParam(notification_params);
      
      mMessageBroker.sendMessage(message, null);
   }

   protected void eventDeviceAccess(GCFNumber pimmgr_id, GCFEnum access_type, GCFString profile) throws MessageBrokerException
   {
      GCFMessage message = new GCFMessage("EV_PIMMGR_DEVICE_ACCESS", "APP_ID", "", Type.EVNT);

      pimmgr_id.setName("pimmgr_id");
      message.addParam(pimmgr_id);
      access_type.setName("access_type");
      message.addParam(access_type);
      profile.setName("profile");
      message.addParam(profile);
      
      mMessageBroker.sendMessage(message, null);
   }

   /*---------------------------------- Requests ---------------------------------------*/
   
   protected abstract void openRequest(GCFEnum pim_access_ctrl, GCFString database_filename, GCFEnum sync_mode, GCFList obj_types, GCFList open_props);
   protected abstract void closeRequest(GCFNumber pimmgr_id);
   protected abstract void getStatusRequest(GCFNumber pimmgr_id);
   protected abstract void synchronizeRequest(GCFNumber pimmgr_id, GCFEnum renew, GCFList obj_types);
   protected abstract void synchronizeExtRequest(GCFNumber pimmgr_id, GCFEnum renew, GCFNumber queue, GCFList obj_types, GCFList sync_props);
   protected abstract void synchronizeAbortRequest(GCFNumber pimmgr_id);
   protected abstract void synchronizeAbortExtRequest(GCFNumber pimmgr_id, GCFNumber queue, GCFList abort_props);
   protected abstract void synchronizeAbortAllRequest(GCFNumber pimmgr_id, GCFList abort_props);
   protected abstract void synchronizeContinueRequest(GCFNumber pimmgr_id);
   protected abstract void synchronizeSuspendRequest(GCFNumber pimmgr_id);
   protected abstract void refreshRequest(GCFNumber pimmgr_id, GCFList obj_types);
   protected abstract void setTimeZoneAndDSTRequest(GCFNumber pimmgr_id, GCFString timezone_offset, GCFNumber dst_rule_id);
   protected abstract void getTimeZoneAndDSTRequest(GCFNumber pimmgr_id);
   protected abstract void getTimeZoneAndDSTRecalcStatusRequest(GCFNumber pimmgr_id);
   protected abstract void recalcTimeZoneAndDSTRequest(GCFNumber pimmgr_id);
   protected abstract void attachDeviceRequest(GCFNumber pimmgr_id, GCFNumber device_id, GCFList device_props);
   protected abstract void detachDeviceRequest(GCFNumber pimmgr_id, GCFNumber device_id);
   protected abstract void getDeviceInfoRequest(GCFNumber pimmgr_id, GCFString info_key);
   protected abstract void deleteDeviceRequest(GCFNumber pimmgr_id, GCFList params);
   protected abstract void authenticateRequest(GCFNumber pimmgr_id, GCFString service, GCFList params);
   protected abstract void authenticateExtRequest(GCFNumber pimmgr_id, GCFNumber queue, GCFString service, GCFList params);
   protected abstract void controlDeviceRequest(GCFNumber pimmgr_id, GCFList params);
   protected abstract void controlObjectRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFString operation, GCFList params);
   protected abstract void getObjectInstancesRequest(GCFNumber pimmgr_id, GCFList object_types);
   protected abstract void setObjectNotificationsRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFList notifications);
   protected abstract void clearObjectNotificationsRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFList notifications);
   protected abstract void getSubfoldersRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFString folder);
   protected abstract void setEventDestinationRequest(GCFString destination);

   /*---------------------------------- Abort calls ---------------------------------------*/
   

}
