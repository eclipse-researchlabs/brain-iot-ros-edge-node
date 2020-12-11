package mavros_msgs;

public interface MessageInterval extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MessageInterval";
  static final java.lang.String _DEFINITION = "# sets message interval\n# See MAV_CMD_SET_MESSAGE_INTERVAL\n\nuint32 message_id\nfloat32 message_rate\n---\nbool success\n";
}
