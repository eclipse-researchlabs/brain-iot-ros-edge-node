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
package robotnik_fms_msgs;

public interface ControlElevatorRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/ControlElevatorRequest";
  static final java.lang.String _DEFINITION = "# Control true: get control false: leave control\nbool control\n\n# elevator: id of Elevator to control\nint32 elevator\n\n# agent: id of agent to control elevator once this agent has control  \n#   other agent cannot control this elevator until an order of leave control \nint32 agent\n\n# floor >= 0 send to floor\nint32 floor\n\n# dooropen true: open door\nbool dooropen\n\n# doorclose true: close door\nbool doorclose\n\n";
  boolean getControl();
  void setControl(boolean value);
  int getElevator();
  void setElevator(int value);
  int getAgent();
  void setAgent(int value);
  int getFloor();
  void setFloor(int value);
  boolean getDooropen();
  void setDooropen(boolean value);
  boolean getDoorclose();
  void setDoorclose(boolean value);
}
