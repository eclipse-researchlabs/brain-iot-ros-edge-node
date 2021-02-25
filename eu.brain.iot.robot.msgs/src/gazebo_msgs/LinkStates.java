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

public interface LinkStates extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/LinkStates";
  static final java.lang.String _DEFINITION = "# broadcast all link states in world frame\nstring[] name                 # link names\ngeometry_msgs/Pose[] pose     # desired pose in world frame\ngeometry_msgs/Twist[] twist   # desired twist in world frame\n";
  java.util.List<java.lang.String> getName();
  void setName(java.util.List<java.lang.String> value);
  java.util.List<geometry_msgs.Pose> getPose();
  void setPose(java.util.List<geometry_msgs.Pose> value);
  java.util.List<geometry_msgs.Twist> getTwist();
  void setTwist(java.util.List<geometry_msgs.Twist> value);
}
