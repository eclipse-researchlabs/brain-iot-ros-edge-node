package system_monitor;

public interface NetStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/NetStatus";
  static final java.lang.String _DEFINITION = "string status\nfloat32 time\nsystem_monitor/Interface[] interfaces";
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getTime();
  void setTime(float value);
  java.util.List<system_monitor.Interface> getInterfaces();
  void setInterfaces(java.util.List<system_monitor.Interface> value);
}
