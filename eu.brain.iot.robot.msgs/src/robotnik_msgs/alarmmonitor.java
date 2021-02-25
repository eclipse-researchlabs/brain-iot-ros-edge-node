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

public interface alarmmonitor extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/alarmmonitor";
  static final java.lang.String _DEFINITION = "# type: error, warning, event\nstring type\n# displaing number for monitoring\nint32 display\n# component\nstring component\n# To be shown in HMI\nbool hmi\n# group: alarm group all alarms of same group will be reset at same time\nstring group\n# text: more explained information about alarm, should include group, time, conditions, etc\nstring text\n# seconds active\nuint64 seconds_active\n";
  java.lang.String getType();
  void setType(java.lang.String value);
  int getDisplay();
  void setDisplay(int value);
  java.lang.String getComponent();
  void setComponent(java.lang.String value);
  boolean getHmi();
  void setHmi(boolean value);
  java.lang.String getGroup();
  void setGroup(java.lang.String value);
  java.lang.String getText();
  void setText(java.lang.String value);
  long getSecondsActive();
  void setSecondsActive(long value);
}
