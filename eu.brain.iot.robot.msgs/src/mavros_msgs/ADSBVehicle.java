package mavros_msgs;

public interface ADSBVehicle extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/ADSBVehicle";
  static final java.lang.String _DEFINITION = "# The location and information of an ADSB vehicle\n#\n# https://mavlink.io/en/messages/common.html#ADSB_VEHICLE\n\n# [[[cog:\n# from pymavlink.dialects.v20 import common\n#\n# def decl_enum(ename, pfx=\'\', bsz=8):\n#     enum = sorted(common.enums[ename].items())\n#     enum.pop() # remove ENUM_END\n#\n#     cog.outl(\"# \" + ename)\n#     for k, e in enum:\n#         sn = e.name[len(ename) + 1:]\n#         l = \"uint{bsz} {pfx}{sn} = {k}\".format(**locals())\n#         if e.description:\n#             l += \' \' * (40 - len(l)) + \' # \' + e.description\n#         cog.outl(l)\n#\n# decl_enum(\'ADSB_ALTITUDE_TYPE\', \'ALT_\')\n# decl_enum(\'ADSB_EMITTER_TYPE\', \'EMITTER_\')\n# decl_enum(\'ADSB_FLAGS\', \'FLAG_\', 16)\n# ]]]\n# ADSB_ALTITUDE_TYPE\nuint8 ALT_PRESSURE_QNH = 0               # Altitude reported from a Baro source using QNH reference\nuint8 ALT_GEOMETRIC = 1                  # Altitude reported from a GNSS source\n# ADSB_EMITTER_TYPE\nuint8 EMITTER_NO_INFO = 0\nuint8 EMITTER_LIGHT = 1\nuint8 EMITTER_SMALL = 2\nuint8 EMITTER_LARGE = 3\nuint8 EMITTER_HIGH_VORTEX_LARGE = 4\nuint8 EMITTER_HEAVY = 5\nuint8 EMITTER_HIGHLY_MANUV = 6\nuint8 EMITTER_ROTOCRAFT = 7\nuint8 EMITTER_UNASSIGNED = 8\nuint8 EMITTER_GLIDER = 9\nuint8 EMITTER_LIGHTER_AIR = 10\nuint8 EMITTER_PARACHUTE = 11\nuint8 EMITTER_ULTRA_LIGHT = 12\nuint8 EMITTER_UNASSIGNED2 = 13\nuint8 EMITTER_UAV = 14\nuint8 EMITTER_SPACE = 15\nuint8 EMITTER_UNASSGINED3 = 16\nuint8 EMITTER_EMERGENCY_SURFACE = 17\nuint8 EMITTER_SERVICE_SURFACE = 18\nuint8 EMITTER_POINT_OBSTACLE = 19\n# ADSB_FLAGS\nuint16 FLAG_VALID_COORDS = 1\nuint16 FLAG_VALID_ALTITUDE = 2\nuint16 FLAG_VALID_HEADING = 4\nuint16 FLAG_VALID_VELOCITY = 8\nuint16 FLAG_VALID_CALLSIGN = 16\nuint16 FLAG_VALID_SQUAWK = 32\nuint16 FLAG_SIMULATED = 64\n# [[[end]]] (checksum: e35e77be43548bd572a3cb24138fc2fd)\n\nstd_msgs/Header header\n\nuint32 ICAO_address\nstring callsign\n\nfloat64 latitude\nfloat64 longitude\nfloat32 altitude \t# AMSL\n\nfloat32 heading\t\t# deg [0..360)\nfloat32 hor_velocity\t# m/s\nfloat32 ver_velocity\t# m/s\n\nuint8 altitude_type\t# Type from ADSB_ALTITUDE_TYPE enum\nuint8 emitter_type\t# Type from ADSB_EMITTER_TYPE enum\n\nduration tslc\t\t# Duration from last communication, seconds [0..255]\nuint16 flags\t\t# ADSB_FLAGS bit field\nuint16 squawk\t\t# Squawk code\n";
  static final byte ALT_PRESSURE_QNH = 0;
  static final byte ALT_GEOMETRIC = 1;
  static final byte EMITTER_NO_INFO = 0;
  static final byte EMITTER_LIGHT = 1;
  static final byte EMITTER_SMALL = 2;
  static final byte EMITTER_LARGE = 3;
  static final byte EMITTER_HIGH_VORTEX_LARGE = 4;
  static final byte EMITTER_HEAVY = 5;
  static final byte EMITTER_HIGHLY_MANUV = 6;
  static final byte EMITTER_ROTOCRAFT = 7;
  static final byte EMITTER_UNASSIGNED = 8;
  static final byte EMITTER_GLIDER = 9;
  static final byte EMITTER_LIGHTER_AIR = 10;
  static final byte EMITTER_PARACHUTE = 11;
  static final byte EMITTER_ULTRA_LIGHT = 12;
  static final byte EMITTER_UNASSIGNED2 = 13;
  static final byte EMITTER_UAV = 14;
  static final byte EMITTER_SPACE = 15;
  static final byte EMITTER_UNASSGINED3 = 16;
  static final byte EMITTER_EMERGENCY_SURFACE = 17;
  static final byte EMITTER_SERVICE_SURFACE = 18;
  static final byte EMITTER_POINT_OBSTACLE = 19;
  static final short FLAG_VALID_COORDS = 1;
  static final short FLAG_VALID_ALTITUDE = 2;
  static final short FLAG_VALID_HEADING = 4;
  static final short FLAG_VALID_VELOCITY = 8;
  static final short FLAG_VALID_CALLSIGN = 16;
  static final short FLAG_VALID_SQUAWK = 32;
  static final short FLAG_SIMULATED = 64;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getICAOAddress();
  void setICAOAddress(int value);
  java.lang.String getCallsign();
  void setCallsign(java.lang.String value);
  double getLatitude();
  void setLatitude(double value);
  double getLongitude();
  void setLongitude(double value);
  float getAltitude();
  void setAltitude(float value);
  float getHeading();
  void setHeading(float value);
  float getHorVelocity();
  void setHorVelocity(float value);
  float getVerVelocity();
  void setVerVelocity(float value);
  byte getAltitudeType();
  void setAltitudeType(byte value);
  byte getEmitterType();
  void setEmitterType(byte value);
  org.ros.message.Duration getTslc();
  void setTslc(org.ros.message.Duration value);
  short getFlags();
  void setFlags(short value);
  short getSquawk();
  void setSquawk(short value);
}
