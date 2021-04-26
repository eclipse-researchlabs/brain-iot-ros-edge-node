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
package be.iminds.iot.ros.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = { RoscoreConfiguartor.class })
public class RoscoreConfiguartor {

	private ConfigurationAdmin ca;
	private Configuration roscoreConfig;

	private ExecutorService executor;

	private Map<String, String> map = new HashMap<>();
	
	private  Logger logger;
	private String resourcesPath;
	
	@ObjectClassDefinition
	public static @interface Config {
		String resourcesPath() default "/opt/fabric/resources/"; // "/opt/fabric/resources/";  /home/rui/resources
	}
	
	@Activate
	void activate(BundleContext context, Config config) throws Exception {
		this.resourcesPath = config.resourcesPath();
		if(resourcesPath != null && resourcesPath.length()>0) {
			if(!resourcesPath.endsWith(File.separator)) {
				resourcesPath+=File.separator;
			}
			
		}
		System.setProperty("logback.configurationFile", resourcesPath+"logback.xml");	
		
		logger = (Logger) LoggerFactory.getLogger(RoscoreConfiguartor.class.getSimpleName());
	
		logger.info("{} activating....... ", "RoscoreConfiguartor");

		executor = Executors.newSingleThreadExecutor();
		BufferedReader reader = null;
		String line;
		File file;
		
		file = new File(resourcesPath);
		
		// This filter will only include files starting with rosConfig
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File f, String name) {
				return name.startsWith("rosConfig");
			}
		};

		String[] pathnames = file.list(filter);

		FileInputStream fstream = null;
		int flag = 0;

		for (String configFile : pathnames) {   // analysing multiple config files is not necessary, because RobotService will only refer to one ROS service.  
			flag = 0;
			if (map.size() != 0)
				map.clear();
			try {
				fstream = new FileInputStream(resourcesPath+configFile); // absolute path
				reader = new BufferedReader(new InputStreamReader(fstream));
				line = reader.readLine();
				while (line != null && line.trim() != null) {

					if (line.trim().isEmpty() || line.startsWith("#") || line.startsWith("//")) {
						line = reader.readLine();

					} else {
						if (line.contains("=")) {
							logger.info(line);
							String[] strs = line.trim().split("=");
							if (strs.length == 2) {
								map.put(strs[0], strs[1]);
							}
							line = reader.readLine();
						} else
							line = reader.readLine();
					}
				}
				
				flag = 1;

			} catch (IOException e) {
				logger.error("\nRoscoreConfiguartor read rosConfig.txt Exception: {}", ExceptionUtils.getStackTrace(e));
			} finally {
				if (reader != null) {
					reader.close();
					fstream.close();
				}
			}
			
			if (flag == 1) {
				logger.info("successfully read file "+configFile +" with flag = "+flag);
				try {
					roscoreConfig = ca.getConfiguration("eu.brain.iot.ros.edge.node.RosEdgeNode", null);
					Properties props = new Properties();

					for (Map.Entry<String, String> entry : map.entrySet()) {
						props.put(entry.getKey(), entry.getValue());
					}
					roscoreConfig.update((Dictionary) props);
					logger.info("successfully create Factory Configuration for roscore");
					
				} catch (Exception e) {
					logger.error("\nRoscoreConfiguartor Exception: {}", ExceptionUtils.getStackTrace(e));
				}
			}
		}
	}

	@Reference
	void setConfigurationAdmin(ConfigurationAdmin ca) {
		this.ca = ca;
	}
	
	@Deactivate
	void stop() {
	
		logger.info("------------  RoscoreConfiguartor is deactivated----------------");
	}

}
