package robotnik_elevator_interface_msgs;

public interface GoToFloor extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/GoToFloor";
  static final java.lang.String _DEFINITION = "int32 floor\n# unique control token to avoid controlling the elevator from external agents\nstring control_token\n---\nbool success\nstring message\n\n";
}
