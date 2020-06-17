package mavros_msgs;

public interface Param extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/Param";
  static final java.lang.String _DEFINITION = "# Parameter msg.\n\nstd_msgs/Header header\n\nstring param_id\nmavros_msgs/ParamValue value\n\nuint16 param_index\nuint16 param_count\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.lang.String getParamId();
  void setParamId(java.lang.String value);
  mavros_msgs.ParamValue getValue();
  void setValue(mavros_msgs.ParamValue value);
  short getParamIndex();
  void setParamIndex(short value);
  short getParamCount();
  void setParamCount(short value);
}
