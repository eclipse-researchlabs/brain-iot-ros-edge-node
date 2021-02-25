/*******************************************************************************
 * *  Copyright (C) 2021 LINKS Foundation
 * 
 * This file is based on the ROSOSGi open-source project which is a part of DIANNE  -  Framework for distributed artificial neural networks
 * 
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package visualization_msgs;

public interface InteractiveMarkerUpdate extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "visualization_msgs/InteractiveMarkerUpdate";
  static final java.lang.String _DEFINITION = "# Identifying string. Must be unique in the topic namespace\n# that this server works on.\nstring server_id\n\n# Sequence number.\n# The client will use this to detect if it has missed an update.\nuint64 seq_num\n\n# Type holds the purpose of this message.  It must be one of UPDATE or KEEP_ALIVE.\n# UPDATE: Incremental update to previous state. \n#         The sequence number must be 1 higher than for\n#         the previous update.\n# KEEP_ALIVE: Indicates the that the server is still living.\n#             The sequence number does not increase.\n#             No payload data should be filled out (markers, poses, or erases).\nuint8 KEEP_ALIVE = 0\nuint8 UPDATE = 1\n\nuint8 type\n\n#Note: No guarantees on the order of processing.\n#      Contents must be kept consistent by sender.\n\n#Markers to be added or updated\nInteractiveMarker[] markers\n\n#Poses of markers that should be moved\nInteractiveMarkerPose[] poses\n\n#Names of markers to be erased\nstring[] erases\n";
  static final byte KEEP_ALIVE = 0;
  static final byte UPDATE = 1;
  java.lang.String getServerId();
  void setServerId(java.lang.String value);
  long getSeqNum();
  void setSeqNum(long value);
  byte getType();
  void setType(byte value);
  java.util.List<visualization_msgs.InteractiveMarker> getMarkers();
  void setMarkers(java.util.List<visualization_msgs.InteractiveMarker> value);
  java.util.List<visualization_msgs.InteractiveMarkerPose> getPoses();
  void setPoses(java.util.List<visualization_msgs.InteractiveMarkerPose> value);
  java.util.List<java.lang.String> getErases();
  void setErases(java.util.List<java.lang.String> value);
}
