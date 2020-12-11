package mavros_msgs;

public interface GPSRTK extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/GPSRTK";
  static final java.lang.String _DEFINITION = "# FCU GPS RTK message for the gps_status plugin\n# A copy of <a href=\"https://mavlink.io/en/messages/common.html#GPS_RTK\">mavlink GPS_RTK message</a>\n\nstd_msgs/Header header\n\nuint8 rtk_receiver_id      # Identification of connected RTK receiver.\nint16 wn                   # GPS Week Number of last baseline.\nuint32 tow                 # [ms] GPS Time of Week of last baseline.\nuint8 rtk_health           # GPS-specific health report for RTK data.\nuint8 rtk_rate             # [Hz] Rate of baseline messages being received by GPS.\nuint8 nsats                # Current number of sats used for RTK calculation.\nint32 baseline_a           # [mm] Current baseline in ECEF x or NED north component, depends on header.frame_id.\nint32 baseline_b           # [mm] Current baseline in ECEF y or NED east component, depends on header.frame_id.\nint32 baseline_c           # [mm] Current baseline in ECEF z or NED down component, depends on header.frame_id.\nuint32 accuracy            # Current estimate of baseline accuracy.\nint32 iar_num_hypotheses   # Current number of integer ambiguity hypotheses.\n\n\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
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
  int getBaselineA();
  void setBaselineA(int value);
  int getBaselineB();
  void setBaselineB(int value);
  int getBaselineC();
  void setBaselineC(int value);
  int getAccuracy();
  void setAccuracy(int value);
  int getIarNumHypotheses();
  void setIarNumHypotheses(int value);
}
