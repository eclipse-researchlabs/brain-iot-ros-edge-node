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
package robotnik_fms_msgs;

public interface PresenceSensor extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/PresenceSensor";
  static final java.lang.String _DEFINITION = "int32 id\nstring zone\nstring name\nstring description\n# Valid Values Load, Unload, Load/Unload\nstring type\nbool enabled\nbool value\n# to be used to store that a change has been detected and managed\nbool ack_change\n";
  int getId();
  void setId(int value);
  java.lang.String getZone();
  void setZone(java.lang.String value);
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getDescription();
  void setDescription(java.lang.String value);
  java.lang.String getType();
  void setType(java.lang.String value);
  boolean getEnabled();
  void setEnabled(boolean value);
  boolean getValue();
  void setValue(boolean value);
  boolean getAckChange();
  void setAckChange(boolean value);
}
