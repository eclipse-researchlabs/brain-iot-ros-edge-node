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

public interface FileOpen extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/FileOpen";
  static final java.lang.String _DEFINITION = "# FTP::Open\n#\n# :file_path:\tused as session id in read/write/close services\n# :size:\tfile size returned for MODE_READ\n# :success:\tindicates success end of request\n# :r_errno:\tremote errno if applicapable\n\nuint8 MODE_READ = 0\t# open for read\nuint8 MODE_WRITE = 1\t# open for write\nuint8 MODE_CREATE = 2\t# do creat()\n\nstring file_path\nuint8 mode\n---\nuint32 size\nbool success\nint32 r_errno\n";
}
