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
package robotnik_msgs;

public interface Data extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/Data";
  static final java.lang.String _DEFINITION = "string interface\nfloat32 bytes_sent\nfloat32 bytes_received\nstring units_sent\nstring units_received\nint32 packages_sent\nint32 packages_received\n\n";
  java.lang.String getInterface();
  void setInterface(java.lang.String value);
  float getBytesSent();
  void setBytesSent(float value);
  float getBytesReceived();
  void setBytesReceived(float value);
  java.lang.String getUnitsSent();
  void setUnitsSent(java.lang.String value);
  java.lang.String getUnitsReceived();
  void setUnitsReceived(java.lang.String value);
  int getPackagesSent();
  void setPackagesSent(int value);
  int getPackagesReceived();
  void setPackagesReceived(int value);
}
