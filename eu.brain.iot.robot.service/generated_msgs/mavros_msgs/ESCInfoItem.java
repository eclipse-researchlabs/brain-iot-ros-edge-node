package mavros_msgs;

public interface ESCInfoItem extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ESCInfoItem";
  static final java.lang.String _DEFINITION = "# ESCInfoItem.msg\n#\n#\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#ESC_INFO\n\nstd_msgs/Header header\n\nuint16 failure_flags\nuint32 error_count\nuint8 temperature\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getFailureFlags();
  void setFailureFlags(short value);
  int getErrorCount();
  void setErrorCount(int value);
  byte getTemperature();
  void setTemperature(byte value);
}
