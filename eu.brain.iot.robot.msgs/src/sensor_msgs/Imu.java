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
package sensor_msgs;

public interface Imu extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/Imu";
  static final java.lang.String _DEFINITION = "# This is a message to hold data from an IMU (Inertial Measurement Unit)\n#\n# Accelerations should be in m/s^2 (not in g\'s), and rotational velocity should be in rad/sec\n#\n# If the covariance of the measurement is known, it should be filled in (if all you know is the \n# variance of each measurement, e.g. from the datasheet, just put those along the diagonal)\n# A covariance matrix of all zeros will be interpreted as \"covariance unknown\", and to use the\n# data a covariance will have to be assumed or gotten from some other source\n#\n# If you have no estimate for one of the data elements (e.g. your IMU doesn\'t produce an orientation \n# estimate), please set element 0 of the associated covariance matrix to -1\n# If you are interpreting this message, please check for a value of -1 in the first element of each \n# covariance matrix, and disregard the associated estimate.\n\nHeader header\n\ngeometry_msgs/Quaternion orientation\nfloat64[9] orientation_covariance # Row major about x, y, z axes\n\ngeometry_msgs/Vector3 angular_velocity\nfloat64[9] angular_velocity_covariance # Row major about x, y, z axes\n\ngeometry_msgs/Vector3 linear_acceleration\nfloat64[9] linear_acceleration_covariance # Row major x, y z \n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Quaternion getOrientation();
  void setOrientation(geometry_msgs.Quaternion value);
  double[] getOrientationCovariance();
  void setOrientationCovariance(double[] value);
  geometry_msgs.Vector3 getAngularVelocity();
  void setAngularVelocity(geometry_msgs.Vector3 value);
  double[] getAngularVelocityCovariance();
  void setAngularVelocityCovariance(double[] value);
  geometry_msgs.Vector3 getLinearAcceleration();
  void setLinearAcceleration(geometry_msgs.Vector3 value);
  double[] getLinearAccelerationCovariance();
  void setLinearAccelerationCovariance(double[] value);
}
