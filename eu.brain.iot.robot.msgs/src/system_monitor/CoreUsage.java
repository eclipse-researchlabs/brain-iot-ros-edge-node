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
package system_monitor;

public interface CoreUsage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/CoreUsage";
  static final java.lang.String _DEFINITION = "int8 id\nstring status\n#Speed of the core in MHz\nfloat32 speed\n# % of the core used by user/system/...\nfloat32 user\nfloat32 nice\nfloat32 system\nfloat32 idle";
  byte getId();
  void setId(byte value);
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getSpeed();
  void setSpeed(float value);
  float getUser();
  void setUser(float value);
  float getNice();
  void setNice(float value);
  float getSystem();
  void setSystem(float value);
  float getIdle();
  void setIdle(float value);
}
