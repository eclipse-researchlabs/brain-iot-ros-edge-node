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
package std_msgs;

public interface MultiArrayLayout extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "std_msgs/MultiArrayLayout";
  static final java.lang.String _DEFINITION = "# The multiarray declares a generic multi-dimensional array of a\n# particular data type.  Dimensions are ordered from outer most\n# to inner most.\n\nMultiArrayDimension[] dim # Array of dimension properties\nuint32 data_offset        # padding elements at front of data\n\n# Accessors should ALWAYS be written in terms of dimension stride\n# and specified outer-most dimension first.\n# \n# multiarray(i,j,k) = data[data_offset + dim_stride[1]*i + dim_stride[2]*j + k]\n#\n# A standard, 3-channel 640x480 image with interleaved color channels\n# would be specified as:\n#\n# dim[0].label  = \"height\"\n# dim[0].size   = 480\n# dim[0].stride = 3*640*480 = 921600  (note dim[0] stride is just size of image)\n# dim[1].label  = \"width\"\n# dim[1].size   = 640\n# dim[1].stride = 3*640 = 1920\n# dim[2].label  = \"channel\"\n# dim[2].size   = 3\n# dim[2].stride = 3\n#\n# multiarray(i,j,k) refers to the ith row, jth column, and kth channel.\n";
  java.util.List<std_msgs.MultiArrayDimension> getDim();
  void setDim(java.util.List<std_msgs.MultiArrayDimension> value);
  int getDataOffset();
  void setDataOffset(int value);
}
