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
package nav_msgs;

public interface Odometry extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "nav_msgs/Odometry";
  static final java.lang.String _DEFINITION = "# This represents an estimate of a position and velocity in free space.  \n# The pose in this message should be specified in the coordinate frame given by header.frame_id.\n# The twist in this message should be specified in the coordinate frame given by the child_frame_id\nHeader header\nstring child_frame_id\ngeometry_msgs/PoseWithCovariance pose\ngeometry_msgs/TwistWithCovariance twist\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.lang.String getChildFrameId();
  void setChildFrameId(java.lang.String value);
  geometry_msgs.PoseWithCovariance getPose();
  void setPose(geometry_msgs.PoseWithCovariance value);
  geometry_msgs.TwistWithCovariance getTwist();
  void setTwist(geometry_msgs.TwistWithCovariance value);
}
