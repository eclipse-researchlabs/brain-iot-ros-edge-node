package robotnik_msgs;

public interface MotorPID extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/MotorPID";
  static final java.lang.String _DEFINITION = "# either can_id or name are set\n# if can_id is -1, then this refers to all motors.\nint32[] can_id\nstring[] name\nfloat32[] kp\nfloat32[] ki\nfloat32[] kd\n";
  int[] getCanId();
  void setCanId(int[] value);
  java.util.List<java.lang.String> getName();
  void setName(java.util.List<java.lang.String> value);
  float[] getKp();
  void setKp(float[] value);
  float[] getKi();
  void setKi(float[] value);
  float[] getKd();
  void setKd(float[] value);
}
