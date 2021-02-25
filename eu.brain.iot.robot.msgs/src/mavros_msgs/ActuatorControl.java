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

public interface ActuatorControl extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ActuatorControl";
  static final java.lang.String _DEFINITION = "# raw servo values for direct actuator controls\n#\n# about groups, mixing and channels:\n# https://pixhawk.org/dev/mixing\n\n# constant for mixer group\nuint8 PX4_MIX_FLIGHT_CONTROL = 0\nuint8 PX4_MIX_FLIGHT_CONTROL_VTOL_ALT = 1\nuint8 PX4_MIX_PAYLOAD = 2\nuint8 PX4_MIX_MANUAL_PASSTHROUGH = 3\n#uint8 PX4_MIX_FC_MC_VIRT = 4\n#uint8 PX4_MIX_FC_FW_VIRT = 5\n\nstd_msgs/Header header\nuint8 group_mix\nfloat32[8] controls\n";
  static final byte PX4_MIX_FLIGHT_CONTROL = 0;
  static final byte PX4_MIX_FLIGHT_CONTROL_VTOL_ALT = 1;
  static final byte PX4_MIX_PAYLOAD = 2;
  static final byte PX4_MIX_MANUAL_PASSTHROUGH = 3;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getGroupMix();
  void setGroupMix(byte value);
  float[] getControls();
  void setControls(float[] value);
}
