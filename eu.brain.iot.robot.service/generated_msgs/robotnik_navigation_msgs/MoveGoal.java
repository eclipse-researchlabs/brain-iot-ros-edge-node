package robotnik_navigation_msgs;

public interface MoveGoal extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_navigation_msgs/MoveGoal";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n# goal\ngeometry_msgs/Pose2D goal\ngeometry_msgs/Twist maximum_velocity\n";
  geometry_msgs.Pose2D getGoal();
  void setGoal(geometry_msgs.Pose2D value);
  geometry_msgs.Twist getMaximumVelocity();
  void setMaximumVelocity(geometry_msgs.Twist value);
}
