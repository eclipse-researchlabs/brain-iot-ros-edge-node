package microstrain_mips;

public interface SetSensorVehicleFrameOffsetRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetSensorVehicleFrameOffsetRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 offset\n";
  geometry_msgs.Vector3 getOffset();
  void setOffset(geometry_msgs.Vector3 value);
}
