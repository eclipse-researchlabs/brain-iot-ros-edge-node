package mavros_msgs;

public interface ESCStatusItem extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ESCStatusItem";
  static final java.lang.String _DEFINITION = "# ESCStatusItem.msg\n#\n#\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#ESC_STATUS\n\nstd_msgs/Header header\n\nint32 rpm\nfloat32 voltage\nfloat32 current\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getRpm();
  void setRpm(int value);
  float getVoltage();
  void setVoltage(float value);
  float getCurrent();
  void setCurrent(float value);
}
