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

public interface TimesyncStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/TimesyncStatus";
  static final java.lang.String _DEFINITION = "# Status of the MAVLink time synchroniser\n\nstd_msgs/Header header\nuint64 remote_timestamp_ns\t\t# remote system timestamp (nanoseconds)\nint64 observed_offset_ns\t\t# raw time offset directly observed from this timesync packet (nanoseconds)\nint64 estimated_offset_ns\t\t# smoothed time offset between companion system and Mavros (nanoseconds)\nfloat32 round_trip_time_ms\t\t# round trip time of this timesync packet (milliseconds)";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  long getRemoteTimestampNs();
  void setRemoteTimestampNs(long value);
  long getObservedOffsetNs();
  void setObservedOffsetNs(long value);
  long getEstimatedOffsetNs();
  void setEstimatedOffsetNs(long value);
  float getRoundTripTimeMs();
  void setRoundTripTimeMs(float value);
}
