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

public interface HilActuatorControls extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/HilActuatorControls";
  static final java.lang.String _DEFINITION = "# HilActuatorControls.msg\n#\n# ROS representation of MAVLink HIL_ACTUATOR_CONTROLS\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#HIL_ACTUATOR_CONTROLS\n\nstd_msgs/Header header\nfloat32[16] controls\nuint8 mode\nuint64 flags\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float[] getControls();
  void setControls(float[] value);
  byte getMode();
  void setMode(byte value);
  long getFlags();
  void setFlags(long value);
}
