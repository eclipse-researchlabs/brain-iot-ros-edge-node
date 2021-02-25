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
package robotnik_msgs;

public interface ElevatorAction extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/ElevatorAction";
  static final java.lang.String _DEFINITION = "int32 RAISE=1\nint32 LOWER=-1\nint32 STOP=0\nint32 NO_ACTION=1000\n\nint32 action\n# speed, height for future applications\n";
  static final int RAISE = 1;
  static final int LOWER = -1;
  static final int STOP = 0;
  static final int NO_ACTION = 1000;
  int getAction();
  void setAction(int value);
}
