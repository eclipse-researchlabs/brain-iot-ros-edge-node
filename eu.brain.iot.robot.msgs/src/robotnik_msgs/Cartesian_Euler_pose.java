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
package robotnik_msgs;

public interface Cartesian_Euler_pose extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/Cartesian_Euler_pose";
  static final java.lang.String _DEFINITION = "float64 x\nfloat64 y\nfloat64 z\nfloat64 A\nfloat64 B\nfloat64 C\n";
  double getX();
  void setX(double value);
  double getY();
  void setY(double value);
  double getZ();
  void setZ(double value);
  double getA();
  void setA(double value);
  double getB();
  void setB(double value);
  double getC();
  void setC(double value);
}
