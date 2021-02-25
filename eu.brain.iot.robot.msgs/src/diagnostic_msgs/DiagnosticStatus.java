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

public interface DiagnosticStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "diagnostic_msgs/DiagnosticStatus";
  static final java.lang.String _DEFINITION = "# This message holds the status of an individual component of the robot.\n# \n\n# Possible levels of operations\nbyte OK=0\nbyte WARN=1\nbyte ERROR=2\nbyte STALE=3\n\nbyte level # level of operation enumerated above \nstring name # a description of the test/component reporting\nstring message # a description of the status\nstring hardware_id # a hardware unique string\nKeyValue[] values # an array of values associated with the status\n\n";
  static final byte OK = 0;
  static final byte WARN = 1;
  static final byte ERROR = 2;
  static final byte STALE = 3;
  byte getLevel();
  void setLevel(byte value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
  java.lang.String getHardwareId();
  void setHardwareId(java.lang.String value);
  java.util.List<diagnostic_msgs.KeyValue> getValues();
  void setValues(java.util.List<diagnostic_msgs.KeyValue> value);
}
