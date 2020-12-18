package be.iminds.iot.ros.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
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

@Component(
		immediate=true,
		service = {RoscoreConfiguartor.class}
)
public class RoscoreConfiguartor {
	
//	private BundleContext context;
	private ConfigurationAdmin ca;
	private Configuration roscoreConfig;
	
	private ExecutorService executor;
	
	private Map<String, String> map = new HashMap<>();
	
	@Activate
	void activate( BundleContext context, Map<String, Object> config ) throws Exception {
		System.out.println("RoscoreConfiguartor activating....... " );
		
		executor = Executors.newSingleThreadExecutor();
		BufferedReader reader = null;
		String line;
		try {
			try {
				Process proc = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c", "pwd" });
				String line2 = "";
				BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while ((line2 = input.readLine()) != null) {
					System.out.println("Current Path = "+line2);
				}
				input.close();
				proc.destroy();
				proc = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			String roscoreConf = context.getProperty("roscore.config");
			
			FileInputStream fstream;
			if(roscoreConf!=null)
				fstream = new FileInputStream(roscoreConf);
			else
				fstream = new FileInputStream("/home/rui/git/ros-edge-node/eu.brain.iot.robot.service/resources/rosConfig.txt");
			
			
			
			reader = new BufferedReader(new InputStreamReader(fstream));
			line = reader.readLine();
			System.out.println("\n roscore config file:");
			while(line!=null && line.trim() != null) {
				
				if (line.trim().isEmpty() || line.startsWith("#") || line.startsWith("//")){
					line = reader.readLine();
					
			    } else {
			    	if(line.contains("=")) {
			    		System.out.println(line);
				    	String[] strs = line.trim().split("=");
				    	if(strs.length==2) {
				    		map.put(strs[0], strs[1]);
				    	}
				    	line = reader.readLine();
			    	} else
			    		line = reader.readLine();
			    }
			}
			reader.close();
			
		} catch (IOException ex) {
			if(reader != null) {
				reader.close();
			}
			ex.printStackTrace();
		}
		
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
	
	@Reference
	void setConfigurationAdmin(ConfigurationAdmin ca){
		this.ca = ca;
	}

}
