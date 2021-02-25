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

public interface CPUUsageStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/CPUUsageStatus";
  static final java.lang.String _DEFINITION = "string status\nfloat32 time\nstring load_status\n#Load average in %\nfloat32 load_avg1\nfloat32 load_avg5\nfloat32 load_avg15\nsystem_monitor/CoreUsage[] cores\n";
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getTime();
  void setTime(float value);
  java.lang.String getLoadStatus();
  void setLoadStatus(java.lang.String value);
  float getLoadAvg1();
  void setLoadAvg1(float value);
  float getLoadAvg5();
  void setLoadAvg5(float value);
  float getLoadAvg15();
  void setLoadAvg15(float value);
  java.util.List<system_monitor.CoreUsage> getCores();
  void setCores(java.util.List<system_monitor.CoreUsage> value);
}
