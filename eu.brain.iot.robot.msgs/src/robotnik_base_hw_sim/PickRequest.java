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
package robotnik_base_hw_sim;

public interface PickRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PickRequest";
  static final java.lang.String _DEFINITION = "# attach object link into robot link\n# Links and models are refered to Gazebo\nstring object_model\nstring object_link\nstring robot_model\nstring robot_link\n# pose related to the robot link\ngeometry_msgs/Pose pose\n";
  java.lang.String getObjectModel();
  void setObjectModel(java.lang.String value);
  java.lang.String getObjectLink();
  void setObjectLink(java.lang.String value);
  java.lang.String getRobotModel();
  void setRobotModel(java.lang.String value);
  java.lang.String getRobotLink();
  void setRobotLink(java.lang.String value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
}
