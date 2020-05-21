package robotnik_msgs;

public interface Pose2DArray extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/Pose2DArray";
  static final java.lang.String _DEFINITION = "geometry_msgs/Pose2D[] poses\n";
  java.util.List<geometry_msgs.Pose2D> getPoses();
  void setPoses(java.util.List<geometry_msgs.Pose2D> value);
}
