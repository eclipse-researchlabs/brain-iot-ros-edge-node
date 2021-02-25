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

public interface HilGPS extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/HilGPS";
  static final java.lang.String _DEFINITION = "# HilControls.msg\n#\n# ROS representation of MAVLink HIL_GPS\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#HIL_GPS\n\nstd_msgs/Header header\nuint8 fix_type\ngeographic_msgs/GeoPoint geo\nuint16 eph\nuint16 epv\nuint16 vel\nint16 vn\nint16 ve\nint16 vd\nuint16 cog\nuint8 satellites_visible\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getFixType();
  void setFixType(byte value);
  geographic_msgs.GeoPoint getGeo();
  void setGeo(geographic_msgs.GeoPoint value);
  short getEph();
  void setEph(short value);
  short getEpv();
  void setEpv(short value);
  short getVel();
  void setVel(short value);
  short getVn();
  void setVn(short value);
  short getVe();
  void setVe(short value);
  short getVd();
  void setVd(short value);
  short getCog();
  void setCog(short value);
  byte getSatellitesVisible();
  void setSatellitesVisible(byte value);
}
