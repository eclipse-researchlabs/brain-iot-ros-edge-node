package system_monitor;

public interface MEMStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/MEMStatus";
  static final java.lang.String _DEFINITION = "float32 time\nsystem_monitor/Memory[] memories\n#Memory space in M\nint32 totalM\nint32 usedM\nint32 freeM";
  float getTime();
  void setTime(float value);
  java.util.List<system_monitor.Memory> getMemories();
  void setMemories(java.util.List<system_monitor.Memory> value);
  int getTotalM();
  void setTotalM(int value);
  int getUsedM();
  void setUsedM(int value);
  int getFreeM();
  void setFreeM(int value);
}
