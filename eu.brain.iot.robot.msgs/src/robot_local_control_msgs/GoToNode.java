package robot_local_control_msgs;

public interface GoToNode extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/GoToNode";
  static final java.lang.String _DEFINITION = "uint16 node\nfloat32 orientation_offset\nfloat32 position_offset";
  short getNode();
  void setNode(short value);
  float getOrientationOffset();
  void setOrientationOffset(float value);
  float getPositionOffset();
  void setPositionOffset(float value);
}
