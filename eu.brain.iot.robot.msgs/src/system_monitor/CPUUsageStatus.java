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
