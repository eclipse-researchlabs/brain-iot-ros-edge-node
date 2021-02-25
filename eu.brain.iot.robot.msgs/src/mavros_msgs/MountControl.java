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

public interface MountControl extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MountControl";
  static final java.lang.String _DEFINITION = "# MAVLink message: DO_MOUNT_CONTROL\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_MOUNT_CONTROL\n\nstd_msgs/Header header\n\nuint8 mode # See enum MAV_MOUNT_MODE.\nuint8 MAV_MOUNT_MODE_RETRACT = 0\nuint8 MAV_MOUNT_MODE_NEUTRAL = 1\nuint8 MAV_MOUNT_MODE_MAVLINK_TARGETING = 2\nuint8 MAV_MOUNT_MODE_RC_TARGETING = 3\nuint8 MAV_MOUNT_MODE_GPS_POINT = 4\n\nfloat32 pitch # roll degrees or degrees/second depending on mount mode.\nfloat32 roll # roll degrees or degrees/second depending on mount mode.\nfloat32 yaw # roll degrees or degrees/second depending on mount mode.\nfloat32 altitude  # altitude depending on mount mode.\nfloat32 latitude # latitude in degrees * 1E7, set if appropriate mount mode.\nfloat32 longitude # longitude in degrees * 1E7, set if appropriate mount mode.";
  static final byte MAV_MOUNT_MODE_RETRACT = 0;
  static final byte MAV_MOUNT_MODE_NEUTRAL = 1;
  static final byte MAV_MOUNT_MODE_MAVLINK_TARGETING = 2;
  static final byte MAV_MOUNT_MODE_RC_TARGETING = 3;
  static final byte MAV_MOUNT_MODE_GPS_POINT = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getMode();
  void setMode(byte value);
  float getPitch();
  void setPitch(float value);
  float getRoll();
  void setRoll(float value);
  float getYaw();
  void setYaw(float value);
  float getAltitude();
  void setAltitude(float value);
  float getLatitude();
  void setLatitude(float value);
  float getLongitude();
  void setLongitude(float value);
}
