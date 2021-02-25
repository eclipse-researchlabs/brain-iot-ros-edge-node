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

public interface State extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/State";
  static final java.lang.String _DEFINITION = "# constants\nint32 INIT_STATE = 100\nint32 STANDBY_STATE = 200\nint32 READY_STATE = 300\nint32 EMERGENCY_STATE = 400\nint32 FAILURE_STATE = 500\nint32 SHUTDOWN_STATE = 600\nint32 UNKOWN_STATE = 700\n\n# state of the component\nint32 state\n# desired control loop frecuency\nfloat32 desired_freq\n# real frecuency \nfloat32 real_freq\n# Description of the state\nstring state_description\n";
  static final int INIT_STATE = 100;
  static final int STANDBY_STATE = 200;
  static final int READY_STATE = 300;
  static final int EMERGENCY_STATE = 400;
  static final int FAILURE_STATE = 500;
  static final int SHUTDOWN_STATE = 600;
  static final int UNKOWN_STATE = 700;
  int getState();
  void setState(int value);
  float getDesiredFreq();
  void setDesiredFreq(float value);
  float getRealFreq();
  void setRealFreq(float value);
  java.lang.String getStateDescription();
  void setStateDescription(java.lang.String value);
}
