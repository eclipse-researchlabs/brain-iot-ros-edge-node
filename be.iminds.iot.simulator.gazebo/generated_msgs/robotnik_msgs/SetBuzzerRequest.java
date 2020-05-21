package robotnik_msgs;

public interface SetBuzzerRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SetBuzzerRequest";
  static final java.lang.String _DEFINITION = "bool enable\nfloat64 beep_freq\nfloat64 time_enabled\n";
  boolean getEnable();
  void setEnable(boolean value);
  double getBeepFreq();
  void setBeepFreq(double value);
  double getTimeEnabled();
  void setTimeEnabled(double value);
}
