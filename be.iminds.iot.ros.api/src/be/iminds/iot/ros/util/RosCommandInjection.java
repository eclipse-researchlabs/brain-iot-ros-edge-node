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
package be.iminds.iot.ros.util;

import java.io.File;
import java.util.Dictionary;
import java.util.Properties;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true,
	property = {"osgi.command.scope=ros", 
				"osgi.command.function=roslaunch",
				"osgi.command.function=rosrun",
				"osgi.command.function=catkinBuild"},
	service = {RosCommandInjection.class}
)
public class RosCommandInjection {
	private ComponentFactory rosCommandFactory;

	// roslaunch command with parameters
	public void roslaunch(String rosWorkspace, String pkg, String node, String... parameters) {
		if (!rosWorkspace.endsWith(File.separator)) {
			rosWorkspace += File.separator;
		}
		Properties props = new Properties();
		props.put("rosWorkspace", rosWorkspace);
		props.put("ros.package", pkg);
		props.put("ros.node", node);
		if (parameters != null) {
			for (String parameter : parameters) {
				if (!parameter.contains(":=")) {
					System.out.println("Invalid parameter: " + parameter);
					continue;
				} else {
					String[] parts = parameter.split(":=");
					props.put(parts[0], parts[1]);
				}
			}
		}
		ComponentInstance commandInstance = null;
		try {
			commandInstance = this.rosCommandFactory.newInstance((Dictionary) props);
			RosCommand roslaunch = (RosCommand) commandInstance.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (commandInstance != null)
				commandInstance.dispose();
		}

	}

	// rosrun command with parameters and a world file
	public void rosrun(String rosWorkspace, String pkg, String node, String... parameters) {
		if (!rosWorkspace.endsWith(File.separator)) {
			rosWorkspace += File.separator;
		}
		Properties props = new Properties();
		props.put("rosWorkspace", rosWorkspace);
		props.put("ros.package", pkg);
		props.put("ros.node", node);
		if (parameters != null) {
			for (String parameter : parameters) {
				if (parameter.contains(":=")) {
					String[] parts = parameter.split(":=");
					props.put(parts[0], parts[1]);
				} else if (parameter.contains(".world")) {
					props.put("worldFile.path", parameter);
				} else {
					System.out.println("Invalid parameter: " + parameter);
					continue;
				}

			}
		}
		ComponentInstance instance = null;
		try {
			instance = this.rosCommandFactory.newInstance((Dictionary) props);
			RosCommand rosrun = (RosCommand) instance.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			instance.dispose();
		}
	}

	public void catkinBuild(String workspace) {
		System.out.println("start building workspace: " + workspace);
		if (!workspace.endsWith(File.separator)) {
			workspace += File.separator;
		}
		Properties props = new Properties();
		props.put("ros.buildWorkspace", workspace);
		ComponentInstance instance = null;
		try {
			instance = this.rosCommandFactory.newInstance((Dictionary) props);
			RosCommand catkinBuild = (RosCommand) instance.getInstance();
			catkinBuild.buildWorkspace(); // Guarantee building process finished before instance destroy
		} catch (Exception e) {
			System.err.println("Error when building workspace: " + workspace);
			e.printStackTrace();
		} finally {
			if (instance != null)
				instance.dispose();
		}

	}

	@Reference(target = "(component.factory=it.ismb.pert.cpswarm.rosCommand.factory)")
	public void getRosCommandFactory(final ComponentFactory s) {
		this.rosCommandFactory = s;
	}
}
