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
package gazebo_msgs;

public interface SetPhysicsPropertiesRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/SetPhysicsPropertiesRequest";
  static final java.lang.String _DEFINITION = "# sets pose and twist of a link.  All children link poses/twists of the URDF tree will be updated accordingly\nfloat64 time_step                  # dt in seconds\nfloat64 max_update_rate            # throttle maximum physics update rate\ngeometry_msgs/Vector3 gravity      # gravity vector (e.g. earth ~[0,0,-9.81])\ngazebo_msgs/ODEPhysics ode_config  # configurations for ODE\n";
  double getTimeStep();
  void setTimeStep(double value);
  double getMaxUpdateRate();
  void setMaxUpdateRate(double value);
  geometry_msgs.Vector3 getGravity();
  void setGravity(geometry_msgs.Vector3 value);
  gazebo_msgs.ODEPhysics getOdeConfig();
  void setOdeConfig(gazebo_msgs.ODEPhysics value);
}
