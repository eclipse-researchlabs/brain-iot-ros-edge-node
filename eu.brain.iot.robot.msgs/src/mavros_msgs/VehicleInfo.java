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

public interface VehicleInfo extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/VehicleInfo";
  static final java.lang.String _DEFINITION = "# Vehicle Info msg\n\nstd_msgs/Header header\n\nuint8 HAVE_INFO_HEARTBEAT = 1\nuint8 HAVE_INFO_AUTOPILOT_VERSION = 2\nuint8 available_info\t\t# Bitmap shows what info is available\n\n# Vehicle address\nuint8 sysid                     # SYSTEM ID\nuint8 compid                    # COMPONENT ID\n\n# -*- Heartbeat info -*-\nuint8 autopilot                 # MAV_AUTOPILOT\nuint8 type                      # MAV_TYPE\nuint8 system_status             # MAV_STATE\nuint8 base_mode\nuint32 custom_mode\nstring mode                     # MAV_MODE string\nuint32 mode_id                  # MAV_MODE number\n\n# -*- Autopilot version -*-\nuint64 capabilities             # MAV_PROTOCOL_CAPABILITY\nuint32 flight_sw_version        # Firmware version number\nuint32 middleware_sw_version    # Middleware version number\nuint32 os_sw_version            # Operating system version number\nuint32 board_version            # HW / board version (last 8 bytes should be silicon ID, if any)\nuint16 vendor_id                # ID of the board vendor\nuint16 product_id               # ID of the product\nuint64 uid                      # UID if provided by hardware\n";
  static final byte HAVE_INFO_HEARTBEAT = 1;
  static final byte HAVE_INFO_AUTOPILOT_VERSION = 2;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getAvailableInfo();
  void setAvailableInfo(byte value);
  byte getSysid();
  void setSysid(byte value);
  byte getCompid();
  void setCompid(byte value);
  byte getAutopilot();
  void setAutopilot(byte value);
  byte getType();
  void setType(byte value);
  byte getSystemStatus();
  void setSystemStatus(byte value);
  byte getBaseMode();
  void setBaseMode(byte value);
  int getCustomMode();
  void setCustomMode(int value);
  java.lang.String getMode();
  void setMode(java.lang.String value);
  int getModeId();
  void setModeId(int value);
  long getCapabilities();
  void setCapabilities(long value);
  int getFlightSwVersion();
  void setFlightSwVersion(int value);
  int getMiddlewareSwVersion();
  void setMiddlewareSwVersion(int value);
  int getOsSwVersion();
  void setOsSwVersion(int value);
  int getBoardVersion();
  void setBoardVersion(int value);
  short getVendorId();
  void setVendorId(short value);
  short getProductId();
  void setProductId(short value);
  long getUid();
  void setUid(long value);
}
