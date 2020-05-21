package mavros_msgs;

public interface MountControl extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/MountControl";
  static final java.lang.String _DEFINITION = "# MAVLink message: DO_MOUNT_CONTROL\n# https://mavlink.io/en/messages/common.html#MAV_CMD_DO_MOUNT_CONTROL\n\nstd_msgs/Header header\n\nuint8 mode # See enum MAV_MOUNT_MODE.\nuint8 MAV_MOUNT_MODE_RETRACT = 0\nuint8 MAV_MOUNT_MODE_NEUTRAL = 1\nuint8 MAV_MOUNT_MODE_MAVLINK_TARGETING = 2\nuint8 MAV_MOUNT_MODE_RC_TARGETING = 3\nuint8 MAV_MOUNT_MODE_GPS_POINT = 4\n\nfloat32 pitch # roll degrees or degrees/second depending on mount mode.\nfloat32 roll # roll degrees or degrees/second depending on mount mode.\nfloat32 yaw # roll degrees or degrees/second depending on mount mode.\nfloat32 altitude  # altitude depending on mount mode.\nfloat32 latitude # latitude in degrees * 1E7, set if appropriate mount mode.\nfloat32 longitude # longitude in degrees * 1E7, set if appropriate mount mode.";
  static final byte MAV_MOUNT_MODE_RETRACT = 0;
  static final byte MAV_MOUNT_MODE_NEUTRAL = 1;
  static final byte MAV_MOUNT_MODE_MAVLINK_TARGETING = 2;
  static final byte MAV_MOUNT_MODE_RC_TARGETING = 3;
  static final byte MAV_MOUNT_MODE_GPS_POINT = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getMode();
  void setMode(byte value);
  float getPitch();
  void setPitch(float value);
  float getRoll();
  void setRoll(float value);
  float getYaw();
  void setYaw(float value);
  float getAltitude();
  void setAltitude(float value);
  float getLatitude();
  void setLatitude(float value);
  float getLongitude();
  void setLongitude(float value);
}
