package swarmros;

public interface Double extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "swarmros/Double";
  static final java.lang.String _DEFINITION = "float64 value";
  double getValue();
  void setValue(double value);
}
