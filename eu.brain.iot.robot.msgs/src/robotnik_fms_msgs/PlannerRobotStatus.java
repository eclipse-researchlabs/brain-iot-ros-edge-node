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

public interface PlannerRobotStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/PlannerRobotStatus";
  static final java.lang.String _DEFINITION = "int32 id\ngeometry_msgs/Pose position\nfloat32 battery_voltage\nfloat32 battery_percent\nint32 floor\nint32 node\nstring map\nbool emergency_stop\nint32 mission_id\nint32 mission_percent\nbool idle\nbool charging\nbool elevator_raised\nbool manual\nbool automatic\nbool pick_done\nbool connected\n\nbool waiting_continue_signal\nbool waiting_continue_after_error\nbool waiting_continue_after_cancel\n\nbool alarm_or_error_active\nstring error_msg\n";
  int getId();
  void setId(int value);
  geometry_msgs.Pose getPosition();
  void setPosition(geometry_msgs.Pose value);
  float getBatteryVoltage();
  void setBatteryVoltage(float value);
  float getBatteryPercent();
  void setBatteryPercent(float value);
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
  int getMissionPercent();
  void setMissionPercent(int value);
  boolean getIdle();
  void setIdle(boolean value);
  boolean getCharging();
  void setCharging(boolean value);
  boolean getElevatorRaised();
  void setElevatorRaised(boolean value);
  boolean getManual();
  void setManual(boolean value);
  boolean getAutomatic();
  void setAutomatic(boolean value);
  boolean getPickDone();
  void setPickDone(boolean value);
  boolean getConnected();
  void setConnected(boolean value);
  boolean getWaitingContinueSignal();
  void setWaitingContinueSignal(boolean value);
  boolean getWaitingContinueAfterError();
  void setWaitingContinueAfterError(boolean value);
  boolean getWaitingContinueAfterCancel();
  void setWaitingContinueAfterCancel(boolean value);
  boolean getAlarmOrErrorActive();
  void setAlarmOrErrorActive(boolean value);
  java.lang.String getErrorMsg();
  void setErrorMsg(java.lang.String value);
}
