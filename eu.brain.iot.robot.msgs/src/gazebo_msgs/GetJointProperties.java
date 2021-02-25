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

public interface GetJointProperties extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/GetJointProperties";
  static final java.lang.String _DEFINITION = "string joint_name                    # name of joint\n---\n# joint type\nuint8 type\nuint8 REVOLUTE    = 0                # single DOF\nuint8 CONTINUOUS  = 1                # single DOF (revolute w/o joints)\nuint8 PRISMATIC   = 2                # single DOF\nuint8 FIXED       = 3                # 0 DOF\nuint8 BALL        = 4                # 3 DOF\nuint8 UNIVERSAL   = 5                # 2 DOF\n# dynamics properties\nfloat64[] damping\n# joint state\nfloat64[] position\nfloat64[] rate\n# service return status\nbool success                         # return true if get successful\nstring status_message                # comments if available\n";
}
