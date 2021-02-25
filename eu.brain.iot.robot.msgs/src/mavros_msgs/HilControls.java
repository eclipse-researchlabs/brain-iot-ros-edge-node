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

public interface HilControls extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/HilControls";
  static final java.lang.String _DEFINITION = "# HilControls.msg\n#\n# ROS representation of MAVLink HIL_CONTROLS\n# (deprecated, use HIL_ACTUATOR_CONTROLS instead)\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#HIL_CONTROLS\n\nstd_msgs/Header header\nfloat32 roll_ailerons\nfloat32 pitch_elevator\nfloat32 yaw_rudder\nfloat32 throttle\nfloat32 aux1\nfloat32 aux2\nfloat32 aux3\nfloat32 aux4\nuint8 mode\nuint8 nav_mode\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getRollAilerons();
  void setRollAilerons(float value);
  float getPitchElevator();
  void setPitchElevator(float value);
  float getYawRudder();
  void setYawRudder(float value);
  float getThrottle();
  void setThrottle(float value);
  float getAux1();
  void setAux1(float value);
  float getAux2();
  void setAux2(float value);
  float getAux3();
  void setAux3(float value);
  float getAux4();
  void setAux4(float value);
  byte getMode();
  void setMode(byte value);
  byte getNavMode();
  void setNavMode(byte value);
}
