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

public interface MultiDOFJointTrajectoryPoint extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "trajectory_msgs/MultiDOFJointTrajectoryPoint";
  static final java.lang.String _DEFINITION = "# Each multi-dof joint can specify a transform (up to 6 DOF)\ngeometry_msgs/Transform[] transforms\n\n# There can be a velocity specified for the origin of the joint \ngeometry_msgs/Twist[] velocities\n\n# There can be an acceleration specified for the origin of the joint \ngeometry_msgs/Twist[] accelerations\n\nduration time_from_start\n";
  java.util.List<geometry_msgs.Transform> getTransforms();
  void setTransforms(java.util.List<geometry_msgs.Transform> value);
  java.util.List<geometry_msgs.Twist> getVelocities();
  void setVelocities(java.util.List<geometry_msgs.Twist> value);
  java.util.List<geometry_msgs.Twist> getAccelerations();
  void setAccelerations(java.util.List<geometry_msgs.Twist> value);
  org.ros.message.Duration getTimeFromStart();
  void setTimeFromStart(org.ros.message.Duration value);
}
