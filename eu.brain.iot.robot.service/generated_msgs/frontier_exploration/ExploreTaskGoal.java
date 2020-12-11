package frontier_exploration;

public interface ExploreTaskGoal extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "frontier_exploration/ExploreTaskGoal";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n#Boundary for frontier exploration\ngeometry_msgs/PolygonStamped explore_boundary\n#Center point for frontier exploration, inside explore_boundary\ngeometry_msgs/PointStamped explore_center\n";
  geometry_msgs.PolygonStamped getExploreBoundary();
  void setExploreBoundary(geometry_msgs.PolygonStamped value);
  geometry_msgs.PointStamped getExploreCenter();
  void setExploreCenter(geometry_msgs.PointStamped value);
}
