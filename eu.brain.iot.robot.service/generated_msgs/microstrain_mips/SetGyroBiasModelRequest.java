package microstrain_mips;

public interface SetGyroBiasModelRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetGyroBiasModelRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 noise_vector\ngeometry_msgs/Vector3 beta_vector\n";
  geometry_msgs.Vector3 getNoiseVector();
  void setNoiseVector(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getBetaVector();
  void setBetaVector(geometry_msgs.Vector3 value);
}
