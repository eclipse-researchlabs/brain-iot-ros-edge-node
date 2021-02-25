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

public interface Mission extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/Mission";
  static final java.lang.String _DEFINITION = "int32 id\nint32 robot_id\nbool charge\nbool load\nbool unload\nbool canceled\nstring error_msg\nint32[] node_or\nint32[] node_dest\nstring node_or_action\nstring node_dest_action\nstring time_start\nstring time_insert\nstring time_end\n";
  int getId();
  void setId(int value);
  int getRobotId();
  void setRobotId(int value);
  boolean getCharge();
  void setCharge(boolean value);
  boolean getLoad();
  void setLoad(boolean value);
  boolean getUnload();
  void setUnload(boolean value);
  boolean getCanceled();
  void setCanceled(boolean value);
  java.lang.String getErrorMsg();
  void setErrorMsg(java.lang.String value);
  int[] getNodeOr();
  void setNodeOr(int[] value);
  int[] getNodeDest();
  void setNodeDest(int[] value);
  java.lang.String getNodeOrAction();
  void setNodeOrAction(java.lang.String value);
  java.lang.String getNodeDestAction();
  void setNodeDestAction(java.lang.String value);
  java.lang.String getTimeStart();
  void setTimeStart(java.lang.String value);
  java.lang.String getTimeInsert();
  void setTimeInsert(java.lang.String value);
  java.lang.String getTimeEnd();
  void setTimeEnd(java.lang.String value);
}
