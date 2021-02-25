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
package procedures_msgs;

public interface ProcedureResult extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "procedures_msgs/ProcedureResult";
  static final java.lang.String _DEFINITION = "# RESULT allowed values. Defined as STRING because it is easier to identify them in a raw message\nstring OK=ok\nstring ERROR=error\n\nstring result\nstring message\n";
  static final java.lang.String OK = "ok";
  static final java.lang.String ERROR = "error";
  java.lang.String getResult();
  void setResult(java.lang.String value);
  java.lang.String getMessage();
  void setMessage(java.lang.String value);
}
