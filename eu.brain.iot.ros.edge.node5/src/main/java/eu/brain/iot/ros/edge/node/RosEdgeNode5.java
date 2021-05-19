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
package eu.brain.iot.ros.edge.node;


import java.io.File;
import java.util.Map.Entry;
import java.net.Socket;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ServiceScope;
import org.ros.RosCore;
import org.ros.concurrent.DefaultScheduledExecutorService;
import org.ros.master.client.MasterStateClient;
import org.ros.master.client.ServiceSystemState;
import org.ros.master.client.SystemState;
import org.ros.master.client.TopicSystemState;
import org.ros.master.client.TopicType;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar_track_alvar_msgs.AlvarMarker;
import be.iminds.iot.ros.api.Ros;
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.BrainIoTEvent;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.api.Coordinate;
import eu.brain.iot.robot.api.RobotCommand;
import eu.brain.iot.robot.events.BatteryVoltage;
import eu.brain.iot.robot.events.BroadcastACK;
import eu.brain.iot.robot.events.BroadcastResponse;
import eu.brain.iot.robot.events.Cancel;
import eu.brain.iot.robot.events.CheckMarker;
import eu.brain.iot.robot.events.MarkerReturn;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCart;
import eu.brain.iot.robot.events.QueryState;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotReadyBroadcast;
import eu.brain.iot.robot.events.WriteGoTo;
import eu.brain.iot.robot.events.QueryStateValueReturn.CurrentState;
import eu.brain.iot.robot.events.RobotPosition;
import eu.brain.iot.service.robotic.startButton.api.StartDTO;
import eu.brain.iot.service.robotic.startButton.api.StartROSDTO;
import geometry_msgs.Pose;
import geometry_msgs.Pose2D;
import geometry_msgs.PoseWithCovariance;
import geometry_msgs.PoseWithCovarianceStamped;
import kobuki_msgs.SensorState;
import procedures_msgs.ProcedureQueryRequest;
import robot_local_control_msgs.GoTo;
import robot_local_control_msgs.GoToPetitionRequest;
import robot_local_control_msgs.Pick;
import robot_local_control_msgs.PickPetitionRequest;
import robot_local_control_msgs.Place;
import robot_local_control_msgs.PlacePetitionRequest;
import robot_local_control_msgs.Pose2DStamped;
import robot_local_control_msgs.Twist2D;
import std_msgs.Header;
import eu.brain.iot.ros.edge.node.common.*;

@Component(service = {Ros.class, SmartBehaviour.class},
	immediate=true,
	scope=ServiceScope.SINGLETON,
	configurationPolicy=ConfigurationPolicy.OPTIONAL,
	configurationPid = "eu.brain.iot.ros.edge.nodes.Ros5",
			
	property = {"osgi.command.scope=ros", 
		"osgi.command.function=env",	
		"osgi.command.function=nodes",
		"osgi.command.function=topics",
		"osgi.command.function=publishers",
		"osgi.command.function=subscribers",
		"osgi.command.function=services",
		"osgi.command.function=providers"})
@SmartBehaviourDefinition(
		consumed = {StartROSDTO.class, WriteGoTo.class, Cancel.class, PickCart.class, PlaceCart.class, QueryState.class, CheckMarker.class, BroadcastResponse.class },    
		author = "LINKS", name = "ROS Edge Node",
		filter = "(|(robotID=5)(robotID=-1))",  // "(|(robotID=1)(robotID=-1))"   "(timestamp=*)"
		description = "Implements a remote Smart Robot.")
public class RosEdgeNode5 extends AbstractNodeMain implements Ros, SmartBehaviour<BrainIoTEvent>{

	// ROS environment variables
	private URI masterURI;
	private String distro;
	private String namespace;
	private String root;
	private String packagePath;
	
	// ROS core instance (native or rosjava)
	private Process nativeCore;
	private RosCore core;
//	private String robotIP;
//	private int robotId;
	
	
	// rosjava connection to the ROS master
	private MasterStateClient master;

	// construct our own thread pool for rosjava
	private NodeMainExecutor executor;
	private ThreadPoolExecutor pool;
	private int threadCount = 0;

	// also register a node to get access to basic ros info
	private ConnectedNode node;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(RosEdgeNode5.class.getSimpleName());
	
	
	private static volatile int robotID ;
    private static volatile String robotIP;
    private static volatile String robotName;
    private static volatile String configName;
    
    private AvailibilityComponent availibility;
    private PoseMarkerComponent ar_pose_marker;
    private GoToComponent  goToComponent;
    private PickComponent pickComponent;
    private PlaceComponent placeComponent;
    private BatteryVoltageComponent batteryVoltageComponent;
    private RobotPositionComponent positionComponent;
	private Coordinate coordinate;
	private String pickFrameId;

	private ExecutorService worker;
	private ServiceRegistration<?> reg;
	private boolean isWorkDone = false;
	private ConfigurationAdmin cm;
	
	private static volatile boolean isIdle = true;
	private static volatile boolean isStarted = false;
	
	private static volatile String UUID;
	private static volatile boolean receivedBroadcastResponse = false;
	
	private static volatile int voltageFrequency = 3;
	private static volatile int positionFrequency = 2;
	
	

	/*   //1
	@ObjectClassDefinition
	public static @interface Config {
		int voltageFrequency() default 1;
	}*/
	
	@Reference
	private EventBus eventBus;
	
	@Reference
	private CartMapper cartMapper;

	@Reference
    void setConfigurationAdmin(ConfigurationAdmin cm) {
        this.cm = cm;
    }
	
	
	@Activate
	void activate(BundleContext context, Map<String, Object> properties) throws InterruptedException {
		try {

			String uri = null;

		/*	if (!properties.isEmpty()) {
				for (Entry<String, Object> entry : properties.entrySet()) {
					String key = entry.getKey();
					if (key.equals("ros.master.uri")) {
						uri = (String) entry.getValue();
					} else if (key.equals("robotIP")) {
						this.robotIP = (String) entry.getValue();
					} else if (key.equals("robotId")) {
						this.robotID = Integer.parseInt((String) entry.getValue());
					} else if (key.equals("robotName")) {
						this.robotName = (String) entry.getValue();
					} else if (key.equals("configName")) {
						this.configName = (String) entry.getValue();
					}
				}
			} */
			this.robotIP = context.getProperty("IP5");
			this.robotName = context.getProperty("name5");
			this.robotID = Integer.parseInt(context.getProperty("ID5"));
			String fre = context.getProperty("positionFrequency");
			if(fre!=null) {
				this.positionFrequency = Integer.parseInt(fre);
			}
			
			
			if(uri == null) {
				if(robotIP != null) {
					uri = "http://"+robotIP+":11311";
				} else {
					uri = getVariable(context, "ROS_MASTER_URI", "ros.master.uri");
				}
			}

			if(uri == null){
				logger.error("Exception: No master URI configured!");
				System.out.println("Exception: No master URI configured!");
				deactivate();
			}
			masterURI = new URI(uri);//uri
			logger.info("\n--> masterURI = "+masterURI);
			System.out.println("\n--> masterURI = "+masterURI);
			
			if(robotIP == null) {
				String[] strs = uri.split(":");
				robotIP = strs[1].substring(2);
				logger.info("--> robotIP = "+robotIP);
			}

			logger.info("--> robotID = "+robotID+" --> robotName = "+robotName+" --> robotIP = "+robotIP);
			System.out.println("--> robotID = "+robotID+" --> robotName = "+robotName+" --> robotIP = "+robotIP);
			
			distro = getVariable(context, "ROS_DISTRO", "ros.distro");
			namespace = getVariable(context, "ROS_NAMESPACE", "ros.namespace");
			root = getVariable(context, "ROS_ROOT", "ros.root");
			packagePath = getVariable(context, "ROS_PACKAGE_PATH", "ros.package.path");
		} catch(Exception e){
			logger.error("\nError setting up the ROS environment: ", e);
		} 
		
		// create threadpool for running additional rosjava nodes
		// these parameters are equal as for CachedThreadPool ... change if useful
		pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(), new ThreadFactory() {	
					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "rosjava-pool-"+(threadCount++));
					}
				});
		executor = DefaultNodeMainExecutor.newDefault(new DefaultScheduledExecutorService(pool));

		// start ROS core if required
		boolean start = rosCoreActive();
		logger.info("\nroscore has been started = " + start);
		System.out.println("\nroscore has been started = " + start);
		
		if(!start){
			logger.info("start installed ROS system");
			boolean startNative = true;   // -------keep always running native ros core
			String rosCoreNative = getVariable(context, null,"ros.core.native");
			
			if(rosCoreNative != null){
				startNative = Boolean.parseBoolean(rosCoreNative);				
			}
			try {
			if(startNative){
				logger.info("start Native roscore =  "+ startNative);
				// native ROScore process
				ProcessBuilder builder = new ProcessBuilder("roscore", "-p "+masterURI.getPort());
				builder.environment().put("ROS_MASTER_URI", masterURI.toString());
				nativeCore = builder.start();
				
			} else {
				logger.info("rosjava ROScore implementation");
				// rosjava ROScore implementation
				core = RosCore.newPublic(masterURI.getHost(), masterURI.getPort());
				core.start();
				logger.info("ROS core service [/rosout] started on "+core.getUri());
			}
			} catch(Exception e){
					logger.error("Launch roscore Exception: {}", ExceptionUtils.getStackTrace(e));
			} 
		}
		
		// block until ROS core is initialized
		// TODO add timeout?
		while(!rosCoreActive()){   //= false
			Thread.sleep(100);
		}
		
		// add this node
		addNode(this);
		
		logger.info("ROSImpl ADDED a new Node: "+this.getDefaultNodeName());
		System.out.println("ROSImpl ADDED a new Node: "+this.getDefaultNodeName());
	
	
		UUID = context.getProperty("org.osgi.framework.uuid");

			logger.info("Hello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID + ", with voltageFrequency = "+voltageFrequency);
	    	
	    	System.out.println("Hello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID + ", with voltageFrequency = "+voltageFrequency);
	    	
	    worker = Executors.newFixedThreadPool(10);

	}
	
	@Override
	public String getRobotIP() {
		return robotIP;
	}
	@Override
	public int getRobotId() {
		return robotID;
	}
	@Override
	public String getRobotName() {
		return robotName;
	}
	
	private boolean rosCoreActive(){
		try {
			// try to connect to ROS core
			Socket s = new Socket(masterURI.getHost(), masterURI.getPort());
			s.close();
			return true;
		} catch(Exception e){
			logger.error("Detecting active ROS core Exception: {}", ExceptionUtils.getStackTrace(e));
			return false;
		}
	}


	
/*	@Override
	public GraphName getDefaultNodeName() {
		logger.info("ROSImpl gets a new default Node name in ROS: Rosinfo/Robot_"+robotId+"/");
		System.out.println("ROSImpl gets a new default Node name in ROS: Rosinfo/Robot_"+robotId+"/");
		return GraphName.of("Rosinfo/Robot_"+robotId+"/");
	}*/
	
	@Override
	public GraphName getDefaultNodeName() {
	/*	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	    logger.info("RosEdgeNode GETs a new default Node name in ROS: Robot_"+robotID+"/Ros_Edge_Node");
		System.out.println("RosEdgeNode GETs a new default Node name in ROS: Robot_"+robotID+"/Ros_Edge_Node");
		return GraphName.of("Robot_"+robotID+"/Ros_Edge_Node");
	}

	/* Called when the Node has started and successfully connected to the master. */
	@Override
	public void onStart(final ConnectedNode connectedNode) {
	/*	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		try {
			node = connectedNode;
			master = new MasterStateClient(connectedNode, connectedNode.getMasterUri());
			synchronized(this){
				notifyAll();
			}
			logger.info("\nonStart(): roscore successfully connected to the master,  created a remote master state client= "+node.getMasterUri().toString());
			System.out.println("\nonStart(): roscore successfully connected to the master,  created a remote master state client= "+node.getMasterUri().toString());
		
		} catch(Exception e){
			logger.error("\nonStart(): roscore failed to create a remote master state client in onStart(): Exception: {}", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
		
		logger.info("The ROS Edge Node is registering service clients....for Robot "+robotID+" in onStart()\n");
		System.out.println("\nThe ROS Edge Node is registering service clients....for Robot "+robotID+" in onStart()\n");
		
		try {
		
		MessageFactory msgfactory =  connectedNode.getTopicMessageFactory();  

	/*	availibility =new AvailibilityComponent(connectedNode,robotName) {};
		availibility.register();
		logger.info("\n ROS Edge Node "+robotID+" availibility service registed.");
		System.out.println("\n ROS Edge Node "+robotID+" availibility service registed.");*/
		
		ar_pose_marker=new PoseMarkerComponent(connectedNode,robotName) {};
		ar_pose_marker.register();
		logger.info("ar_pose_marker registered."); 
		
		goToComponent=new GoToComponent(connectedNode,msgfactory,robotName) {
			@Override
			 public GoToPetitionRequest constructMsg_gotoRun()
			{
				robot_local_control_msgs.GoToPetitionRequest GoToRequest = gotoRun.serviceClient.newMessage();
				GoTo procedure=msgfactory.newFromType(GoTo._TYPE);
				// --- construct goals-----
				List<Pose2DStamped> goals= new LinkedList<Pose2DStamped>(); 
				Pose2DStamped goal=msgfactory.newFromType(Pose2DStamped._TYPE);
				Header header=msgfactory.newFromType(Header._TYPE);
				header.setFrameId("map");
				header.setSeq(0);
				Time time= new Time();
				Time.fromMillis(0);
				Time.fromNano(0);
				header.setStamp(time);;
				goal.setHeader(header);
				Pose2D pose=msgfactory.newFromType(Pose2D._TYPE);
				pose.setTheta(coordinate.getZ());
				pose.setX(coordinate.getX());
				pose.setY(coordinate.getY());
				goal.setPose(pose);
				goals.add(goal);
				//------construct velocities-------
				List<Twist2D> velocities= new LinkedList<Twist2D>();
				Twist2D twist2D= msgfactory.newFromType(Twist2D._TYPE);
				twist2D.setAngularZ(0.0);
				twist2D.setLinearX(1.0);
				twist2D.setLinearY(1.0);
				velocities.add(twist2D);
				procedure.setGoals(goals);
				procedure.setMaxVelocities(velocities);
				GoToRequest.setProcedure(procedure);
				return GoToRequest;
			}
		};
		goToComponent.register();
		logger.info("\n ROS Edge Node "+robotID+" GoToComponent service registed.");
		System.out.println("\n ROS Edge Node "+robotID+" GoToComponent service registed.");
		
		pickComponent=new PickComponent(connectedNode,msgfactory,robotName) {
			@Override
			public PickPetitionRequest constructMsg_pickRun() {
				robot_local_control_msgs.PickPetitionRequest pickRequest=pickRun.serviceClient.newMessage();
				Pick procedure= msgfactory.newFromType(Pick._TYPE);
		//		procedure.setPickFrameId(pickFrameId); // TODO 1, to be used in real robot, 2 is in queryer
				pickRequest.setProcedure(procedure);
				return pickRequest;
			}
		};
		pickComponent.register();
		logger.info("\n ROS Edge Node "+robotID+" PickComponent service registed.");
		System.out.println("\n ROS Edge Node "+robotID+" PickComponent service registed.");
		
		placeComponent=new PlaceComponent(connectedNode,msgfactory,robotName) {
			@Override
			public PlacePetitionRequest constructMsg_placeRun() {
				robot_local_control_msgs.PlacePetitionRequest placeRequest=placeRun.serviceClient.newMessage();
		    	Place place = msgfactory.newFromType(Place._TYPE);
		    	place.setPickFrameId("");
				placeRequest.setProcedure(place);
		    	return placeRequest;
			}
		};
		placeComponent.register();
		logger.info("\n ROS Edge Node "+robotID+" PlaceComponent service registed.");
		System.out.println("\n ROS Edge Node "+robotID+" PlaceComponent service registed.");
		
		
		
/*		batteryVoltageComponent =new BatteryVoltageComponent(connectedNode,robotName) {};	//3
		batteryVoltageComponent.register();
		logger.info("BatteryVoltageComponent registered.");
		System.out.println("BatteryVoltageComponent registered.");
	*/	
		
	/* */	positionComponent =new RobotPositionComponent(connectedNode,robotName) {};
		positionComponent.register();
		logger.info("ROS Edge Node "+robotID+" RobotPositionComponent service registed.");
		System.out.println("ROS Edge Node "+robotID+" RobotPositionComponent service registed.");
		
		
		worker.execute(() -> {
			PoseWithCovarianceStamped positionMsg = null;
			RobotPosition robotPosition = null;
			
			System.out.println("ROS Edge Node "+robotID+": New Thread starts to read Robot Position info ........... ");
			
			while(true) {
				positionMsg = positionComponent.get_robotPosition_value();
				if(positionMsg != null) {
					robotPosition = createRobotPosition(positionMsg );
					
			//		System.out.println("Position: "+robotPosition.x+", "+robotPosition.y+", "+robotPosition.z);
					if(robotPosition!=null) {
						eventBus.deliver(robotPosition);
					}
				}else {
					
					System.out.println("get empty position, again....");
				}
				wait(positionFrequency);
			}
		});

		} catch(Exception e) {
			logger.error("\n ROS Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
	}
	
	public void broadCastReady(){
		RobotReadyBroadcast rbc=new RobotReadyBroadcast();  // TODO  RobotReady not exist in real robot, now only for local test
		rbc.robotID = robotID;
		rbc.robotIP = robotIP;
		rbc.UUID = UUID;
		rbc.isReady = true;
		eventBus.deliver(rbc);
	}
	
	@Modified
    void modified(Map<String, Object> properties) {
		
	//	this.robotID = (int) properties.get("robotId");
	//	this.robotIP = (String) properties.get("robotIP");
	//	this.robotName = (String) properties.get("robotName"); 
		
		logger.info("\n-->Hi, RosEdgeNode " + robotID + "  has osgi service properties :" + properties+"\n");
		System.out.println("\n-->Hi, RosEdgeNode " + robotID + "  has osgi service properties :" + properties+"\n");
		
    }
	
	@Override
	public void notify(BrainIoTEvent event) {
		
		if(event instanceof StartROSDTO) {
			StartROSDTO start = (StartROSDTO) event;
			logger.info("\nROS Edge Node "+ robotID+" received StartDTO event = "+start.robotID+"...............");
			System.out.println("\nROS Edge Node "+ robotID+" received StartDTO event = "+start.robotID+"...............");
			
			worker.execute(() -> {
				Bundle adminBundle = FrameworkUtil.getBundle(RosEdgeNode5.class);
				String location = adminBundle.getLocation();
			
			Configuration config;
			try {
				config = cm.getConfiguration("eu.brain.iot.ros.edge.nodes.Ros5", location);

				Hashtable<String, Object> props = new Hashtable<>();
				props.put(SmartBehaviourDefinition.PREFIX_ + "filter", // only receive some sepecific events with robotID
						String.format("(|(robotID=%s)(robotID=%s))", robotID, RobotCommand.ALL_ROBOTS));
				config.update(props); // the modified() method will be called. it will receive only the events with the robotID.
				logger.info("-->RosEdgeNode " + robotID + " update properties = "+props+"\n");
				System.out.println("-->RosEdgeNode " + robotID + " update properties = "+props+"\n");
				
				TimeUnit.SECONDS.sleep(1);
				
				while(!receivedBroadcastResponse) {
					broadCastReady();
					logger.info("ROS Edge Node "+robotID +"  is sending RobotReadyBroadcast event...............");
					System.out.println("ROS Edge Node "+robotID +"  is sending RobotReadyBroadcast event................ ");
					TimeUnit.SECONDS.sleep(1);
				}
				
			} catch (Exception e) {
				logger.error("RosEdgeNode OSGI Service Exception: {}", ExceptionUtils.getStackTrace(e));
			}
			});	
			
		} else if(event instanceof BroadcastResponse) {
			BroadcastResponse bcr = (BroadcastResponse) event;
			worker.execute(() -> {
			
				if(!receivedBroadcastResponse) {

			logger.info("-->RosEdgeNode " + robotID + " received an BroadcastResponse event with robotID="+bcr.robotID+ "\n");
			System.out.println("-->RosEdgeNode " + robotID + " received an BroadcastResponse event with robotID="+bcr.robotID+"\n");
			
			if(bcr.robotID == robotID) {
				receivedBroadcastResponse = true;
				
				BroadcastACK ack = new BroadcastACK();
				ack.robotID = robotID;
				eventBus.deliver(ack);
				
				logger.info("\n-->RosEdgeNode " + robotID + " connects to RB "+bcr.robotID+", send BroadcastACK");
				System.out.println("\n-->RosEdgeNode " + robotID + " connects to RB "+bcr.robotID+", send BroadcastACK");
			
		/*		worker.execute(() ->{			//4
					SensorState state = null;
					BatteryVoltage voltage = null;
					
					System.out.println("New Worker starts to read Battery Voltage info ........... ");
					
					while(true) {
						state = batteryVoltageComponent.get_voltage_value();
						if(state != null) {
							voltage = createBetteryVoltage(state );
							logger.info("Voltage: "+voltage.index+", "+voltage.target);
							System.out.println("Voltage: "+voltage.index+", "+voltage.target);
						}else {
							logger.info("get empty voltage, again....");
							System.out.println("get empty voltage, again....");
						}
						wait(voltageFrequency);
					}
					}
				); */
				
			} else {
				logger.info("-->Failed!! RosEdgeNode " + robotID + " rejected to connect to RB "+bcr.robotID);
				System.out.println("-->Failed!! RosEdgeNode " + robotID + " rejected to connect to RB "+bcr.robotID);
			}
			
		   }
		});
		}
		else if(event instanceof WriteGoTo || event instanceof PickCart || event instanceof PlaceCart) {
		logger.info(" >>> Robot "+robotID+" received an event: " + event.getClass().getSimpleName()+ ", with robotID = " +((RobotCommand)event).robotID);
			
		if (event instanceof WriteGoTo) {
			
			WriteGoTo writeGoTo = (WriteGoTo) event;
	//		if(writeGoTo.robotID == robotID) {
			worker.execute(() ->{
				if(writeGoTo.robotID == robotID) {
				logger.info(" >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate +" with robotID="+writeGoTo.robotID);
				System.out.println(" >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate+" with robotID="+writeGoTo.robotID);
				
				QueryStateValueReturn queryReturnedValue = new QueryStateValueReturn(); 
				
				String sendResult = writeGOTO(writeGoTo.coordinate);
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = writeGoTo.command;
				if(!sendResult.isEmpty()) {
				if(sendResult.equals("ok"))  // sending goto ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(writeGoTo.command);	// goto Query
					if((callResp != null)) {
						
						if(callResp.current_state.equals("finished")) {
							
							logger.info(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							System.out.println(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if(callResp.last_event.equals("abort")) {
								logger.info(" >>> robot "+robotID+" query WriteGOTO action finished, but last_event = abort, so send CurrentState = unknown!");
								System.out.println(" >>> robot "+robotID+" query WriteGOTO action finished, but last_event = abort, so send CurrentState = unknown!");
							//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
								queryReturnedValue.currentState = CurrentState.unknown;
							} else {
								queryReturnedValue.currentState = CurrentState.finished;
							}
			//				setIsIdle(true);
							break;
							
						} else if(callResp.current_state.equals("unknown")) {
							
							logger.info(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							System.out.println(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);

							queryReturnedValue.currentState = CurrentState.unknown;
					//		setIsIdle(true);
							break;
						} else {
							wait(2);
						}
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" query WriteGOTO no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
						System.out.println(" >>> robot "+robotID+" query WriteGOTO no response, timeout!"); 
						break;
					}
				  }  // while end
				} else { // send result is ERROR
					queryReturnedValue.currentState = CurrentState.unknown;
					logger.info(" >>> robot "+robotID+" failed to send WriteGOTO with ERROR response ! Return CurrentState.unknown");
					System.out.println(" >>> robot "+robotID+" failed to send WriteGOTO with ERROR response ! Return CurrentState.unknown");

				}
				} else { // timeout, no response
					queryReturnedValue.currentState = CurrentState.unknown;
					logger.info(" >>> robot "+robotID+" failed to send WriteGOTO without response(timeout) ! Return CurrentState.unknown");
					System.out.println(" >>> robot "+robotID+" failed to send WriteGOTO without response(timeout) ! Return CurrentState.unknown");
				}
				
				eventBus.deliver(queryReturnedValue);
				} else {
					logger.info(" >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate +" with robotID="+writeGoTo.robotID+", but to be ignored......");
					System.out.println(" >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate+" with robotID="+writeGoTo.robotID+", but to be ignored......");
				}
			}
			);
	//	}
		} else if (event instanceof PickCart) {
			
			PickCart pickCart = (PickCart) event;
		//	if(pickCart.robotID == robotID) {
			worker.execute(() ->{
				if(pickCart.robotID == robotID) {
				logger.info(" >>> Robot "+robotID+" received PickCart: " + pickCart.markerID+" with robotID="+pickCart.robotID);
				System.out.println(" >>> Robot "+robotID+" received PickCart: " + pickCart.markerID+" with robotID="+pickCart.robotID);
				
				QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
				String sendResult = pickCart(pickCart.markerID);
				
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = pickCart.command;  // "PICK"
				if(!sendResult.isEmpty()) {
				if(sendResult.equals("ok"))  // sensing pick ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(pickCart.command);	// pick Query
						if((callResp != null)) {
							if(callResp.current_state.equals("finished")) {
								
								logger.info(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								System.out.println(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								
								if(callResp.last_event.equals("abort")) {
									logger.info("robot "+robotID+" query pickCart action finished, but last_event = abort, so send default CurrentState = unknown!");
									System.out.println("robot "+robotID+" query pickCart action finished, but last_event = abort, so send default CurrentState = unknown!");

								//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
									queryReturnedValue.currentState = CurrentState.unknown;
								} else {
									queryReturnedValue.currentState = CurrentState.finished;
								}
					//			setIsIdle(true);
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								logger.info(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								System.out.println(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);

								queryReturnedValue.currentState = CurrentState.unknown;
							//	setIsIdle(true);
								break;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							logger.info(" >>> robot "+robotID+" query pickCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent

							break;
						}
					  }  // while end
					}else { // send result is ERROR
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" failed to send PickCart with ERROR response ! Return CurrentState.unknown");
						System.out.println(" >>> robot "+robotID+" failed to send PickCart with ERROR response ! Return CurrentState.unknown");

					}
				} else { // timeout, no response
					queryReturnedValue.currentState = CurrentState.unknown;
					logger.info(" >>> robot "+robotID+" failed to send PickCart without response(timeout) ! Return CurrentState.unknown");
					System.out.println(" >>> robot "+robotID+" failed to send PickCart without response(timeout) ! Return CurrentState.unknown");
				}
					eventBus.deliver(queryReturnedValue);
					} else {
						logger.info(" >>> Robot "+robotID+" received PickCart: " + pickCart.markerID+" with robotID="+pickCart.robotID+", but to be ignored......");
						System.out.println(" >>> Robot "+robotID+" received PickCart: " + pickCart.markerID+" with robotID="+pickCart.robotID+", but to be ignored......");
					}
			}
				);
		//	}
			
		} else if (event instanceof PlaceCart) {
			
			PlaceCart plceCart = (PlaceCart) event;
		//	if(plceCart.robotID == robotID) {
			worker.execute(() ->{
				if(plceCart.robotID == robotID) {
				logger.info(" >>> Robot "+robotID+" received PlaceCart  with robotID="+plceCart.robotID);
				System.out.println(" >>> Robot "+robotID+" received PlaceCart  with robotID="+plceCart.robotID);
				
				QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
				String sendResult = placeCart();
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = plceCart.command;   // "PLACE"
				if(!sendResult.isEmpty()) {
				if(sendResult.equals("ok"))  // sending place ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(plceCart.command);	// place Query
						if((callResp != null)) {
							if(callResp.current_state.equals("finished")) {
								
								logger.info(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								System.out.println(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								
								if(callResp.last_event.equals("abort")) {
									logger.info("robot "+robotID+" query PlaceCart action finished, but last_event = abort, so send CurrentState = unknown!");
									System.out.println("robot "+robotID+" query PlaceCart action finished, but last_event = abort, so send CurrentState = unknown!");
								//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
									queryReturnedValue.currentState = CurrentState.unknown;
								} else {
									queryReturnedValue.currentState = CurrentState.finished;
								}
					//			setIsIdle(true);
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								logger.info(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								System.out.println(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								queryReturnedValue.currentState = CurrentState.unknown;
							//	setIsIdle(true);
								break;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							logger.info(" >>> robot "+robotID+" query PlaceCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
							System.out.println(" >>> robot "+robotID+" query PlaceCart no response, timeout!");
							break;
						}
					  } // while end
					}else { // send result is ERROR
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" failed to send PlaceCart with ERROR response ! Return CurrentState.unknown");
						System.out.println(" >>> robot "+robotID+" failed to send PlaceCart with ERROR response ! Return CurrentState.unknown");
					}
				} else { // timeout, no response
					queryReturnedValue.currentState = CurrentState.unknown;
					logger.info(" >>> robot "+robotID+" failed to send PlaceCart without response(timeout) ! Return CurrentState.unknown");
					System.out.println(" >>> robot "+robotID+" failed to send PlaceCart without response(timeout) ! Return CurrentState.unknown");
				}
					eventBus.deliver(queryReturnedValue);
			//	checkDoorStatus = false;
				isWorkDone = true;  // TODO
				logger.info(" >>> ROBOT "+robotID +" finishs this iteration......... ");
				System.out.println(" >>> ROBOT "+robotID +" finishs this iteration......... ");
			} else {
				logger.info(" >>> Robot "+robotID+" received PlaceCart  with robotID="+plceCart.robotID+", but to be ignored......");
				System.out.println(" >>> Robot "+robotID+" received PlaceCart  with robotID="+plceCart.robotID+", but to be ignored......");
			}
				});
		//	}  // end if
						
			  } // end if PlaceCart
		//	} // else if robot is idle 
		
		}  // end if WriteGoTo, PickCart, PlaceCart
		else { 
			if (event instanceof Cancel) {
			Cancel cancel = (Cancel) event;
			worker.execute(() -> cancel(cancel.command));
			
			} else if (event instanceof QueryState) {	// Edge Node also can receive additional QueryState from other entities
			
			QueryState querySate = (QueryState) event;
			if(querySate.robotID == robotID) {
			worker.execute(() ->  {
					QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
					queryReturnedValue.robotID = querySate.robotID;
					queryReturnedValue.command = querySate.command;
					CallResponse callResp = queryState(querySate.command);
					
					if((callResp != null)) {	
						logger.info("robot "+robotID+" queryState() gets response: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if (callResp.current_state.compareTo("finished") == 0) {
								queryReturnedValue.currentState = CurrentState.finished;
					        }
					        if (callResp.current_state.compareTo("queued") == 0) {
					        	queryReturnedValue.currentState = CurrentState.queued;
					        }
					        if (callResp.current_state.compareTo("running") == 0) {
					        	queryReturnedValue.currentState = CurrentState.running;
					        }
					        if (callResp.current_state.compareTo("paused") == 0) {
					        	queryReturnedValue.currentState = CurrentState.paused;
					        }
					        if (callResp.current_state.compareTo("unknown") == 0) {
					        	queryReturnedValue.currentState = CurrentState.unknown;
					        }
						
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" queryState() no response, timeout!  ==> it's set to unknown.");
					}
					
					eventBus.deliver(queryReturnedValue);
					
					logger.info(" >>> Robot "+robotID+" reply with QueryStateValueReturn: " + queryReturnedValue.currentState);			
			});
			}
							
		} else if (event instanceof CheckMarker) {
			CheckMarker cm = (CheckMarker) event;
			if(cm.robotID == robotID) {
			worker.execute(() -> {
			//	CheckMarker cm = (CheckMarker) event;
				MarkerReturn markerReturn =new MarkerReturn ();
				markerReturn.robotID = cm.robotID;
				markerReturn.markerID=checkMarkers();
				eventBus.deliver(markerReturn);									
				logger.info(" >>> Robot "+robotID+" reply with MarkerReturn: " + markerReturn.markerID);	
				System.out.println(" >>> Robot "+robotID+" reply with MarkerReturn: " + markerReturn.markerID);
			});		
			}
		} else {
			logger.info(" >>> Argh! Robot "+robotID+" Received an unknown event type " + event.getClass());
								
		}
		
		}  // if other events that can be executed at same time 
		
		
	}
	
	private String writeGOTO(String coordinate) {
		this.coordinate = convertCord(coordinate);
		robot_local_control_msgs.GoToPetitionRequest GoTorequest=goToComponent.constructMsg_gotoRun();
		return goToComponent.call_gotoRun(GoTorequest);

	}
	private Coordinate convertCord(String coordinate) {
		
		String[] strs = coordinate.trim().split(",");
		Coordinate cord = new Coordinate(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Double.parseDouble(strs[2]));
		return cord;
	}
	
	private String pickCart(int markerID){
		String cartName= cartMapper.getCartName(markerID);//
		this.pickFrameId=cartName;//
		robot_local_control_msgs.PickPetitionRequest Pickrequest= pickComponent.constructMsg_pickRun();
		return pickComponent.call_pickRun(Pickrequest);  // ok/error
	}
	
	private String placeCart(){
		robot_local_control_msgs.PlacePetitionRequest Placerequest= placeComponent.constructMsg_placeRun();
		return placeComponent.call_placeRun(Placerequest);
	}
	
	
	
	private String cancel(Command command){
		String state;
		switch(command){
			case GOTO:
				procedures_msgs.ProcedureQueryRequest Gotocancel= goToComponent.constructMsg_gotoCancle();
				state=goToComponent.call_gotoCancle(Gotocancel);
				break;
			case PICK:
				procedures_msgs.ProcedureQueryRequest Pickcancel= pickComponent.constructMsg_pickCancle();
				state=pickComponent.call_pickCancle(Pickcancel);
				break;
			case PLACE:
				procedures_msgs.ProcedureQueryRequest Placecancel= placeComponent.constructMsg_placeCancle();
				state=placeComponent.call_placeCancle(Placecancel);
				break;
			default:
				state=null;
				break;	
		}
		logger.info(" >>> Finish cancel");
		return state;
	}
	

	private CallResponse queryState(Command command){
		CallResponse callResp = null;
		
		switch(command){
		case GOTO:
			ProcedureQueryRequest Gotoquery=goToComponent.constructMsg_gotoQuery();
			callResp=goToComponent.call_gotoQuery(Gotoquery);
			break;
		case PICK:
			procedures_msgs.ProcedureQueryRequest Pickquery= pickComponent.constructMsg_pickQuery();
			callResp=pickComponent.call_pickQuery(Pickquery);
			break;
		case PLACE:
			procedures_msgs.ProcedureQueryRequest Placequery= placeComponent.constructMsg_placeQuery();
			callResp=placeComponent.call_placeQuery(Placequery);
			break;
		default:
			callResp = null;
			logger.info("\n >>> Robot "+robotID+" queryState() not match any action  !!!!");
			break;	
		}
		return callResp;		
	}
	
	private int checkMarkers(){
		List<AlvarMarker> markerList = ar_pose_marker.get_poseMarker_value().getMarkers();
		int flag = 0;
		while(markerList!=null) {
			logger.info("\n >>> Robot "+robotID+" see Markers List size = "+ markerList.size());
			System.out.println("\n >>> Robot "+robotID+" see Markers List size = "+ markerList.size());
			
			if(markerList.size()>=1) {
				logger.info("\n >>> Robot "+robotID+" see first Marker ID = "+ markerList.get(0).getId());
				System.out.println("\n >>> Robot "+robotID+" see first Marker ID = "+ markerList.get(0).getId());
				flag = 1;
				break;
			}
			else {  // TODO query 5 times if still no marker found, then it means the door is open?
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					logger.error("\nRos Edge Node wait marker Exception: {}", ExceptionUtils.getStackTrace(e));
				}
				markerList = ar_pose_marker.get_poseMarker_value().getMarkers();
			}
		}
		 if(flag == 1) {
			 return markerList.get(0).getId();
		 } else {
			 logger.info("\n >>> Robot "+robotID+" see Marker List is empty, return default marker =100");
			 System.out.println("\n >>> Robot "+robotID+" see Marker List is empty, return default marker =100");
			 return 100;
		 }
		
	}
	
	private RobotPosition createRobotPosition(PoseWithCovarianceStamped positionMsg) {
		RobotPosition position =  new RobotPosition();
		
		PoseWithCovariance pose = positionMsg.getPose();
		if(pose!=null) {
			Pose p = pose.getPose();
			position.x = Double.parseDouble(String.format("%.3f", p.getPosition().getX()));
			position.y = Double.parseDouble(String.format("%.3f", p.getPosition().getY()));
			position.z = Double.parseDouble(String.format("%.3f", p.getPosition().getZ()));
		} else {
			System.out.println("ROS Edge Node "+robotID+" get null PoseWithCovariance.");
			return null;
		}
		
		position.robotID = robotID;
		
		return position;
	}
	
	
	private BatteryVoltage createBetteryVoltage(SensorState state ) {
		
		BatteryVoltage bv = new BatteryVoltage();
		
		Time time = state.getHeader().getStamp();
		
		int secs = time.secs;
		int nsecs = time.nsecs;
		
		long tplus = secs*1000 + nsecs/1000000;  //msecs
		String plus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(tplus));		
		System.out.println("plus1: "+plus);
		
				
		double totalSecs = time.toSeconds();    //secs
		String tSecs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((long) (totalSecs * 1000L))); //msecs
		System.out.println("tSecs2: "+tSecs);
		
		
		long totalNsecs = time.totalNsecs();    //Nsecs
		String tNsecs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(totalNsecs/1000000)); //msecs
		System.out.println("tNsecs3: "+tNsecs);
		
		bv.index = tNsecs;
		
		byte voltage = state.getBattery();
		bv.target = Byte.toUnsignedInt(voltage);

		return bv;

	}
	
	
	public void wait(int t) {
		try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			logger.error("\nRos Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
		}
	}
	
	
	
		
	private String getVariable(BundleContext context, String environmentKey, String propertyKey){
		// then try context property
		
		String ctx = context.getProperty(propertyKey);
		if(ctx != null && ctx.length()!=0){
			return ctx;
		} else if(environmentKey != null){
			return System.getenv(environmentKey);
		}
		return null;
	}
            
	
	@Override
	public URI masterURI() {
		return masterURI;
	}

	@Override
	public String masterHost(){
		return masterURI.getHost();
	}
	
	@Override
	public int masterPort(){
		return masterURI.getPort();
	}
	
	@Override
	public String distro() {
		return distro;
	}

	@Override
	public String namespace() {
		return namespace;
	}

	@Override
	public File root() {
		if(root==null){
			return new File(".");
		}
		return new File(root);
	}

	@Override
	public List<File> packagePath() {
		List<File> files = new ArrayList<>();
		if(packagePath!=null){
			for(String f : packagePath.split(":")){
				files.add(new File(f));
			}
		}
		return files;
	}
	
	
	/**
	 * This method ensures that any call to the OSGi service waits until the ROS node
	 * is actually intialized. This is a hacky way of making sure the OSGi and ROS lifecycles
	 * can interwork while using DS 
	 */
	private synchronized void waitForInit() {
		if(master!=null){
			logger.info("The RosImpl detected Master is initialized, master!=null");
			System.out.println("The RosImpl detected Master is initialized, master!=null");
			return;
		} else {
			logger.info("The RosImpl havn't connect to Master, waiting...");
			System.out.println("The RosImpl havn't connect to Master, waiting...");
			try {
				this.wait();
			} catch(InterruptedException e){
				logger.error("waitForInit(): waiting for connecting to Master Exception: {}", ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	@Override
	public URI nodeURI(String node) {
		waitForInit();
		return master.lookupNode(node);
	}

	@Override
	public Collection<String> nodes() {
		waitForInit();
		// TODO is there a better way to list all nodes?
		SystemState state = master.getSystemState();
		final Set<String> nodes = new HashSet<>();
		state.getTopics().stream().forEach(t -> {
			nodes.addAll(t.getSubscribers());
			nodes.addAll(t.getPublishers());
		});
		return nodes;
	}
	
	@Override
	public Collection<String> topics() {
		waitForInit();
		SystemState state = master.getSystemState();
		return state.getTopics().stream().map(t -> t.getTopicName()).collect(Collectors.toList());
	}

	@Override
	public Collection<String> publishers(String topic) {
		waitForInit();
		SystemState state = master.getSystemState();
		TopicSystemState tss = state.getTopics().stream().filter(t -> t.getTopicName().equals(topic)).findFirst().get();
		return tss.getPublishers();
	}

	@Override
	public Collection<String> subscribers(String topic) {
		waitForInit();
		SystemState state = master.getSystemState();
		TopicSystemState tss = state.getTopics().stream().filter(t -> t.getTopicName().equals(topic)).findFirst().get();
		return tss.getSubscribers();
	}

	@Override
	public String topicType(String topic) {
		waitForInit();
		TopicType type = master.getTopicTypes().stream().filter(t -> t.getName().equals(topic)).findFirst().get();
		return type.getMessageType();
	}

	@Override
	public Collection<String> services() {
		waitForInit();
		SystemState state = master.getSystemState();
		return state.getServices().stream().map(s -> s.getServiceName()).collect(Collectors.toList());
	}

	@Override
	public Collection<String> providers(String service) {
		waitForInit();
		SystemState state = master.getSystemState();
		ServiceSystemState sss = state.getServices().stream().filter(s -> s.getServiceName().equals(service)).findFirst().get();
		return sss.getProviders();
	}
	
	@Override
	public String env(){
		StringBuilder builder = new StringBuilder();
		builder.append("ROS Environmnent \n");
		builder.append(" masterURI: ").append(masterURI).append("\n");
		builder.append(" distro: ").append(distro).append("\n");
		builder.append(" namespace: ").append(namespace).append("\n");
		builder.append(" root: ").append(root).append("\n");
		builder.append(" packagePath: ").append(packagePath).append("\n");
		return builder.toString();
	}
	
	@Override
	public void setParameter(String key, Object value){
		waitForInit();
		if(value instanceof Integer){
			node.getParameterTree().set(key, (Integer)value);
		} else if(value instanceof Double){
			node.getParameterTree().set(key, (Double)value);
		} else if(value instanceof String){
			node.getParameterTree().set(key, (String)value);
		} else if(value instanceof Boolean){
			node.getParameterTree().set(key, (Boolean)value);
		} else if(value instanceof List){
			node.getParameterTree().set(key, (List)value);
		} else if(value instanceof Map){
			node.getParameterTree().set(key, (Map)value);
		} else {
			node.getParameterTree().set(key, String.valueOf(value));
		}
	}	
	
	@Override
	public <T> T getParameter(String key, Class<T> type){
		waitForInit();
		if(type == Integer.class){
			return (T) new Integer(node.getParameterTree().getInteger(key));
		} else if(type == Double.class){
			return (T) new Double(node.getParameterTree().getDouble(key));
		} else if(type == String.class){
			return (T) node.getParameterTree().getString(key);
		} else if(type == Boolean.class){
			return (T) new Boolean(node.getParameterTree().getBoolean(key));
		} else if(type == List.class){
			return (T) node.getParameterTree().getList(key);
		} else if(type == Map.class){
			return (T) node.getParameterTree().getMap(key);
		} 
		return null;
	}
	
	
	@Reference(cardinality=ReferenceCardinality.MULTIPLE, 
			   policy=ReferencePolicy.DYNAMIC)
	public void addNode(NodeMain node) {
		logger.info("RosImpl DETECTED a new NodeMain: "+node.getDefaultNodeName()+ " in addNode()");
		System.out.println("RosImpl DETECTED a new NodeMain: "+node.getDefaultNodeName()+ " in addNode()");
		if(node != this)
			waitForInit();
		try {
			NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(masterHost(), masterURI());
			nodeConfiguration.setRosRoot(root());
			nodeConfiguration.setRosPackagePath(packagePath());
			executor.execute(node, nodeConfiguration);  // Executes the supplied NodeMain using the supplied NodeConfiguration.
			logger.info("NodeMainExecutor is executing : "+node.getDefaultNodeName()+ " in addNode()");
			System.out.println("NodeMainExecutor is executing : "+node.getDefaultNodeName()+ " in addNode()");
		} catch(Throwable e){
			logger.error("RosImpl addNode() method Exception: {}", ExceptionUtils.getStackTrace(e));
		}
	}
	
	public void removeNode(NodeMain node){
		
		waitForInit();
		try {
			executor.shutdownNodeMain(node);
		} catch(Throwable e){
			logger.error("RosImpl removeNode() method Exception: {}", ExceptionUtils.getStackTrace(e));
		}
	}
	
	@Deactivate
	void deactivate() throws Exception {
		removeNode(this);
		
		if(core != null)
			core.shutdown();
		
		if(nativeCore != null){
			nativeCore.destroy();
		}
		if(executor != null){
			executor.shutdown();
		}
		
		 if(reg!=null) {
				reg.unregister();
				worker.shutdown();
				try {
					worker.awaitTermination(1, TimeUnit.SECONDS);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			  }
				logger.info("------------  ROS Edge Node "+ robotID+" is deactivated----------------");
				System.out.println("------------  ROS Edge Node "+ robotID+" is deactivated----------------");
	}

}
