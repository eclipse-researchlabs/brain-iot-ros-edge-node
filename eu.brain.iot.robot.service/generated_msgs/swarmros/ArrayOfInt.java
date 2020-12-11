package swarmros;

public interface ArrayOfInt extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ArrayOfInt";
  static final java.lang.String _DEFINITION = "int64[] values";
  long[] getValues();
  void setValues(long[] value);
}
