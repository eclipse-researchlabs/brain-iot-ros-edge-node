package elevator_node;

public interface doorleftmsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "elevator_node/doorleftmsg";
  static final java.lang.String _DEFINITION = "float64 doorleft_msg\n";
  double getDoorleftMsg();
  void setDoorleftMsg(double value);
}
