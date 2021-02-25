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
package mavros_msgs;

public interface DebugValue extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/DebugValue";
  static final java.lang.String _DEFINITION = "# Msg for Debug MAVLink API\n#\n# Supported types:\n# DEBUG\t\t\thttps://mavlink.io/en/messages/common.html#DEBUG\n# DEBUG_VECTOR\t\t\thttps://mavlink.io/en/messages/common.html#DEBUG_VECT\n# NAMED_VALUE_FLOAT\t\thttps://mavlink.io/en/messages/common.html#NAMED_VALUE_FLOAT\n# NAMED_VALUE_INT\t\thttps://mavlink.io/en/messages/common.html#NAMED_VALUE_INT\n# @TODO: add support for DEBUG_ARRAY (https://github.com/mavlink/mavlink/pull/734)\n\nstd_msgs/Header header\n\nint32 index\t\t\t# index value of DEBUG value (-1 if not indexed)\n\nstring name\t\t\t# value name/key\n\nfloat32 value_float\t\t# float value for NAMED_VALUE_FLOAT and DEBUG\nint32 value_int\t\t# int value for NAMED_VALUE_INT\nfloat32[] data\t\t\t# DEBUG vector or array\n\nuint8 type\nuint8 TYPE_DEBUG\t\t= 0\nuint8 TYPE_DEBUG_VECT\t\t= 1\nuint8 TYPE_DEBUG_ARRAY\t\t= 2\nuint8 TYPE_NAMED_VALUE_FLOAT\t= 3\nuint8 TYPE_NAMED_VALUE_INT\t= 4\n";
  static final byte TYPE_DEBUG = 0;
  static final byte TYPE_DEBUG_VECT = 1;
  static final byte TYPE_DEBUG_ARRAY = 2;
  static final byte TYPE_NAMED_VALUE_FLOAT = 3;
  static final byte TYPE_NAMED_VALUE_INT = 4;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getIndex();
  void setIndex(int value);
  java.lang.String getName();
  void setName(java.lang.String value);
  float getValueFloat();
  void setValueFloat(float value);
  int getValueInt();
  void setValueInt(int value);
  float[] getData();
  void setData(float[] value);
  byte getType();
  void setType(byte value);
}
