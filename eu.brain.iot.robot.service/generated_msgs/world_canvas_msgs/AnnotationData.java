package world_canvas_msgs;

public interface AnnotationData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "world_canvas_msgs/AnnotationData";
  static final java.lang.String _DEFINITION = "# Data for an element in a semantic map stored as a byte array generated by ros::serialization\n# These objects are unique, although they can be referenced by one or more annotations\n#  - id   : Object unique id; data_id field on Annotation messages point to this uuid\n#  - type : Object type; duplicated from annotation for convenience on deserialization\n#  - data : Serialized data\nuuid_msgs/UniqueID id\nstring type\nuint8[] data\n";
  uuid_msgs.UniqueID getId();
  void setId(uuid_msgs.UniqueID value);
  java.lang.String getType();
  void setType(java.lang.String value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
