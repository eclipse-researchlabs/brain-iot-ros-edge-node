package robotnik_msgs;

public interface SetMotorPIDRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/SetMotorPIDRequest";
  static final java.lang.String _DEFINITION = "MotorPID pid\n";
  robotnik_msgs.MotorPID getPid();
  void setPid(robotnik_msgs.MotorPID value);
}
