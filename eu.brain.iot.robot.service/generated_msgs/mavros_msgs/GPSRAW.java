package mavros_msgs;

public interface GPSRAW extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/GPSRAW";
  static final java.lang.String _DEFINITION = "# FCU GPS RAW message for the gps_status plugin\n# A merge of <a href=\"https://mavlink.io/en/messages/common.html#GPS_RAW_INT\">mavlink GPS_RAW_INT</a> and \n# <a href=\"https://mavlink.io/en/messages/common.html#GPS2_RAW\">mavlink GPS2_RAW</a> messages.\n\nstd_msgs/Header header\n## GPS_FIX_TYPE enum\nuint8 GPS_FIX_TYPE_NO_GPS     = 0    # No GPS connected\nuint8 GPS_FIX_TYPE_NO_FIX     = 1    # No position information, GPS is connected\nuint8 GPS_FIX_TYPE_2D_FIX     = 2    # 2D position\nuint8 GPS_FIX_TYPE_3D_FIX     = 3    # 3D position\nuint8 GPS_FIX_TYPE_DGPS       = 4    # DGPS/SBAS aided 3D position\nuint8 GPS_FIX_TYPE_RTK_FLOATR = 5    # TK float, 3D position\nuint8 GPS_FIX_TYPE_RTK_FIXEDR = 6    # TK Fixed, 3D position\nuint8 GPS_FIX_TYPE_STATIC     = 7    # Static fixed, typically used for base stations\nuint8 GPS_FIX_TYPE_PPP        = 8    # PPP, 3D position\nuint8 fix_type      # [GPS_FIX_TYPE] GPS fix type\n\nint32 lat           # [degE7] Latitude (WGS84, EGM96 ellipsoid)\nint32 lon           # [degE7] Longitude (WGS84, EGM96 ellipsoid)\nint32 alt           # [mm] Altitude (MSL). Positive for up. Note that virtually all GPS modules provide the MSL altitude in addition to the WGS84 altitude.\nuint16 eph          # GPS HDOP horizontal dilution of position (unitless). If unknown, set to: UINT16_MAX\nuint16 epv          # GPS VDOP vertical dilution of position (unitless). If unknown, set to: UINT16_MAX\nuint16 vel          # [cm/s] GPS ground speed. If unknown, set to: UINT16_MAX\nuint16 cog          # [cdeg] Course over ground (NOT heading, but direction of movement), 0.0..359.99 degrees. If unknown, set to: UINT16_MAX\nuint8 satellites_visible # Number of satellites visible. If unknown, set to 255\n\n# -*- only available with MAVLink v2.0 and GPS_RAW_INT messages -*-\nint32 alt_ellipsoid # [mm] Altitude (above WGS84, EGM96 ellipsoid). Positive for up.\nuint32 h_acc        # [mm] Position uncertainty. Positive for up.\nuint32 v_acc        # [mm] Altitude uncertainty. Positive for up.\nuint32 vel_acc      # [mm] Speed uncertainty. Positive for up.\nint32  hdg_acc      # [degE5] Heading / track uncertainty\n\n# -*- only available with MAVLink v2.0 and GPS2_RAW messages -*-\nuint8 dgps_numch    # Number of DGPS satellites\nuint32 dgps_age     # [ms] Age of DGPS info\n";
  static final byte GPS_FIX_TYPE_NO_GPS = 0;
  static final byte GPS_FIX_TYPE_NO_FIX = 1;
  static final byte GPS_FIX_TYPE_2D_FIX = 2;
  static final byte GPS_FIX_TYPE_3D_FIX = 3;
  static final byte GPS_FIX_TYPE_DGPS = 4;
  static final byte GPS_FIX_TYPE_RTK_FLOATR = 5;
  static final byte GPS_FIX_TYPE_RTK_FIXEDR = 6;
  static final byte GPS_FIX_TYPE_STATIC = 7;
  static final byte GPS_FIX_TYPE_PPP = 8;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getFixType();
  void setFixType(byte value);
  int getLat();
  void setLat(int value);
  int getLon();
  void setLon(int value);
  int getAlt();
  void setAlt(int value);
  short getEph();
  void setEph(short value);
  short getEpv();
  void setEpv(short value);
  short getVel();
  void setVel(short value);
  short getCog();
  void setCog(short value);
  byte getSatellitesVisible();
  void setSatellitesVisible(byte value);
  int getAltEllipsoid();
  void setAltEllipsoid(int value);
  int getHAcc();
  void setHAcc(int value);
  int getVAcc();
  void setVAcc(int value);
  int getVelAcc();
  void setVelAcc(int value);
  int getHdgAcc();
  void setHdgAcc(int value);
  byte getDgpsNumch();
  void setDgpsNumch(byte value);
  int getDgpsAge();
  void setDgpsAge(int value);
}
