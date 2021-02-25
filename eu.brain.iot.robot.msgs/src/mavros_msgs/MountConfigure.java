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

public interface MountConfigure extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MountConfigure";
  static final java.lang.String _DEFINITION = "# MAVLink message: DO_MOUNT_CONTROL\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_MOUNT_CONFIGURE\n\nstd_msgs/Header header\n\nuint8 mode              # See enum MAV_MOUNT_MODE.\n#MAV_MOUNT_MODE\nuint8 MODE_RETRACT = 0\nuint8 MODE_NEUTRAL = 1\nuint8 MODE_MAVLINK_TARGETING = 2\nuint8 MODE_RC_TARGETING = 3\nuint8 MODE_GPS_POINT = 4\n\nbool stabilize_roll     # stabilize roll? (1 = yes, 0 = no)\nbool stabilize_pitch    # stabilize pitch? (1 = yes, 0 = no)\nbool stabilize_yaw      # stabilize yaw? (1 = yes, 0 = no)\nuint8 roll_input        # roll input (See enum MOUNT_INPUT)\nuint8 pitch_input       # pitch input (See enum MOUNT_INPUT)\nuint8 yaw_input         # yaw input (See enum MOUNT_INPUT)\n\n#MOUNT_INPUT\nuint8 INPUT_ANGLE_BODY_FRAME = 0\nuint8 INPUT_ANGULAR_RATE = 1\nuint8 INPUT_ANGLE_ABSOLUTE_FRAME = 2\n---\nbool success\n# raw result returned by COMMAND_ACK\nuint8 result";
}
