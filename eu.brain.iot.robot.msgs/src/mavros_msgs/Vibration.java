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
package mavros_msgs;

public interface Vibration extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/Vibration";
  static final java.lang.String _DEFINITION = "# VIBRATION message data\n# @description: Vibration levels and accelerometer clipping\n\nstd_msgs/Header header\n\ngeometry_msgs/Vector3 vibration\t\t# 3-axis vibration levels\nfloat32[3] clipping\t\t\t\t# Accelerometers clipping";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Vector3 getVibration();
  void setVibration(geometry_msgs.Vector3 value);
  float[] getClipping();
  void setClipping(float[] value);
}
