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

public interface FileEntry extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/FileEntry";
  static final java.lang.String _DEFINITION = "# File/Dir information\n\nuint8 TYPE_FILE = 0\nuint8 TYPE_DIRECTORY = 1\n\nstring name\nuint8 type\nuint64 size\n\n# Not supported by MAVLink FTP\n#time atime\n#int32 access_flags\n";
  static final byte TYPE_FILE = 0;
  static final byte TYPE_DIRECTORY = 1;
  java.lang.String getName();
  void setName(java.lang.String value);
  byte getType();
  void setType(byte value);
  long getSize();
  void setSize(long value);
}
