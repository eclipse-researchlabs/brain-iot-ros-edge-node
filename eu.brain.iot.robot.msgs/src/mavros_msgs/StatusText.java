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

public interface StatusText extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "mavros_msgs/StatusText";
  static final java.lang.String _DEFINITION = "# STATUSTEXT message representation\n# https://mavlink.io/en/messages/common.html#STATUSTEXT\n\n# Severity levels\nuint8 EMERGENCY = 0\nuint8 ALERT = 1\nuint8 CRITICAL = 2\nuint8 ERROR = 3\nuint8 WARNING = 4\nuint8 NOTICE = 5\nuint8 INFO = 6\nuint8 DEBUG = 7\n\n# Fields\nstd_msgs/Header header\nuint8 severity\nstring text\n";
  static final byte EMERGENCY = 0;
  static final byte ALERT = 1;
  static final byte CRITICAL = 2;
  static final byte ERROR = 3;
  static final byte WARNING = 4;
  static final byte NOTICE = 5;
  static final byte INFO = 6;
  static final byte DEBUG = 7;
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  byte getSeverity();
  void setSeverity(byte value);
  java.lang.String getText();
  void setText(java.lang.String value);
}
