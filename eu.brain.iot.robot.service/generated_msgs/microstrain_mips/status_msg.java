package microstrain_mips;

public interface status_msg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "microstrain_mips/status_msg";
  static final java.lang.String _DEFINITION = "uint16 device_model\nuint8  status_selector\n\nuint32 status_flags\n\nuint16 system_state\nuint32 system_timer_ms\n\nuint8 gps_power_on\n\nuint32 num_gps_pps_triggers\nuint32 last_gps_pps_trigger_ms\n\nuint8 imu_stream_enabled\nuint8 gps_stream_enabled\nuint8 filter_stream_enabled\n\nuint32 imu_dropped_packets\nuint32 gps_dropped_packets\nuint32 filter_dropped_packets\n\nuint32 com1_port_bytes_written\nuint32 com1_port_bytes_read\nuint32 com1_port_write_overruns\nuint32 com1_port_read_overruns\n\nuint32 imu_parser_errors\nuint32 imu_message_count\nuint32 imu_last_message_ms\n\nuint32 gps_parser_errors\nuint32 gps_message_count\nuint32 gps_last_message_ms\n";
  short getDeviceModel();
  void setDeviceModel(short value);
  byte getStatusSelector();
  void setStatusSelector(byte value);
  int getStatusFlags();
  void setStatusFlags(int value);
  short getSystemState();
  void setSystemState(short value);
  int getSystemTimerMs();
  void setSystemTimerMs(int value);
  byte getGpsPowerOn();
  void setGpsPowerOn(byte value);
  int getNumGpsPpsTriggers();
  void setNumGpsPpsTriggers(int value);
  int getLastGpsPpsTriggerMs();
  void setLastGpsPpsTriggerMs(int value);
  byte getImuStreamEnabled();
  void setImuStreamEnabled(byte value);
  byte getGpsStreamEnabled();
  void setGpsStreamEnabled(byte value);
  byte getFilterStreamEnabled();
  void setFilterStreamEnabled(byte value);
  int getImuDroppedPackets();
  void setImuDroppedPackets(int value);
  int getGpsDroppedPackets();
  void setGpsDroppedPackets(int value);
  int getFilterDroppedPackets();
  void setFilterDroppedPackets(int value);
  int getCom1PortBytesWritten();
  void setCom1PortBytesWritten(int value);
  int getCom1PortBytesRead();
  void setCom1PortBytesRead(int value);
  int getCom1PortWriteOverruns();
  void setCom1PortWriteOverruns(int value);
  int getCom1PortReadOverruns();
  void setCom1PortReadOverruns(int value);
  int getImuParserErrors();
  void setImuParserErrors(int value);
  int getImuMessageCount();
  void setImuMessageCount(int value);
  int getImuLastMessageMs();
  void setImuLastMessageMs(int value);
  int getGpsParserErrors();
  void setGpsParserErrors(int value);
  int getGpsMessageCount();
  void setGpsMessageCount(int value);
  int getGpsLastMessageMs();
  void setGpsLastMessageMs(int value);
}
