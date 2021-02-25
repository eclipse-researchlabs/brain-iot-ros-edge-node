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

public interface StreamRate extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/StreamRate";
  static final java.lang.String _DEFINITION = "# sets stream rate\n# See REQUEST_DATA_STREAM message\n\nuint8 STREAM_ALL = 0\nuint8 STREAM_RAW_SENSORS = 1\nuint8 STREAM_EXTENDED_STATUS = 2\nuint8 STREAM_RC_CHANNELS = 3\nuint8 STREAM_RAW_CONTROLLER = 4\nuint8 STREAM_POSITION = 6\nuint8 STREAM_EXTRA1 = 10\nuint8 STREAM_EXTRA2 = 11\nuint8 STREAM_EXTRA3 = 12\n\nuint8 stream_id\nuint16 message_rate\nbool on_off\n---\n";
}
