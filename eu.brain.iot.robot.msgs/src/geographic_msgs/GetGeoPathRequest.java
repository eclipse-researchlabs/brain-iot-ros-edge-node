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

public interface GetGeoPathRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/GetGeoPathRequest";
  static final java.lang.String _DEFINITION = "# Searches for given start and goal the nearest route segments\n# and determine the path through the RouteNetwork\n\ngeographic_msgs/GeoPoint start        # starting point\ngeographic_msgs/GeoPoint goal         # goal point\n\n";
  geographic_msgs.GeoPoint getStart();
  void setStart(geographic_msgs.GeoPoint value);
  geographic_msgs.GeoPoint getGoal();
  void setGoal(geographic_msgs.GeoPoint value);
}
