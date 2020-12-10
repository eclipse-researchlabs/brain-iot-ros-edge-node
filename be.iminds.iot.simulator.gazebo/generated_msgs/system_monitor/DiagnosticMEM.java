package system_monitor;

public interface DiagnosticMEM extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/DiagnosticMEM";
  static final java.lang.String _DEFINITION = "string name\n# Possible values for message: \'OK\', \'Warning\',\'Error\'\nstring message\nstring hardware_id\nsystem_monitor/MEMStatus status";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
  java.lang.String getHardwareId();
  void setHardwareId(java.lang.String value);
  system_monitor.MEMStatus getStatus();
  void setStatus(system_monitor.MEMStatus value);
}
