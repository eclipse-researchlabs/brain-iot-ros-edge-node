package swarmros;

public interface ArrayOfDouble extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/ArrayOfDouble";
  static final java.lang.String _DEFINITION = "float64[] values";
  double[] getValues();
  void setValues(double[] value);
}
