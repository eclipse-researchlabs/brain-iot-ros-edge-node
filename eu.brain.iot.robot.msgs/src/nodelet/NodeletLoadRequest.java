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
package nodelet;

public interface NodeletLoadRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "nodelet/NodeletLoadRequest";
  static final java.lang.String _DEFINITION = "string name\nstring type\nstring[] remap_source_args\nstring[] remap_target_args\nstring[] my_argv\n\nstring bond_id\n";
  java.lang.String getName();
  void setName(java.lang.String value);
  java.lang.String getType();
  void setType(java.lang.String value);
  java.util.List<java.lang.String> getRemapSourceArgs();
  void setRemapSourceArgs(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getRemapTargetArgs();
  void setRemapTargetArgs(java.util.List<java.lang.String> value);
  java.util.List<java.lang.String> getMyArgv();
  void setMyArgv(java.util.List<java.lang.String> value);
  java.lang.String getBondId();
  void setBondId(java.lang.String value);
}
