package robotnik_msgs;

public interface SetElevatorActionGoal extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SetElevatorActionGoal";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nHeader header\nactionlib_msgs/GoalID goal_id\nSetElevatorGoal goal\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  actionlib_msgs.GoalID getGoalId();
  void setGoalId(actionlib_msgs.GoalID value);
  robotnik_msgs.SetElevatorGoal getGoal();
  void setGoal(robotnik_msgs.SetElevatorGoal value);
}
