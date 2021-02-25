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

public interface encoders extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/encoders";
  static final java.lang.String _DEFINITION = "# 0-> right incremental, 1-> left incremental, 2-> steer absolute\nstring[] type\nint32[] counts\nint32[] vel\n";
  java.util.List<java.lang.String> getType();
  void setType(java.util.List<java.lang.String> value);
  int[] getCounts();
  void setCounts(int[] value);
  int[] getVel();
  void setVel(int[] value);
}
