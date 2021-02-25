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

public interface GetPointMapROIRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "map_msgs/GetPointMapROIRequest";
  static final java.lang.String _DEFINITION = "float64 x\nfloat64 y\nfloat64 z\nfloat64 r    # if != 0, circular ROI of radius r\nfloat64 l_x  # if r == 0, length of AABB on x\nfloat64 l_y  # if r == 0, length of AABB on y\nfloat64 l_z  # if r == 0, length of AABB on z\n";
  double getX();
  void setX(double value);
  double getY();
  void setY(double value);
  double getZ();
  void setZ(double value);
  double getR();
  void setR(double value);
  double getLX();
  void setLX(double value);
  double getLY();
  void setLY(double value);
  double getLZ();
  void setLZ(double value);
}
