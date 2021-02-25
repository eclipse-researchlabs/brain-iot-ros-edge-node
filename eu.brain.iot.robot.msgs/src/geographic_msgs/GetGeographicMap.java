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

public interface GetGeographicMap extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/GetGeographicMap";
  static final java.lang.String _DEFINITION = "# This service requests a region of a geographic map.\n\nstring url            # where to read map data\n\n# Bounding box for the desired map.  If all zeros, provide all data\n# available from the specified URL.\nBoundingBox bounds\n\n---\n\nbool success          # true if the call succeeded\nstring status         # more details\n\n# The requested map, its bounds may differ from the requested bounds.\nGeographicMap map\n";
}
