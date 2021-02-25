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
package dynamic_reconfigure;

public interface Config extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "dynamic_reconfigure/Config";
  static final java.lang.String _DEFINITION = "BoolParameter[] bools\nIntParameter[] ints\nStrParameter[] strs\nDoubleParameter[] doubles\nGroupState[] groups\n";
  java.util.List<dynamic_reconfigure.BoolParameter> getBools();
  void setBools(java.util.List<dynamic_reconfigure.BoolParameter> value);
  java.util.List<dynamic_reconfigure.IntParameter> getInts();
  void setInts(java.util.List<dynamic_reconfigure.IntParameter> value);
  java.util.List<dynamic_reconfigure.StrParameter> getStrs();
  void setStrs(java.util.List<dynamic_reconfigure.StrParameter> value);
  java.util.List<dynamic_reconfigure.DoubleParameter> getDoubles();
  void setDoubles(java.util.List<dynamic_reconfigure.DoubleParameter> value);
  java.util.List<dynamic_reconfigure.GroupState> getGroups();
  void setGroups(java.util.List<dynamic_reconfigure.GroupState> value);
}
