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

public interface RouteSegment extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/RouteSegment";
  static final java.lang.String _DEFINITION = "# Route network segment.\n#\n# This is one directed edge of a RouteNetwork graph. It represents a\n# known path from one way point to another.  If the path is two-way,\n# there will be another RouteSegment with \"start\" and \"end\" reversed.\n\nuuid_msgs/UniqueID id           # Unique identifier for this segment\n\nuuid_msgs/UniqueID start        # beginning way point of segment\nuuid_msgs/UniqueID end          # ending way point of segment\n\nKeyValue[] props                # segment properties\n";
  uuid_msgs.UniqueID getId();
  void setId(uuid_msgs.UniqueID value);
  uuid_msgs.UniqueID getStart();
  void setStart(uuid_msgs.UniqueID value);
  uuid_msgs.UniqueID getEnd();
  void setEnd(uuid_msgs.UniqueID value);
  java.util.List<geographic_msgs.KeyValue> getProps();
  void setProps(java.util.List<geographic_msgs.KeyValue> value);
}
