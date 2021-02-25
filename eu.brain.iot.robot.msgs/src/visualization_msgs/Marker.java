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
package visualization_msgs;

public interface Marker extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "visualization_msgs/Marker";
  static final java.lang.String _DEFINITION = "# See http://www.ros.org/wiki/rviz/DisplayTypes/Marker and http://www.ros.org/wiki/rviz/Tutorials/Markers%3A%20Basic%20Shapes for more information on using this message with rviz\n\nuint8 ARROW=0\nuint8 CUBE=1\nuint8 SPHERE=2\nuint8 CYLINDER=3\nuint8 LINE_STRIP=4\nuint8 LINE_LIST=5\nuint8 CUBE_LIST=6\nuint8 SPHERE_LIST=7\nuint8 POINTS=8\nuint8 TEXT_VIEW_FACING=9\nuint8 MESH_RESOURCE=10\nuint8 TRIANGLE_LIST=11\n\nuint8 ADD=0\nuint8 MODIFY=0\nuint8 DELETE=2\nuint8 DELETEALL=3\n\nHeader header                        # header for time/frame information\nstring ns                            # Namespace to place this object in... used in conjunction with id to create a unique name for the object\nint32 id \t\t                         # object ID useful in conjunction with the namespace for manipulating and deleting the object later\nint32 type \t\t                       # Type of object\nint32 action \t                       # 0 add/modify an object, 1 (deprecated), 2 deletes an object, 3 deletes all objects\ngeometry_msgs/Pose pose                 # Pose of the object\ngeometry_msgs/Vector3 scale             # Scale of the object 1,1,1 means default (usually 1 meter square)\nstd_msgs/ColorRGBA color             # Color [0.0-1.0]\nduration lifetime                    # How long the object should last before being automatically deleted.  0 means forever\nbool frame_locked                    # If this marker should be frame-locked, i.e. retransformed into its frame every timestep\n\n#Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...)\ngeometry_msgs/Point[] points\n#Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...)\n#number of colors must either be 0 or equal to the number of points\n#NOTE: alpha is not yet used\nstd_msgs/ColorRGBA[] colors\n\n# NOTE: only used for text markers\nstring text\n\n# NOTE: only used for MESH_RESOURCE markers\nstring mesh_resource\nbool mesh_use_embedded_materials\n";
  static final byte ARROW = 0;
  static final byte CUBE = 1;
  static final byte SPHERE = 2;
  static final byte CYLINDER = 3;
  static final byte LINE_STRIP = 4;
  static final byte LINE_LIST = 5;
  static final byte CUBE_LIST = 6;
  static final byte SPHERE_LIST = 7;
  static final byte POINTS = 8;
  static final byte TEXT_VIEW_FACING = 9;
  static final byte MESH_RESOURCE = 10;
  static final byte TRIANGLE_LIST = 11;
  static final byte ADD = 0;
  static final byte MODIFY = 0;
  static final byte DELETE = 2;
  static final byte DELETEALL = 3;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.lang.String getNs();
  void setNs(java.lang.String value);
  int getId();
  void setId(int value);
  int getType();
  void setType(int value);
  int getAction();
  void setAction(int value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
  geometry_msgs.Vector3 getScale();
  void setScale(geometry_msgs.Vector3 value);
  std_msgs.ColorRGBA getColor();
  void setColor(std_msgs.ColorRGBA value);
  org.ros.message.Duration getLifetime();
  void setLifetime(org.ros.message.Duration value);
  boolean getFrameLocked();
  void setFrameLocked(boolean value);
  java.util.List<geometry_msgs.Point> getPoints();
  void setPoints(java.util.List<geometry_msgs.Point> value);
  java.util.List<std_msgs.ColorRGBA> getColors();
  void setColors(java.util.List<std_msgs.ColorRGBA> value);
  java.lang.String getText();
  void setText(java.lang.String value);
  java.lang.String getMeshResource();
  void setMeshResource(java.lang.String value);
  boolean getMeshUseEmbeddedMaterials();
  void setMeshUseEmbeddedMaterials(boolean value);
}
