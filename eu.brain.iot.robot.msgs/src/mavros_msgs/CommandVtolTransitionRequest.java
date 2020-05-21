package mavros_msgs;

public interface CommandVtolTransitionRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandVtolTransitionRequest";
  static final java.lang.String _DEFINITION = "\n# MAVLink command: DO_VTOL_TRANSITION\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_VTOL_TRANSITION\n\nstd_msgs/Header header\n\n# MAV_VTOL_STATE\nuint8 STATE_MC = 3\nuint8 STATE_FW = 4\n\nuint8 state              # See enum MAV_VTOL_STATE.\n\n";
  static final byte STATE_MC = 3;
  static final byte STATE_FW = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getState();
  void setState(byte value);
}
