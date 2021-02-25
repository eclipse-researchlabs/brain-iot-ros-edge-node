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

public interface ApplyJointEffortRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/ApplyJointEffortRequest";
  static final java.lang.String _DEFINITION = "# set urdf joint effort\nstring joint_name           # joint to apply wrench (linear force and torque)\nfloat64 effort              # effort to apply\ntime start_time             # optional wrench application start time (seconds)\n                            # if start_time < current time, start as soon as possible\nduration duration           # optional duration of wrench application time (seconds)\n                            # if duration < 0, apply wrench continuously without end\n                            # if duration = 0, do nothing\n                            # if duration < step size, assume step size and\n                            #               display warning in status_message\n";
  java.lang.String getJointName();
  void setJointName(java.lang.String value);
  double getEffort();
  void setEffort(double value);
  org.ros.message.Time getStartTime();
  void setStartTime(org.ros.message.Time value);
  org.ros.message.Duration getDuration();
  void setDuration(org.ros.message.Duration value);
}
