package swarmros;

public interface SimpleEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/SimpleEvent";
  static final java.lang.String _DEFINITION = "EventHeader eventHeader";
  swarmros.EventHeader getEventHeader();
  void setEventHeader(swarmros.EventHeader value);
}
