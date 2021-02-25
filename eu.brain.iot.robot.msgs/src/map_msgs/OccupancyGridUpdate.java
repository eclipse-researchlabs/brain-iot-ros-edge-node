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
package map_msgs;

public interface OccupancyGridUpdate extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "map_msgs/OccupancyGridUpdate";
  static final java.lang.String _DEFINITION = "Header header\nint32 x\nint32 y\nuint32 width\nuint32 height\nint8[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getX();
  void setX(int value);
  int getY();
  void setY(int value);
  int getWidth();
  void setWidth(int value);
  int getHeight();
  void setHeight(int value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
