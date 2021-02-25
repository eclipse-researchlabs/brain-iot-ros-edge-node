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

public interface CommandLong extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandLong";
  static final java.lang.String _DEFINITION = "# Generic COMMAND_LONG\n\nbool broadcast # send this command in broadcast mode\n\nuint16 command\nuint8 confirmation\nfloat32 param1\nfloat32 param2\nfloat32 param3\nfloat32 param4\nfloat32 param5\t# x_lat\nfloat32 param6\t# y_lon\nfloat32 param7\t# z_alt\n---\nbool success\n# raw result returned by COMMAND_ACK\nuint8 result\n";
}
