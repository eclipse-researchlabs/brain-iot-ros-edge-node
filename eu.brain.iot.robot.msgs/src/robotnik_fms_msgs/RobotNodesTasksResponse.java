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

public interface RobotNodesTasksResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/RobotNodesTasksResponse";
  static final java.lang.String _DEFINITION = "geometry_msgs/Pose position\nint32 current_mission\nint32 last_mission\nint32 current_node\nint32 percent_mission\nfloat32 percent_battery\nfloat32 voltage_battery\nint32 floor\nbool pick_done\nbool waiting_continue_signal\nbool manual\nbool error\nbool elevator_raised\nbool connected\nbool emergency_stop\nbool ret\nstring map\nstring msg";
  geometry_msgs.Pose getPosition();
  void setPosition(geometry_msgs.Pose value);
  int getCurrentMission();
  void setCurrentMission(int value);
  int getLastMission();
  void setLastMission(int value);
  int getCurrentNode();
  void setCurrentNode(int value);
  int getPercentMission();
  void setPercentMission(int value);
  float getPercentBattery();
  void setPercentBattery(float value);
  float getVoltageBattery();
  void setVoltageBattery(float value);
  int getFloor();
  void setFloor(int value);
  boolean getPickDone();
  void setPickDone(boolean value);
  boolean getWaitingContinueSignal();
  void setWaitingContinueSignal(boolean value);
  boolean getManual();
  void setManual(boolean value);
  boolean getError();
  void setError(boolean value);
  boolean getElevatorRaised();
  void setElevatorRaised(boolean value);
  boolean getConnected();
  void setConnected(boolean value);
  boolean getEmergencyStop();
  void setEmergencyStop(boolean value);
  boolean getRet();
  void setRet(boolean value);
  java.lang.String getMap();
  void setMap(java.lang.String value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
}
