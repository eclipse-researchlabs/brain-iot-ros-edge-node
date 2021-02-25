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
package robot_local_control_msgs;

public interface SetGoToPetition extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robot_local_control_msgs/SetGoToPetition";
  static final java.lang.String _DEFINITION = "string action_namespace  # this will be the new action_namespace of the move_base client\nstring frame_id          # the new global frame_id\n---\nbool success   # indicate successful run of triggered service\nstring message # informational, e.g. for error messages\n";
}
