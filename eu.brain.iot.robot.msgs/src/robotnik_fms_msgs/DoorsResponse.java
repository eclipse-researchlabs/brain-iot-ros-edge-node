package robotnik_fms_msgs;

public interface DoorsResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/DoorsResponse";
  static final java.lang.String _DEFINITION = "\nbool success\nbool error\nstring msg";
  boolean getSuccess();
  void setSuccess(boolean value);
  boolean getError();
  void setError(boolean value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
}
