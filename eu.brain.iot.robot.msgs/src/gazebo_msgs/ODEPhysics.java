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

public interface ODEPhysics extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "gazebo_msgs/ODEPhysics";
  static final java.lang.String _DEFINITION = "bool auto_disable_bodies           # enable auto disabling of bodies, default false\nuint32 sor_pgs_precon_iters        # preconditioning inner iterations when uisng projected Gauss Seidel\nuint32 sor_pgs_iters               # inner iterations when uisng projected Gauss Seidel\nfloat64 sor_pgs_w                  # relaxation parameter when using projected Gauss Seidel, 1 = no relaxation\nfloat64 sor_pgs_rms_error_tol      # rms error tolerance before stopping inner iterations\nfloat64 contact_surface_layer      # contact \"dead-band\" width\nfloat64 contact_max_correcting_vel # contact maximum correction velocity\nfloat64 cfm                        # global constraint force mixing\nfloat64 erp                        # global error reduction parameter\nuint32 max_contacts                # maximum contact joints between two geoms\n";
  boolean getAutoDisableBodies();
  void setAutoDisableBodies(boolean value);
  int getSorPgsPreconIters();
  void setSorPgsPreconIters(int value);
  int getSorPgsIters();
  void setSorPgsIters(int value);
  double getSorPgsW();
  void setSorPgsW(double value);
  double getSorPgsRmsErrorTol();
  void setSorPgsRmsErrorTol(double value);
  double getContactSurfaceLayer();
  void setContactSurfaceLayer(double value);
  double getContactMaxCorrectingVel();
  void setContactMaxCorrectingVel(double value);
  double getCfm();
  void setCfm(double value);
  double getErp();
  void setErp(double value);
  int getMaxContacts();
  void setMaxContacts(int value);
}
