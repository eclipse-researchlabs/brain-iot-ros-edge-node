package mavros_msgs;

public interface LogEntry extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogEntry";
  static final java.lang.String _DEFINITION = "# Information about a single log\n#\n#  :id: - log id\n#  :num_logs: - total number of logs\n#  :last_log_num: - id of last log\n#  :time_utc: - UTC timestamp of log (ros::Time(0) if not available)\n#  :size: - size of log in bytes (may be approximate)\n\nstd_msgs/Header header\n\nuint16 id\nuint16 num_logs\nuint16 last_log_num\ntime time_utc\nuint32 size\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getId();
  void setId(short value);
  short getNumLogs();
  void setNumLogs(short value);
  short getLastLogNum();
  void setLastLogNum(short value);
  org.ros.message.Time getTimeUtc();
  void setTimeUtc(org.ros.message.Time value);
  int getSize();
  void setSize(int value);
}
