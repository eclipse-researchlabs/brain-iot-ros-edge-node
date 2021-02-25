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

public interface LaserMode extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "robotnik_msgs/LaserMode";
  static final java.lang.String _DEFINITION = "string STANDARD=standard\nstring DOCKING_STATION=docking_station\nstring CART=cart\nstring CART_DOCKING_CART=cart_docking_cart\nstring DOCKING_CART=docking_cart\nstring INVALID=invalid\n\nstring name\n";
  static final java.lang.String STANDARD = "standard";
  static final java.lang.String DOCKING_STATION = "docking_station";
  static final java.lang.String CART = "cart";
  static final java.lang.String CART_DOCKING_CART = "cart_docking_cart";
  static final java.lang.String DOCKING_CART = "docking_cart";
  static final java.lang.String INVALID = "invalid";
  java.lang.String getName();
  void setName(java.lang.String value);
}
