package robotnik_msgs;

public interface LaserStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/LaserStatus";
  static final java.lang.String _DEFINITION = "string name\nbool detecting_obstacles\nbool contaminated\nbool free_warning\n# one input per each warning zone.\n# first areas are closer to the robot (i.e. more restrictive)\nbool[] warning_zones \n";
  java.lang.String getName();
  void setName(java.lang.String value);
  boolean getDetectingObstacles();
  void setDetectingObstacles(boolean value);
  boolean getContaminated();
  void setContaminated(boolean value);
  boolean getFreeWarning();
  void setFreeWarning(boolean value);
  boolean[] getWarningZones();
  void setWarningZones(boolean[] value);
}
