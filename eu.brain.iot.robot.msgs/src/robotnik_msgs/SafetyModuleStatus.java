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

public interface SafetyModuleStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SafetyModuleStatus";
  static final java.lang.String _DEFINITION = "# robot safety mode\nstring SAFE=safe\nstring OVERRIDABLE=overridable\nstring EMERGENCY=emergency\n\nstring safety_mode\nbool charging\nbool emergency_stop        # if e-stop is pressed\nbool safety_stop           # if system is stopped due to safety system\nbool safety_overrided      # if safety system is overrided\nbool lasers_on_standby     # if lasers don\'t have power\nfloat64 current_speed       # current speed measured by safety system\nfloat64 speed_at_safety_stop  # speed measured at last safety stop by safety system\n\nrobotnik_msgs/LaserMode lasers_mode\nrobotnik_msgs/LaserStatus[] lasers_status\n";
  static final java.lang.String SAFE = "safe";
  static final java.lang.String OVERRIDABLE = "overridable";
  static final java.lang.String EMERGENCY = "emergency";
  java.lang.String getSafetyMode();
  void setSafetyMode(java.lang.String value);
  boolean getCharging();
  void setCharging(boolean value);
  boolean getEmergencyStop();
  void setEmergencyStop(boolean value);
  boolean getSafetyStop();
  void setSafetyStop(boolean value);
  boolean getSafetyOverrided();
  void setSafetyOverrided(boolean value);
  boolean getLasersOnStandby();
  void setLasersOnStandby(boolean value);
  double getCurrentSpeed();
  void setCurrentSpeed(double value);
  double getSpeedAtSafetyStop();
  void setSpeedAtSafetyStop(double value);
  robotnik_msgs.LaserMode getLasersMode();
  void setLasersMode(robotnik_msgs.LaserMode value);
  java.util.List<robotnik_msgs.LaserStatus> getLasersStatus();
  void setLasersStatus(java.util.List<robotnik_msgs.LaserStatus> value);
}
