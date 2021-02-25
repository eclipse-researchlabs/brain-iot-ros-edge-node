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
package robot_local_control_msgs;

public interface Missions extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/Missions";
  static final java.lang.String _DEFINITION = "# current mission\nMissionStatus current\n# list of old missions\nMissionStatus[] last\n";
  robot_local_control_msgs.MissionStatus getCurrent();
  void setCurrent(robot_local_control_msgs.MissionStatus value);
  java.util.List<robot_local_control_msgs.MissionStatus> getLast();
  void setLast(java.util.List<robot_local_control_msgs.MissionStatus> value);
}
