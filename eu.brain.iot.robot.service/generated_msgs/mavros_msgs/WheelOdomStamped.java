package mavros_msgs;

public interface WheelOdomStamped extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/WheelOdomStamped";
  static final java.lang.String _DEFINITION = "# Stamped wheel odometry message\n#\n# For streaming timestamped data from FCU wheel encoders (RPM or WHEEL_DISTANCE).\n\nstd_msgs/Header header\nfloat64[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  double[] getData();
  void setData(double[] value);
}
