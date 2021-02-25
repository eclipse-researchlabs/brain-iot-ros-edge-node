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

public interface CommandTriggerIntervalRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandTriggerIntervalRequest";
  static final java.lang.String _DEFINITION = "# Type for controlling camera trigger interval and integration time\n\nfloat32   cycle_time\t\t\t# Trigger cycle_time (interval between to triggers) - set to 0 to ignore command\nfloat32   integration_time\t# Camera shutter integration_time - set to 0 to ignore command\n";
  float getCycleTime();
  void setCycleTime(float value);
  float getIntegrationTime();
  void setIntegrationTime(float value);
}
