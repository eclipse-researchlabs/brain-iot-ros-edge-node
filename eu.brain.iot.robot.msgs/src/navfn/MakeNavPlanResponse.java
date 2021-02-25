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
package navfn;

public interface MakeNavPlanResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "navfn/MakeNavPlanResponse";
  static final java.lang.String _DEFINITION = "\nuint8 plan_found\nstring error_message\n\n# if plan_found is true, this is an array of waypoints from start to goal, where the first one equals start and the last one equals goal\ngeometry_msgs/PoseStamped[] path";
  byte getPlanFound();
  void setPlanFound(byte value);
  java.lang.String getErrorMessage();
  void setErrorMessage(java.lang.String value);
  java.util.List<geometry_msgs.PoseStamped> getPath();
  void setPath(java.util.List<geometry_msgs.PoseStamped> value);
}
