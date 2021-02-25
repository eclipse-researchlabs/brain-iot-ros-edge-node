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

public interface State extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/State";
  static final java.lang.String _DEFINITION = "# Current autopilot state\n#\n# Known modes listed here:\n# http://wiki.ros.org/mavros/CustomModes\n#\n# For system_status values\n# see https://mavlink.io/en/messages/common.html#MAV_STATE\n#\n\nstd_msgs/Header header\nbool connected\nbool armed\nbool guided\nbool manual_input\nstring mode\nuint8 system_status\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  boolean getConnected();
  void setConnected(boolean value);
  boolean getArmed();
  void setArmed(boolean value);
  boolean getGuided();
  void setGuided(boolean value);
  boolean getManualInput();
  void setManualInput(boolean value);
  java.lang.String getMode();
  void setMode(java.lang.String value);
  byte getSystemStatus();
  void setSystemStatus(byte value);
}
