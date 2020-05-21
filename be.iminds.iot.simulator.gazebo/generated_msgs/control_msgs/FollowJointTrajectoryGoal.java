package control_msgs;

public interface FollowJointTrajectoryGoal extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/FollowJointTrajectoryGoal";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\n# The joint trajectory to follow\ntrajectory_msgs/JointTrajectory trajectory\n\n# Tolerances for the trajectory.  If the measured joint values fall\n# outside the tolerances the trajectory goal is aborted.  Any\n# tolerances that are not specified (by being omitted or set to 0) are\n# set to the defaults for the action server (often taken from the\n# parameter server).\n\n# Tolerances applied to the joints as the trajectory is executed.  If\n# violated, the goal aborts with error_code set to\n# PATH_TOLERANCE_VIOLATED.\nJointTolerance[] path_tolerance\n\n# To report success, the joints must be within goal_tolerance of the\n# final trajectory value.  The goal must be achieved by time the\n# trajectory ends plus goal_time_tolerance.  (goal_time_tolerance\n# allows some leeway in time, so that the trajectory goal can still\n# succeed even if the joints reach the goal some time after the\n# precise end time of the trajectory).\n#\n# If the joints are not within goal_tolerance after \"trajectory finish\n# time\" + goal_time_tolerance, the goal aborts with error_code set to\n# GOAL_TOLERANCE_VIOLATED\nJointTolerance[] goal_tolerance\nduration goal_time_tolerance\n\n";
  trajectory_msgs.JointTrajectory getTrajectory();
  void setTrajectory(trajectory_msgs.JointTrajectory value);
  java.util.List<control_msgs.JointTolerance> getPathTolerance();
  void setPathTolerance(java.util.List<control_msgs.JointTolerance> value);
  java.util.List<control_msgs.JointTolerance> getGoalTolerance();
  void setGoalTolerance(java.util.List<control_msgs.JointTolerance> value);
  org.ros.message.Duration getGoalTimeTolerance();
  void setGoalTimeTolerance(org.ros.message.Duration value);
}
