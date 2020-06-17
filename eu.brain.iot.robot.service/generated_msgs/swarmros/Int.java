package swarmros;

public interface Int extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/Int";
  static final java.lang.String _DEFINITION = "int64 value";
  long getValue();
  void setValue(long value);
}
