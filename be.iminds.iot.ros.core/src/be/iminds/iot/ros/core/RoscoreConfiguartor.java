package be.iminds.iot.ros.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
	
	private static volatile BufferedWriter out = null;

	@Activate
	void activate(BundleContext context, Config config) throws Exception {
		this.resourcesPath = config.resourcesPath();
		if(resourcesPath != null && resourcesPath.length()>0) {
			if(!resourcesPath.endsWith(File.separator)) {
				resourcesPath+=File.separator;
			}
			
		}
		System.setProperty("logback.configurationFile", resourcesPath+"logback.xml");	
		
		FileWriter fr = null;
/*		RandomAccessFile writer = null;
		FileChannel channel = null;*/
		try {
	/*		writer = new RandomAccessFile(resourcesPath+"roscore1.log", "rw",StandardOpenOption.APPEND);
			channel = writer.getChannel();
			ByteBuffer buff = ByteBuffer.wrap("Hello world".getBytes(StandardCharsets.UTF_8));
			channel.write(buff);
	*/		
			
			fr = new FileWriter(resourcesPath+"roscore.log", true);

			out = new BufferedWriter(fr);
		
		logger = (Logger) LoggerFactory.getLogger(RoscoreConfiguartor.class.getSimpleName());
	
		logger.info("{} activating....... ", "RoscoreConfiguartor");
		
		out.write("RoscoreConfiguartor activating....... ");
	//	out.flush();

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

		FileInputStream fstream;
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
				reader.close();
				flag = 1;

			} catch (IOException e) {
				if (reader != null) {
					reader.close();
				}
				logger.error("\nRoscoreConfiguartor read rosConfig.txt Exception: {}", ExceptionUtils.getStackTrace(e));
			}
			

			if (flag == 1) {
				logger.info("successfully read file "+configFile +" with flag = "+flag);
				out.write("\nsuccessfully read file "+configFile +" with flag = "+flag);
				try {
					roscoreConfig = ca.createFactoryConfiguration("eu.brain.iot.robotics.roscore.ROS", null);
					Properties props = new Properties();

					for (Map.Entry<String, String> entry : map.entrySet()) {
						props.put(entry.getKey(), entry.getValue());
					}

					roscoreConfig.update((Dictionary) props);

				} catch (Exception e) {
					logger.error("\nRoscoreConfiguartor Exception: {}", ExceptionUtils.getStackTrace(e));
					out.write("\nRoscoreConfiguartor Exception: "+ ExceptionUtils.getStackTrace(e));
				}
			}
		}
		
		} catch (IOException e) {
			logger.error("\n ROS Edge Node write log.txt Exception: {}", ExceptionUtils.getStackTrace(e));
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
			if (fr != null) {
				fr.close();
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