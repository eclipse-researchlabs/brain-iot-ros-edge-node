/*******************************************************************************
 * *  Copyright (C) 2021 LINKS Foundation
 * 
 * This file is based on the ROSOSGi open-source project which is a part of DIANNE  -  Framework for distributed artificial neural networks
 * 
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package teb_local_planner;

public interface TrajectoryPointMsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "teb_local_planner/TrajectoryPointMsg";
  static final java.lang.String _DEFINITION = "# Message that contains single point on a trajectory suited for mobile navigation.\n# The trajectory is described by a sequence of poses, velocities,\n# accelerations and temporal information.\n\n# Why this message type?\n# nav_msgs/Path describes only a path without temporal information.\n# trajectory_msgs package contains only messages for joint trajectories.\n\n# The pose of the robot\ngeometry_msgs/Pose pose\n\n# Corresponding velocity\ngeometry_msgs/Twist velocity\n\n# Corresponding acceleration\ngeometry_msgs/Twist acceleration\n\nduration time_from_start\n\n\n\n";
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
  geometry_msgs.Twist getVelocity();
  void setVelocity(geometry_msgs.Twist value);
  geometry_msgs.Twist getAcceleration();
  void setAcceleration(geometry_msgs.Twist value);
  org.ros.message.Duration getTimeFromStart();
  void setTimeFromStart(org.ros.message.Duration value);
}
