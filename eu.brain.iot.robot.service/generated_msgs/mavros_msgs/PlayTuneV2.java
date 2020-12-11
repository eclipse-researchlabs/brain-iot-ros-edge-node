package mavros_msgs;

public interface PlayTuneV2 extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/PlayTuneV2";
  static final java.lang.String _DEFINITION = "# Play tune V2\n#\n# https://mavlink.io/en/messages/common.html#PLAY_TUNE_V2\n\n## TUNE_FORMAT enum\nuint8 QBASIC1_1 = 1\nuint8 MML_MODERN = 2\n\nuint8 format\nstring tune\n";
  static final byte QBASIC1_1 = 1;
  static final byte MML_MODERN = 2;
  byte getFormat();
  void setFormat(byte value);
  java.lang.String getTune();
  void setTune(java.lang.String value);
}
