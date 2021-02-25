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

public interface SetLightsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_fms_msgs/SetLightsRequest";
  static final java.lang.String _DEFINITION = "bool setRed\nbool setGreen\nbool blinkRed\nbool blinkGreen\n\n";
  boolean getSetRed();
  void setSetRed(boolean value);
  boolean getSetGreen();
  void setSetGreen(boolean value);
  boolean getBlinkRed();
  void setBlinkRed(boolean value);
  boolean getBlinkGreen();
  void setBlinkGreen(boolean value);
}
