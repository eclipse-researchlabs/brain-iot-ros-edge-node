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

public interface SpawnModelRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/SpawnModelRequest";
  static final java.lang.String _DEFINITION = "string model_name                 # name of the model to be spawn\nstring model_xml                  # this should be an urdf or gazebo xml\nstring robot_namespace            # spawn robot and all ROS interfaces under this namespace\ngeometry_msgs/Pose initial_pose   # only applied to canonical body\nstring reference_frame            # initial_pose is defined relative to the frame of this model/body\n                                  # if left empty or \"world\", then gazebo world frame is used\n                                  # if non-existent model/body is specified, an error is returned\n                                  #   and the model is not spawned\n";
  java.lang.String getModelName();
  void setModelName(java.lang.String value);
  java.lang.String getModelXml();
  void setModelXml(java.lang.String value);
  java.lang.String getRobotNamespace();
  void setRobotNamespace(java.lang.String value);
  geometry_msgs.Pose getInitialPose();
  void setInitialPose(geometry_msgs.Pose value);
  java.lang.String getReferenceFrame();
  void setReferenceFrame(java.lang.String value);
}
