/*****************************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************************
 *
 * @file    GCFMessage.java
 * @author  DBradul
 *****************************************************************************************/
package org.onemoresunday.messagebroker.messaging;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.onemoresunday.gcfparser.AbstractGCFValue;
import org.onemoresunday.gcfparser.GCFParser;
import org.onemoresunday.gcfparser.GCFRoot;
import org.onemoresunday.gcfpath.parser.ParseException;

/****************************************************************************************
 * Defines GCF-message that is used for communication between client and server
 * 
 * GCF (Generic Communication Format) - is JSON-like format used for serialization of messages 
 * sent between client and server via MessageBroker 
 * 
 * @author DBradul
 ****************************************************************************************/
public class GCFMessage
{
   public enum Type
   {
      CALL,
      RESP,
      ABRT,
      EVNT,
      CTRL,
      UNKNOWN
   }
   
   private GCFRoot  mParamList;
   private Type     mMessageType = Type.UNKNOWN;
   private String   mSourceAddrName = "";
   private String   mDestAddrName = "";
   private String   mCallId = "";
   private boolean  mIsEvent;
   private long     mTimestamp;
   
   public GCFMessage()
   {
   }

   public GCFMessage(String sourceAddress, String destAddress, String callId)
   {
      mSourceAddrName = sourceAddress;
      mDestAddrName = destAddress;
      mCallId = callId;
   }

   public GCFMessage(String sourceAddress, String destAddress, String callId, Type type)
   {
      this(sourceAddress, destAddress, callId);
      mMessageType = type;
   }

   public GCFMessage(String gcfMessage)
   {
      boolean bMatched = false;
      
      if (!bMatched)
      {
         Matcher matcher = Pattern.compile("(CALL|ABRT) (\\w+):(\\d+) (\\w+)(.*);").matcher(gcfMessage);
         
         if (matcher.matches())
         {
            mMessageType = matcher.group(1).equals("CALL") ? Type.CALL :
                           matcher.group(1).equals("ABRT") ? Type.ABRT : Type.UNKNOWN;

            mSourceAddrName   = matcher.group(2);
            mCallId           = matcher.group(3);
            mDestAddrName     = matcher.group(4);
            mParamList        = (GCFRoot)new GCFParser().parse( matcher.group(4) + " " + matcher.group(5) );
            
            bMatched = true;
         }
      }
      
      if (!bMatched)
      {
         Matcher matcher = Pattern.compile("RESP (\\w+):(\\d+) (\\w+)(.*);").matcher(gcfMessage);
         
         if (matcher.matches())
         {
            mMessageType = Type.RESP;

            mSourceAddrName   = matcher.group(3);
            mCallId           = matcher.group(2);
            mDestAddrName     = matcher.group(1);
            mParamList        = (GCFRoot)new GCFParser().parse( matcher.group(3) + " " + matcher.group(4) );
            
            bMatched = true;
         }
      }

      if (!bMatched)
      {
         Matcher matcher = Pattern.compile("EVNT (\\w+) NAME=(\\w+)\\s*(.*);").matcher(gcfMessage);

         if (matcher.matches())
         {
            mMessageType = Type.EVNT;

            mSourceAddrName   = matcher.group(2);
            mDestAddrName     = matcher.group(1);
            mParamList        = (GCFRoot)new GCFParser().parse( matcher.group(2) + " " + matcher.group(3) );
            mIsEvent          = true;
            
            bMatched = true;
         }
      }

      if (!bMatched)
      {
         Matcher matcher = Pattern.compile("CTRL (\\S{4}) (\\w+)(.*);").matcher(gcfMessage);

         if (matcher.matches())
         {
            mMessageType = Type.CTRL;

            mSourceAddrName   = matcher.group(1);
            mDestAddrName     = matcher.group(2);
            mParamList        = (GCFRoot)new GCFParser().parse( matcher.group(2) + " " + matcher.group(3) );

            bMatched = true;
         }
      }
      
      if (!bMatched)
      {
         mMessageType = Type.UNKNOWN;
      }
   }
   
   public boolean isOutgoing()
   {
      return ( mMessageType == Type.CALL 
            || mMessageType == Type.ABRT );
   }
   
   public boolean isIncoming()
   {
      return ( mMessageType == Type.RESP
            || mMessageType == Type.EVNT
            || mMessageType == Type.CTRL);
   }

   public GCFMessage createResponse(GCFMessage request)
   {
      GCFMessage response = new GCFMessage();
      
      response.setSourceAddress( request.getSourceAddress() );
      response.setDestAddress( request.getDestAddress() );
      response.setCallId( request.getCallId() );
      
      return response;
   }

   public void setSourceAddress(String sourceAddress)
   {
      mSourceAddrName = sourceAddress;
   }

   public void setDestAddress(String destAddress)
   {
      mDestAddrName = destAddress;
   }

   public void setCallId(String callId)
   {
      mCallId = callId;
   }

   public String getSourceAddress()
   {
      return mSourceAddrName;
   }

   public String getDestAddress()
   {
      return mDestAddrName;
   }

   public String getCallId()
   {
      return mCallId;
   }
   
   public GCFMessage addParam(AbstractGCFValue paramValue)
   {
      if (null == mParamList)
      {
         mParamList = new GCFRoot();
      }
      mParamList.addMember( paramValue );
      
      return this;
   }

   @Override
   public String toString()
   {
      String serializedMessage = "";
      
      serializedMessage += Type.CALL == mMessageType ? "CALL" : 
                           Type.RESP == mMessageType ? "RESP" :
                           Type.ABRT == mMessageType ? "ABRT" :
                           Type.EVNT == mMessageType ? "EVNT" : 
                           Type.CTRL == mMessageType ? "CTRL" : "UNKN";
      
      if (Type.CALL == mMessageType || Type.RESP == mMessageType || Type.ABRT == mMessageType)
      {
         serializedMessage += " " + mSourceAddrName;

         serializedMessage += ":" + mCallId;

         serializedMessage += " " + mDestAddrName;
      }
      else if (Type.EVNT == mMessageType)
      {
         serializedMessage += " " + mDestAddrName;

         serializedMessage += " " + "NAME=" + mSourceAddrName;
      }
      else if (Type.CTRL == mMessageType)
      {
         serializedMessage += " " + mSourceAddrName;

         serializedMessage += " " + mDestAddrName;
      }

      serializedMessage += getParamListStr();

      serializedMessage += ";";
      
      return serializedMessage;
   }

   public boolean isEvent()
   {
      return mIsEvent;
   }

   public void setEvent(boolean isEvent)
   {
      this.mIsEvent = isEvent;
   }
   
   public Type getType()
   {
      return mMessageType;
   }

   public boolean matches(GCFMessage gcfMessage)
   {
      boolean bResult = false;
      
      if (   ( Type.CALL == mMessageType ) && ( Type.RESP == gcfMessage.getType() ) 
          || ( Type.RESP == mMessageType ) && ( Type.CALL == gcfMessage.getType() ) )
      {
         if ( mSourceAddrName.equals( gcfMessage.getDestAddress() ) && 
              mDestAddrName.equals( gcfMessage.getSourceAddress() ) && 
              mCallId.equals( gcfMessage.getCallId() ) )
         {
            bResult = true;
         }
      }

      return bResult;
   }

   public AbstractGCFValue getParamByName(String paramName)
   {
      return mParamList.getValueByName( paramName );
   }
   
   public AbstractGCFValue getValueByPath(String pathName) throws ParseException
   {
      return mParamList.getValueByPath( pathName );
   }

   public GCFRoot getParamList()
   {
      return mParamList;
   }

   public String getParamListStr()
   {
      String paramList = "";
      
      if (null != mParamList)
      {
         for( AbstractGCFValue param : mParamList.getMembers() )
         {
            paramList += ( " " + param.getName() + "=" + param.getSerializedValue() );
         }
      }
      
      return paramList;
   }
   
   /***************************************************************************************
    * Set message timestamp
    * 
    * @param timestamp - timestamp in milliseconds to set
    * @param offset - in milliseconds
    */
   public void setTimestamp( long timestamp, long timeout )
   {
      if (-1 == timeout)
      {
         mTimestamp = Long.MAX_VALUE;
      }
      else
      {
         mTimestamp = timestamp + timeout;
      }
   }
   
   public void setTimestamp( long timestamp )
   {
      setTimestamp(timestamp, 0);
   }
   
   /***************************************************************************************
    * Get message timestamp
    * 
    * @return timestamp in milliseconds
    */
   public long getTimestamp()
   {
      return mTimestamp;
   }
   
   /*************************************************************************************
    * Get message timestamp in a user-friendly form
    * 
    * @param format - Date/Time format string. E.g.: yyyy-MM-dd HH:mm:ss.SSS
    * @param local - Locale
    * @return
    */
   public String getTimestampStr( String format, Locale local )
   {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis( mTimestamp );
      
      return new SimpleDateFormat( format, local ).format( calendar.getTime() );
   }

   public String getTimestampStr()
   {
      return getTimestampStr( "H:mm:ss:SSS", new Locale("de", "DE") );
   }
}
