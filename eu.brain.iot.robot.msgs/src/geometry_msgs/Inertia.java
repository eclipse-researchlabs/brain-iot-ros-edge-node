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

public interface Inertia extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geometry_msgs/Inertia";
  static final java.lang.String _DEFINITION = "# Mass [kg]\nfloat64 m\n\n# Center of mass [m]\ngeometry_msgs/Vector3 com\n\n# Inertia Tensor [kg-m^2]\n#     | ixx ixy ixz |\n# I = | ixy iyy iyz |\n#     | ixz iyz izz |\nfloat64 ixx\nfloat64 ixy\nfloat64 ixz\nfloat64 iyy\nfloat64 iyz\nfloat64 izz\n";
  double getM();
  void setM(double value);
  geometry_msgs.Vector3 getCom();
  void setCom(geometry_msgs.Vector3 value);
  double getIxx();
  void setIxx(double value);
  double getIxy();
  void setIxy(double value);
  double getIxz();
  void setIxz(double value);
  double getIyy();
  void setIyy(double value);
  double getIyz();
  void setIyz(double value);
  double getIzz();
  void setIzz(double value);
}
