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

public interface MapFeature extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/MapFeature";
  static final java.lang.String _DEFINITION = "# Geographic map feature.\n#\n# A list of WayPoint IDs for features like streets, highways, hiking\n# trails, the outlines of buildings and parking lots in sequential\n# order.\n#\n# Feature lists may also contain other feature lists as members.\n\nuuid_msgs/UniqueID   id         # Unique feature identifier\nuuid_msgs/UniqueID[] components # Sequence of feature components\nKeyValue[] props                # Key/value properties for this feature\n";
  uuid_msgs.UniqueID getId();
  void setId(uuid_msgs.UniqueID value);
  java.util.List<uuid_msgs.UniqueID> getComponents();
  void setComponents(java.util.List<uuid_msgs.UniqueID> value);
  java.util.List<geographic_msgs.KeyValue> getProps();
  void setProps(java.util.List<geographic_msgs.KeyValue> value);
}
