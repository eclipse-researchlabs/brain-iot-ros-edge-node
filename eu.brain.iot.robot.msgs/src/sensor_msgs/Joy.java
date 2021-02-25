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
package sensor_msgs;

public interface Joy extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/Joy";
  static final java.lang.String _DEFINITION = "# Reports the state of a joysticks axes and buttons.\nHeader header           # timestamp in the header is the time the data is received from the joystick\nfloat32[] axes          # the axes measurements from a joystick\nint32[] buttons         # the buttons measurements from a joystick \n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float[] getAxes();
  void setAxes(float[] value);
  int[] getButtons();
  void setButtons(int[] value);
}
