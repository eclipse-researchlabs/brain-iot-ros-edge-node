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

public interface NodeInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/NodeInfo";
  static final java.lang.String _DEFINITION = "int32 id\nrobot_local_control_msgs/Pose2DStamped position\ngeometry_msgs/Pose2D position_offset\nint32 laser_range\nint32 floor\nint32 robot\nint32 robot_reserved\nbool acoustic_signal\nbool recalibration\nbool used\nbool charge\nbool load\nbool unload\nbool prepick\nbool preplace\nbool postpick\nbool postplace\n\nbool stop\nbool dooropen\nbool doorclose\nint32 door_id\nbool entershower\nbool leaveshower\nstring showerdoor\nstring showerfrom\nstring name\n\nstring leavemagnetic_turn_direction\nfloat32 findmagnetic_distance\nstring findmagnetic_turn_direction\n\nbool magneticload\nbool magneticunload\nbool findmagnetic\nbool leavemagnetic\n\nuint32 magnetic_load_unload_lane\nuint32 magnetic_load_unload_cart_position\nstring magnetic_load_unload_turn_direction\nbool   magnetic_load_unload_allow_markers\nstring magnetic_load_unload_cart_id\n\nbool elevator\nbool elevator_get_control\nbool elevator_leave_control\nbool elevator_open_door\nbool elevator_close_door\nuint32 elevator_id\n\n";
  int getId();
  void setId(int value);
  robot_local_control_msgs.Pose2DStamped getPosition();
  void setPosition(robot_local_control_msgs.Pose2DStamped value);
  geometry_msgs.Pose2D getPositionOffset();
  void setPositionOffset(geometry_msgs.Pose2D value);
  int getLaserRange();
  void setLaserRange(int value);
  int getFloor();
  void setFloor(int value);
  int getRobot();
  void setRobot(int value);
  int getRobotReserved();
  void setRobotReserved(int value);
  boolean getAcousticSignal();
  void setAcousticSignal(boolean value);
  boolean getRecalibration();
  void setRecalibration(boolean value);
  boolean getUsed();
  void setUsed(boolean value);
  boolean getCharge();
  void setCharge(boolean value);
  boolean getLoad();
  void setLoad(boolean value);
  boolean getUnload();
  void setUnload(boolean value);
  boolean getPrepick();
  void setPrepick(boolean value);
  boolean getPreplace();
  void setPreplace(boolean value);
  boolean getPostpick();
  void setPostpick(boolean value);
  boolean getPostplace();
  void setPostplace(boolean value);
  boolean getStop();
  void setStop(boolean value);
  boolean getDooropen();
  void setDooropen(boolean value);
  boolean getDoorclose();
  void setDoorclose(boolean value);
  int getDoorId();
  void setDoorId(int value);
  boolean getEntershower();
  void setEntershower(boolean value);
  boolean getLeaveshower();
  void setLeaveshower(boolean value);
  java.lang.String getShowerdoor();
  void setShowerdoor(java.lang.String value);
  java.lang.String getShowerfrom();
  void setShowerfrom(java.lang.String value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getLeavemagneticTurnDirection();
  void setLeavemagneticTurnDirection(java.lang.String value);
  float getFindmagneticDistance();
  void setFindmagneticDistance(float value);
  java.lang.String getFindmagneticTurnDirection();
  void setFindmagneticTurnDirection(java.lang.String value);
  boolean getMagneticload();
  void setMagneticload(boolean value);
  boolean getMagneticunload();
  void setMagneticunload(boolean value);
  boolean getFindmagnetic();
  void setFindmagnetic(boolean value);
  boolean getLeavemagnetic();
  void setLeavemagnetic(boolean value);
  int getMagneticLoadUnloadLane();
  void setMagneticLoadUnloadLane(int value);
  int getMagneticLoadUnloadCartPosition();
  void setMagneticLoadUnloadCartPosition(int value);
  java.lang.String getMagneticLoadUnloadTurnDirection();
  void setMagneticLoadUnloadTurnDirection(java.lang.String value);
  boolean getMagneticLoadUnloadAllowMarkers();
  void setMagneticLoadUnloadAllowMarkers(boolean value);
  java.lang.String getMagneticLoadUnloadCartId();
  void setMagneticLoadUnloadCartId(java.lang.String value);
  boolean getElevator();
  void setElevator(boolean value);
  boolean getElevatorGetControl();
  void setElevatorGetControl(boolean value);
  boolean getElevatorLeaveControl();
  void setElevatorLeaveControl(boolean value);
  boolean getElevatorOpenDoor();
  void setElevatorOpenDoor(boolean value);
  boolean getElevatorCloseDoor();
  void setElevatorCloseDoor(boolean value);
  int getElevatorId();
  void setElevatorId(int value);
}
