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

public interface Pose2D extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geometry_msgs/Pose2D";
  static final java.lang.String _DEFINITION = "# Deprecated\n# Please use the full 3D pose.\n\n# In general our recommendation is to use a full 3D representation of everything and for 2D specific applications make the appropriate projections into the plane for their calculations but optimally will preserve the 3D information during processing.\n\n# If we have parallel copies of 2D datatypes every UI and other pipeline will end up needing to have dual interfaces to plot everything. And you will end up with not being able to use 3D tools for 2D use cases even if they\'re completely valid, as you\'d have to reimplement it with different inputs and outputs. It\'s not particularly hard to plot the 2D pose or compute the yaw error for the Pose message and there are already tools and libraries that can do this for you.\n\n\n# This expresses a position and orientation on a 2D manifold.\n\nfloat64 x\nfloat64 y\nfloat64 theta\n";
  double getX();
  void setX(double value);
  double getY();
  void setY(double value);
  double getTheta();
  void setTheta(double value);
}
