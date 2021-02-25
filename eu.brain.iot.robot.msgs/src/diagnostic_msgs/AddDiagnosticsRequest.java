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
package diagnostic_msgs;

public interface AddDiagnosticsRequest extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "diagnostic_msgs/AddDiagnosticsRequest";
  static final java.lang.String _DEFINITION = "# This service is used as part of the process for loading analyzers at runtime,\n# and should be used by a loader script or program, not as a standalone service.\n# Information about dynamic addition of analyzers can be found at\n# http://wiki.ros.org/diagnostics/Tutorials/Adding%20Analyzers%20at%20Runtime\n\n# The load_namespace parameter defines the namespace where parameters for the\n# initialization of analyzers in the diagnostic aggregator have been loaded. The\n# value should be a global name (i.e. /my/name/space), not a relative\n# (my/name/space) or private (~my/name/space) name. Analyzers will not be added\n# if a non-global name is used. The call will also fail if the namespace\n# contains parameters that follow a namespace structure that does not conform to\n# that expected by the analyzer definitions. See\n# http://wiki.ros.org/diagnostics/Tutorials/Configuring%20Diagnostic%20Aggregators\n# and http://wiki.ros.org/diagnostics/Tutorials/Using%20the%20GenericAnalyzer\n# for examples of the structure of yaml files which are expected to have been\n# loaded into the namespace.\nstring load_namespace\n";
  java.lang.String getLoadNamespace();
  void setLoadNamespace(java.lang.String value);
}
