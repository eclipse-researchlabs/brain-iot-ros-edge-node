package kobuki_msgs;

public interface AutoDockingAction extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/AutoDockingAction";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nAutoDockingActionGoal action_goal\nAutoDockingActionResult action_result\nAutoDockingActionFeedback action_feedback\n";
  kobuki_msgs.AutoDockingActionGoal getActionGoal();
  void setActionGoal(kobuki_msgs.AutoDockingActionGoal value);
  kobuki_msgs.AutoDockingActionResult getActionResult();
  void setActionResult(kobuki_msgs.AutoDockingActionResult value);
  kobuki_msgs.AutoDockingActionFeedback getActionFeedback();
  void setActionFeedback(kobuki_msgs.AutoDockingActionFeedback value);
}
