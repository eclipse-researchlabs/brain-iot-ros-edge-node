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

public interface VersionInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "kobuki_msgs/VersionInfo";
  static final java.lang.String _DEFINITION = "# Contains unique device id, version info and available features for the kobuki platform.\n# Useful for compatibility checking and introspection\n\nstring hardware   # <major>.<minor>.<patch>\nstring firmware   # <major>.<minor>.<patch>\nstring software   # Still to decide how it will look\nuint32[] udid\n\n# Bitmask that specifies the available features in the firmware and/or driver\nuint64 features\n\n# The following represent the bit fields corresponding to bits in the features value\n\n# Provided firmware kobuki_firmware_1.1.1.hex\nuint64 SMOOTH_MOVE_START    = 0000000000000001\nuint64 GYROSCOPE_3D_DATA    = 0000000000000002\n";
  static final long SMOOTH_MOVE_START = 1;
  static final long GYROSCOPE_3D_DATA = 2;
  java.lang.String getHardware();
  void setHardware(java.lang.String value);
  java.lang.String getFirmware();
  void setFirmware(java.lang.String value);
  java.lang.String getSoftware();
  void setSoftware(java.lang.String value);
  int[] getUdid();
  void setUdid(int[] value);
  long getFeatures();
  void setFeatures(long value);
}
