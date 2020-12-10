package system_monitor;

public interface Interface extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/Interface";
  static final java.lang.String _DEFINITION = "string name\nstring state\n#input/output speed in MB/s\nfloat32 input\nfloat32 output\nint32 mtu\n#Data received/transmitted in MB\nfloat32 received\nfloat32 transmitted\nint32 collisions\nint32 rxError\nint32 txError\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getState();
  void setState(java.lang.String value);
  float getInput();
  void setInput(float value);
  float getOutput();
  void setOutput(float value);
  int getMtu();
  void setMtu(int value);
  float getReceived();
  void setReceived(float value);
  float getTransmitted();
  void setTransmitted(float value);
  int getCollisions();
  void setCollisions(int value);
  int getRxError();
  void setRxError(int value);
  int getTxError();
  void setTxError(int value);
}
