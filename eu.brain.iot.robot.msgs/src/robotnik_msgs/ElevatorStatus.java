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

public interface ElevatorStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/ElevatorStatus";
  static final java.lang.String _DEFINITION = "# state\nstring RAISING=raising\nstring LOWERING=lowering\nstring IDLE=idle\nstring ERROR_G_IO=error_getting_io\nstring ERROR_S_IO=error_setting_io\nstring ERROR_TIMEOUT=error_timeout_in_action\n# position\nstring UP=up\nstring DOWN=down\nstring UNKNOWN=unknown\n# IDLE, RAISING, LOWERING\nstring state\n# UP, DOWN, UNKNOWN\nstring position\n";
  static final java.lang.String RAISING = "raising";
  static final java.lang.String LOWERING = "lowering";
  static final java.lang.String IDLE = "idle";
  static final java.lang.String ERROR_G_IO = "error_getting_io";
  static final java.lang.String ERROR_S_IO = "error_setting_io";
  static final java.lang.String ERROR_TIMEOUT = "error_timeout_in_action";
  static final java.lang.String UP = "up";
  static final java.lang.String DOWN = "down";
  static final java.lang.String UNKNOWN = "unknown";
  java.lang.String getState();
  void setState(java.lang.String value);
  java.lang.String getPosition();
  void setPosition(java.lang.String value);
}
