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

import org.onemoresunday.gcfparser.GCFEnum;
import org.onemoresunday.gcfparser.GCFList;
import org.onemoresunday.gcfparser.GCFNumber;
import org.onemoresunday.gcfparser.GCFString;
import org.onemoresunday.gcfparser.GCFStruct;

import org.onemoresunday.messagebroker.messaging.GCFMessage;
import org.onemoresunday.messagebroker.messaging.MessageBroker;
import org.onemoresunday.messagebroker.messaging.GCFMessage.Type;
import org.onemoresunday.messagebroker.messaging.MessageBrokerException;

import org.onemoresunday.messagebroker.common.AbstractProxy;

/**********************************************************************
 * SGPimMgrProxy
 *
 * Provides GCF-interface for SGPimMgrProxy
 *
 *********************************************************************/
@SuppressWarnings("unused")
public class SGPimMgrProxy extends AbstractProxy
{
   /*****************************************************************
    * Constructor.
    */
   public SGPimMgrProxy()
   {
      super();
      // TODO: ServiceLocator?
      setBroker(MessageBroker.getInstance());
   }

   /*************************************************************************************
    * openRequest
    */
   public int openRequest(GCFEnum pim_access_ctrl, GCFString database_filename, GCFEnum sync_mode, GCFList obj_types, GCFList open_props) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_Open", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_Open", ""+callId, Type.CALL);
      }

      pim_access_ctrl.setName("pim_access_ctrl");
      database_filename.setName("database_filename");
      sync_mode.setName("sync_mode");
      obj_types.setName("obj_types");
      open_props.setName("open_props");
      message.addParam( pim_access_ctrl );
      message.addParam( database_filename );
      message.addParam( sync_mode );
      message.addParam( obj_types );
      message.addParam( open_props );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * closeRequest
    */
   public int closeRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_Close", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_Close", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getStatusRequest
    */
   public int getStatusRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetStatus", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetStatus", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeRequest
    */
   public int synchronizeRequest(GCFNumber pimmgr_id, GCFEnum renew, GCFList obj_types) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_Synchronize", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_Synchronize", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      renew.setName("renew");
      obj_types.setName("obj_types");
      message.addParam( pimmgr_id );
      message.addParam( renew );
      message.addParam( obj_types );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeExtRequest
    */
   public int synchronizeExtRequest(GCFNumber pimmgr_id, GCFEnum renew, GCFNumber queue, GCFList obj_types, GCFList sync_props) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeExt", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeExt", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      renew.setName("renew");
      queue.setName("queue");
      obj_types.setName("obj_types");
      sync_props.setName("sync_props");
      message.addParam( pimmgr_id );
      message.addParam( renew );
      message.addParam( queue );
      message.addParam( obj_types );
      message.addParam( sync_props );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeAbortRequest
    */
   public int synchronizeAbortRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeAbort", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeAbort", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeAbortExtRequest
    */
   public int synchronizeAbortExtRequest(GCFNumber pimmgr_id, GCFNumber queue, GCFList abort_props) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeAbortExt", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeAbortExt", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      queue.setName("queue");
      abort_props.setName("abort_props");
      message.addParam( pimmgr_id );
      message.addParam( queue );
      message.addParam( abort_props );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeAbortAllRequest
    */
   public int synchronizeAbortAllRequest(GCFNumber pimmgr_id, GCFList abort_props) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeAbortAll", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeAbortAll", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      abort_props.setName("abort_props");
      message.addParam( pimmgr_id );
      message.addParam( abort_props );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeContinueRequest
    */
   public int synchronizeContinueRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeContinue", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeContinue", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * synchronizeSuspendRequest
    */
   public int synchronizeSuspendRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SynchronizeSuspend", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SynchronizeSuspend", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * refreshRequest
    */
   public int refreshRequest(GCFNumber pimmgr_id, GCFList obj_types) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_Refresh", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_Refresh", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      obj_types.setName("obj_types");
      message.addParam( pimmgr_id );
      message.addParam( obj_types );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * setTimeZoneAndDSTRequest
    */
   public int setTimeZoneAndDSTRequest(GCFNumber pimmgr_id, GCFString timezone_offset, GCFNumber dst_rule_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SetTimeZoneAndDST", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SetTimeZoneAndDST", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      timezone_offset.setName("timezone_offset");
      dst_rule_id.setName("dst_rule_id");
      message.addParam( pimmgr_id );
      message.addParam( timezone_offset );
      message.addParam( dst_rule_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getTimeZoneAndDSTRequest
    */
   public int getTimeZoneAndDSTRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetTimeZoneAndDST", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetTimeZoneAndDST", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getTimeZoneAndDSTRecalcStatusRequest
    */
   public int getTimeZoneAndDSTRecalcStatusRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetTimeZoneAndDSTRecalcStatus", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetTimeZoneAndDSTRecalcStatus", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * recalcTimeZoneAndDSTRequest
    */
   public int recalcTimeZoneAndDSTRequest(GCFNumber pimmgr_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_RecalcTimeZoneAndDST", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_RecalcTimeZoneAndDST", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      message.addParam( pimmgr_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * attachDeviceRequest
    */
   public int attachDeviceRequest(GCFNumber pimmgr_id, GCFNumber device_id, GCFList device_props) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_AttachDevice", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_AttachDevice", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      device_id.setName("device_id");
      device_props.setName("device_props");
      message.addParam( pimmgr_id );
      message.addParam( device_id );
      message.addParam( device_props );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * detachDeviceRequest
    */
   public int detachDeviceRequest(GCFNumber pimmgr_id, GCFNumber device_id) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_DetachDevice", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_DetachDevice", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      device_id.setName("device_id");
      message.addParam( pimmgr_id );
      message.addParam( device_id );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getDeviceInfoRequest
    */
   public int getDeviceInfoRequest(GCFNumber pimmgr_id, GCFString info_key) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetDeviceInfo", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetDeviceInfo", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      info_key.setName("info_key");
      message.addParam( pimmgr_id );
      message.addParam( info_key );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * deleteDeviceRequest
    */
   public int deleteDeviceRequest(GCFNumber pimmgr_id, GCFList params) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_DeleteDevice", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_DeleteDevice", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( params );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * authenticateRequest
    */
   public int authenticateRequest(GCFNumber pimmgr_id, GCFString service, GCFList params) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_Authenticate", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_Authenticate", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      service.setName("service");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( service );
      message.addParam( params );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * authenticateExtRequest
    */
   public int authenticateExtRequest(GCFNumber pimmgr_id, GCFNumber queue, GCFString service, GCFList params) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_AuthenticateExt", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_AuthenticateExt", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      queue.setName("queue");
      service.setName("service");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( queue );
      message.addParam( service );
      message.addParam( params );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * controlDeviceRequest
    */
   public int controlDeviceRequest(GCFNumber pimmgr_id, GCFList params) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_ControlDevice", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_ControlDevice", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( params );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * controlObjectRequest
    */
   public int controlObjectRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFString operation, GCFList params) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_ControlObject", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_ControlObject", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      obj_type.setName("obj_type");
      instance.setName("instance");
      operation.setName("operation");
      params.setName("params");
      message.addParam( pimmgr_id );
      message.addParam( obj_type );
      message.addParam( instance );
      message.addParam( operation );
      message.addParam( params );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getObjectInstancesRequest
    */
   public int getObjectInstancesRequest(GCFNumber pimmgr_id, GCFList object_types) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetObjectInstances", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetObjectInstances", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      object_types.setName("object_types");
      message.addParam( pimmgr_id );
      message.addParam( object_types );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * setObjectNotificationsRequest
    */
   public int setObjectNotificationsRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFList notifications) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SetObjectNotifications", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SetObjectNotifications", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      obj_type.setName("obj_type");
      instance.setName("instance");
      notifications.setName("notifications");
      message.addParam( pimmgr_id );
      message.addParam( obj_type );
      message.addParam( instance );
      message.addParam( notifications );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * clearObjectNotificationsRequest
    */
   public int clearObjectNotificationsRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFList notifications) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_ClearObjectNotifications", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_ClearObjectNotifications", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      obj_type.setName("obj_type");
      instance.setName("instance");
      notifications.setName("notifications");
      message.addParam( pimmgr_id );
      message.addParam( obj_type );
      message.addParam( instance );
      message.addParam( notifications );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * getSubfoldersRequest
    */
   public int getSubfoldersRequest(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFString folder) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_GetSubfolders", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_GetSubfolders", ""+callId, Type.CALL);
      }

      pimmgr_id.setName("pimmgr_id");
      obj_type.setName("obj_type");
      instance.setName("instance");
      folder.setName("folder");
      message.addParam( pimmgr_id );
      message.addParam( obj_type );
      message.addParam( instance );
      message.addParam( folder );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }

   /*************************************************************************************
    * setEventDestinationRequest
    */
   public int setEventDestinationRequest(GCFString destination) throws MessageBrokerException
   {
      int callId = mMessageBroker.generateNextCallID();

      GCFMessage message = null;
      
      if (getInstanceName().isEmpty())
      {
         message = new GCFMessage("APP_ID", "PimMgr_SetEventDestination", ""+callId, Type.CALL);
      }
      else
      {
         message = new GCFMessage("APP_ID" + "_" + getInstanceName(), getInstanceName() + "PimMgr_SetEventDestination", ""+callId, Type.CALL);
      }

      destination.setName("destination");
      message.addParam( destination );

      mMessageBroker.sendMessage(message, this);

      return callId;
   }



   /****************************************************************************************
    * Dispatch response to a concrete method by the source address in the passed GCF-message
    */
   public void dispatchResponse(GCFMessage response) throws MessageBrokerException
   {
      setRawMessage(response);
      if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Open"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         openResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFNumber)response.getParamByName( "pimmgr_id" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Close"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         closeResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetStatus"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getStatusResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFEnum)response.getParamByName( "status" ), 
            (GCFEnum)response.getParamByName( "device_status" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Synchronize"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "obj_types" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeExt"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeExtResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFNumber)response.getParamByName( "queue" ), 
            (GCFList)response.getParamByName( "obj_types" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbort"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeAbortResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbortExt"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeAbortExtResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFNumber)response.getParamByName( "queue" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeAbortAll"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeAbortAllResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeContinue"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeContinueResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SynchronizeSuspend"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         synchronizeSuspendResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Refresh"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         refreshResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         setTimeZoneAndDSTResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getTimeZoneAndDSTResponse( 
            (GCFString)response.getParamByName( "timezone_offset" ), 
            (GCFNumber)response.getParamByName( "dst_rule_id" ), 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetTimeZoneAndDSTRecalcStatus"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getTimeZoneAndDSTRecalcStatusResponse( 
            (GCFEnum)response.getParamByName( "recalculation_required" ), 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_RecalcTimeZoneAndDST"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         recalcTimeZoneAndDSTResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_AttachDevice"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         attachDeviceResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFNumber)response.getParamByName( "device_id" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_DetachDevice"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         detachDeviceResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetDeviceInfo"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getDeviceInfoResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFString)response.getParamByName( "info_value" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_DeleteDevice"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         deleteDeviceResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_Authenticate"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         authenticateResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_AuthenticateExt"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         authenticateExtResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFNumber)response.getParamByName( "queue" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ControlDevice"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         controlDeviceResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ControlObject"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         controlObjectResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "result_values" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetObjectInstances"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getObjectInstancesResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "obj_inst_infos" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetObjectNotifications"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         setObjectNotificationsResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "unsupported_notifications" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_ClearObjectNotifications"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         clearObjectNotificationsResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "unsupported_notifications" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_GetSubfolders"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         getSubfoldersResponse( 
            (GCFEnum)response.getParamByName( "result" ), 
            (GCFList)response.getParamByName( "subfolders" ));
      }
      else if (response.getSourceAddress().equalsIgnoreCase(getInstanceName() + "PimMgr_SetEventDestination"))
      {
         mCallId = Integer.parseInt( response.getCallId() );
         setEventDestinationResponse( 
            (GCFEnum)response.getParamByName( "result" ));
      }
   }

   /****************************************************************************************
    * Dispatch event
    */
   public void dispatchEvent(GCFMessage event) throws MessageBrokerException
   {
      setRawMessage(event);
      if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_STATUS_CHANGED"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFEnum status = (GCFEnum)event.getParamByName("status");
         GCFEnum device_status = (GCFEnum)event.getParamByName("device_status");
         GCFList params = (GCFList)event.getParamByName("params");
         eventStatusChanged(pimmgr_id, status, device_status, params);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_SYNCHRONIZE_PROGRESS"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFEnum obj_type = (GCFEnum)event.getParamByName("obj_type");
         GCFNumber obj_count = (GCFNumber)event.getParamByName("obj_count");
         GCFEnum state = (GCFEnum)event.getParamByName("state");
         GCFList obj_type_props = (GCFList)event.getParamByName("obj_type_props");
         eventSynchronizeProgress(pimmgr_id, obj_type, obj_count, state, obj_type_props);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_SYNCHRONIZE_PROGRESS_EXT"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFNumber queue = (GCFNumber)event.getParamByName("queue");
         GCFEnum obj_type = (GCFEnum)event.getParamByName("obj_type");
         GCFNumber obj_count = (GCFNumber)event.getParamByName("obj_count");
         GCFEnum state = (GCFEnum)event.getParamByName("state");
         GCFList obj_type_props = (GCFList)event.getParamByName("obj_type_props");
         eventSynchronizeProgressExt(pimmgr_id, queue, obj_type, obj_count, state, obj_type_props);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_AUTHENTICATION_REQUIRED"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFString service = (GCFString)event.getParamByName("service");
         GCFList params = (GCFList)event.getParamByName("params");
         eventAuthenticationRequired(pimmgr_id, service, params);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_AUTHENTICATION_REQUIRED_EXT"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFNumber queue = (GCFNumber)event.getParamByName("queue");
         GCFString service = (GCFString)event.getParamByName("service");
         GCFList params = (GCFList)event.getParamByName("params");
         eventAuthenticationRequiredExt(pimmgr_id, queue, service, params);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_ARCHIVING_PROGRESS"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFEnum archiving_state = (GCFEnum)event.getParamByName("archiving_state");
         GCFList obj_types = (GCFList)event.getParamByName("obj_types");
         eventArchivingProgress(pimmgr_id, archiving_state, obj_types);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_OBJ_NOTIFICATION"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFEnum obj_type = (GCFEnum)event.getParamByName("obj_type");
         GCFStruct instance = (GCFStruct)event.getParamByName("instance");
         GCFEnum notification = (GCFEnum)event.getParamByName("notification");
         GCFList notification_params = (GCFList)event.getParamByName("notification_params");
         eventObjectNotification(pimmgr_id, obj_type, instance, notification, notification_params);
      }
      else if (event.getSourceAddress().equalsIgnoreCase("EV_PIMMGR_DEVICE_ACCESS"))
      {
         GCFNumber pimmgr_id = (GCFNumber)event.getParamByName("pimmgr_id");
         GCFEnum access_type = (GCFEnum)event.getParamByName("access_type");
         GCFString profile = (GCFString)event.getParamByName("profile");
         eventDeviceAccess(pimmgr_id, access_type, profile);
      }
   }

   /****************************************************************************************
    * Register for events
    */
   public void registerForEvents()
   {
      String destAddress = getInstanceName().isEmpty() ? "APP_ID" : "APP_ID" + "_" + getInstanceName();

      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_STATUS_CHANGED", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_SYNCHRONIZE_PROGRESS", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_SYNCHRONIZE_PROGRESS_EXT", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_AUTHENTICATION_REQUIRED", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_AUTHENTICATION_REQUIRED_EXT", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_ARCHIVING_PROGRESS", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_OBJ_NOTIFICATION", destAddress, null));
      mMessageBroker.addEventSubscriber(this, new GCFMessage("EV_PIMMGR_DEVICE_ACCESS", destAddress, null));
      
   }

   /****************************************************************************************
    * Unregister from events
    */
   public void unregisterFromEvents()
   {
      mMessageBroker.removeEventSubscriber(this);
   }
   
   /****************************************************************************************
    * Set Message Router
    */
   public void setBroker(MessageBroker router)
   {
      mMessageBroker = router;
   }   

   /*---------------------------------Responses --------------------------------------*/

   protected void openResponse(GCFEnum result, GCFNumber pimmgr_id) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void closeResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getStatusResponse(GCFEnum result, GCFEnum status, GCFEnum device_status) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeResponse(GCFEnum result, GCFList obj_types) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeExtResponse(GCFEnum result, GCFNumber queue, GCFList obj_types) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeAbortResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeAbortExtResponse(GCFEnum result, GCFNumber queue) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeAbortAllResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeContinueResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void synchronizeSuspendResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void refreshResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void setTimeZoneAndDSTResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getTimeZoneAndDSTResponse(GCFString timezone_offset, GCFNumber dst_rule_id, GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getTimeZoneAndDSTRecalcStatusResponse(GCFEnum recalculation_required, GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void recalcTimeZoneAndDSTResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void attachDeviceResponse(GCFEnum result, GCFNumber device_id) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void detachDeviceResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getDeviceInfoResponse(GCFEnum result, GCFString info_value) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void deleteDeviceResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void authenticateResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void authenticateExtResponse(GCFEnum result, GCFNumber queue) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void controlDeviceResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void controlObjectResponse(GCFEnum result, GCFList result_values) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getObjectInstancesResponse(GCFEnum result, GCFList obj_inst_infos) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void setObjectNotificationsResponse(GCFEnum result, GCFList unsupported_notifications) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void clearObjectNotificationsResponse(GCFEnum result, GCFList unsupported_notifications) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void getSubfoldersResponse(GCFEnum result, GCFList subfolders) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void setEventDestinationResponse(GCFEnum result) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   /*--------------------------------- Events -----------------------------------------*/

   protected void eventStatusChanged(GCFNumber pimmgr_id, GCFEnum status, GCFEnum device_status, GCFList params) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventSynchronizeProgress(GCFNumber pimmgr_id, GCFEnum obj_type, GCFNumber obj_count, GCFEnum state, GCFList obj_type_props) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventSynchronizeProgressExt(GCFNumber pimmgr_id, GCFNumber queue, GCFEnum obj_type, GCFNumber obj_count, GCFEnum state, GCFList obj_type_props) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventAuthenticationRequired(GCFNumber pimmgr_id, GCFString service, GCFList params) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventAuthenticationRequiredExt(GCFNumber pimmgr_id, GCFNumber queue, GCFString service, GCFList params) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventArchivingProgress(GCFNumber pimmgr_id, GCFEnum archiving_state, GCFList obj_types) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventObjectNotification(GCFNumber pimmgr_id, GCFEnum obj_type, GCFStruct instance, GCFEnum notification, GCFList notification_params) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }

   protected void eventDeviceAccess(GCFNumber pimmgr_id, GCFEnum access_type, GCFString profile) throws MessageBrokerException
   {
      //the method is overriden by a client interested in the corresponding response or event 
   }
}
