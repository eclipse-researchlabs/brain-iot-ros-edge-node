package mavros_msgs;

public interface MessageIntervalRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MessageIntervalRequest";
  static final java.lang.String _DEFINITION = "# sets message interval\n# See MAV_CMD_SET_MESSAGE_INTERVAL\n\nuint32 message_id\nfloat32 message_rate\n";
  int getMessageId();
  void setMessageId(int value);
  float getMessageRate();
  void setMessageRate(float value);
}
