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

public interface LocalizationStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/LocalizationStatus";
  static final java.lang.String _DEFINITION = "# Type of localization systems used\nstring LOCALIZATION_TYPE_NONE=none\n# amcl\nstring LOCALIZATION_TYPE_1=1\n# gps\nstring LOCALIZATION_TYPE_2=2\n# cartogphr\nstring LOCALIZATION_TYPE_3=3\n# gmapping\nstring LOCALIZATION_TYPE_4=4\n\n# state of the navigation node running\nstring LOCALIZATION_STATE_INIT=init\nstring LOCALIZATION_STATE_READY=ready\nstring LOCALIZATION_STATE_EMERGENCY=emergency\nstring LOCALIZATION_STATE_FAILURE=failure\nstring LOCALIZATION_STATE_UNKNOWN=unknown\n\n# Pose of the robot based on the current localization algorithm\nPose2DStamped pose\n\n# reliability\nbool reliable\n\n# Node/tag where the robot is \nstring node\n\n# Type of localization technology\nstring type\n\n# INIT, READY, EMERGENCY, FAILURE\nstring state\n";
  static final java.lang.String LOCALIZATION_TYPE_NONE = "none";
  static final java.lang.String LOCALIZATION_TYPE_1 = "1";
  static final java.lang.String LOCALIZATION_TYPE_2 = "2";
  static final java.lang.String LOCALIZATION_TYPE_3 = "3";
  static final java.lang.String LOCALIZATION_TYPE_4 = "4";
  static final java.lang.String LOCALIZATION_STATE_INIT = "init";
  static final java.lang.String LOCALIZATION_STATE_READY = "ready";
  static final java.lang.String LOCALIZATION_STATE_EMERGENCY = "emergency";
  static final java.lang.String LOCALIZATION_STATE_FAILURE = "failure";
  static final java.lang.String LOCALIZATION_STATE_UNKNOWN = "unknown";
  robot_local_control_msgs.Pose2DStamped getPose();
  void setPose(robot_local_control_msgs.Pose2DStamped value);
  boolean getReliable();
  void setReliable(boolean value);
  java.lang.String getNode();
  void setNode(java.lang.String value);
  java.lang.String getType();
  void setType(java.lang.String value);
  java.lang.String getState();
  void setState(java.lang.String value);
}
