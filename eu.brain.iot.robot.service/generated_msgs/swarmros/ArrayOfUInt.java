package swarmros;

public interface ArrayOfUInt extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ArrayOfUInt";
  static final java.lang.String _DEFINITION = "uint64[] values";
  long[] getValues();
  void setValues(long[] value);
}
