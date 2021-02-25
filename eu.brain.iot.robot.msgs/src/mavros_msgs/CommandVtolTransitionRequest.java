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

public interface CommandVtolTransitionRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandVtolTransitionRequest";
  static final java.lang.String _DEFINITION = "\n# MAVLink command: DO_VTOL_TRANSITION\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_VTOL_TRANSITION\n\nstd_msgs/Header header\n\n# MAV_VTOL_STATE\nuint8 STATE_MC = 3\nuint8 STATE_FW = 4\n\nuint8 state              # See enum MAV_VTOL_STATE.\n\n";
  static final byte STATE_MC = 3;
  static final byte STATE_FW = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getState();
  void setState(byte value);
}
