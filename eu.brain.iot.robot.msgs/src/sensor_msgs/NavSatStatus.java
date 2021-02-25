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

public interface NavSatStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "sensor_msgs/NavSatStatus";
  static final java.lang.String _DEFINITION = "# Navigation Satellite fix status for any Global Navigation Satellite System\n\n# Whether to output an augmented fix is determined by both the fix\n# type and the last time differential corrections were received.  A\n# fix is valid when status >= STATUS_FIX.\n\nint8 STATUS_NO_FIX =  -1        # unable to fix position\nint8 STATUS_FIX =      0        # unaugmented fix\nint8 STATUS_SBAS_FIX = 1        # with satellite-based augmentation\nint8 STATUS_GBAS_FIX = 2        # with ground-based augmentation\n\nint8 status\n\n# Bits defining which Global Navigation Satellite System signals were\n# used by the receiver.\n\nuint16 SERVICE_GPS =     1\nuint16 SERVICE_GLONASS = 2\nuint16 SERVICE_COMPASS = 4      # includes BeiDou.\nuint16 SERVICE_GALILEO = 8\n\nuint16 service\n";
  static final byte STATUS_NO_FIX = -1;
  static final byte STATUS_FIX = 0;
  static final byte STATUS_SBAS_FIX = 1;
  static final byte STATUS_GBAS_FIX = 2;
  static final short SERVICE_GPS = 1;
  static final short SERVICE_GLONASS = 2;
  static final short SERVICE_COMPASS = 4;
  static final short SERVICE_GALILEO = 8;
  byte getStatus();
  void setStatus(byte value);
  short getService();
  void setService(short value);
}
