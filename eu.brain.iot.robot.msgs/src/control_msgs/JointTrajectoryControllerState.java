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
package control_msgs;

public interface JointTrajectoryControllerState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/JointTrajectoryControllerState";
  static final java.lang.String _DEFINITION = "Header header\nstring[] joint_names\ntrajectory_msgs/JointTrajectoryPoint desired\ntrajectory_msgs/JointTrajectoryPoint actual\ntrajectory_msgs/JointTrajectoryPoint error  # Redundant, but useful\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<java.lang.String> getJointNames();
  void setJointNames(java.util.List<java.lang.String> value);
  trajectory_msgs.JointTrajectoryPoint getDesired();
  void setDesired(trajectory_msgs.JointTrajectoryPoint value);
  trajectory_msgs.JointTrajectoryPoint getActual();
  void setActual(trajectory_msgs.JointTrajectoryPoint value);
  trajectory_msgs.JointTrajectoryPoint getError();
  void setError(trajectory_msgs.JointTrajectoryPoint value);
}
