package mavros_msgs;

public interface FileTruncate extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/FileTruncate";
  static final java.lang.String _DEFINITION = "# FTP::Truncate\n#\n# :success:\tindicates success end of request\n# :r_errno:\tremote errno if applicapable\n\nstring file_path\nuint64 length\n---\nbool success\nint32 r_errno\n";
}
