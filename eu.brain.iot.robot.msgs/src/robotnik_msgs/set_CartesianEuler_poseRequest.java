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

public interface set_CartesianEuler_poseRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/set_CartesianEuler_poseRequest";
  static final java.lang.String _DEFINITION = "# New robot X position (mm)\nfloat32 x\n# New robot Y position (mm)\nfloat32 y\n# New robot Z position (mm)\nfloat32 z\n# New robot Orientation (grad)\nfloat32 A\n# New robot Orientation (grad)\nfloat32 B\n# New robot Orientation (grad)\nfloat32 C\n";
  float getX();
  void setX(float value);
  float getY();
  void setY(float value);
  float getZ();
  void setZ(float value);
  float getA();
  void setA(float value);
  float getB();
  void setB(float value);
  float getC();
  void setC(float value);
}
