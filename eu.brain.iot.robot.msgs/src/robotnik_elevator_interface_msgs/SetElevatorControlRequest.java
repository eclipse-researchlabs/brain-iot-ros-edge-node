package robotnik_elevator_interface_msgs;

public interface SetElevatorControlRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/SetElevatorControlRequest";
  static final java.lang.String _DEFINITION = "# flat to set or not the control\nbool under_control\n# id to identify who is controlling the elevator\nstring master_id\n# unique control token to avoid controlling the elevator from external agents\nstring control_token\n";
  boolean getUnderControl();
  void setUnderControl(boolean value);
  java.lang.String getMasterId();
  void setMasterId(java.lang.String value);
  java.lang.String getControlToken();
  void setControlToken(java.lang.String value);
}
