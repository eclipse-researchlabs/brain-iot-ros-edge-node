package microstrain_mips;

public interface SetComplementaryFilterRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetComplementaryFilterRequest";
  static final java.lang.String _DEFINITION = "int8 north_comp_enable\nint8 up_comp_enable\nfloat32 north_comp_time_const\nfloat32 up_comp_time_const\n\n";
  byte getNorthCompEnable();
  void setNorthCompEnable(byte value);
  byte getUpCompEnable();
  void setUpCompEnable(byte value);
  float getNorthCompTimeConst();
  void setNorthCompTimeConst(float value);
  float getUpCompTimeConst();
  void setUpCompTimeConst(float value);
}
