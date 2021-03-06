package yocs_msgs;

public interface DockingInteractorActionFeedback extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "yocs_msgs/DockingInteractorActionFeedback";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nHeader header\nactionlib_msgs/GoalStatus status\nDockingInteractorFeedback feedback\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  actionlib_msgs.GoalStatus getStatus();
  void setStatus(actionlib_msgs.GoalStatus value);
  yocs_msgs.DockingInteractorFeedback getFeedback();
  void setFeedback(yocs_msgs.DockingInteractorFeedback value);
}
