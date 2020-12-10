package robotnik_elevator_interface_msgs;

public interface SetElevatorControl extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/SetElevatorControl";
  static final java.lang.String _DEFINITION = "# flat to set or not the control\nbool under_control\n# id to identify who is controlling the elevator\nstring master_id\n# unique control token to avoid controlling the elevator from external agents\nstring control_token\n---\nbool success\nstring message\n";
}
