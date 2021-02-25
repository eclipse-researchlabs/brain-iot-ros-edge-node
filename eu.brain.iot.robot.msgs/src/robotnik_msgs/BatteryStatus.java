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

public interface BatteryStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/BatteryStatus";
  static final java.lang.String _DEFINITION = "float32 voltage\t\t\t# in volts\nfloat32 level\t\t\t# in %\nuint32 time_remaining\t\t# in minutes\nuint32 time_charging            # in minutes \nbool is_charging                # true when connected\n";
  float getVoltage();
  void setVoltage(float value);
  float getLevel();
  void setLevel(float value);
  int getTimeRemaining();
  void setTimeRemaining(int value);
  int getTimeCharging();
  void setTimeCharging(int value);
  boolean getIsCharging();
  void setIsCharging(boolean value);
}
