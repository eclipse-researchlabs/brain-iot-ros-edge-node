package control_msgs;

public interface PointHeadActionResult extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/PointHeadActionResult";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n\nHeader header\nactionlib_msgs/GoalStatus status\nPointHeadResult result\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  actionlib_msgs.GoalStatus getStatus();
  void setStatus(actionlib_msgs.GoalStatus value);
  control_msgs.PointHeadResult getResult();
  void setResult(control_msgs.PointHeadResult value);
}
