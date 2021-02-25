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

public interface MagneticField extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/MagneticField";
  static final java.lang.String _DEFINITION = " # Measurement of the Magnetic Field vector at a specific location.\n\n # If the covariance of the measurement is known, it should be filled in\n # (if all you know is the variance of each measurement, e.g. from the datasheet,\n #just put those along the diagonal)\n # A covariance matrix of all zeros will be interpreted as \"covariance unknown\",\n # and to use the data a covariance will have to be assumed or gotten from some\n # other source\n\n\n Header header                        # timestamp is the time the\n                                      # field was measured\n                                      # frame_id is the location and orientation\n                                      # of the field measurement\n\n geometry_msgs/Vector3 magnetic_field # x, y, and z components of the\n                                      # field vector in Tesla\n                                      # If your sensor does not output 3 axes,\n                                      # put NaNs in the components not reported.\n\n float64[9] magnetic_field_covariance # Row major about x, y, z axes\n                                      # 0 is interpreted as variance unknown";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Vector3 getMagneticField();
  void setMagneticField(geometry_msgs.Vector3 value);
  double[] getMagneticFieldCovariance();
  void setMagneticFieldCovariance(double[] value);
}
