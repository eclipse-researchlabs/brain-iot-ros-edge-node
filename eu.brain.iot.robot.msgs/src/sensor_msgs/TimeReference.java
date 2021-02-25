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
package sensor_msgs;

public interface TimeReference extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/TimeReference";
  static final java.lang.String _DEFINITION = "# Measurement from an external time source not actively synchronized with the system clock.\n\nHeader header    # stamp is system time for which measurement was valid\n                 # frame_id is not used \n\ntime   time_ref  # corresponding time from this external source\nstring source    # (optional) name of time source\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  org.ros.message.Time getTimeRef();
  void setTimeRef(org.ros.message.Time value);
  java.lang.String getSource();
  void setSource(java.lang.String value);
}
