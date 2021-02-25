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

public interface LaserStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/LaserStatus";
  static final java.lang.String _DEFINITION = "string name\nbool detecting_obstacles\nbool contaminated\nbool free_warning\n# one input per each warning zone.\n# first areas are closer to the robot (i.e. more restrictive)\nbool[] warning_zones \n";
  java.lang.String getName();
  void setName(java.lang.String value);
  boolean getDetectingObstacles();
  void setDetectingObstacles(boolean value);
  boolean getContaminated();
  void setContaminated(boolean value);
  boolean getFreeWarning();
  void setFreeWarning(boolean value);
  boolean[] getWarningZones();
  void setWarningZones(boolean[] value);
}
