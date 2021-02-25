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

public interface WayPoint extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/WayPoint";
  static final java.lang.String _DEFINITION = "# Way-point element for a geographic map.\n\nuuid_msgs/UniqueID id   # Unique way-point identifier\nGeoPoint   position     # Position relative to WGS 84 ellipsoid\nKeyValue[] props        # Key/value properties for this point\n";
  uuid_msgs.UniqueID getId();
  void setId(uuid_msgs.UniqueID value);
  geographic_msgs.GeoPoint getPosition();
  void setPosition(geographic_msgs.GeoPoint value);
  java.util.List<geographic_msgs.KeyValue> getProps();
  void setProps(java.util.List<geographic_msgs.KeyValue> value);
}
