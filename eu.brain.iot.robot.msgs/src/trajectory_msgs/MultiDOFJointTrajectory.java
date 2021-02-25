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
package trajectory_msgs;

public interface MultiDOFJointTrajectory extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "trajectory_msgs/MultiDOFJointTrajectory";
  static final java.lang.String _DEFINITION = "# The header is used to specify the coordinate frame and the reference time for the trajectory durations\nHeader header\n\n# A representation of a multi-dof joint trajectory (each point is a transformation)\n# Each point along the trajectory will include an array of positions/velocities/accelerations\n# that has the same length as the array of joint names, and has the same order of joints as \n# the joint names array.\n\nstring[] joint_names\nMultiDOFJointTrajectoryPoint[] points\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<java.lang.String> getJointNames();
  void setJointNames(java.util.List<java.lang.String> value);
  java.util.List<trajectory_msgs.MultiDOFJointTrajectoryPoint> getPoints();
  void setPoints(java.util.List<trajectory_msgs.MultiDOFJointTrajectoryPoint> value);
}
