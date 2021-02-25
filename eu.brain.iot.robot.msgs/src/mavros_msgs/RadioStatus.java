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

public interface RadioStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/RadioStatus";
  static final java.lang.String _DEFINITION = "# RADIO_STATUS message\n\nstd_msgs/Header header\n\n# message data\nuint8 rssi\nuint8 remrssi\nuint8 txbuf\nuint8 noise\nuint8 remnoise\nuint16 rxerrors\nuint16 fixed\n\n# calculated\nfloat32 rssi_dbm\nfloat32 remrssi_dbm\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getRssi();
  void setRssi(byte value);
  byte getRemrssi();
  void setRemrssi(byte value);
  byte getTxbuf();
  void setTxbuf(byte value);
  byte getNoise();
  void setNoise(byte value);
  byte getRemnoise();
  void setRemnoise(byte value);
  short getRxerrors();
  void setRxerrors(short value);
  short getFixed();
  void setFixed(short value);
  float getRssiDbm();
  void setRssiDbm(float value);
  float getRemrssiDbm();
  void setRemrssiDbm(float value);
}
