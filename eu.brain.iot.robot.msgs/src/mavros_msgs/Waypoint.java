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

public interface Waypoint extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/Waypoint";
  static final java.lang.String _DEFINITION = "# Waypoint.msg\n#\n# ROS representation of MAVLink MISSION_ITEM\n# See mavlink documentation\n\n\n\n# see enum MAV_FRAME\nuint8 frame\nuint8 FRAME_GLOBAL = 0\nuint8 FRAME_LOCAL_NED = 1\nuint8 FRAME_MISSION = 2\nuint8 FRAME_GLOBAL_REL_ALT = 3\nuint8 FRAME_LOCAL_ENU = 4\n\n# see enum MAV_CMD and CommandCode.msg\nuint16 command\n\nbool is_current\nbool autocontinue\n# meaning of this params described in enum MAV_CMD\nfloat32 param1\nfloat32 param2\nfloat32 param3\nfloat32 param4\nfloat64 x_lat\nfloat64 y_long\nfloat64 z_alt\n";
  static final byte FRAME_GLOBAL = 0;
  static final byte FRAME_LOCAL_NED = 1;
  static final byte FRAME_MISSION = 2;
  static final byte FRAME_GLOBAL_REL_ALT = 3;
  static final byte FRAME_LOCAL_ENU = 4;
  byte getFrame();
  void setFrame(byte value);
  short getCommand();
  void setCommand(short value);
  boolean getIsCurrent();
  void setIsCurrent(boolean value);
  boolean getAutocontinue();
  void setAutocontinue(boolean value);
  float getParam1();
  void setParam1(float value);
  float getParam2();
  void setParam2(float value);
  float getParam3();
  void setParam3(float value);
  float getParam4();
  void setParam4(float value);
  double getXLat();
  void setXLat(double value);
  double getYLong();
  void setYLong(double value);
  double getZAlt();
  void setZAlt(double value);
}
