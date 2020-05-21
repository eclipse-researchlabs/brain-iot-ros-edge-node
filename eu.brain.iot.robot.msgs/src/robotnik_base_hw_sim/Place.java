package robotnik_base_hw_sim;

public interface Place extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/Place";
  static final java.lang.String _DEFINITION = "# attach object link into robot link\n# Links and models are refered to Gazebo\nstring object_model\nstring robot_model\n---\nbool success\nstring msg\n";
}
