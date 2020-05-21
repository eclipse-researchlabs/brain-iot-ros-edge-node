package system_monitor;

public interface DiagnosticCPUTemperature extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/DiagnosticCPUTemperature";
  static final java.lang.String _DEFINITION = "string name\n# Possible values for message: \'OK\', \'Warning\',\'Error\'\nstring message\nstring hardware_id\nsystem_monitor/CPUTemperatureStatus status\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
  java.lang.String getHardwareId();
  void setHardwareId(java.lang.String value);
  system_monitor.CPUTemperatureStatus getStatus();
  void setStatus(system_monitor.CPUTemperatureStatus value);
}
