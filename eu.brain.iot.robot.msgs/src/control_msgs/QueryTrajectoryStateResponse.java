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
package control_msgs;

public interface QueryTrajectoryStateResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/QueryTrajectoryStateResponse";
  static final java.lang.String _DEFINITION = "string[] name\nfloat64[] position\nfloat64[] velocity\nfloat64[] acceleration";
  java.util.List<java.lang.String> getName();
  void setName(java.util.List<java.lang.String> value);
  double[] getPosition();
  void setPosition(double[] value);
  double[] getVelocity();
  void setVelocity(double[] value);
  double[] getAcceleration();
  void setAcceleration(double[] value);
}
