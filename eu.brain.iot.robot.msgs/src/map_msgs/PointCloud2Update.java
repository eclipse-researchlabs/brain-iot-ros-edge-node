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

public interface PointCloud2Update extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "map_msgs/PointCloud2Update";
  static final java.lang.String _DEFINITION = "uint32 ADD=0\nuint32 DELETE=1\nHeader header\nuint32 type          # type of update, one of ADD or DELETE\nsensor_msgs/PointCloud2 points\n";
  static final int ADD = 0;
  static final int DELETE = 1;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getType();
  void setType(int value);
  sensor_msgs.PointCloud2 getPoints();
  void setPoints(sensor_msgs.PointCloud2 value);
}
