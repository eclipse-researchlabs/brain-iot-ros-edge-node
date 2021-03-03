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
package kobuki_msgs;

public interface PowerSystemEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/PowerSystemEvent";
  static final java.lang.String _DEFINITION = "# Power system events\n# This message is generated by important changes in the power system:\n#  - plug/unplug to the docking base or adapter\n#  - transitions to low/critical battery levels\n#  - battery charge completed\n\nuint8 UNPLUGGED           = 0\nuint8 PLUGGED_TO_ADAPTER  = 1\nuint8 PLUGGED_TO_DOCKBASE = 2\nuint8 CHARGE_COMPLETED    = 3\nuint8 BATTERY_LOW         = 4\nuint8 BATTERY_CRITICAL    = 5\n\nuint8 event\n";
  static final byte UNPLUGGED = 0;
  static final byte PLUGGED_TO_ADAPTER = 1;
  static final byte PLUGGED_TO_DOCKBASE = 2;
  static final byte CHARGE_COMPLETED = 3;
  static final byte BATTERY_LOW = 4;
  static final byte BATTERY_CRITICAL = 5;
  byte getEvent();
  void setEvent(byte value);
}