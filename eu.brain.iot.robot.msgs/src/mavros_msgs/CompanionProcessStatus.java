package mavros_msgs;

public interface CompanionProcessStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CompanionProcessStatus";
  static final java.lang.String _DEFINITION = "# Mavros message: COMPANIONPROCESSSTATUS\n\nstd_msgs/Header header\n\nuint8 state\t\t\t# See enum COMPANION_PROCESS_STATE\nuint8 component\t\t# See enum MAV_COMPONENT\n\nuint8 MAV_STATE_UNINIT = 0\nuint8 MAV_STATE_BOOT = 1\nuint8 MAV_STATE_CALIBRATING = 2\nuint8 MAV_STATE_STANDBY = 3\nuint8 MAV_STATE_ACTIVE = 4\nuint8 MAV_STATE_CRITICAL = 5\nuint8 MAV_STATE_EMERGENCY = 6\nuint8 MAV_STATE_POWEROFF = 7\nuint8 MAV_STATE_FLIGHT_TERMINATION = 8\n\nuint8 MAV_COMP_ID_OBSTACLE_AVOIDANCE = 196\nuint8 MAV_COMP_ID_VISUAL_INERTIAL_ODOMETRY = 197\n";
  static final byte MAV_STATE_UNINIT = 0;
  static final byte MAV_STATE_BOOT = 1;
  static final byte MAV_STATE_CALIBRATING = 2;
  static final byte MAV_STATE_STANDBY = 3;
  static final byte MAV_STATE_ACTIVE = 4;
  static final byte MAV_STATE_CRITICAL = 5;
  static final byte MAV_STATE_EMERGENCY = 6;
  static final byte MAV_STATE_POWEROFF = 7;
  static final byte MAV_STATE_FLIGHT_TERMINATION = 8;
  static final byte MAV_COMP_ID_OBSTACLE_AVOIDANCE = -60;
  static final byte MAV_COMP_ID_VISUAL_INERTIAL_ODOMETRY = -59;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getState();
  void setState(byte value);
  byte getComponent();
  void setComponent(byte value);
}
