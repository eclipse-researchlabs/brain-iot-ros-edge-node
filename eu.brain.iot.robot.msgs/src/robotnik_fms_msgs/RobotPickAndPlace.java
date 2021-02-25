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
package robotnik_fms_msgs;

public interface RobotPickAndPlace extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/RobotPickAndPlace";
  static final java.lang.String _DEFINITION = "# Robot id to command\nint32 robot_id\nint32 pick_from\nint32 place_to\n---\nbool success\nstring message\nint32 id\n";
}
