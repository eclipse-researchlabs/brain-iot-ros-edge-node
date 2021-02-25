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

public interface Led extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/Led";
  static final java.lang.String _DEFINITION = "# Sends a command for controlling the a LED.\n# \n# Typically the first LED is always reserved to denote\n# the state - the remainder will be controllable. \n\nuint8 BLACK   = 0\nuint8 GREEN   = 1\nuint8 ORANGE  = 2\nuint8 RED     = 3\n\n# For kobuki there are only two controllable LED\'s.\nuint8 value\n";
  static final byte BLACK = 0;
  static final byte GREEN = 1;
  static final byte ORANGE = 2;
  static final byte RED = 3;
  byte getValue();
  void setValue(byte value);
}
