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

public interface GetModelState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/GetModelState";
  static final java.lang.String _DEFINITION = "string model_name                    # name of Gazebo Model\nstring relative_entity_name          # return pose and twist relative to this entity\n                                     # an entity can be a model, body, or geom\n                                     # be sure to use gazebo scoped naming notation (e.g. [model_name::body_name])\n                                     # leave empty or \"world\" will use inertial world frame\n---\nHeader header                        # Standard metadata for higher-level stamped data types.\n                                     # * header.seq holds the number of requests since the plugin started\n                                     # * header.stamp timestamp related to the pose\n                                     # * header.frame_id not used but currently filled with the relative_entity_name\ngeometry_msgs/Pose pose              # pose of model in relative entity frame\ngeometry_msgs/Twist twist            # twist of model in relative entity frame\nbool success                         # return true if get successful\nstring status_message                # comments if available\n";
}
