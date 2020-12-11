package microstrain_mips;

public interface SetAccelBiasRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetAccelBiasRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 bias\n";
  geometry_msgs.Vector3 getBias();
  void setBias(geometry_msgs.Vector3 value);
}
