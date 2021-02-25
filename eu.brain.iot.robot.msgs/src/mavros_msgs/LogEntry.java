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

public interface LogEntry extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/LogEntry";
  static final java.lang.String _DEFINITION = "# Information about a single log\n#\n#  :id: - log id\n#  :num_logs: - total number of logs\n#  :last_log_num: - id of last log\n#  :time_utc: - UTC timestamp of log (ros::Time(0) if not available)\n#  :size: - size of log in bytes (may be approximate)\n\nstd_msgs/Header header\n\nuint16 id\nuint16 num_logs\nuint16 last_log_num\ntime time_utc\nuint32 size\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  short getId();
  void setId(short value);
  short getNumLogs();
  void setNumLogs(short value);
  short getLastLogNum();
  void setLastLogNum(short value);
  org.ros.message.Time getTimeUtc();
  void setTimeUtc(org.ros.message.Time value);
  int getSize();
  void setSize(int value);
}
