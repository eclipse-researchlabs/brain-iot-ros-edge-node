package robotnik_elevator_interface_msgs;

public interface SetDoorStateRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/SetDoorStateRequest";
  static final java.lang.String _DEFINITION = "# See ElevatorState (open,close)\nstring state\n# unique control token to avoid controlling the elevator from external agents\nstring control_token\n";
  java.lang.String getState();
  void setState(java.lang.String value);
  java.lang.String getControlToken();
  void setControlToken(java.lang.String value);
}
