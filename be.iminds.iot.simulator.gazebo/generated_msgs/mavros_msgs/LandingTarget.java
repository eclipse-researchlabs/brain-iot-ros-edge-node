package mavros_msgs;

public interface LandingTarget extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LandingTarget";
  static final java.lang.String _DEFINITION = "# MAVLink message: LANDING_TARGET\n# https://mavlink.io/en/messages/common.html\n\nstd_msgs/Header header\n\n## MAV_FRAME enum\nuint8 GLOBAL = 0                   # Global coordinate frame, WGS84 coordinate system. First value / x: latitude, second value / y: longitude, third value / z: positive altitude over mean sea level (MSL)\nuint8 LOCAL_NED = 2                # Local coordinate frame, Z-up (x: north, y: east, z: down).\nuint8 MISSION = 3                  # NOT a coordinate frame, indicates a mission command.\nuint8 GLOBAL_RELATIVE_ALT = 4      # Global coordinate frame, WGS84 coordinate system, relative altitude over ground with respect to the home position. First value / x: latitude, second value / y: longitude, third value / z: positive altitude with 0 being at the altitude of the home location.\nuint8 LOCAL_ENU = 5                # Local coordinate frame, Z-down (x: east, y: north, z: up)\nuint8 GLOBAL_INT = 6               # Global coordinate frame, WGS84 coordinate system. First value / x: latitude in degrees*1.0e-7, second value / y: longitude in degrees*1.0e-7, third value / z: positive altitude over mean sea level (MSL)\nuint8 GLOBAL_RELATIVE_ALT_INT = 7  # Global coordinate frame, WGS84 coordinate system, relative altitude over ground with respect to the home position. First value / x: latitude in degrees*10e-7, second value / y: longitude in degrees*10e-7, third value / z: positive altitude with 0 being at the altitude of the home location.\nuint8 LOCAL_OFFSET_NED = 8         # Offset to the current local frame. Anything expressed in this frame should be added to the current local frame position.\nuint8 BODY_NED = 9                 # Setpoint in body NED frame. This makes sense if all position control is externalized - e.g. useful to command 2 m/s^2 acceleration to the right.\nuint8 BODY_OFFSET_NED = 10         # Offset in body NED frame. This makes sense if adding setpoints to the current flight path, to avoid an obstacle - e.g. useful to command 2 m/s^2 acceleration to the east.\nuint8 GLOBAL_TERRAIN_ALT = 11      # Global coordinate frame with above terrain level altitude. WGS84 coordinate system, relative altitude over terrain with respect to the waypoint coordinate. First value / x: latitude in degrees, second value / y: longitude in degrees, third value / z: positive altitude in meters with 0 being at ground level in terrain model.\nuint8 GLOBAL_TERRAIN_ALT_INT = 12  # Global coordinate frame with above terrain level altitude. WGS84 coordinate system, relative altitude over terrain with respect to the waypoint coordinate. First value / x: latitude in degrees*10e-7, second value / y: longitude in degrees*10e-7, third value / z: positive altitude in meters with 0 being at ground level in terrain model.\n\n## LANDING_TARGET_TYPE enum\nuint8 LIGHT_BEACON = 0             # Landing target signaled by light beacon (ex: IR-LOCK)\nuint8 RADIO_BEACON = 1             # Landing target signaled by radio beacon (ex: ILS, NDB)\nuint8 VISION_FIDUCIAL = 2          # Landing target represented by a fiducial marker (ex: ARTag)\nuint8 VISION_OTHER = 3             # Landing target represented by a pre-defined visual shape/feature (ex: X-marker, H-marker, square)\n\nuint8 target_num\nuint8 frame\nfloat32[2] angle\nfloat32 distance\nfloat32[2] size\ngeometry_msgs/Pose pose\nuint8 type\n";
  static final byte GLOBAL = 0;
  static final byte LOCAL_NED = 2;
  static final byte MISSION = 3;
  static final byte GLOBAL_RELATIVE_ALT = 4;
  static final byte LOCAL_ENU = 5;
  static final byte GLOBAL_INT = 6;
  static final byte GLOBAL_RELATIVE_ALT_INT = 7;
  static final byte LOCAL_OFFSET_NED = 8;
  static final byte BODY_NED = 9;
  static final byte BODY_OFFSET_NED = 10;
  static final byte GLOBAL_TERRAIN_ALT = 11;
  static final byte GLOBAL_TERRAIN_ALT_INT = 12;
  static final byte LIGHT_BEACON = 0;
  static final byte RADIO_BEACON = 1;
  static final byte VISION_FIDUCIAL = 2;
  static final byte VISION_OTHER = 3;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getTargetNum();
  void setTargetNum(byte value);
  byte getFrame();
  void setFrame(byte value);
  float[] getAngle();
  void setAngle(float[] value);
  float getDistance();
  void setDistance(float value);
  float[] getSize();
  void setSize(float[] value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
  byte getType();
  void setType(byte value);
}
