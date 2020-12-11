package swarmros;

public interface NodeInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/NodeInfo";
  static final java.lang.String _DEFINITION = "string uuid        # UUID of the node\nstring name        # Possible non-unique name of the node\nstring deviceClass # Device class\nbool online        # True if the node is considered online";
  java.lang.String getUuid();
  void setUuid(java.lang.String value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getDeviceClass();
  void setDeviceClass(java.lang.String value);
  boolean getOnline();
  void setOnline(boolean value);
}
