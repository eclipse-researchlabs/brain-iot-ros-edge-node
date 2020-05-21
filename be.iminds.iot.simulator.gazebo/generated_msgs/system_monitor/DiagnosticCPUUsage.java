package system_monitor;

public interface DiagnosticCPUUsage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/DiagnosticCPUUsage";
  static final java.lang.String _DEFINITION = "string name\n# Possible values for message: \'OK\', \'Warning\',\'Error\'\nstring message\nstring hardware_id\nsystem_monitor/CPUUsageStatus status\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
  java.lang.String getHardwareId();
  void setHardwareId(java.lang.String value);
  system_monitor.CPUUsageStatus getStatus();
  void setStatus(system_monitor.CPUUsageStatus value);
}
