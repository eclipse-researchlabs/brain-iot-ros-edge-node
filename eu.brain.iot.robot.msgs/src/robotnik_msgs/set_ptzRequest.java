package robotnik_msgs;

public interface set_ptzRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/set_ptzRequest";
  static final java.lang.String _DEFINITION = "# PAN value\nfloat32 pan\n# Tilt value\nfloat32 tilt\n# Zoom value\nfloat32 zoom\n# Flag for relative  movements\nbool relative\n";
  float getPan();
  void setPan(float value);
  float getTilt();
  void setTilt(float value);
  float getZoom();
  void setZoom(float value);
  boolean getRelative();
  void setRelative(boolean value);
}
