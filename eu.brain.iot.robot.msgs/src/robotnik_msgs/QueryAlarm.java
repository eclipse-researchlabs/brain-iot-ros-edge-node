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

public interface QueryAlarm extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/QueryAlarm";
  static final java.lang.String _DEFINITION = "int32 alm_id\nstring alm_component\nstring alm_type\nstring alm_group\nstring alm_description\nstring alm_start_time\nstring alm_end_time\n";
  int getAlmId();
  void setAlmId(int value);
  java.lang.String getAlmComponent();
  void setAlmComponent(java.lang.String value);
  java.lang.String getAlmType();
  void setAlmType(java.lang.String value);
  java.lang.String getAlmGroup();
  void setAlmGroup(java.lang.String value);
  java.lang.String getAlmDescription();
  void setAlmDescription(java.lang.String value);
  java.lang.String getAlmStartTime();
  void setAlmStartTime(java.lang.String value);
  java.lang.String getAlmEndTime();
  void setAlmEndTime(java.lang.String value);
}
