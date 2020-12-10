package system_monitor;

public interface HDDStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/HDDStatus";
  static final java.lang.String _DEFINITION = "string status\nfloat32 time\nstring space_reading\nsystem_monitor/Disk[] disks\n";
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getTime();
  void setTime(float value);
  java.lang.String getSpaceReading();
  void setSpaceReading(java.lang.String value);
  java.util.List<system_monitor.Disk> getDisks();
  void setDisks(java.util.List<system_monitor.Disk> value);
}
