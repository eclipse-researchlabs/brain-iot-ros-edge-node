package microstrain_mips;

public interface SetReferencePositionRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetReferencePositionRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 position\n";
  geometry_msgs.Vector3 getPosition();
  void setPosition(geometry_msgs.Vector3 value);
}
