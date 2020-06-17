package swarmros;

public interface ArrayOfBool extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ArrayOfBool";
  static final java.lang.String _DEFINITION = "bool[] values";
  boolean[] getValues();
  void setValues(boolean[] value);
}
