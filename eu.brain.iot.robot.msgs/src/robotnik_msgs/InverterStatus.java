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

public interface InverterStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/InverterStatus";
  static final java.lang.String _DEFINITION = "Header  header\nfloat32 ac_voltage       # Output Voltage in Volts (Mandatory)\nfloat32 dc_voltage       # Input Voltage in Volts (If unmeasured NaN)\nfloat32 load             # Current percentage load on 0 to 1 range (If unmeasured NaN)\nfloat32 percentage       # Charge percentage on 0 to 1 range (If unmeasured NaN)\nfloat32 temperature      # Current temperature in centigrads (If unmeasured NaN)\nbool    on               # True if the inverter is on\n\nstring serial_number     # The best approximation of the battery serial number\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getAcVoltage();
  void setAcVoltage(float value);
  float getDcVoltage();
  void setDcVoltage(float value);
  float getLoad();
  void setLoad(float value);
  float getPercentage();
  void setPercentage(float value);
  float getTemperature();
  void setTemperature(float value);
  boolean getOn();
  void setOn(boolean value);
  java.lang.String getSerialNumber();
  void setSerialNumber(java.lang.String value);
}
