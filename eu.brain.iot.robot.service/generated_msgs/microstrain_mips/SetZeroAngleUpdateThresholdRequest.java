package microstrain_mips;

public interface SetZeroAngleUpdateThresholdRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetZeroAngleUpdateThresholdRequest";
  static final java.lang.String _DEFINITION = "int8 enable\nfloat32 threshold\n";
  byte getEnable();
  void setEnable(byte value);
  float getThreshold();
  void setThreshold(float value);
}
