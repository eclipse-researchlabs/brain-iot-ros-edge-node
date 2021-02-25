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
package kobuki_msgs;

public interface CliffEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/CliffEvent";
  static final java.lang.String _DEFINITION = "# Provides a cliff sensor event.\n# This message is generated whenever a particular cliff sensor signals that the\n# robot approaches or moves away from a cliff.\n# Note that, despite cliff field on SensorState messages, state field is not a\n# bitmask, but the new state of a single sensor.\n\n# cliff sensor\nuint8 LEFT   = 0\nuint8 CENTER = 1\nuint8 RIGHT  = 2\n\n# cliff sensor state\nuint8 FLOOR = 0\nuint8 CLIFF = 1\n\nuint8 sensor\nuint8 state\n\n# distance to floor when cliff was detected\nuint16 bottom";
  static final byte LEFT = 0;
  static final byte CENTER = 1;
  static final byte RIGHT = 2;
  static final byte FLOOR = 0;
  static final byte CLIFF = 1;
  byte getSensor();
  void setSensor(byte value);
  byte getState();
  void setState(byte value);
  short getBottom();
  void setBottom(short value);
}
