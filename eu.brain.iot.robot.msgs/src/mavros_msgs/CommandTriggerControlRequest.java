package mavros_msgs;

public interface CommandTriggerControlRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandTriggerControlRequest";
  static final java.lang.String _DEFINITION = "# Type for controlling onboard camera triggering system\n\nbool    trigger_enable\t\t# Trigger enable/disable\nbool    sequence_reset\t\t# Reset the trigger sequence\nbool    trigger_pause\t\t# Pause triggering, but without switching the camera off or retracting it.\n";
  boolean getTriggerEnable();
  void setTriggerEnable(boolean value);
  boolean getSequenceReset();
  void setSequenceReset(boolean value);
  boolean getTriggerPause();
  void setTriggerPause(boolean value);
}
