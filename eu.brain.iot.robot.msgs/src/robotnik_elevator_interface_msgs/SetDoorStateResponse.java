package robotnik_elevator_interface_msgs;

public interface SetDoorStateResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/SetDoorStateResponse";
  static final java.lang.String _DEFINITION = "bool success\nstring message";
  boolean getSuccess();
  void setSuccess(boolean value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
}
