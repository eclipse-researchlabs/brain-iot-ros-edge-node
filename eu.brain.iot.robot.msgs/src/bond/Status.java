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
package bond;

public interface Status extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "bond/Status";
  static final java.lang.String _DEFINITION = "Header header\nstring id  # ID of the bond\nstring instance_id  # Unique ID for an individual in a bond\nbool active\n\n# Including the timeouts for the bond makes it easier to debug mis-matches\n# between the two sides.\nfloat32 heartbeat_timeout\nfloat32 heartbeat_period";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.lang.String getId();
  void setId(java.lang.String value);
  java.lang.String getInstanceId();
  void setInstanceId(java.lang.String value);
  boolean getActive();
  void setActive(boolean value);
  float getHeartbeatTimeout();
  void setHeartbeatTimeout(float value);
  float getHeartbeatPeriod();
  void setHeartbeatPeriod(float value);
}
