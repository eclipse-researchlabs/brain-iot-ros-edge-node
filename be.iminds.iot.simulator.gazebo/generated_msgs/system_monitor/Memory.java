package system_monitor;

public interface Memory extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/Memory";
  static final java.lang.String _DEFINITION = "string name\n#Memory space in M\n#Physical w/o buffers total is zero\nint32 total\nint32 used\nint32 free";
  java.lang.String getName();
  void setName(java.lang.String value);
  int getTotal();
  void setTotal(int value);
  int getUsed();
  void setUsed(int value);
  int getFree();
  void setFree(int value);
}
