package mavros_msgs;

public interface LogRequestDataRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogRequestDataRequest";
  static final java.lang.String _DEFINITION = "# Request a chunk of a log\n#\n#  :id: - log id from LogEntry message\n#  :offset: - offset into the log\n#  :count: - number of bytes to get\n\nuint16 id\nuint32 offset\nuint32 count\n";
  short getId();
  void setId(short value);
  int getOffset();
  void setOffset(int value);
  int getCount();
  void setCount(int value);
}
