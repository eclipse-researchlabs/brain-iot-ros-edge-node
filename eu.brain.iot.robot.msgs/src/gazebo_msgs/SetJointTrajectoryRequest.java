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
package gazebo_msgs;

public interface SetJointTrajectoryRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/SetJointTrajectoryRequest";
  static final java.lang.String _DEFINITION = "string model_name\ntrajectory_msgs/JointTrajectory joint_trajectory\ngeometry_msgs/Pose model_pose\nbool set_model_pose\nbool disable_physics_updates # defaults to false\n";
  java.lang.String getModelName();
  void setModelName(java.lang.String value);
  trajectory_msgs.JointTrajectory getJointTrajectory();
  void setJointTrajectory(trajectory_msgs.JointTrajectory value);
  geometry_msgs.Pose getModelPose();
  void setModelPose(geometry_msgs.Pose value);
  boolean getSetModelPose();
  void setSetModelPose(boolean value);
  boolean getDisablePhysicsUpdates();
  void setDisablePhysicsUpdates(boolean value);
}
