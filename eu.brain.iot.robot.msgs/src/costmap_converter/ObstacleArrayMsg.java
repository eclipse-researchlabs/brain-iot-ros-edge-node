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
package costmap_converter;

public interface ObstacleArrayMsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "costmap_converter/ObstacleArrayMsg";
  static final java.lang.String _DEFINITION = "# Message that contains a list of polygon shaped obstacles.\n# Special types:\n# Polygon with 1 vertex: Point obstacle\n# Polygon with 2 vertices: Line obstacle\n# Polygon with more than 2 vertices: First and last points are assumed to be connected\n\nstd_msgs/Header header\n\ncostmap_converter/ObstacleMsg[] obstacles\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<costmap_converter.ObstacleMsg> getObstacles();
  void setObstacles(java.util.List<costmap_converter.ObstacleMsg> value);
}
