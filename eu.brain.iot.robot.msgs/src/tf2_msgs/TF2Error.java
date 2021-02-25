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
package tf2_msgs;

public interface TF2Error extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "tf2_msgs/TF2Error";
  static final java.lang.String _DEFINITION = "uint8 NO_ERROR = 0\nuint8 LOOKUP_ERROR = 1\nuint8 CONNECTIVITY_ERROR = 2\nuint8 EXTRAPOLATION_ERROR = 3\nuint8 INVALID_ARGUMENT_ERROR = 4\nuint8 TIMEOUT_ERROR = 5\nuint8 TRANSFORM_ERROR = 6\n\nuint8 error\nstring error_string\n";
  static final byte NO_ERROR = 0;
  static final byte LOOKUP_ERROR = 1;
  static final byte CONNECTIVITY_ERROR = 2;
  static final byte EXTRAPOLATION_ERROR = 3;
  static final byte INVALID_ARGUMENT_ERROR = 4;
  static final byte TIMEOUT_ERROR = 5;
  static final byte TRANSFORM_ERROR = 6;
  byte getError();
  void setError(byte value);
  java.lang.String getErrorString();
  void setErrorString(java.lang.String value);
}
