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

public interface MEMStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/MEMStatus";
  static final java.lang.String _DEFINITION = "float32 time\nsystem_monitor/Memory[] memories\n#Memory space in M\nint32 totalM\nint32 usedM\nint32 freeM";
  float getTime();
  void setTime(float value);
  java.util.List<system_monitor.Memory> getMemories();
  void setMemories(java.util.List<system_monitor.Memory> value);
  int getTotalM();
  void setTotalM(int value);
  int getUsedM();
  void setUsedM(int value);
  int getFreeM();
  void setFreeM(int value);
}
