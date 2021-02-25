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

public interface BumperEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/BumperEvent";
  static final java.lang.String _DEFINITION = "# Provides a bumper event.\n# This message is generated whenever a particular bumper is pressed or released.\n# Note that, despite bumper field on SensorState messages, state field is not a\n# bitmask, but the new state of a single sensor.\n\n# bumper\nuint8 LEFT   = 0\nuint8 CENTER = 1\nuint8 RIGHT  = 2\n\n# state\nuint8 RELEASED = 0\nuint8 PRESSED  = 1\n\nuint8 bumper\nuint8 state\n";
  static final byte LEFT = 0;
  static final byte CENTER = 1;
  static final byte RIGHT = 2;
  static final byte RELEASED = 0;
  static final byte PRESSED = 1;
  byte getBumper();
  void setBumper(byte value);
  byte getState();
  void setState(byte value);
}
