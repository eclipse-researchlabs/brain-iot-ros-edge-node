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
package gazebo_msgs;

public interface SetModelConfigurationRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/SetModelConfigurationRequest";
  static final java.lang.String _DEFINITION = "# Set Gazebo Model pose and twist\nstring model_name           # model to set state (pose and twist)\nstring urdf_param_name      # parameter name that contains the urdf XML.\n\nstring[] joint_names        # list of joints to set positions.  if joint is not listed here, preserve current position.\nfloat64[] joint_positions   # set to this position.\n";
  java.lang.String getModelName();
  void setModelName(java.lang.String value);
  java.lang.String getUrdfParamName();
  void setUrdfParamName(java.lang.String value);
  java.util.List<java.lang.String> getJointNames();
  void setJointNames(java.util.List<java.lang.String> value);
  double[] getJointPositions();
  void setJointPositions(double[] value);
}
