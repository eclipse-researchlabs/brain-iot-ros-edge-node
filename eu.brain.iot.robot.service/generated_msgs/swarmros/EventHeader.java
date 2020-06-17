package swarmros;

public interface EventHeader extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/EventHeader";
  static final java.lang.String _DEFINITION = "string name # Name of the event\nstring node # UUID of the source or target node";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getNode();
  void setNode(java.lang.String value);
}
