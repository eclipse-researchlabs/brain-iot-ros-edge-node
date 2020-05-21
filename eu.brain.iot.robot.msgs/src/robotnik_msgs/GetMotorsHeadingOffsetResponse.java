package robotnik_msgs;

public interface GetMotorsHeadingOffsetResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/GetMotorsHeadingOffsetResponse";
  static final java.lang.String _DEFINITION = "robotnik_msgs/MotorHeadingOffset[] offsets";
  java.util.List<robotnik_msgs.MotorHeadingOffset> getOffsets();
  void setOffsets(java.util.List<robotnik_msgs.MotorHeadingOffset> value);
}
