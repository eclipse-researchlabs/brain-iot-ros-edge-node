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
package map_msgs;

public interface ProjectedMap extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "map_msgs/ProjectedMap";
  static final java.lang.String _DEFINITION = "nav_msgs/OccupancyGrid map\nfloat64 min_z\nfloat64 max_z";
  nav_msgs.OccupancyGrid getMap();
  void setMap(nav_msgs.OccupancyGrid value);
  double getMinZ();
  void setMinZ(double value);
  double getMaxZ();
  void setMaxZ(double value);
}
