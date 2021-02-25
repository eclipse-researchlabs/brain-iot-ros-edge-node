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

public interface JointTolerance extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/JointTolerance";
  static final java.lang.String _DEFINITION = "# The tolerances specify the amount the position, velocity, and\n# accelerations can vary from the setpoints.  For example, in the case\n# of trajectory control, when the actual position varies beyond\n# (desired position + position tolerance), the trajectory goal may\n# abort.\n# \n# There are two special values for tolerances:\n#  * 0 - The tolerance is unspecified and will remain at whatever the default is\n#  * -1 - The tolerance is \"erased\".  If there was a default, the joint will be\n#         allowed to move without restriction.\n\nstring name\nfloat64 position  # in radians or meters (for a revolute or prismatic joint, respectively)\nfloat64 velocity  # in rad/sec or m/sec\nfloat64 acceleration  # in rad/sec^2 or m/sec^2\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  double getPosition();
  void setPosition(double value);
  double getVelocity();
  void setVelocity(double value);
  double getAcceleration();
  void setAcceleration(double value);
}
