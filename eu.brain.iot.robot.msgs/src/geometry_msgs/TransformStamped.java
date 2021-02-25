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
package geometry_msgs;

public interface TransformStamped extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geometry_msgs/TransformStamped";
  static final java.lang.String _DEFINITION = "# This expresses a transform from coordinate frame header.frame_id\n# to the coordinate frame child_frame_id\n#\n# This message is mostly used by the \n# <a href=\"http://wiki.ros.org/tf\">tf</a> package. \n# See its documentation for more information.\n\nHeader header\nstring child_frame_id # the frame id of the child frame\nTransform transform\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.lang.String getChildFrameId();
  void setChildFrameId(java.lang.String value);
  geometry_msgs.Transform getTransform();
  void setTransform(geometry_msgs.Transform value);
}
