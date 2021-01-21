package be.iminds.iot.ros.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = { RoscoreConfiguartor.class })
public class RoscoreConfiguartor {

	// private BundleContext context;
	private ConfigurationAdmin ca;
	private Configuration roscoreConfig;

	private ExecutorService executor;

	private Map<String, String> map = new HashMap<>();

	@Activate
	void activate(BundleContext context, Map<String, Object> config) throws Exception {
		System.out.println("RoscoreConfiguartor activating....... ");

		executor = Executors.newSingleThreadExecutor();
		BufferedReader reader = null;
		String line;

		try {
			Process proc = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", "pwd" });
			String line2 = "";
			BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((line2 = input.readLine()) != null) {
				System.out.println("Current Path = " + line2);
			}
			input.close();
			proc.destroy();
			proc = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		File file;
		String roscoreConfFolder;
		
		roscoreConfFolder = context.getProperty("roscoreConfFolder");
		if (roscoreConfFolder == null)
		//	roscoreConfFolder = "/home/rui/git/ros-edge-node/eu.brain.iot.robot.service/resources";
			roscoreConfFolder = System.getenv("HOME");
		
		if(!roscoreConfFolder.endsWith(File.separator)) {
			roscoreConfFolder+=File.separator;
		}
		
		file = new File(roscoreConfFolder);
		
		// This filter will only include files starting with rosConfig
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File f, String name) {
				return name.startsWith("rosConfig");
			}
		};

		String[] pathnames = file.list(filter);

		FileInputStream fstream;
		int flag = 0;

		for (String configFile : pathnames) {   // analysing multiple config files is not necessary, because RobotService will only refer to one ROS service.  
			flag = 0;
			if (map.size() != 0)
				map.clear();
			try {
				fstream = new FileInputStream(roscoreConfFolder+configFile); // absolute path
				reader = new BufferedReader(new InputStreamReader(fstream));
				line = reader.readLine();
				System.out.println("\nroscore config file: " + roscoreConfFolder+configFile);
				while (line != null && line.trim() != null) {

					if (line.trim().isEmpty() || line.startsWith("#") || line.startsWith("//")) {
						line = reader.readLine();

					} else {
						if (line.contains("=")) {
							System.out.println(line);
							String[] strs = line.trim().split("=");
							if (strs.length == 2) {
								map.put(strs[0], strs[1]);
							}
							line = reader.readLine();
						} else
							line = reader.readLine();
					}
				}
				reader.close();
				flag = 1;

			} catch (IOException ex) {
				if (reader != null) {
					reader.close();
				}
				ex.printStackTrace();
			}
			System.out.println("successfully read file "+configFile +" with flag = "+flag);

			if (flag == 1) {
				try {
					roscoreConfig = ca.createFactoryConfiguration("eu.brain.iot.robotics.roscore.ROS", null);
					Properties props = new Properties();

					for (Map.Entry<String, String> entry : map.entrySet()) {
						props.put(entry.getKey(), entry.getValue());
					}

					roscoreConfig.update((Dictionary) props);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	@Reference
	void setConfigurationAdmin(ConfigurationAdmin ca) {
		this.ca = ca;
	}

}
