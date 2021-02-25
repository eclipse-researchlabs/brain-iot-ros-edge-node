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
package controller_manager_msgs;

public interface UnloadControllerRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "controller_manager_msgs/UnloadControllerRequest";
  static final java.lang.String _DEFINITION = "# The UnloadController service allows you to unload a single controller \n# from controller_manager\n\n# To unload a controller, specify the \"name\" of the controller. \n# The return value \"ok\" indicates if the controller was successfully\n# unloaded or not\n\nstring name\n";
  java.lang.String getName();
  void setName(java.lang.String value);
}
