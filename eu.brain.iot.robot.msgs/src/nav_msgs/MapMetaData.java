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

public interface MapMetaData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "nav_msgs/MapMetaData";
  static final java.lang.String _DEFINITION = "# This hold basic information about the characterists of the OccupancyGrid\n\n# The time at which the map was loaded\ntime map_load_time\n# The map resolution [m/cell]\nfloat32 resolution\n# Map width [cells]\nuint32 width\n# Map height [cells]\nuint32 height\n# The origin of the map [m, m, rad].  This is the real-world pose of the\n# cell (0,0) in the map.\ngeometry_msgs/Pose origin";
  org.ros.message.Time getMapLoadTime();
  void setMapLoadTime(org.ros.message.Time value);
  float getResolution();
  void setResolution(float value);
  int getWidth();
  void setWidth(int value);
  int getHeight();
  void setHeight(int value);
  geometry_msgs.Pose getOrigin();
  void setOrigin(geometry_msgs.Pose value);
}
