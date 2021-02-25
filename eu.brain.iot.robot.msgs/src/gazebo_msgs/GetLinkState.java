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

public interface GetLinkState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/GetLinkState";
  static final java.lang.String _DEFINITION = "string link_name          # name of link\n                          # link names are prefixed by model name, e.g. pr2::base_link\nstring reference_frame    # reference frame of returned information, must be a valid link\n                          # if empty, use inertial (gazebo world) frame\n                          # reference_frame names are prefixed by model name, e.g. pr2::base_link\n---\ngazebo_msgs/LinkState link_state\nbool success              # return true if get info is successful\nstring status_message     # comments if available\n";
}
