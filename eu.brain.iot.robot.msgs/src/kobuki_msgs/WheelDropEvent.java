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

public interface WheelDropEvent extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/WheelDropEvent";
  static final java.lang.String _DEFINITION = "# Provides a wheel drop event.\n# This message is generated whenever one of the wheels is dropped (robot fell\n# or was raised) or raised (normal condition).\n# Note that, despite wheel_drop field on SensorState messages, state field is\n# not a bitmask, but the new state of a single sensor.\n\n# wheel\nuint8 LEFT  = 0\nuint8 RIGHT = 1\n\n# state\nuint8 RAISED  = 0\nuint8 DROPPED = 1\n\nuint8 wheel\nuint8 state\n";
  static final byte LEFT = 0;
  static final byte RIGHT = 1;
  static final byte RAISED = 0;
  static final byte DROPPED = 1;
  byte getWheel();
  void setWheel(byte value);
  byte getState();
  void setState(byte value);
}
