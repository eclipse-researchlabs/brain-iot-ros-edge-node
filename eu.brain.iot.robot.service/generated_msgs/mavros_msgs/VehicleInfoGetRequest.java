package mavros_msgs;

public interface VehicleInfoGetRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/VehicleInfoGetRequest";
  static final java.lang.String _DEFINITION = "# Request the Vehicle Info\n# use this to request the current target sysid / compid defined in mavros\n# set get_all = True to request all available vehicles\n\nuint8 GET_MY_SYSID = 0\nuint8 GET_MY_COMPID = 0\n\nuint8 sysid\nuint8 compid\nbool get_all\n";
  static final byte GET_MY_SYSID = 0;
  static final byte GET_MY_COMPID = 0;
  byte getSysid();
  void setSysid(byte value);
  byte getCompid();
  void setCompid(byte value);
  boolean getGetAll();
  void setGetAll(boolean value);
}
