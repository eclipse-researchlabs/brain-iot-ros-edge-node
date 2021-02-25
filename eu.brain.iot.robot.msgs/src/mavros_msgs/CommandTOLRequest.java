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

public interface CommandTOLRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandTOLRequest";
  static final java.lang.String _DEFINITION = "# Common type for Take Off and Landing\n\nfloat32 min_pitch\t# used by takeoff\nfloat32 yaw\nfloat32 latitude\nfloat32 longitude\nfloat32 altitude\n";
  float getMinPitch();
  void setMinPitch(float value);
  float getYaw();
  void setYaw(float value);
  float getLatitude();
  void setLatitude(float value);
  float getLongitude();
  void setLongitude(float value);
  float getAltitude();
  void setAltitude(float value);
}
