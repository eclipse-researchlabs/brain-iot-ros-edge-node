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
package gazebo_msgs;

public interface GetWorldPropertiesResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/GetWorldPropertiesResponse";
  static final java.lang.String _DEFINITION = "float64 sim_time                     # current sim time\nstring[] model_names                 # list of models in the world\nbool rendering_enabled               # if X is used\nbool success                         # return true if get successful\nstring status_message                # comments if available";
  double getSimTime();
  void setSimTime(double value);
  java.util.List<java.lang.String> getModelNames();
  void setModelNames(java.util.List<java.lang.String> value);
  boolean getRenderingEnabled();
  void setRenderingEnabled(boolean value);
  boolean getSuccess();
  void setSuccess(boolean value);
  java.lang.String getStatusMessage();
  void setStatusMessage(java.lang.String value);
}
