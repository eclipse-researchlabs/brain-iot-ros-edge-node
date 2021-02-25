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

public interface MotorStatus extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/MotorStatus";
  static final java.lang.String _DEFINITION = "string state\nstring status\nstring communicationstatus\nstring statusword\nstring driveflags\nstring[] activestatusword\nstring[] activedriveflags\nint32 digitaloutputs\nint32 digitalinputs\nfloat32 averagecurrent\nfloat32[] analoginputs\n\n";
  java.lang.String getState();
  void setState(java.lang.String value);
  java.lang.String getStatus();
  void setStatus(java.lang.String value);
  java.lang.String getCommunicationstatus();
  void setCommunicationstatus(java.lang.String value);
  java.lang.String getStatusword();
  void setStatusword(java.lang.String value);
  java.lang.String getDriveflags();
  void setDriveflags(java.lang.String value);
  java.util.List<java.lang.String> getActivestatusword();
  void setActivestatusword(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getActivedriveflags();
  void setActivedriveflags(java.util.List<java.lang.String> value);
  int getDigitaloutputs();
  void setDigitaloutputs(int value);
  int getDigitalinputs();
  void setDigitalinputs(int value);
  float getAveragecurrent();
  void setAveragecurrent(float value);
  float[] getAnaloginputs();
  void setAnaloginputs(float[] value);
}
