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
package geographic_msgs;

public interface BoundingBox extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/BoundingBox";
  static final java.lang.String _DEFINITION = "# Geographic map bounding box. \n#\n# The two GeoPoints denote diagonally opposite corners of the box.\n#\n# If min_pt.latitude is NaN, the bounding box is \"global\", matching\n# any valid latitude, longitude and altitude.\n#\n# If min_pt.altitude is NaN, the bounding box is two-dimensional and\n# matches any altitude within the specified latitude and longitude\n# range.\n\nGeoPoint min_pt         # lowest and most Southwestern corner\nGeoPoint max_pt         # highest and most Northeastern corner\n";
  geographic_msgs.GeoPoint getMinPt();
  void setMinPt(geographic_msgs.GeoPoint value);
  geographic_msgs.GeoPoint getMaxPt();
  void setMaxPt(geographic_msgs.GeoPoint value);
}
