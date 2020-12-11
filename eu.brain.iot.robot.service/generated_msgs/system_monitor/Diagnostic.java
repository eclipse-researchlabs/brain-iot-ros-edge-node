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
