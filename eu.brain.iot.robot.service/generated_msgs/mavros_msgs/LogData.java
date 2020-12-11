package mavros_msgs;

public interface LogData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogData";
  static final java.lang.String _DEFINITION = "# Reply to LogRequestData, - a chunk of a log\n#\n#  :id: - log id\n#  :offset: - offset into the log\n#  :data: - chunk of data (if zero-sized, then there are no more chunks)\n\nstd_msgs/Header header\n\nuint16 id\nuint32 offset\nuint8[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getId();
  void setId(short value);
  int getOffset();
  void setOffset(int value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
