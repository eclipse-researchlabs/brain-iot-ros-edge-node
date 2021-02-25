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

public interface SetLightPropertiesRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/SetLightPropertiesRequest";
  static final java.lang.String _DEFINITION = "string light_name                    # name of Gazebo Light\nstd_msgs/ColorRGBA diffuse           # diffuse color as red, green, blue, alpha\nfloat64 attenuation_constant\nfloat64 attenuation_linear\nfloat64 attenuation_quadratic\n";
  java.lang.String getLightName();
  void setLightName(java.lang.String value);
  std_msgs.ColorRGBA getDiffuse();
  void setDiffuse(std_msgs.ColorRGBA value);
  double getAttenuationConstant();
  void setAttenuationConstant(double value);
  double getAttenuationLinear();
  void setAttenuationLinear(double value);
  double getAttenuationQuadratic();
  void setAttenuationQuadratic(double value);
}
