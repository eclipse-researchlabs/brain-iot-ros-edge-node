package robotnik_base_hw_sim;

public interface PickRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PickRequest";
  static final java.lang.String _DEFINITION = "# attach object link into robot link\n# Links and models are refered to Gazebo\nstring object_model\nstring object_link\nstring robot_model\nstring robot_link\n# pose related to the robot link\ngeometry_msgs/Pose pose\n";
  java.lang.String getObjectModel();
  void setObjectModel(java.lang.String value);
  java.lang.String getObjectLink();
  void setObjectLink(java.lang.String value);
  java.lang.String getRobotModel();
  void setRobotModel(java.lang.String value);
  java.lang.String getRobotLink();
  void setRobotLink(java.lang.String value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
}
