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

public interface HilStateQuaternion extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/HilStateQuaternion";
  static final java.lang.String _DEFINITION = "# HilStateQuaternion.msg\n#\n# ROS representation of MAVLink HIL_STATE_QUATERNION\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#HIL_STATE_QUATERNION\n\nstd_msgs/Header header\n\ngeometry_msgs/Quaternion orientation\ngeometry_msgs/Vector3 angular_velocity\ngeometry_msgs/Vector3 linear_acceleration\ngeometry_msgs/Vector3 linear_velocity\ngeographic_msgs/GeoPoint geo\nfloat32 ind_airspeed\nfloat32 true_airspeed\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Quaternion getOrientation();
  void setOrientation(geometry_msgs.Quaternion value);
  geometry_msgs.Vector3 getAngularVelocity();
  void setAngularVelocity(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getLinearAcceleration();
  void setLinearAcceleration(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getLinearVelocity();
  void setLinearVelocity(geometry_msgs.Vector3 value);
  geographic_msgs.GeoPoint getGeo();
  void setGeo(geographic_msgs.GeoPoint value);
  float getIndAirspeed();
  void setIndAirspeed(float value);
  float getTrueAirspeed();
  void setTrueAirspeed(float value);
}
