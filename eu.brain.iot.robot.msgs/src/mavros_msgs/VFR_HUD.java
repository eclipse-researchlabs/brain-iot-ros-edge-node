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

public interface VFR_HUD extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/VFR_HUD";
  static final java.lang.String _DEFINITION = "# Metrics typically displayed on a HUD for fixed wing aircraft\n#\n# VFR_HUD message\n\nstd_msgs/Header header\nfloat32 airspeed\t# m/s\nfloat32 groundspeed\t# m/s\nint16 heading\t\t# degrees 0..360\nfloat32 throttle\t\t# normalized to 0.0..1.0\nfloat32 altitude\t\t# MSL\nfloat32 climb\t\t# current climb rate m/s\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getAirspeed();
  void setAirspeed(float value);
  float getGroundspeed();
  void setGroundspeed(float value);
  short getHeading();
  void setHeading(short value);
  float getThrottle();
  void setThrottle(float value);
  float getAltitude();
  void setAltitude(float value);
  float getClimb();
  void setClimb(float value);
}
