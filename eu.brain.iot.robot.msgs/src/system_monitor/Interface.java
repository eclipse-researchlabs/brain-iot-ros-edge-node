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
package system_monitor;

public interface Interface extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "system_monitor/Interface";
  static final java.lang.String _DEFINITION = "string name\nstring state\n#input/output speed in MB/s\nfloat32 input\nfloat32 output\nint32 mtu\n#Data received/transmitted in MB\nfloat32 received\nfloat32 transmitted\nint32 collisions\nint32 rxError\nint32 txError\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getState();
  void setState(java.lang.String value);
  float getInput();
  void setInput(float value);
  float getOutput();
  void setOutput(float value);
  int getMtu();
  void setMtu(int value);
  float getReceived();
  void setReceived(float value);
  float getTransmitted();
  void setTransmitted(float value);
  int getCollisions();
  void setCollisions(int value);
  int getRxError();
  void setRxError(int value);
  int getTxError();
  void setTxError(int value);
}
