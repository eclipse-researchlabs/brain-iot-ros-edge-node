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

public interface GetModelPropertiesResponse extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/GetModelPropertiesResponse";
  static final java.lang.String _DEFINITION = "string parent_model_name             # parent model\nstring canonical_body_name           # name of canonical body, body names are prefixed by model name, e.g. pr2::base_link\nstring[] body_names                  # list of bodies, body names are prefixed by model name, e.g. pr2::base_link\nstring[] geom_names                  # list of geoms\nstring[] joint_names                 # list of joints attached to the model\nstring[] child_model_names           # list of child models\nbool is_static                       # returns true if model is static\nbool success                         # return true if get successful\nstring status_message                # comments if available";
  java.lang.String getParentModelName();
  void setParentModelName(java.lang.String value);
  java.lang.String getCanonicalBodyName();
  void setCanonicalBodyName(java.lang.String value);
  java.util.List<java.lang.String> getBodyNames();
  void setBodyNames(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getGeomNames();
  void setGeomNames(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getJointNames();
  void setJointNames(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getChildModelNames();
  void setChildModelNames(java.util.List<java.lang.String> value);
  boolean getIsStatic();
  void setIsStatic(boolean value);
  boolean getSuccess();
  void setSuccess(boolean value);
  java.lang.String getStatusMessage();
  void setStatusMessage(java.lang.String value);
}
