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

public interface TaskData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/TaskData";
  static final java.lang.String _DEFINITION = "int32 task_id\nint32 robot_id\nint16 service_type\nstring service_type_name\nint32 task_rlc_id\nstring tf_dock\nbool block_node\nint32 door_id\nstring shower_door\nstring shower_from\n\nstring leave_magnetic_turn_direction\nfloat32 find_magnetic_distance\nstring find_magnetic_turn_direction\n\nbool elevator_door_open\nbool elevator_door_close\nuint32 elevator_id\nbool elevator_get_control\nbool elevator_leave_control\n\nuint32 magnetic_pick_place_lane\nuint32 magnetic_pick_place_cart_position\nstring magnetic_pick_place_turn_direction\nbool   magnetic_pick_place_allow_markers\nstring magnetic_pick_place_cart_id\n\nbool demo\n\nint32[] vgoals_id\nrobot_local_control_msgs/Pose2DStamped[] vgoals_pos \n\n";
  int getTaskId();
  void setTaskId(int value);
  int getRobotId();
  void setRobotId(int value);
  short getServiceType();
  void setServiceType(short value);
  java.lang.String getServiceTypeName();
  void setServiceTypeName(java.lang.String value);
  int getTaskRlcId();
  void setTaskRlcId(int value);
  java.lang.String getTfDock();
  void setTfDock(java.lang.String value);
  boolean getBlockNode();
  void setBlockNode(boolean value);
  int getDoorId();
  void setDoorId(int value);
  java.lang.String getShowerDoor();
  void setShowerDoor(java.lang.String value);
  java.lang.String getShowerFrom();
  void setShowerFrom(java.lang.String value);
  java.lang.String getLeaveMagneticTurnDirection();
  void setLeaveMagneticTurnDirection(java.lang.String value);
  float getFindMagneticDistance();
  void setFindMagneticDistance(float value);
  java.lang.String getFindMagneticTurnDirection();
  void setFindMagneticTurnDirection(java.lang.String value);
  boolean getElevatorDoorOpen();
  void setElevatorDoorOpen(boolean value);
  boolean getElevatorDoorClose();
  void setElevatorDoorClose(boolean value);
  int getElevatorId();
  void setElevatorId(int value);
  boolean getElevatorGetControl();
  void setElevatorGetControl(boolean value);
  boolean getElevatorLeaveControl();
  void setElevatorLeaveControl(boolean value);
  int getMagneticPickPlaceLane();
  void setMagneticPickPlaceLane(int value);
  int getMagneticPickPlaceCartPosition();
  void setMagneticPickPlaceCartPosition(int value);
  java.lang.String getMagneticPickPlaceTurnDirection();
  void setMagneticPickPlaceTurnDirection(java.lang.String value);
  boolean getMagneticPickPlaceAllowMarkers();
  void setMagneticPickPlaceAllowMarkers(boolean value);
  java.lang.String getMagneticPickPlaceCartId();
  void setMagneticPickPlaceCartId(java.lang.String value);
  boolean getDemo();
  void setDemo(boolean value);
  int[] getVgoalsId();
  void setVgoalsId(int[] value);
  java.util.List<robot_local_control_msgs.Pose2DStamped> getVgoalsPos();
  void setVgoalsPos(java.util.List<robot_local_control_msgs.Pose2DStamped> value);
}
