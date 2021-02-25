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
package dynamic_reconfigure;

public interface SensorLevels extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "dynamic_reconfigure/SensorLevels";
  static final java.lang.String _DEFINITION = "# This message is deprecated, please use driver_base/SensorLevels instead.\n\nbyte RECONFIGURE_CLOSE = 3  # Parameters that need a sensor to be stopped completely when changed\nbyte RECONFIGURE_STOP = 1  # Parameters that need a sensor to stop streaming when changed\nbyte RECONFIGURE_RUNNING = 0 # Parameters that can be changed while a sensor is streaming\n";
  static final byte RECONFIGURE_CLOSE = 3;
  static final byte RECONFIGURE_STOP = 1;
  static final byte RECONFIGURE_RUNNING = 0;
}
