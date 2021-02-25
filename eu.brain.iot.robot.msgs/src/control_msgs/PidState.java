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

public interface PidState extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "control_msgs/PidState";
  static final java.lang.String _DEFINITION = "Header header\nduration timestep\nfloat64 error\nfloat64 error_dot\nfloat64 p_error\nfloat64 i_error\nfloat64 d_error\nfloat64 p_term\nfloat64 i_term\nfloat64 d_term\nfloat64 i_max\nfloat64 i_min\nfloat64 output\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  org.ros.message.Duration getTimestep();
  void setTimestep(org.ros.message.Duration value);
  double getError();
  void setError(double value);
  double getErrorDot();
  void setErrorDot(double value);
  double getPError();
  void setPError(double value);
  double getIError();
  void setIError(double value);
  double getDError();
  void setDError(double value);
  double getPTerm();
  void setPTerm(double value);
  double getITerm();
  void setITerm(double value);
  double getDTerm();
  void setDTerm(double value);
  double getIMax();
  void setIMax(double value);
  double getIMin();
  void setIMin(double value);
  double getOutput();
  void setOutput(double value);
}
