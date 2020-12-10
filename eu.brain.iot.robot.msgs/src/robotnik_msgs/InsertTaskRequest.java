package robotnik_msgs;

public interface InsertTaskRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/InsertTaskRequest";
  static final java.lang.String _DEFINITION = "int32 id_submission\nstring description_task\nstring datatime_start\n";
  int getIdSubmission();
  void setIdSubmission(int value);
  java.lang.String getDescriptionTask();
  void setDescriptionTask(java.lang.String value);
  java.lang.String getDatatimeStart();
  void setDatatimeStart(java.lang.String value);
}
