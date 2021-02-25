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

public interface OverrideRCIn extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/OverrideRCIn";
  static final java.lang.String _DEFINITION = "# Override RC Input\n# Currently MAVLink defines override for 8 channel\n\nuint16 CHAN_RELEASE=0\nuint16 CHAN_NOCHANGE=65535\n\nuint16[8] channels\n";
  static final short CHAN_RELEASE = 0;
  static final short CHAN_NOCHANGE = -1;
  short[] getChannels();
  void setChannels(short[] value);
}
