package robot_local_control_msgs;

public interface MissionPetition extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/MissionPetition";
  static final java.lang.String _DEFINITION = "# Actions to tell mission component what to do\n# START, starts a mission\n# STOP, stops a mission\n\nstring START=start\nstring STOP=stop\n\nMissionStatus mission\nstring action\n---\nbool success\nstring message\n";
}
