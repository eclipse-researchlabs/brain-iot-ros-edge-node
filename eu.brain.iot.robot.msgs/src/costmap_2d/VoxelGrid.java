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
package costmap_2d;

public interface VoxelGrid extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "costmap_2d/VoxelGrid";
  static final java.lang.String _DEFINITION = "Header header\nuint32[] data\ngeometry_msgs/Point32 origin\ngeometry_msgs/Vector3 resolutions\nuint32 size_x\nuint32 size_y\nuint32 size_z\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int[] getData();
  void setData(int[] value);
  geometry_msgs.Point32 getOrigin();
  void setOrigin(geometry_msgs.Point32 value);
  geometry_msgs.Vector3 getResolutions();
  void setResolutions(geometry_msgs.Vector3 value);
  int getSizeX();
  void setSizeX(int value);
  int getSizeY();
  void setSizeY(int value);
  int getSizeZ();
  void setSizeZ(int value);
}
