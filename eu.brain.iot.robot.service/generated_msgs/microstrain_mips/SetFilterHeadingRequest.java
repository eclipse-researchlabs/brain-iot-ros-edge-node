package microstrain_mips;

public interface SetFilterHeadingRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetFilterHeadingRequest";
  static final java.lang.String _DEFINITION = "float32 angle\n";
  float getAngle();
  void setAngle(float value);
}
