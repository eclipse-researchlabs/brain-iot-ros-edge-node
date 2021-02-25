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
package robotnik_fms_msgs;

public interface GetRobotStatusResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/GetRobotStatusResponse";
  static final java.lang.String _DEFINITION = "robotnik_fms_msgs/RobotStatus robot_status\nrobot_local_control_msgs/Status rlc_status\nbool rlc_connected\nbool ret\nstring msg";
  robotnik_fms_msgs.RobotStatus getRobotStatus();
  void setRobotStatus(robotnik_fms_msgs.RobotStatus value);
  robot_local_control_msgs.Status getRlcStatus();
  void setRlcStatus(robot_local_control_msgs.Status value);
  boolean getRlcConnected();
  void setRlcConnected(boolean value);
  boolean getRet();
  void setRet(boolean value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
}
