package mavros_msgs;

public interface LogRequestData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogRequestData";
  static final java.lang.String _DEFINITION = "# Request a chunk of a log\n#\n#  :id: - log id from LogEntry message\n#  :offset: - offset into the log\n#  :count: - number of bytes to get\n\nuint16 id\nuint32 offset\nuint32 count\n---\nbool success\n";
}
