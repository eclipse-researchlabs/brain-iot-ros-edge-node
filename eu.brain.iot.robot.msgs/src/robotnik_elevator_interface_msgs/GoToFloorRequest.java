package robotnik_elevator_interface_msgs;

public interface GoToFloorRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/GoToFloorRequest";
  static final java.lang.String _DEFINITION = "int32 floor\n# unique control token to avoid controlling the elevator from external agents\nstring control_token\n";
  int getFloor();
  void setFloor(int value);
  java.lang.String getControlToken();
  void setControlToken(java.lang.String value);
}
