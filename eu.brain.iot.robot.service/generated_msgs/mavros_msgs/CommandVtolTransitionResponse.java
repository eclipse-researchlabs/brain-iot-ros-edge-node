package mavros_msgs;

public interface CommandVtolTransitionResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandVtolTransitionResponse";
  static final java.lang.String _DEFINITION = "bool success\nuint8 result            # Raw result returned by COMMAND_ACK\n ";
  boolean getSuccess();
  void setSuccess(boolean value);
  byte getResult();
  void setResult(byte value);
}
