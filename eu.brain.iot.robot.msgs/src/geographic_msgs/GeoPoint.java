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

public interface GeoPoint extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/GeoPoint";
  static final java.lang.String _DEFINITION = "# Geographic point, using the WGS 84 reference ellipsoid.\n\n# Latitude [degrees]. Positive is north of equator; negative is south\n# (-90 <= latitude <= +90).\nfloat64 latitude\n\n# Longitude [degrees]. Positive is east of prime meridian; negative is\n# west (-180 <= longitude <= +180). At the poles, latitude is -90 or\n# +90, and longitude is irrelevant, but must be in range.\nfloat64 longitude\n\n# Altitude [m]. Positive is above the WGS 84 ellipsoid (NaN if unspecified).\nfloat64 altitude\n";
  double getLatitude();
  void setLatitude(double value);
  double getLongitude();
  void setLongitude(double value);
  double getAltitude();
  void setAltitude(double value);
}
