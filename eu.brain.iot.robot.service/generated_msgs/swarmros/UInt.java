package swarmros;

public interface UInt extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/UInt";
  static final java.lang.String _DEFINITION = "uint64 value";
  long getValue();
  void setValue(long value);
}
