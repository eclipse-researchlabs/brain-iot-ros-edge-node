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
package mavros_msgs;

public interface RTCM extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/RTCM";
  static final java.lang.String _DEFINITION = "# RTCM message for the gps_rtk plugin\n# The gps_rtk plugin will fragment the data if necessary and \n# forward it to the FCU via Mavlink through the available link.\n# data should be <= 4*180, higher will be discarded.\nstd_msgs/Header header\nuint8[] data\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  org.jboss.netty.buffer.ChannelBuffer getData();
  void setData(org.jboss.netty.buffer.ChannelBuffer value);
}
