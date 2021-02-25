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
package trajectory_msgs;

public interface JointTrajectoryPoint extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "trajectory_msgs/JointTrajectoryPoint";
  static final java.lang.String _DEFINITION = "# Each trajectory point specifies either positions[, velocities[, accelerations]]\n# or positions[, effort] for the trajectory to be executed.\n# All specified values are in the same order as the joint names in JointTrajectory.msg\n\nfloat64[] positions\nfloat64[] velocities\nfloat64[] accelerations\nfloat64[] effort\nduration time_from_start\n";
  double[] getPositions();
  void setPositions(double[] value);
  double[] getVelocities();
  void setVelocities(double[] value);
  double[] getAccelerations();
  void setAccelerations(double[] value);
  double[] getEffort();
  void setEffort(double[] value);
  org.ros.message.Duration getTimeFromStart();
  void setTimeFromStart(org.ros.message.Duration value);
}
