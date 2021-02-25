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

public interface MissionCommand extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/MissionCommand";
  static final java.lang.String _DEFINITION = "# available commands\nstring GOTO_POSE=goto_pose\nstring GOTO_TAG=goto_tag\nstring GOTO_NODE=goto_node\n\n# id set from the subsystem calling the service\nstring id\n# command from the available commands\nstring command\n\nMissionParamInt[] int_params\nMissionParamFloat[] float_params\nMissionParamString[] string_params\nMissionParamBool[] bool_params\n\n\n\n";
  static final java.lang.String GOTO_POSE = "goto_pose";
  static final java.lang.String GOTO_TAG = "goto_tag";
  static final java.lang.String GOTO_NODE = "goto_node";
  java.lang.String getId();
  void setId(java.lang.String value);
  java.lang.String getCommand();
  void setCommand(java.lang.String value);
  java.util.List<robot_local_control_msgs.MissionParamInt> getIntParams();
  void setIntParams(java.util.List<robot_local_control_msgs.MissionParamInt> value);
  java.util.List<robot_local_control_msgs.MissionParamFloat> getFloatParams();
  void setFloatParams(java.util.List<robot_local_control_msgs.MissionParamFloat> value);
  java.util.List<robot_local_control_msgs.MissionParamString> getStringParams();
  void setStringParams(java.util.List<robot_local_control_msgs.MissionParamString> value);
  java.util.List<robot_local_control_msgs.MissionParamBool> getBoolParams();
  void setBoolParams(java.util.List<robot_local_control_msgs.MissionParamBool> value);
}
