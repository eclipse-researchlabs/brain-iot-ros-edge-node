package swarmros;

public interface ExampleComplexMessage extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ExampleComplexMessage";
  static final java.lang.String _DEFINITION = "uint64 field1\nuint64 field2\nExampleComplexSubmessage submessage1\nExampleComplexSubmessage submessage2\nfloat64 field3";
  long getField1();
  void setField1(long value);
  long getField2();
  void setField2(long value);
  swarmros.ExampleComplexSubmessage getSubmessage1();
  void setSubmessage1(swarmros.ExampleComplexSubmessage value);
  swarmros.ExampleComplexSubmessage getSubmessage2();
  void setSubmessage2(swarmros.ExampleComplexSubmessage value);
  double getField3();
  void setField3(double value);
}
