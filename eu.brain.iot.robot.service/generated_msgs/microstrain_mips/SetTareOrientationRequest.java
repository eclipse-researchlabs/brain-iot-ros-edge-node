package microstrain_mips;

public interface SetTareOrientationRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetTareOrientationRequest";
  static final java.lang.String _DEFINITION = "int8 axis\n";
  byte getAxis();
  void setAxis(byte value);
}
