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

public interface HomePosition extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/HomePosition";
  static final java.lang.String _DEFINITION = "# MAVLink message: HOME_POSITION\n# https://mavlink.io/en/messages/common.html#HOME_POSITION\n\nstd_msgs/Header header\n\ngeographic_msgs/GeoPoint geo # geodetic coordinates in WGS-84 datum\n\ngeometry_msgs/Point position\t# local position\ngeometry_msgs/Quaternion orientation\t# XXX: verify field name (q[4])\ngeometry_msgs/Vector3 approach\t# position of the end of approach vector\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geographic_msgs.GeoPoint getGeo();
  void setGeo(geographic_msgs.GeoPoint value);
  geometry_msgs.Point getPosition();
  void setPosition(geometry_msgs.Point value);
  geometry_msgs.Quaternion getOrientation();
  void setOrientation(geometry_msgs.Quaternion value);
  geometry_msgs.Vector3 getApproach();
  void setApproach(geometry_msgs.Vector3 value);
}
