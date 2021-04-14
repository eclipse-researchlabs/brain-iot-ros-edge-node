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
package eu.brain.iot.robot.voltage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import be.iminds.iot.ros.api.Ros;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.robot.events.BatteryVoltage;
import kobuki_msgs.SensorState;
import std_msgs.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
		immediate=true,
		service = {NodeMain.class})

public class VoltageService extends AbstractNodeMain{
	
    private String robotName;
    private int robotID;
    private String robotIP;
    private BatteryVoltageComponent batteryVoltageComponent;
	
	@Reference
	private Ros ros;
	
	@Reference
	private EventBus eventBus;
	 


	private static final Logger logger = (Logger) LoggerFactory.getLogger(VoltageService.class.getSimpleName());
	
    @Activate
	void activate(BundleContext context){
    	
			this.robotName = ros.getRobotName();
			this.robotID = ros.getRobotId();
			this.robotIP = ros.getRobotIP();
			
    	String UUID = context.getProperty("org.osgi.framework.uuid");

    	System.out.println("\nHello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID);
	}
    

   
   @Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("robot_"+robotID+"/batteryVoltage");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		
		System.out.println("\n The BatteryVoltageComponent is registering....for Robot "+robotID);
		try {

		batteryVoltageComponent =new BatteryVoltageComponent(connectedNode,robotName) {};
		batteryVoltageComponent.register();
		
		System.out.println("batteryVoltageComponent registered.");

		}catch(Exception e) {
	//		logger.error("\n ROS Edge Node Exception:", e);
			e.printStackTrace();
		}
		
		SensorState state = null;
		BatteryVoltage voltage = null;
				
		int counter = 3;
		
		while(counter>0) {
			state = batteryVoltageComponent.get_voltage_value();
			if (state != null) {
				voltage = createBetteryVoltage(state );
				System.out.println("Voltage: "+voltage.index+", "+voltage.target);
				wait(2);
			} else {
				System.out.println("get empty voltage, again....");
				wait(2);
			}
			counter --;
			
		}
	}


/*  #Two-integer timestamp that is expressed as:
	# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')
	# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')
	# time-handling sugar is provided by the client library */
/*	public String index; // timestamp format: yyyy-MM-dd HH:mm:ss  org.ros.message.Time getStamp();
 
 *  uint8==>byte  battery         # battery voltage in 0.1V (ex. 16.1V -> 161)
	public double target; // voltage     byte getBattery();*/
	
	private BatteryVoltage createBetteryVoltage(SensorState state ) {
		
		BatteryVoltage bv = new BatteryVoltage();
		
		Time time = state.getHeader().getStamp();
		
		int secs = time.secs;
		int nsecs = time.nsecs;
		
		long tplus = secs*1000 + nsecs/1000000;  //msecs
		String plus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tplus));		
		System.out.println("plus1: "+plus); // plus1: 1970-01-07 15:20:22
		
				
		double totalSecs = time.toSeconds();    //secs
		String tSecs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((long) (totalSecs * 1000L))); //msecs
		System.out.println("tSecs2: "+tSecs);  // tSecs2: 2021-03-11 16:48:45
		
		
		long totalNsecs = time.totalNsecs();    //Nsecs
		String tNsecs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(totalNsecs/1000000)); //msecs
		System.out.println("tNsecs3: "+tNsecs);  // tNsecs3: 2021-03-11 16:48:45
		
		
		
	//	Timestamp timeStamp = new Timestamp((long) s);
		
	/*	long ns = time.totalNsecs();
		Timestamp timeStamp1 = new Timestamp(ns);*/
		
	//    String formattedDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(timeStamp.getTime());
		
	//	bv.index = formattedDate;
		
		bv.index = tNsecs;
		
		bv.target = state.getBattery();

		return bv;

	}
	
	
	public void wait(int t) {
		try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
