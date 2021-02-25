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

public interface OccupancyGrid extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "nav_msgs/OccupancyGrid";
  static final java.lang.String _DEFINITION = "# This represents a 2-D grid map, in which each cell represents the probability of\n# occupancy.\n\nHeader header \n\n#MetaData for the map\nMapMetaData info\n\n# The map data, in row-major order, starting with (0,0).  Occupancy\n# probabilities are in the range [0,100].  Unknown is -1.\nint8[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  nav_msgs.MapMetaData getInfo();
  void setInfo(nav_msgs.MapMetaData value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
