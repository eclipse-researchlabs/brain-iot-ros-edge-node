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
package robot_local_control_msgs;

public interface GoTo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/GoTo";
  static final java.lang.String _DEFINITION = "# goals is a set of 2D poses that will be reached by the robot\nPose2DStamped[] goals\n# twist can be either empty, in that case no maximum velocity is\n# specified, or a set of the same size as goals, in which each goal\n# will be reached with its maximum velocity\nTwist2D[] max_velocities\n";
  java.util.List<robot_local_control_msgs.Pose2DStamped> getGoals();
  void setGoals(java.util.List<robot_local_control_msgs.Pose2DStamped> value);
  java.util.List<robot_local_control_msgs.Twist2D> getMaxVelocities();
  void setMaxVelocities(java.util.List<robot_local_control_msgs.Twist2D> value);
}
