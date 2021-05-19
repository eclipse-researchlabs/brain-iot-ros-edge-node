package robot_local_control_msgs;

public interface SwitchMap extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/SwitchMap";
  static final java.lang.String _DEFINITION = "# sample values: floor_0, floor_1\nstring map\n\n# position and orentation on the new floor\nstd_msgs/Float32 x \nstd_msgs/Float32 y\nstd_msgs/Float32 theta\n";
  java.lang.String getMap();
  void setMap(java.lang.String value);
  std_msgs.Float32 getX();
  void setX(std_msgs.Float32 value);
  std_msgs.Float32 getY();
  void setY(std_msgs.Float32 value);
  std_msgs.Float32 getTheta();
  void setTheta(std_msgs.Float32 value);
}
