package mavros_msgs;

public interface MountConfigure extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MountConfigure";
  static final java.lang.String _DEFINITION = "# MAVLink message: DO_MOUNT_CONTROL\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_MOUNT_CONFIGURE\n\nstd_msgs/Header header\n\nuint8 mode              # See enum MAV_MOUNT_MODE.\n#MAV_MOUNT_MODE\nuint8 MODE_RETRACT = 0\nuint8 MODE_NEUTRAL = 1\nuint8 MODE_MAVLINK_TARGETING = 2\nuint8 MODE_RC_TARGETING = 3\nuint8 MODE_GPS_POINT = 4\n\nbool stabilize_roll     # stabilize roll? (1 = yes, 0 = no)\nbool stabilize_pitch    # stabilize pitch? (1 = yes, 0 = no)\nbool stabilize_yaw      # stabilize yaw? (1 = yes, 0 = no)\nuint8 roll_input        # roll input (See enum MOUNT_INPUT)\nuint8 pitch_input       # pitch input (See enum MOUNT_INPUT)\nuint8 yaw_input         # yaw input (See enum MOUNT_INPUT)\n\n#MOUNT_INPUT\nuint8 INPUT_ANGLE_BODY_FRAME = 0\nuint8 INPUT_ANGULAR_RATE = 1\nuint8 INPUT_ANGLE_ABSOLUTE_FRAME = 2\n---\nbool success\n# raw result returned by COMMAND_ACK\nuint8 result";
}
