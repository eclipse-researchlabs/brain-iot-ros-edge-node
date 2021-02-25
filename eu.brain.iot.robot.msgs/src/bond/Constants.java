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
package bond;

public interface Constants extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "bond/Constants";
  static final java.lang.String _DEFINITION = "float32 DEAD_PUBLISH_PERIOD = 0.05\nfloat32 DEFAULT_CONNECT_TIMEOUT = 10.0\nfloat32 DEFAULT_HEARTBEAT_TIMEOUT = 4.0\nfloat32 DEFAULT_DISCONNECT_TIMEOUT = 2.0\nfloat32 DEFAULT_HEARTBEAT_PERIOD = 1.0\n\nstring DISABLE_HEARTBEAT_TIMEOUT_PARAM=/bond_disable_heartbeat_timeout";
  static final float DEAD_PUBLISH_PERIOD = 0.05f;
  static final float DEFAULT_CONNECT_TIMEOUT = 10.0f;
  static final float DEFAULT_HEARTBEAT_TIMEOUT = 4.0f;
  static final float DEFAULT_DISCONNECT_TIMEOUT = 2.0f;
  static final float DEFAULT_HEARTBEAT_PERIOD = 1.0f;
  static final java.lang.String DISABLE_HEARTBEAT_TIMEOUT_PARAM = "/bond_disable_heartbeat_timeout";
}
