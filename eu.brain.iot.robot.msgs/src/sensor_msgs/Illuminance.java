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

public interface Illuminance extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/Illuminance";
  static final java.lang.String _DEFINITION = " # Single photometric illuminance measurement.  Light should be assumed to be\n # measured along the sensor\'s x-axis (the area of detection is the y-z plane).\n # The illuminance should have a 0 or positive value and be received with\n # the sensor\'s +X axis pointing toward the light source.\n\n # Photometric illuminance is the measure of the human eye\'s sensitivity of the\n # intensity of light encountering or passing through a surface.\n\n # All other Photometric and Radiometric measurements should\n # not use this message.\n # This message cannot represent:\n # Luminous intensity (candela/light source output)\n # Luminance (nits/light output per area)\n # Irradiance (watt/area), etc.\n\n Header header           # timestamp is the time the illuminance was measured\n                         # frame_id is the location and direction of the reading\n\n float64 illuminance     # Measurement of the Photometric Illuminance in Lux.\n\n float64 variance        # 0 is interpreted as variance unknown";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  double getIlluminance();
  void setIlluminance(double value);
  double getVariance();
  void setVariance(double value);
}
