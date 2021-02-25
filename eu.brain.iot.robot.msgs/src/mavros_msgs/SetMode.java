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

public interface SetMode extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/SetMode";
  static final java.lang.String _DEFINITION = "# set FCU mode\n#\n# Known custom modes listed here:\n# http://wiki.ros.org/mavros/CustomModes\n\n# basic modes from MAV_MODE\nuint8 MAV_MODE_PREFLIGHT\t\t= 0\nuint8 MAV_MODE_STABILIZE_DISARMED\t= 80\nuint8 MAV_MODE_STABILIZE_ARMED\t\t= 208\nuint8 MAV_MODE_MANUAL_DISARMED\t\t= 64\nuint8 MAV_MODE_MANUAL_ARMED\t\t= 192\nuint8 MAV_MODE_GUIDED_DISARMED\t\t= 88\nuint8 MAV_MODE_GUIDED_ARMED\t\t= 216\nuint8 MAV_MODE_AUTO_DISARMED\t\t= 92\nuint8 MAV_MODE_AUTO_ARMED\t\t= 220\nuint8 MAV_MODE_TEST_DISARMED\t\t= 66\nuint8 MAV_MODE_TEST_ARMED\t\t= 194\n\nuint8 base_mode\t\t# filled by MAV_MODE enum value or 0 if custom_mode != \'\'\nstring custom_mode\t# string mode representation or integer\n---\nbool mode_sent\t\t# Mode known/parsed correctly and SET_MODE are sent\n";
}
