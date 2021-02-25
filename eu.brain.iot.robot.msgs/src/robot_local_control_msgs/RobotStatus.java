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

public interface RobotStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/RobotStatus";
  static final java.lang.String _DEFINITION = "# robot battery\nrobotnik_msgs/BatteryStatus battery\n\n# Robot emergency stop\nbool emergency_stop\n\n# lights\n# acoustic signal\n# bool acoustic_signal\n\n# Robot internal odometry\nPose2DStamped pose\nTwist2D velocity\n\nSensorStatus[] sensors\nrobotnik_msgs/ElevatorStatus elevator\n";
  robotnik_msgs.BatteryStatus getBattery();
  void setBattery(robotnik_msgs.BatteryStatus value);
  boolean getEmergencyStop();
  void setEmergencyStop(boolean value);
  robot_local_control_msgs.Pose2DStamped getPose();
  void setPose(robot_local_control_msgs.Pose2DStamped value);
  robot_local_control_msgs.Twist2D getVelocity();
  void setVelocity(robot_local_control_msgs.Twist2D value);
  java.util.List<robot_local_control_msgs.SensorStatus> getSensors();
  void setSensors(java.util.List<robot_local_control_msgs.SensorStatus> value);
  robotnik_msgs.ElevatorStatus getElevator();
  void setElevator(robotnik_msgs.ElevatorStatus value);
}
