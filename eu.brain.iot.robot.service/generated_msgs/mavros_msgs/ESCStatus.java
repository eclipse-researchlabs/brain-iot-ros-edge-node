package mavros_msgs;

public interface ESCStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ESCStatus";
  static final java.lang.String _DEFINITION = "# ESCStatus.msg\n#\n#\n# See mavlink message documentation here:\n# https://mavlink.io/en/messages/common.html#ESC_STATUS\n\nstd_msgs/Header header\n\nmavros_msgs/ESCStatusItem[] esc_status\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<mavros_msgs.ESCStatusItem> getEscStatus();
  void setEscStatus(java.util.List<mavros_msgs.ESCStatusItem> value);
}
