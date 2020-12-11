package microstrain_mips;

public interface SetDynamicsModeRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetDynamicsModeRequest";
  static final java.lang.String _DEFINITION = "int8 PORTABLE=1\nint8 AUTOMOTIVE=2\nint8 AIRBORNE=3\nint8 mode\n";
  static final byte PORTABLE = 1;
  static final byte AUTOMOTIVE = 2;
  static final byte AIRBORNE = 3;
  byte getMode();
  void setMode(byte value);
}
