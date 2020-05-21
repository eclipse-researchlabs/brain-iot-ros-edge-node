package mavros_msgs;

public interface CommandTriggerIntervalResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandTriggerIntervalResponse";
  static final java.lang.String _DEFINITION = "bool success\nuint8 result";
  boolean getSuccess();
  void setSuccess(boolean value);
  byte getResult();
  void setResult(byte value);
}
