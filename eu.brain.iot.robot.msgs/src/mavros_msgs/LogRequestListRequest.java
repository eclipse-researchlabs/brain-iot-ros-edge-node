package mavros_msgs;

public interface LogRequestListRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogRequestListRequest";
  static final java.lang.String _DEFINITION = "# Request a list of available logs\n#\n#  :start: - first log id (0 for first available)\n#  :end: - last log id (0xffff for last available)\n\nuint16 start\nuint16 end\n";
  short getStart();
  void setStart(short value);
  short getEnd();
  void setEnd(short value);
}
