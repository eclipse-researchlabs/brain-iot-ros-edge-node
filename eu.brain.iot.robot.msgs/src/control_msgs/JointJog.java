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
package control_msgs;

public interface JointJog extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/JointJog";
  static final java.lang.String _DEFINITION = "# Used in time-stamping the message.\nHeader header\n\n# Name list of the joints. You don\'t need to specify all joints of the\n# robot. Joint names are case-sensitive.\nstring[] joint_names\n\n# A position command to the joints listed in joint_names.\n# The order must be identical.\n# Units are meters or radians.\n# If displacements and velocities are filled, a profiled motion is requested.\nfloat64[] displacements # or position_deltas\n\n# A velocity command to the joints listed in joint_names.\n# The order must be identical.\n# Units are m/s or rad/s.\n# If displacements and velocities are filled, a profiled motion is requested.\nfloat64[] velocities\n\nfloat64 duration\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<java.lang.String> getJointNames();
  void setJointNames(java.util.List<java.lang.String> value);
  double[] getDisplacements();
  void setDisplacements(double[] value);
  double[] getVelocities();
  void setVelocities(double[] value);
  double getDuration();
  void setDuration(double value);
}
