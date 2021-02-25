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
package diagnostic_msgs;

public interface DiagnosticArray extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "diagnostic_msgs/DiagnosticArray";
  static final java.lang.String _DEFINITION = "# This message is used to send diagnostic information about the state of the robot\nHeader header #for timestamp\nDiagnosticStatus[] status # an array of components being reported on";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  java.util.List<diagnostic_msgs.DiagnosticStatus> getStatus();
  void setStatus(java.util.List<diagnostic_msgs.DiagnosticStatus> value);
}
