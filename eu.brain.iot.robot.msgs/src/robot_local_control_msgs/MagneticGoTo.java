package robot_local_control_msgs;

public interface MagneticGoTo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/MagneticGoTo";
  static final java.lang.String _DEFINITION = "string node\nfloat32 speed_factor\nfloat32 orientation_offset\nfloat32 position_offset";
  java.lang.String getNode();
  void setNode(java.lang.String value);
  float getSpeedFactor();
  void setSpeedFactor(float value);
  float getOrientationOffset();
  void setOrientationOffset(float value);
  float getPositionOffset();
  void setPositionOffset(float value);
}
