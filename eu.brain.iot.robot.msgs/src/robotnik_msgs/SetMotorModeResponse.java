package robotnik_msgs;

public interface SetMotorModeResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SetMotorModeResponse";
  static final java.lang.String _DEFINITION = "bool success\nstring message";
  boolean getSuccess();
  void setSuccess(boolean value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
}
