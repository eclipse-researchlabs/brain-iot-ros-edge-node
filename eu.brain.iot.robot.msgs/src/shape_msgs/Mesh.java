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
package shape_msgs;

public interface Mesh extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "shape_msgs/Mesh";
  static final java.lang.String _DEFINITION = "# Definition of a mesh\n\n# list of triangles; the index values refer to positions in vertices[]\nMeshTriangle[] triangles\n\n# the actual vertices that make up the mesh\ngeometry_msgs/Point[] vertices\n";
  java.util.List<shape_msgs.MeshTriangle> getTriangles();
  void setTriangles(java.util.List<shape_msgs.MeshTriangle> value);
  java.util.List<geometry_msgs.Point> getVertices();
  void setVertices(java.util.List<geometry_msgs.Point> value);
}
