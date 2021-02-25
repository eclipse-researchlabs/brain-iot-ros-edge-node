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
package robotnik_navigation_msgs;

public interface PoseStampedArray extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_navigation_msgs/PoseStampedArray";
  static final java.lang.String _DEFINITION = "# An array of poses with a header for global reference.\n\nHeader header\n\ngeometry_msgs/PoseStamped[] poses\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<geometry_msgs.PoseStamped> getPoses();
  void setPoses(java.util.List<geometry_msgs.PoseStamped> value);
}
