package world_canvas_msgs;

public interface EditAnnotationsData extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "world_canvas_msgs/EditAnnotationsData";
  static final java.lang.String _DEFINITION = "# Request an auxiliary editor to add/modify/delete the data for the given annotation. The annotation\n# itself is passed just for information, to show extra information to the user or assist data edition,\n# but will not be changed.\n#  - On request, action involves whether the editor must show the given data or create it from scratch\n#  - On response, action provides the user choice concerning what to do with old and new annotation data\n\nuint8 NEW=0\nuint8 EDIT=1\n\nuint8 action\n\nAnnotation annotation\nAnnotationData data\n---\nuint8 UPDATE=10\nuint8 DELETE=12\nuint8 CANCEL=13\n\nuint8 action\n\nAnnotationData data ";
}
