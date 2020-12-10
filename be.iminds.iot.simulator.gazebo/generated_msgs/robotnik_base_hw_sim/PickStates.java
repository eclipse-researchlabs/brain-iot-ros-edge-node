package robotnik_base_hw_sim;

public interface PickStates extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_base_hw_sim/PickStates";
  static final java.lang.String _DEFINITION = "# List of current picks\nPickState[] picks";
  java.util.List<robotnik_base_hw_sim.PickState> getPicks();
  void setPicks(java.util.List<robotnik_base_hw_sim.PickState> value);
}
