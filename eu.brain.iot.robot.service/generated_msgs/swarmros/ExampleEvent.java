package swarmros;

public interface ExampleEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ExampleEvent";
  static final java.lang.String _DEFINITION = "Header header\nEventHeader eventHeader\nuint64 counter";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  swarmros.EventHeader getEventHeader();
  void setEventHeader(swarmros.EventHeader value);
  long getCounter();
  void setCounter(long value);
}
