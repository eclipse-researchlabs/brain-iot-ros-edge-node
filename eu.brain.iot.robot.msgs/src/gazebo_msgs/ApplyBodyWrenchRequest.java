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

public interface ApplyBodyWrenchRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/ApplyBodyWrenchRequest";
  static final java.lang.String _DEFINITION = "# Apply Wrench to Gazebo Body.\n# via the callback mechanism\n# all Gazebo operations are made in world frame\nstring body_name                          # Gazebo body to apply wrench (linear force and torque)\n                                          # wrench is applied in the gazebo world by default\n                                          # body names are prefixed by model name, e.g. pr2::base_link\nstring reference_frame                    # wrench is defined in the reference frame of this entity\n                                          # use inertial frame if left empty\n                                          # frame names are bodies prefixed by model name, e.g. pr2::base_link\ngeometry_msgs/Point  reference_point      # wrench is defined at this location in the reference frame\ngeometry_msgs/Wrench wrench               # wrench applied to the origin of the body\ntime start_time                           # (optional) wrench application start time (seconds)\n                                          # if start_time is not specified, or\n                                          # start_time < current time, start as soon as possible\nduration duration                         # optional duration of wrench application time (seconds)\n                                          # if duration < 0, apply wrench continuously without end\n                                          # if duration = 0, do nothing\n                                          # if duration < step size, apply wrench\n                                          # for one step size\n";
  java.lang.String getBodyName();
  void setBodyName(java.lang.String value);
  java.lang.String getReferenceFrame();
  void setReferenceFrame(java.lang.String value);
  geometry_msgs.Point getReferencePoint();
  void setReferencePoint(geometry_msgs.Point value);
  geometry_msgs.Wrench getWrench();
  void setWrench(geometry_msgs.Wrench value);
  org.ros.message.Time getStartTime();
  void setStartTime(org.ros.message.Time value);
  org.ros.message.Duration getDuration();
  void setDuration(org.ros.message.Duration value);
}
