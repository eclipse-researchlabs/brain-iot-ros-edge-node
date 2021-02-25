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

public interface ExtendedState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ExtendedState";
  static final java.lang.String _DEFINITION = "# Extended autopilot state\n#\n# https://mavlink.io/en/messages/common.html#EXTENDED_SYS_STATE\n\nuint8 VTOL_STATE_UNDEFINED = 0\nuint8 VTOL_STATE_TRANSITION_TO_FW = 1\nuint8 VTOL_STATE_TRANSITION_TO_MC = 2\nuint8 VTOL_STATE_MC = 3\nuint8 VTOL_STATE_FW = 4\n\nuint8 LANDED_STATE_UNDEFINED = 0\nuint8 LANDED_STATE_ON_GROUND = 1\nuint8 LANDED_STATE_IN_AIR = 2\nuint8 LANDED_STATE_TAKEOFF = 3\nuint8 LANDED_STATE_LANDING = 4\n\nstd_msgs/Header header\nuint8 vtol_state\nuint8 landed_state\n";
  static final byte VTOL_STATE_UNDEFINED = 0;
  static final byte VTOL_STATE_TRANSITION_TO_FW = 1;
  static final byte VTOL_STATE_TRANSITION_TO_MC = 2;
  static final byte VTOL_STATE_MC = 3;
  static final byte VTOL_STATE_FW = 4;
  static final byte LANDED_STATE_UNDEFINED = 0;
  static final byte LANDED_STATE_ON_GROUND = 1;
  static final byte LANDED_STATE_IN_AIR = 2;
  static final byte LANDED_STATE_TAKEOFF = 3;
  static final byte LANDED_STATE_LANDING = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getVtolState();
  void setVtolState(byte value);
  byte getLandedState();
  void setLandedState(byte value);
}
