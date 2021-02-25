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

public interface Sound extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/Sound";
  static final java.lang.String _DEFINITION = "# Sends a command for playing sounds.\n# The available sound sequences:\n# 0 - turn on\n# 1 - turn off\n# 2 - recharge start\n# 3 - press button,\n# 4 - error sound\n# 5 - start cleaning\n# 6 - cleaning end\n\nuint8 ON            = 0\nuint8 OFF           = 1\nuint8 RECHARGE      = 2\nuint8 BUTTON        = 3\nuint8 ERROR         = 4\nuint8 CLEANINGSTART = 5\nuint8 CLEANINGEND   = 6\n\nuint8 value";
  static final byte ON = 0;
  static final byte OFF = 1;
  static final byte RECHARGE = 2;
  static final byte BUTTON = 3;
  static final byte ERROR = 4;
  static final byte CLEANINGSTART = 5;
  static final byte CLEANINGEND = 6;
  byte getValue();
  void setValue(byte value);
}
