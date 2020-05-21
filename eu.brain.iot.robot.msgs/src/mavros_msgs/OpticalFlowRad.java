package mavros_msgs;

public interface OpticalFlowRad extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/OpticalFlowRad";
  static final java.lang.String _DEFINITION = "# OPTICAL_FLOW_RAD message data\n\nstd_msgs/Header header\n\nuint32 integration_time_us\nfloat32 integrated_x\nfloat32 integrated_y\nfloat32 integrated_xgyro\nfloat32 integrated_ygyro\nfloat32 integrated_zgyro\nint16 temperature\nuint8 quality\nuint32 time_delta_distance_us\nfloat32 distance\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getIntegrationTimeUs();
  void setIntegrationTimeUs(int value);
  float getIntegratedX();
  void setIntegratedX(float value);
  float getIntegratedY();
  void setIntegratedY(float value);
  float getIntegratedXgyro();
  void setIntegratedXgyro(float value);
  float getIntegratedYgyro();
  void setIntegratedYgyro(float value);
  float getIntegratedZgyro();
  void setIntegratedZgyro(float value);
  short getTemperature();
  void setTemperature(short value);
  byte getQuality();
  void setQuality(byte value);
  int getTimeDeltaDistanceUs();
  void setTimeDeltaDistanceUs(int value);
  float getDistance();
  void setDistance(float value);
}
