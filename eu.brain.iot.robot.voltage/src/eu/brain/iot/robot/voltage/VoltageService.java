package eu.brain.iot.robot.voltage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import eu.brain.iot.robot.events.*;
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
	
/*	@Reference
	private EventBus eventBus;
*/	


//	private static final Logger logger = (Logger) LoggerFactory.getLogger(VoltageService.class.getSimpleName());
	
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
		BetteryVoltage voltage = null;
				
		while(true) {
			state = batteryVoltageComponent.get_voltage_value();
			voltage = createBetteryVoltage(state );
			System.out.println("Voltage: "+voltage.index+", "+voltage.target);
			wait(3);
		}
	}



/*	public String index; // timestamp format: yyyy-MM-dd HH:mm:ss  org.ros.message.Time getStamp();
	public double target; // voltage     byte getBattery();*/
	
	private BetteryVoltage createBetteryVoltage(SensorState state ) {
		
		BetteryVoltage bv = new BetteryVoltage();
		
		Time time = state.getHeader().getStamp();
		double s = time.toSeconds();
		Timestamp timeStamp = new Timestamp((long) s);
	    String formattedDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(timeStamp.getTime());
		
		bv.index = formattedDate;
		
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