package robotnik_base_hw_sim;

public interface Pick extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/Pick";
  static final java.lang.String _DEFINITION = "# attach object link into robot link\n# Links and models are refered to Gazebo\nstring object_model\nstring object_link\nstring robot_model\nstring robot_link\n# pose related to the robot link\ngeometry_msgs/Pose pose\n---\nbool success\nstring msg\n";
}
