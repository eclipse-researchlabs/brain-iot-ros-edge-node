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

public interface ControllerInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/ControllerInfo";
  static final java.lang.String _DEFINITION = "# Controller info message, contains PID parameters\n\nuint8 DEFAULT   =  0\nuint8 USER_CONFIGURED =  1\n\nuint8 type\nfloat64 p_gain #should be positive\nfloat64 i_gain #should be positive\nfloat64 d_gain #should be positive\n";
  static final byte DEFAULT = 0;
  static final byte USER_CONFIGURED = 1;
  byte getType();
  void setType(byte value);
  double getPGain();
  void setPGain(double value);
  double getIGain();
  void setIGain(double value);
  double getDGain();
  void setDGain(double value);
}
