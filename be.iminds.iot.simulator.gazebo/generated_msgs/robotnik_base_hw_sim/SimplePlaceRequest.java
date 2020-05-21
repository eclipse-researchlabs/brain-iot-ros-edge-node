package robotnik_base_hw_sim;

public interface SimplePlaceRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/SimplePlaceRequest";
  static final java.lang.String _DEFINITION = "# gazebo robot model \nstring robot_model\n";
  java.lang.String getRobotModel();
  void setRobotModel(java.lang.String value);
}
