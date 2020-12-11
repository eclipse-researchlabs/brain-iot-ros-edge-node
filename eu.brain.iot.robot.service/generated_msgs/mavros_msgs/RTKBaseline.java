package mavros_msgs;

public interface RTKBaseline extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/RTKBaseline";
  static final java.lang.String _DEFINITION = "# RTKBaseline received from the FCU.\n# Full description: https://mavlink.io/en/messages/common.html#GPS_RTK\n# Mavlink Common, #127and #128\n\nstd_msgs/Header header\n\nuint32 time_last_baseline_ms\nuint8 rtk_receiver_id\nuint16 wn\nuint32 tow\nuint8 rtk_health\nuint8 rtk_rate\nuint8 nsats\n\nuint8 baseline_coords_type \nuint8 RTK_BASELINE_COORDINATE_SYSTEM_ECEF = 0   # Earth-centered, earth-fixed\nuint8 RTK_BASELINE_COORDINATE_SYSTEM_NED = 1    # RTK basestation centered, north, east, down\n\nint32 baseline_a_mm\nint32 baseline_b_mm\nint32 baseline_c_mm\nuint32 accuracy\nint32 iar_num_hypotheses\n";
  static final byte RTK_BASELINE_COORDINATE_SYSTEM_ECEF = 0;
  static final byte RTK_BASELINE_COORDINATE_SYSTEM_NED = 1;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getTimeLastBaselineMs();
  void setTimeLastBaselineMs(int value);
  byte getRtkReceiverId();
  void setRtkReceiverId(byte value);
  short getWn();
  void setWn(short value);
  int getTow();
  void setTow(int value);
  byte getRtkHealth();
  void setRtkHealth(byte value);
  byte getRtkRate();
  void setRtkRate(byte value);
  byte getNsats();
  void setNsats(byte value);
  byte getBaselineCoordsType();
  void setBaselineCoordsType(byte value);
  int getBaselineAMm();
  void setBaselineAMm(int value);
  int getBaselineBMm();
  void setBaselineBMm(int value);
  int getBaselineCMm();
  void setBaselineCMm(int value);
  int getAccuracy();
  void setAccuracy(int value);
  int getIarNumHypotheses();
  void setIarNumHypotheses(int value);
}
