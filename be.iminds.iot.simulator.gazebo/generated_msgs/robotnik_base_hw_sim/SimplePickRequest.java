package robotnik_base_hw_sim;

public interface SimplePickRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/SimplePickRequest";
  static final java.lang.String _DEFINITION = "# Picks closes object\nstring robot_model\n";
  java.lang.String getRobotModel();
  void setRobotModel(java.lang.String value);
}
