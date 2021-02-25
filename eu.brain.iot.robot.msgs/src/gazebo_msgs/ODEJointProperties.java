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

public interface ODEJointProperties extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/ODEJointProperties";
  static final java.lang.String _DEFINITION = "# access to low level joint properties, change these at your own risk\nfloat64[] damping             # joint damping\nfloat64[] hiStop              # joint limit\nfloat64[] loStop              # joint limit\nfloat64[] erp                 # set joint erp\nfloat64[] cfm                 # set joint cfm\nfloat64[] stop_erp            # set joint erp for joint limit \"contact\" joint\nfloat64[] stop_cfm            # set joint cfm for joint limit \"contact\" joint\nfloat64[] fudge_factor        # joint fudge_factor applied at limits, see ODE manual for info.\nfloat64[] fmax                # ode joint param fmax\nfloat64[] vel                 # ode joint param vel\n";
  double[] getDamping();
  void setDamping(double[] value);
  double[] getHiStop();
  void setHiStop(double[] value);
  double[] getLoStop();
  void setLoStop(double[] value);
  double[] getErp();
  void setErp(double[] value);
  double[] getCfm();
  void setCfm(double[] value);
  double[] getStopErp();
  void setStopErp(double[] value);
  double[] getStopCfm();
  void setStopCfm(double[] value);
  double[] getFudgeFactor();
  void setFudgeFactor(double[] value);
  double[] getFmax();
  void setFmax(double[] value);
  double[] getVel();
  void setVel(double[] value);
}
