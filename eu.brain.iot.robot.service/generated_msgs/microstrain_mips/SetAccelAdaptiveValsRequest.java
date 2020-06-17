package microstrain_mips;

public interface SetAccelAdaptiveValsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetAccelAdaptiveValsRequest";
  static final java.lang.String _DEFINITION = "float32 enable\nfloat32 low_pass_cutoff\nfloat32 min_1sigma\nfloat32 low_limit\nfloat32 high_limit\nfloat32 low_limit_1sigma\nfloat32 high_limit_1sigma\n";
  float getEnable();
  void setEnable(float value);
  float getLowPassCutoff();
  void setLowPassCutoff(float value);
  float getMin1sigma();
  void setMin1sigma(float value);
  float getLowLimit();
  void setLowLimit(float value);
  float getHighLimit();
  void setHighLimit(float value);
  float getLowLimit1sigma();
  void setLowLimit1sigma(float value);
  float getHighLimit1sigma();
  void setHighLimit1sigma(float value);
}
