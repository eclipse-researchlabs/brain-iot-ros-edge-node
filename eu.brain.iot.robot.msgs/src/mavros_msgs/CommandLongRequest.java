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
package mavros_msgs;

public interface CommandLongRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/CommandLongRequest";
  static final java.lang.String _DEFINITION = "# Generic COMMAND_LONG\n\nbool broadcast # send this command in broadcast mode\n\nuint16 command\nuint8 confirmation\nfloat32 param1\nfloat32 param2\nfloat32 param3\nfloat32 param4\nfloat32 param5\t# x_lat\nfloat32 param6\t# y_lon\nfloat32 param7\t# z_alt\n";
  boolean getBroadcast();
  void setBroadcast(boolean value);
  short getCommand();
  void setCommand(short value);
  byte getConfirmation();
  void setConfirmation(byte value);
  float getParam1();
  void setParam1(float value);
  float getParam2();
  void setParam2(float value);
  float getParam3();
  void setParam3(float value);
  float getParam4();
  void setParam4(float value);
  float getParam5();
  void setParam5(float value);
  float getParam6();
  void setParam6(float value);
  float getParam7();
  void setParam7(float value);
}
