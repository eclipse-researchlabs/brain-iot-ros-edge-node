package robotnik_navigation_msgs;

public interface MoveActionGoal extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_navigation_msgs/MoveActionGoal";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nHeader header\nactionlib_msgs/GoalID goal_id\nMoveGoal goal\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  actionlib_msgs.GoalID getGoalId();
  void setGoalId(actionlib_msgs.GoalID value);
  robotnik_navigation_msgs.MoveGoal getGoal();
  void setGoal(robotnik_navigation_msgs.MoveGoal value);
}