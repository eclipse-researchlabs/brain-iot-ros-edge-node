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
package robotnik_elevator_interface_msgs;

public interface ElevatorState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_elevator_interface_msgs/ElevatorState";
  static final java.lang.String _DEFINITION = "string ELEVATOR_STATUS_IDLE=idle\nstring ELEVATOR_STATUS_UNKNOWN=unknown\nstring ELEVATOR_STATUS_MOVING=moving\n\nstring DOOR_STATUS_UNKNOWN=unknown\nstring DOOR_STATUS_OPENING=opening\nstring DOOR_STATUS_CLOSING=closing\nstring DOOR_STATUS_CLOSE=close\nstring DOOR_STATUS_OPEN=open\n\nstring elevator_id\n\nint32 current_floor\nint32 target_floor\n# True if the elevator is under control\nbool under_control\n# id to identify who is controlling the elevator\nstring master_id\n# elevator status\nstring elevator_status\n# door status\nstring door_status\n# flag active when no one is in the cabin\nbool cabin_presence_free\n";
  static final java.lang.String ELEVATOR_STATUS_IDLE = "idle";
  static final java.lang.String ELEVATOR_STATUS_UNKNOWN = "unknown";
  static final java.lang.String ELEVATOR_STATUS_MOVING = "moving";
  static final java.lang.String DOOR_STATUS_UNKNOWN = "unknown";
  static final java.lang.String DOOR_STATUS_OPENING = "opening";
  static final java.lang.String DOOR_STATUS_CLOSING = "closing";
  static final java.lang.String DOOR_STATUS_CLOSE = "close";
  static final java.lang.String DOOR_STATUS_OPEN = "open";
  java.lang.String getElevatorId();
  void setElevatorId(java.lang.String value);
  int getCurrentFloor();
  void setCurrentFloor(int value);
  int getTargetFloor();
  void setTargetFloor(int value);
  boolean getUnderControl();
  void setUnderControl(boolean value);
  java.lang.String getMasterId();
  void setMasterId(java.lang.String value);
  java.lang.String getElevatorStatus();
  void setElevatorStatus(java.lang.String value);
  java.lang.String getDoorStatus();
  void setDoorStatus(java.lang.String value);
  boolean getCabinPresenceFree();
  void setCabinPresenceFree(boolean value);
}
