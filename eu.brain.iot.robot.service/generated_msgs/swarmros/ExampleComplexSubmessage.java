package swarmros;

public interface ExampleComplexSubmessage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ExampleComplexSubmessage";
  static final java.lang.String _DEFINITION = "string field1\nfloat64 field2";
  java.lang.String getField1();
  void setField1(java.lang.String value);
  double getField2();
  void setField2(double value);
}
