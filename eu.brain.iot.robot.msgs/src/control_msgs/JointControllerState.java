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
package control_msgs;

public interface JointControllerState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/JointControllerState";
  static final java.lang.String _DEFINITION = "Header header\nfloat64 set_point\nfloat64 process_value\nfloat64 process_value_dot\nfloat64 error\nfloat64 time_step\nfloat64 command\nfloat64 p\nfloat64 i\nfloat64 d\nfloat64 i_clamp\nbool antiwindup\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  double getSetPoint();
  void setSetPoint(double value);
  double getProcessValue();
  void setProcessValue(double value);
  double getProcessValueDot();
  void setProcessValueDot(double value);
  double getError();
  void setError(double value);
  double getTimeStep();
  void setTimeStep(double value);
  double getCommand();
  void setCommand(double value);
  double getP();
  void setP(double value);
  double getI();
  void setI(double value);
  double getD();
  void setD(double value);
  double getIClamp();
  void setIClamp(double value);
  boolean getAntiwindup();
  void setAntiwindup(boolean value);
}
