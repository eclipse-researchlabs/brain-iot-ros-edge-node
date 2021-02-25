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

public interface RobotStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/RobotStatus";
  static final java.lang.String _DEFINITION = "int32 id\ngeometry_msgs/Pose position\nfloat32 battery\nint32 floor\nint32 node\nstring map\nbool emergency_stop\nint32 mission_id\nint32 task_id\nint32 task_rlc_id\nint32 task_rlc_sent_id\nstring task_rlc_type\nint32 last_task_rlc_id\nstring last_task_rlc_type\nbool waiting_continue_signal\nbool alarm_or_error_active\nint32 last_task_rlc_error\nstring error_msg\n";
  int getId();
  void setId(int value);
  geometry_msgs.Pose getPosition();
  void setPosition(geometry_msgs.Pose value);
  float getBattery();
  void setBattery(float value);
  int getFloor();
  void setFloor(int value);
  int getNode();
  void setNode(int value);
  java.lang.String getMap();
  void setMap(java.lang.String value);
  boolean getEmergencyStop();
  void setEmergencyStop(boolean value);
  int getMissionId();
  void setMissionId(int value);
  int getTaskId();
  void setTaskId(int value);
  int getTaskRlcId();
  void setTaskRlcId(int value);
  int getTaskRlcSentId();
  void setTaskRlcSentId(int value);
  java.lang.String getTaskRlcType();
  void setTaskRlcType(java.lang.String value);
  int getLastTaskRlcId();
  void setLastTaskRlcId(int value);
  java.lang.String getLastTaskRlcType();
  void setLastTaskRlcType(java.lang.String value);
  boolean getWaitingContinueSignal();
  void setWaitingContinueSignal(boolean value);
  boolean getAlarmOrErrorActive();
  void setAlarmOrErrorActive(boolean value);
  int getLastTaskRlcError();
  void setLastTaskRlcError(int value);
  java.lang.String getErrorMsg();
  void setErrorMsg(java.lang.String value);
}
