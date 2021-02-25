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

public interface Point32 extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geometry_msgs/Point32";
  static final java.lang.String _DEFINITION = "# This contains the position of a point in free space(with 32 bits of precision).\n# It is recommeded to use Point wherever possible instead of Point32.  \n# \n# This recommendation is to promote interoperability.  \n#\n# This message is designed to take up less space when sending\n# lots of points at once, as in the case of a PointCloud.  \n\nfloat32 x\nfloat32 y\nfloat32 z";
  float getX();
  void setX(float value);
  float getY();
  void setY(float value);
  float getZ();
  void setZ(float value);
}
