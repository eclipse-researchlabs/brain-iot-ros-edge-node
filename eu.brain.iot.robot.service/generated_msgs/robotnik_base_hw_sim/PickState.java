package robotnik_base_hw_sim;

public interface PickState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PickState";
  static final java.lang.String _DEFINITION = "string id\nstring robot_link\nstring object_link";
  java.lang.String getId();
  void setId(java.lang.String value);
  java.lang.String getRobotLink();
  void setRobotLink(java.lang.String value);
  java.lang.String getObjectLink();
  void setObjectLink(java.lang.String value);
}
