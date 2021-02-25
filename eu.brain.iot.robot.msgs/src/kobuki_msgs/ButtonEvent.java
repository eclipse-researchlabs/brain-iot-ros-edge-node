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

public interface ButtonEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/ButtonEvent";
  static final java.lang.String _DEFINITION = "# Provides a button event.\n# This message is generated whenever a particular button is pressed or released.\n# Note that, despite buttons field on SensorState messages, state field is not a\n# bitmask, but the new state of a single button.\n\nuint8 Button0 = 0\nuint8 Button1 = 1\nuint8 Button2 = 2\n\nuint8 RELEASED = 0\nuint8 PRESSED  = 1\n\nuint8 button\nuint8 state\n";
  static final byte Button0 = 0;
  static final byte Button1 = 1;
  static final byte Button2 = 2;
  static final byte RELEASED = 0;
  static final byte PRESSED = 1;
  byte getButton();
  void setButton(byte value);
  byte getState();
  void setState(byte value);
}
