package elevator_node;

public interface SetDoorRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "elevator_node/SetDoorRequest";
  static final java.lang.String _DEFINITION = "string state\n";
  java.lang.String getState();
  void setState(java.lang.String value);
}
