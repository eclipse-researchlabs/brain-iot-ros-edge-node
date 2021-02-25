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

public interface KeyboardInput extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/KeyboardInput";
  static final java.lang.String _DEFINITION = "# KEYBOARD INPUT\n# \n# Keycodes to be transferred for remote teleops.\n\nuint8  KeyCode_Right    = 67     # 0x43\nuint8  KeyCode_Left     = 68     # 0x44\nuint8  KeyCode_Up       = 65     # 0x41\nuint8  KeyCode_Down     = 66     # 0x42\nuint8  KeyCode_Space    = 32     # 0x20\nuint8  KeyCode_Enable   = 101    # 0x65, \'e\'\nuint8  KeyCode_Disable  = 100    # 0x64, \'d\'\n\nuint8 pressedKey";
  static final byte KeyCode_Right = 67;
  static final byte KeyCode_Left = 68;
  static final byte KeyCode_Up = 65;
  static final byte KeyCode_Down = 66;
  static final byte KeyCode_Space = 32;
  static final byte KeyCode_Enable = 101;
  static final byte KeyCode_Disable = 100;
  byte getPressedKey();
  void setPressedKey(byte value);
}
