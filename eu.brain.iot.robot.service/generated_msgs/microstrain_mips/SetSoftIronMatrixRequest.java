package microstrain_mips;

public interface SetSoftIronMatrixRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetSoftIronMatrixRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 soft_iron_1\ngeometry_msgs/Vector3 soft_iron_2\ngeometry_msgs/Vector3 soft_iron_3\n";
  geometry_msgs.Vector3 getSoftIron1();
  void setSoftIron1(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getSoftIron2();
  void setSoftIron2(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getSoftIron3();
  void setSoftIron3(geometry_msgs.Vector3 value);
}
