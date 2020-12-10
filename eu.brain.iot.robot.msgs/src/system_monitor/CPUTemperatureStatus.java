package system_monitor;

public interface CPUTemperatureStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/CPUTemperatureStatus";
  static final java.lang.String _DEFINITION = "string status\nfloat32 time\nsystem_monitor/CoreTemp[] cores\n";
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getTime();
  void setTime(float value);
  java.util.List<system_monitor.CoreTemp> getCores();
  void setCores(java.util.List<system_monitor.CoreTemp> value);
}
