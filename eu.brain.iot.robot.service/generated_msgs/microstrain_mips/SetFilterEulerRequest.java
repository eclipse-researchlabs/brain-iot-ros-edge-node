package microstrain_mips;

public interface SetFilterEulerRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetFilterEulerRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 angle\n";
  geometry_msgs.Vector3 getAngle();
  void setAngle(geometry_msgs.Vector3 value);
}
