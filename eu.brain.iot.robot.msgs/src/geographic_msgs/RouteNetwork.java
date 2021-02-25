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
package geographic_msgs;

public interface RouteNetwork extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "geographic_msgs/RouteNetwork";
  static final java.lang.String _DEFINITION = "# Geographic map route network.\n#\n# A directed graph of WayPoint nodes and RouteSegment edges.  This\n# information is extracted from the more-detailed contents of a\n# GeographicMap.  A RouteNetwork contains only the way points and\n# route segments of interest for path planning.\n\nHeader          header\n\nuuid_msgs/UniqueID id    # This route network identifier\nBoundingBox     bounds   # 2D bounding box for network\n\nWayPoint[]      points   # Way points in this network\nRouteSegment[]  segments # Directed edges of this network\n\nKeyValue[]      props    # Network key/value properties\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  uuid_msgs.UniqueID getId();
  void setId(uuid_msgs.UniqueID value);
  geographic_msgs.BoundingBox getBounds();
  void setBounds(geographic_msgs.BoundingBox value);
  java.util.List<geographic_msgs.WayPoint> getPoints();
  void setPoints(java.util.List<geographic_msgs.WayPoint> value);
  java.util.List<geographic_msgs.RouteSegment> getSegments();
  void setSegments(java.util.List<geographic_msgs.RouteSegment> value);
  java.util.List<geographic_msgs.KeyValue> getProps();
  void setProps(java.util.List<geographic_msgs.KeyValue> value);
}
