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

public interface Diagnostic extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/Diagnostic";
  static final java.lang.String _DEFINITION = "system_monitor/DiagnosticCPUUsage diagCpuUsage\nsystem_monitor/DiagnosticCPUTemperature diagCpuTemp\nsystem_monitor/DiagnosticMEM diagMem\nsystem_monitor/DiagnosticNET diagNet\nsystem_monitor/DiagnosticHDD diagHdd\n";
  system_monitor.DiagnosticCPUUsage getDiagCpuUsage();
  void setDiagCpuUsage(system_monitor.DiagnosticCPUUsage value);
  system_monitor.DiagnosticCPUTemperature getDiagCpuTemp();
  void setDiagCpuTemp(system_monitor.DiagnosticCPUTemperature value);
  system_monitor.DiagnosticMEM getDiagMem();
  void setDiagMem(system_monitor.DiagnosticMEM value);
  system_monitor.DiagnosticNET getDiagNet();
  void setDiagNet(system_monitor.DiagnosticNET value);
  system_monitor.DiagnosticHDD getDiagHdd();
  void setDiagHdd(system_monitor.DiagnosticHDD value);
}
