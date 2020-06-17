package mavros_msgs;

public interface RTCM extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/RTCM";
  static final java.lang.String _DEFINITION = "# RTCM message for the gps_rtk plugin\n# The gps_rtk plugin will fragment the data if necessary and \n# forward it to the FCU via Mavlink through the available link.\n# data should be <= 4*180, higher will be discarded.\nstd_msgs/Header header\nuint8[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
