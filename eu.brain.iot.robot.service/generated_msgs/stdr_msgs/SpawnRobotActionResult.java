package stdr_msgs;

public interface SpawnRobotActionResult extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "stdr_msgs/SpawnRobotActionResult";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nHeader header\nactionlib_msgs/GoalStatus status\nSpawnRobotResult result\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  actionlib_msgs.GoalStatus getStatus();
  void setStatus(actionlib_msgs.GoalStatus value);
  stdr_msgs.SpawnRobotResult getResult();
  void setResult(stdr_msgs.SpawnRobotResult value);
}
