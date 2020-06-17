package microstrain_mips;

public interface SetMagNoiseRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetMagNoiseRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 noise\n";
  geometry_msgs.Vector3 getNoise();
  void setNoise(geometry_msgs.Vector3 value);
}
