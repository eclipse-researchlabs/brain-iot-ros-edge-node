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

public interface RobotNodesTasksRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/RobotNodesTasksRequest";
  static final java.lang.String _DEFINITION = "int32 robot\nint32 mission\nint32[] node_origin\nstring action_origin\nint32[] node_dest\nstring action_dest\nstring planner_status\nbool error_ack\nstring msg\n";
  int getRobot();
  void setRobot(int value);
  int getMission();
  void setMission(int value);
  int[] getNodeOrigin();
  void setNodeOrigin(int[] value);
  java.lang.String getActionOrigin();
  void setActionOrigin(java.lang.String value);
  int[] getNodeDest();
  void setNodeDest(int[] value);
  java.lang.String getActionDest();
  void setActionDest(java.lang.String value);
  java.lang.String getPlannerStatus();
  void setPlannerStatus(java.lang.String value);
  boolean getErrorAck();
  void setErrorAck(boolean value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
}
