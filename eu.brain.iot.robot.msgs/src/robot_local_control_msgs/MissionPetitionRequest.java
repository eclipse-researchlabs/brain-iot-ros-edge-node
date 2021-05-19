package robot_local_control_msgs;

public interface MissionPetitionRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/MissionPetitionRequest";
  static final java.lang.String _DEFINITION = "# Actions to tell mission component what to do\n# START, starts a mission\n# STOP, stops a mission\n\nstring START=start\nstring STOP=stop\n\nMissionStatus mission\nstring action\n";
  static final java.lang.String START = "start";
  static final java.lang.String STOP = "stop";
  robot_local_control_msgs.MissionStatus getMission();
  void setMission(robot_local_control_msgs.MissionStatus value);
  java.lang.String getAction();
  void setAction(java.lang.String value);
}
