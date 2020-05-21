package system_monitor;

public interface CoreTemp extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/CoreTemp";
  static final java.lang.String _DEFINITION = "int8 id\n#Temperature of the core in DegC\nfloat32 temp";
  byte getId();
  void setId(byte value);
  float getTemp();
  void setTemp(float value);
}
