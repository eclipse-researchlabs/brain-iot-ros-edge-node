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

public interface UInt16MultiArray extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "std_msgs/UInt16MultiArray";
  static final java.lang.String _DEFINITION = "# Please look at the MultiArrayLayout message definition for\n# documentation on all multiarrays.\n\nMultiArrayLayout  layout        # specification of data layout\nuint16[]            data        # array of data\n\n";
  std_msgs.MultiArrayLayout getLayout();
  void setLayout(std_msgs.MultiArrayLayout value);
  short[] getData();
  void setData(short[] value);
}
