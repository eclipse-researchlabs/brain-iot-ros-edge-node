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
package kobuki_msgs;

public interface DockInfraRed extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/DockInfraRed";
  static final java.lang.String _DEFINITION = "# Docking base ir sensors messages.\n# Generated on the proximity of the docking base to assist the automatic docking.\n\nuint8 NEAR_LEFT   =  1\nuint8 NEAR_CENTER =  2\nuint8 NEAR_RIGHT  =  4\nuint8 FAR_LEFT    = 16\nuint8 FAR_CENTER  =  8\nuint8 FAR_RIGHT   = 32\n\nHeader header\nuint8[] data\n";
  static final byte NEAR_LEFT = 1;
  static final byte NEAR_CENTER = 2;
  static final byte NEAR_RIGHT = 4;
  static final byte FAR_LEFT = 16;
  static final byte FAR_CENTER = 8;
  static final byte FAR_RIGHT = 32;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
