package microstrain_mips;

public interface SetEstimationControlFlagsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetEstimationControlFlagsRequest";
  static final java.lang.String _DEFINITION = "int8 GYRO_BIAS_ESTIMATION=1\nint8 HARD_IRON_AUTO_CALIBRATION=32\nint8 SOFT_IRON_AUTO_CALIBRATION=64\nint8 flag\n";
  static final byte GYRO_BIAS_ESTIMATION = 1;
  static final byte HARD_IRON_AUTO_CALIBRATION = 32;
  static final byte SOFT_IRON_AUTO_CALIBRATION = 64;
  byte getFlag();
  void setFlag(byte value);
}
