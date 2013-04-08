/*****************************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************************
*
* @file    MessageBrokerException.java
* @author  DBradul
******************************************************************************************/
package org.bench.messagebroker.messaging;

/****************************************************************************************
 * MessageBroker exception
 * 
 * @author DBradul
 ****************************************************************************************/
public class MessageBrokerException extends Exception
{
   private static final long serialVersionUID = 1L;
   
   public MessageBrokerException(Throwable cause)
   {
      super(cause);
   }

   public MessageBrokerException(String description)
   {
      super(description);
   }
}