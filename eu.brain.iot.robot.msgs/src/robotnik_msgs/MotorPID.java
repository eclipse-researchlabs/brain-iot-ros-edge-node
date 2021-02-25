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

public interface MotorPID extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/MotorPID";
  static final java.lang.String _DEFINITION = "# either can_id or name are set\n# if can_id is -1, then this refers to all motors.\nint32[] can_id\nstring[] name\nfloat32[] kp\nfloat32[] ki\nfloat32[] kd\n";
  int[] getCanId();
  void setCanId(int[] value);
  java.util.List<java.lang.String> getName();
  void setName(java.util.List<java.lang.String> value);
  float[] getKp();
  void setKp(float[] value);
  float[] getKi();
  void setKi(float[] value);
  float[] getKd();
  void setKd(float[] value);
}
