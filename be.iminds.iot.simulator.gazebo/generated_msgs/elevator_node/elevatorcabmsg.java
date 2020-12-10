package elevator_node;

public interface elevatorcabmsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "elevator_node/elevatorcabmsg";
  static final java.lang.String _DEFINITION = "float64 lift_msg\n";
  double getLiftMsg();
  void setLiftMsg(double value);
}
