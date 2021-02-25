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
package mavros_msgs;

public interface ManualControl extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ManualControl";
  static final java.lang.String _DEFINITION = "# Manual Control state\nstd_msgs/Header header\nfloat32 x\nfloat32 y\nfloat32 z\nfloat32 r\nuint16 buttons\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getX();
  void setX(float value);
  float getY();
  void setY(float value);
  float getZ();
  void setZ(float value);
  float getR();
  void setR(float value);
  short getButtons();
  void setButtons(short value);
}
