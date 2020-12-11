package microstrain_mips;

public interface SetAccelNoiseRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetAccelNoiseRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 noise\n";
  geometry_msgs.Vector3 getNoise();
  void setNoise(geometry_msgs.Vector3 value);
}
