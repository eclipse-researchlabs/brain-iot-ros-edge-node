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
package robotnik_msgs;

public interface BatteryDockingStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/BatteryDockingStatus";
  static final java.lang.String _DEFINITION = "# Modes of operation:\n# no docking station contacts\nstring MODE_DISABLED=disabled\n# Unattended relay detection & activation with no inputs/outputs feedback. Done by the hw\nstring MODE_AUTO_HW=automatic_hw\n# Unattended relay detection & activation with inputs/outputs feedback. Done by the sw\nstring MODE_AUTO_SW=automatic_sw\n# Unattended relay detection & and manual activation of the charging relay\nstring MODE_MANUAL_SW=manual_sw\n\nstring operation_mode\n\t\nfloat32 battery_current\t\t# current flow in Amperes\nbool contact_relay_status\t# shows if there\'s contact with the charger\nbool charger_relay_status   # shows if the relay for the charge is active or not\n";
  static final java.lang.String MODE_DISABLED = "disabled";
  static final java.lang.String MODE_AUTO_HW = "automatic_hw";
  static final java.lang.String MODE_AUTO_SW = "automatic_sw";
  static final java.lang.String MODE_MANUAL_SW = "manual_sw";
  java.lang.String getOperationMode();
  void setOperationMode(java.lang.String value);
  float getBatteryCurrent();
  void setBatteryCurrent(float value);
  boolean getContactRelayStatus();
  void setContactRelayStatus(boolean value);
  boolean getChargerRelayStatus();
  void setChargerRelayStatus(boolean value);
}
