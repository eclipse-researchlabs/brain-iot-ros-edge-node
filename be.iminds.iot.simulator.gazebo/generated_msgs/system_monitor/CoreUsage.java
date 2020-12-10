package system_monitor;

public interface CoreUsage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/CoreUsage";
  static final java.lang.String _DEFINITION = "int8 id\nstring status\n#Speed of the core in MHz\nfloat32 speed\n# % of the core used by user/system/...\nfloat32 user\nfloat32 nice\nfloat32 system\nfloat32 idle";
  byte getId();
  void setId(byte value);
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  float getSpeed();
  void setSpeed(float value);
  float getUser();
  void setUser(float value);
  float getNice();
  void setNice(float value);
  float getSystem();
  void setSystem(float value);
  float getIdle();
  void setIdle(float value);
}
