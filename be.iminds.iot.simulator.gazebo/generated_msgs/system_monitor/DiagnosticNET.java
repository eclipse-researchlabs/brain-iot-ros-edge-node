package system_monitor;

public interface DiagnosticNET extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/DiagnosticNET";
  static final java.lang.String _DEFINITION = "string name\nstring message\nstring hardware_id\nsystem_monitor/NetStatus status\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
  java.lang.String getHardwareId();
  void setHardwareId(java.lang.String value);
  system_monitor.NetStatus getStatus();
  void setStatus(system_monitor.NetStatus value);
}
