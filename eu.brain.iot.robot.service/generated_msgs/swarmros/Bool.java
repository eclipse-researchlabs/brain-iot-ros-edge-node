package swarmros;

public interface Bool extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/Bool";
  static final java.lang.String _DEFINITION = "bool value";
  boolean getValue();
  void setValue(boolean value);
}
