package mavros_msgs;

public interface ESCInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ESCInfo";
  static final java.lang.String _DEFINITION = "# ESCInfo.msg\n#\n#\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#ESC_INFO\n\nstd_msgs/Header header\n\nuint16 counter\nuint8 count\nuint8 connection_type\nuint8 info\nmavros_msgs/ESCInfoItem[] esc_info\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getCounter();
  void setCounter(short value);
  byte getCount();
  void setCount(byte value);
  byte getConnectionType();
  void setConnectionType(byte value);
  byte getInfo();
  void setInfo(byte value);
  java.util.List<mavros_msgs.ESCInfoItem> getEscInfo();
  void setEscInfo(java.util.List<mavros_msgs.ESCInfoItem> value);
}
