package eu.brain.iot.robot.logger;

import org.osgi.service.component.annotations.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate = true, service = { BrainiotLogger.class })
public class BrainiotLogger {
	
	private static final Logger log = LoggerFactory.getLogger(BrainiotLogger.class.getSimpleName());

	@Activate
	void activate() throws Exception {
		
	
		System.out.println("BrainiotLogger is activating..........");

        
	}
	
	public Logger getLogger() {
		return log;
	}

	@Deactivate
	public void stop() throws Exception {  
		System.out.println("BrainiotLogger is deactivating..........");
       
    } 
	
}
