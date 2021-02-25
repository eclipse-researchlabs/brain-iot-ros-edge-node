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
package sensor_msgs;

public interface BatteryState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/BatteryState";
  static final java.lang.String _DEFINITION = "\n# Constants are chosen to match the enums in the linux kernel\n# defined in include/linux/power_supply.h as of version 3.7\n# The one difference is for style reasons the constants are\n# all uppercase not mixed case.\n\n# Power supply status constants\nuint8 POWER_SUPPLY_STATUS_UNKNOWN = 0\nuint8 POWER_SUPPLY_STATUS_CHARGING = 1\nuint8 POWER_SUPPLY_STATUS_DISCHARGING = 2\nuint8 POWER_SUPPLY_STATUS_NOT_CHARGING = 3\nuint8 POWER_SUPPLY_STATUS_FULL = 4\n\n# Power supply health constants\nuint8 POWER_SUPPLY_HEALTH_UNKNOWN = 0\nuint8 POWER_SUPPLY_HEALTH_GOOD = 1\nuint8 POWER_SUPPLY_HEALTH_OVERHEAT = 2\nuint8 POWER_SUPPLY_HEALTH_DEAD = 3\nuint8 POWER_SUPPLY_HEALTH_OVERVOLTAGE = 4\nuint8 POWER_SUPPLY_HEALTH_UNSPEC_FAILURE = 5\nuint8 POWER_SUPPLY_HEALTH_COLD = 6\nuint8 POWER_SUPPLY_HEALTH_WATCHDOG_TIMER_EXPIRE = 7\nuint8 POWER_SUPPLY_HEALTH_SAFETY_TIMER_EXPIRE = 8\n\n# Power supply technology (chemistry) constants\nuint8 POWER_SUPPLY_TECHNOLOGY_UNKNOWN = 0\nuint8 POWER_SUPPLY_TECHNOLOGY_NIMH = 1\nuint8 POWER_SUPPLY_TECHNOLOGY_LION = 2\nuint8 POWER_SUPPLY_TECHNOLOGY_LIPO = 3\nuint8 POWER_SUPPLY_TECHNOLOGY_LIFE = 4\nuint8 POWER_SUPPLY_TECHNOLOGY_NICD = 5\nuint8 POWER_SUPPLY_TECHNOLOGY_LIMN = 6\n\nHeader  header\nfloat32 voltage          # Voltage in Volts (Mandatory)\nfloat32 current          # Negative when discharging (A)  (If unmeasured NaN)\nfloat32 charge           # Current charge in Ah  (If unmeasured NaN)\nfloat32 capacity         # Capacity in Ah (last full capacity)  (If unmeasured NaN)\nfloat32 design_capacity  # Capacity in Ah (design capacity)  (If unmeasured NaN)\nfloat32 percentage       # Charge percentage on 0 to 1 range  (If unmeasured NaN)\nuint8   power_supply_status     # The charging status as reported. Values defined above\nuint8   power_supply_health     # The battery health metric. Values defined above\nuint8   power_supply_technology # The battery chemistry. Values defined above\nbool    present          # True if the battery is present\n\nfloat32[] cell_voltage   # An array of individual cell voltages for each cell in the pack\n                         # If individual voltages unknown but number of cells known set each to NaN\nstring location          # The location into which the battery is inserted. (slot number or plug)\nstring serial_number     # The best approximation of the battery serial number\n";
  static final byte POWER_SUPPLY_STATUS_UNKNOWN = 0;
  static final byte POWER_SUPPLY_STATUS_CHARGING = 1;
  static final byte POWER_SUPPLY_STATUS_DISCHARGING = 2;
  static final byte POWER_SUPPLY_STATUS_NOT_CHARGING = 3;
  static final byte POWER_SUPPLY_STATUS_FULL = 4;
  static final byte POWER_SUPPLY_HEALTH_UNKNOWN = 0;
  static final byte POWER_SUPPLY_HEALTH_GOOD = 1;
  static final byte POWER_SUPPLY_HEALTH_OVERHEAT = 2;
  static final byte POWER_SUPPLY_HEALTH_DEAD = 3;
  static final byte POWER_SUPPLY_HEALTH_OVERVOLTAGE = 4;
  static final byte POWER_SUPPLY_HEALTH_UNSPEC_FAILURE = 5;
  static final byte POWER_SUPPLY_HEALTH_COLD = 6;
  static final byte POWER_SUPPLY_HEALTH_WATCHDOG_TIMER_EXPIRE = 7;
  static final byte POWER_SUPPLY_HEALTH_SAFETY_TIMER_EXPIRE = 8;
  static final byte POWER_SUPPLY_TECHNOLOGY_UNKNOWN = 0;
  static final byte POWER_SUPPLY_TECHNOLOGY_NIMH = 1;
  static final byte POWER_SUPPLY_TECHNOLOGY_LION = 2;
  static final byte POWER_SUPPLY_TECHNOLOGY_LIPO = 3;
  static final byte POWER_SUPPLY_TECHNOLOGY_LIFE = 4;
  static final byte POWER_SUPPLY_TECHNOLOGY_NICD = 5;
  static final byte POWER_SUPPLY_TECHNOLOGY_LIMN = 6;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  float getVoltage();
  void setVoltage(float value);
  float getCurrent();
  void setCurrent(float value);
  float getCharge();
  void setCharge(float value);
  float getCapacity();
  void setCapacity(float value);
  float getDesignCapacity();
  void setDesignCapacity(float value);
  float getPercentage();
  void setPercentage(float value);
  byte getPowerSupplyStatus();
  void setPowerSupplyStatus(byte value);
  byte getPowerSupplyHealth();
  void setPowerSupplyHealth(byte value);
  byte getPowerSupplyTechnology();
  void setPowerSupplyTechnology(byte value);
  boolean getPresent();
  void setPresent(boolean value);
  float[] getCellVoltage();
  void setCellVoltage(float[] value);
  java.lang.String getLocation();
  void setLocation(java.lang.String value);
  java.lang.String getSerialNumber();
  void setSerialNumber(java.lang.String value);
}
