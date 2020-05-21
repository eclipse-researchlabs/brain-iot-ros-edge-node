package mavros_msgs;

public interface TimesyncStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/TimesyncStatus";
  static final java.lang.String _DEFINITION = "# Status of the MAVLink time synchroniser\n\nstd_msgs/Header header\nuint64 remote_timestamp_ns\t\t# remote system timestamp (nanoseconds)\nint64 observed_offset_ns\t\t# raw time offset directly observed from this timesync packet (nanoseconds)\nint64 estimated_offset_ns\t\t# smoothed time offset between companion system and Mavros (nanoseconds)\nfloat32 round_trip_time_ms\t\t# round trip time of this timesync packet (milliseconds)";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  long getRemoteTimestampNs();
  void setRemoteTimestampNs(long value);
  long getObservedOffsetNs();
  void setObservedOffsetNs(long value);
  long getEstimatedOffsetNs();
  void setEstimatedOffsetNs(long value);
  float getRoundTripTimeMs();
  void setRoundTripTimeMs(float value);
}
