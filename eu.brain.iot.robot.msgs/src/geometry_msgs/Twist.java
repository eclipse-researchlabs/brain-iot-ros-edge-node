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
package geometry_msgs;

public interface Twist extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geometry_msgs/Twist";
  static final java.lang.String _DEFINITION = "# This expresses velocity in free space broken into its linear and angular parts.\nVector3  linear\nVector3  angular\n";
  geometry_msgs.Vector3 getLinear();
  void setLinear(geometry_msgs.Vector3 value);
  geometry_msgs.Vector3 getAngular();
  void setAngular(geometry_msgs.Vector3 value);
}
