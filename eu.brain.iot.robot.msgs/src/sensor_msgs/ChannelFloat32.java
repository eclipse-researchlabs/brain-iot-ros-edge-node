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
package sensor_msgs;

public interface ChannelFloat32 extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/ChannelFloat32";
  static final java.lang.String _DEFINITION = "# This message is used by the PointCloud message to hold optional data\n# associated with each point in the cloud. The length of the values\n# array should be the same as the length of the points array in the\n# PointCloud, and each value should be associated with the corresponding\n# point.\n\n# Channel names in existing practice include:\n#   \"u\", \"v\" - row and column (respectively) in the left stereo image.\n#              This is opposite to usual conventions but remains for\n#              historical reasons. The newer PointCloud2 message has no\n#              such problem.\n#   \"rgb\" - For point clouds produced by color stereo cameras. uint8\n#           (R,G,B) values packed into the least significant 24 bits,\n#           in order.\n#   \"intensity\" - laser or pixel intensity.\n#   \"distance\"\n\n# The channel name should give semantics of the channel (e.g.\n# \"intensity\" instead of \"value\").\nstring name\n\n# The values array should be 1-1 with the elements of the associated\n# PointCloud.\nfloat32[] values\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  float[] getValues();
  void setValues(float[] value);
}
