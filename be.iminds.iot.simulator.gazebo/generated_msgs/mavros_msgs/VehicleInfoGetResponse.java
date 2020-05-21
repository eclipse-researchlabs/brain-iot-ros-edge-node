package mavros_msgs;

public interface VehicleInfoGetResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/VehicleInfoGetResponse";
  static final java.lang.String _DEFINITION = "bool success\nmavros_msgs/VehicleInfo[] vehicles";
  boolean getSuccess();
  void setSuccess(boolean value);
  java.util.List<mavros_msgs.VehicleInfo> getVehicles();
  void setVehicles(java.util.List<mavros_msgs.VehicleInfo> value);
}
