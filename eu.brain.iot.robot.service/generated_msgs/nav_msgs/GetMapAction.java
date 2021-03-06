package nav_msgs;

public interface GetMapAction extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "nav_msgs/GetMapAction";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nGetMapActionGoal action_goal\nGetMapActionResult action_result\nGetMapActionFeedback action_feedback\n";
  nav_msgs.GetMapActionGoal getActionGoal();
  void setActionGoal(nav_msgs.GetMapActionGoal value);
  nav_msgs.GetMapActionResult getActionResult();
  void setActionResult(nav_msgs.GetMapActionResult value);
  nav_msgs.GetMapActionFeedback getActionFeedback();
  void setActionFeedback(nav_msgs.GetMapActionFeedback value);
}
