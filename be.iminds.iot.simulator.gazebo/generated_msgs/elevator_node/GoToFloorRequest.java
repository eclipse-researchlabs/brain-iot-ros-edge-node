package elevator_node;

public interface GoToFloorRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "elevator_node/GoToFloorRequest";
  static final java.lang.String _DEFINITION = "int64 floor\n";
  long getFloor();
  void setFloor(long value);
}
