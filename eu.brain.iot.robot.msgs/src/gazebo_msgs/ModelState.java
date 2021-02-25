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

public interface ModelState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/ModelState";
  static final java.lang.String _DEFINITION = "# Set Gazebo Model pose and twist\nstring model_name           # model to set state (pose and twist)\ngeometry_msgs/Pose pose     # desired pose in reference frame\ngeometry_msgs/Twist twist   # desired twist in reference frame\nstring reference_frame      # set pose/twist relative to the frame of this entity (Body/Model)\n                            # leave empty or \"world\" or \"map\" defaults to world-frame\n\n";
  java.lang.String getModelName();
  void setModelName(java.lang.String value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
  geometry_msgs.Twist getTwist();
  void setTwist(geometry_msgs.Twist value);
  java.lang.String getReferenceFrame();
  void setReferenceFrame(java.lang.String value);
}
