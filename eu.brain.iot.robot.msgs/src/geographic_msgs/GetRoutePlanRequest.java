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

public interface GetRoutePlanRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/GetRoutePlanRequest";
  static final java.lang.String _DEFINITION = "# Get a plan to traverse a route network from start to goal.\n#\n# Similar to nav_msgs/GetPlan, but constrained to use the route network.\n\nuuid_msgs/UniqueID network      # route network to use\nuuid_msgs/UniqueID start        # starting way point\nuuid_msgs/UniqueID goal         # goal way point\n\n";
  uuid_msgs.UniqueID getNetwork();
  void setNetwork(uuid_msgs.UniqueID value);
  uuid_msgs.UniqueID getStart();
  void setStart(uuid_msgs.UniqueID value);
  uuid_msgs.UniqueID getGoal();
  void setGoal(uuid_msgs.UniqueID value);
}
