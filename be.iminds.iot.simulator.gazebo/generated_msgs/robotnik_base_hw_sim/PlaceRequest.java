package robotnik_base_hw_sim;

public interface PlaceRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PlaceRequest";
  static final java.lang.String _DEFINITION = "# attach object link into robot link\n# Links and models are refered to Gazebo\nstring object_model\nstring robot_model\n";
  java.lang.String getObjectModel();
  void setObjectModel(java.lang.String value);
  java.lang.String getRobotModel();
  void setRobotModel(java.lang.String value);
}
