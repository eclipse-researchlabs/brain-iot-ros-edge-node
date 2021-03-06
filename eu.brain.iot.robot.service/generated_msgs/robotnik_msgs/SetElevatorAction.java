package robotnik_msgs;

public interface SetElevatorAction extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SetElevatorAction";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nSetElevatorActionGoal action_goal\nSetElevatorActionResult action_result\nSetElevatorActionFeedback action_feedback\n";
  robotnik_msgs.SetElevatorActionGoal getActionGoal();
  void setActionGoal(robotnik_msgs.SetElevatorActionGoal value);
  robotnik_msgs.SetElevatorActionResult getActionResult();
  void setActionResult(robotnik_msgs.SetElevatorActionResult value);
  robotnik_msgs.SetElevatorActionFeedback getActionFeedback();
  void setActionFeedback(robotnik_msgs.SetElevatorActionFeedback value);
}
