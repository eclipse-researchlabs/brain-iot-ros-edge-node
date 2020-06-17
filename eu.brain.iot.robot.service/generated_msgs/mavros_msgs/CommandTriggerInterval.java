package mavros_msgs;

public interface CommandTriggerInterval extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandTriggerInterval";
  static final java.lang.String _DEFINITION = "# Type for controlling camera trigger interval and integration time\n\nfloat32   cycle_time\t\t\t# Trigger cycle_time (interval between to triggers) - set to 0 to ignore command\nfloat32   integration_time\t# Camera shutter integration_time - set to 0 to ignore command\n---\nbool success\nuint8 result";
}
