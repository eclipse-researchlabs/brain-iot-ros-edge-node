package system_monitor;

public interface Disk extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/Disk";
  static final java.lang.String _DEFINITION = "int8 id\nstring name\nstring status\nstring mount_point\n#Size of the disk in G\nfloat32 size\nfloat32 available\n#% of total size used\nfloat32 use\n";
  byte getId();
  void setId(byte value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  java.lang.String getMountPoint();
  void setMountPoint(java.lang.String value);
  float getSize();
  void setSize(float value);
  float getAvailable();
  void setAvailable(float value);
  float getUse();
  void setUse(float value);
}
