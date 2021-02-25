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

public interface VehicleInfoGet extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/VehicleInfoGet";
  static final java.lang.String _DEFINITION = "# Request the Vehicle Info\n# use this to request the current target sysid / compid defined in mavros\n# set get_all = True to request all available vehicles\n\nuint8 GET_MY_SYSID = 0\nuint8 GET_MY_COMPID = 0\n\nuint8 sysid\nuint8 compid\nbool get_all\n---\nbool success\nmavros_msgs/VehicleInfo[] vehicles\n\n";
}
