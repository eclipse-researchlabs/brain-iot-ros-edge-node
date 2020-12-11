package microstrain_mips;

public interface SetMagDipAdaptiveValsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetMagDipAdaptiveValsRequest";
  static final java.lang.String _DEFINITION = "float32 enable\nfloat32 low_pass_cutoff\nfloat32 min_1sigma\nfloat32 high_limit\nfloat32 high_limit_1sigma\n";
  float getEnable();
  void setEnable(float value);
  float getLowPassCutoff();
  void setLowPassCutoff(float value);
  float getMin1sigma();
  void setMin1sigma(float value);
  float getHighLimit();
  void setHighLimit(float value);
  float getHighLimit1sigma();
  void setHighLimit1sigma(float value);
}
