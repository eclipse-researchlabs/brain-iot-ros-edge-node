package actionlib;

public interface TestResult extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "actionlib/TestResult";
  static final java.lang.String _DEFINITION = "# ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======\nint32 result\n";
  int getResult();
  void setResult(int value);
}
