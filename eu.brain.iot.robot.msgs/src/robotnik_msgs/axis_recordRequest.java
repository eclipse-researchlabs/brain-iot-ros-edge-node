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

public interface axis_recordRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/axis_recordRequest";
  static final java.lang.String _DEFINITION = "# \n# RECORD = 1, STOP = 2\nint8 action\n# DIRECTORY TO SAVE THE VIDEO\nstring directory\n# PROFILE TO USE DURING THE RECORDING\nstring profile\n# id / name of the recording\nstring id\n";
  byte getAction();
  void setAction(byte value);
  java.lang.String getDirectory();
  void setDirectory(java.lang.String value);
  java.lang.String getProfile();
  void setProfile(java.lang.String value);
  java.lang.String getId();
  void setId(java.lang.String value);
}
