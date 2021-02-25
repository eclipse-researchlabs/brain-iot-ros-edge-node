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

public interface Altitude extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/Altitude";
  static final java.lang.String _DEFINITION = "# Altitude information\n#\n# https://mavlink.io/en/messages/common.html#ALTITUDE\n\nstd_msgs/Header header\n\nfloat32 monotonic\nfloat32 amsl\nfloat32 local\nfloat32 relative\nfloat32 terrain\nfloat32 bottom_clearance\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getMonotonic();
  void setMonotonic(float value);
  float getAmsl();
  void setAmsl(float value);
  float getLocal();
  void setLocal(float value);
  float getRelative();
  void setRelative(float value);
  float getTerrain();
  void setTerrain(float value);
  float getBottomClearance();
  void setBottomClearance(float value);
}
