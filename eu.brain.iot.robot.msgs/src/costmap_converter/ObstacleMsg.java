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
package costmap_converter;

public interface ObstacleMsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "costmap_converter/ObstacleMsg";
  static final java.lang.String _DEFINITION = "# Special types:\n# Polygon with 1 vertex: Point obstacle (you might also specify a non-zero value for radius)\n# Polygon with 2 vertices: Line obstacle\n# Polygon with more than 2 vertices: First and last points are assumed to be connected\n\nstd_msgs/Header header\n\n# Obstacle footprint (polygon descriptions)\ngeometry_msgs/Polygon polygon\n\n# Specify the radius for circular/point obstacles\nfloat64 radius\n\n# Obstacle ID\n# Specify IDs in order to provide (temporal) relationships\n# between obstacles among multiple messages.\nint64 id\n\n# Individual orientation (centroid)\ngeometry_msgs/Quaternion orientation\n\n# Individual velocities (centroid)\ngeometry_msgs/TwistWithCovariance velocities\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Polygon getPolygon();
  void setPolygon(geometry_msgs.Polygon value);
  double getRadius();
  void setRadius(double value);
  long getId();
  void setId(long value);
  geometry_msgs.Quaternion getOrientation();
  void setOrientation(geometry_msgs.Quaternion value);
  geometry_msgs.TwistWithCovariance getVelocities();
  void setVelocities(geometry_msgs.TwistWithCovariance value);
}
