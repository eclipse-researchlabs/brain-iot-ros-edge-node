package robotnik_base_hw_sim;

public interface PickResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PickResponse";
  static final java.lang.String _DEFINITION = "bool success\nstring msg";
  boolean getSuccess();
  void setSuccess(boolean value);
  java.lang.String getMsg();
  void setMsg(java.lang.String value);
}
