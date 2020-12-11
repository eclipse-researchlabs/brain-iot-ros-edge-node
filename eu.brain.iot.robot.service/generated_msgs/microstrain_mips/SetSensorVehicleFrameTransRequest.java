package microstrain_mips;

public interface SetSensorVehicleFrameTransRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/SetSensorVehicleFrameTransRequest";
  static final java.lang.String _DEFINITION = "geometry_msgs/Vector3 angle\n";
  geometry_msgs.Vector3 getAngle();
  void setAngle(geometry_msgs.Vector3 value);
}
