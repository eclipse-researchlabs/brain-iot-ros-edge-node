package swarmros;

public interface ArrayOfString extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ArrayOfString";
  static final java.lang.String _DEFINITION = "string[] values";
  java.util.List<java.lang.String> getValues();
  void setValues(java.util.List<java.lang.String> value);
}
