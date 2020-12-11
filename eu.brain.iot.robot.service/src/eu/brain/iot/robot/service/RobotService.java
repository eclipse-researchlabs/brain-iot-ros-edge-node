package eu.brain.iot.robot.service;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;

import ar_track_alvar_msgs.AlvarMarker;
import ar_track_alvar_msgs.AlvarMarkers;
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.api.CallResponse;
import eu.brain.iot.robot.events.Cancel;
import eu.brain.iot.robot.events.CheckMarker;
import eu.brain.iot.robot.events.CheckValueReturn;
import eu.brain.iot.robot.events.DoorFound;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCART;
import eu.brain.iot.robot.events.QueryState;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotReady;
import eu.brain.iot.robot.events.RobotCommand;
import eu.brain.iot.robot.events.WriteGOTO;
import eu.brain.iot.robot.warehouse.Cooridinate;
import eu.brain.iot.robot.warehouse.Warehouse;
import geometry_msgs.Pose2D;
import procedures_msgs.ProcedureQueryRequest;
import robot_local_control_msgs.GoTo;
import robot_local_control_msgs.GoToPetitionRequest;
import robot_local_control_msgs.Pick;
import robot_local_control_msgs.PickPetitionRequest;
import robot_local_control_msgs.Place;
import robot_local_control_msgs.PlacePetitionRequest;
import robot_local_control_msgs.Pose2DStamped;
import robot_local_control_msgs.Status;
import robot_local_control_msgs.Twist2D;
import std_msgs.Header;


@Component(
		configurationPid= "eu.brain.iot.example.robot.Robot",
		configurationPolicy=ConfigurationPolicy.REQUIRE,
		service = {NodeMain.class})
@SmartBehaviourDefinition(
		consumed = {WriteGOTO.class,Cancel.class, PickCart.class,PlaceCART.class,QueryState.class,CheckMarker.class },    
		author = "LINKS", name = "Smart Robot",
		description = "Implements a remote Smart Robot.")
public class RobotService extends AbstractNodeMain implements SmartBehaviour<RobotCommand>{
	
    private String robotName;
    private int robotId;
    private AvailibilityComponent availibility;
    private Ar_pose_markerComponent ar_pose_marker;
    private GoToComponent  goToComponent;
    private PickComponent pickComponent;
    private PlaceComponent placeComponent;
	private Cooridinate cooridinate;
	private String pickFrameId;
	private String currentMission = null;
	
	@ObjectClassDefinition
	public static @interface Config {
/*		@AttributeDefinition(description = "The IP of the robot")
		String host();

		@AttributeDefinition(description = "The Port of the robot")
		int port();
*/
		@AttributeDefinition(description = "The name of the robot")
		String name();

		@AttributeDefinition(description = "The identifier for the robot")
		int id();

	}

	private Config config;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;
	private boolean checkDoorStatus = true;
	private boolean isWorkDone = false;
	
	@Reference
	private EventBus eventBus;
	
	@Reference
	private Warehouse robotWarehouse;

    @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
    	
    	String UUID = context.getProperty("org.osgi.framework.uuid");
    	
	    this.config=config;
	    System.out.println("\nHello!  I am robot : "+config.id()+ "  name = "+config.name());
	    
	    worker = Executors.newFixedThreadPool(10);
//	    worker = Executors.newSingleThreadExecutor();
	    Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith("."))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			
			serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter", 
		    String.format("(|(robotId=%s)(robotId=%s))", config.id(), RobotCommand.ALL_ROBOTS));
			
			System.out.println("+++++++++ filter = "+serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
			reg = context.registerService(SmartBehaviour.class, this, serviceProps);
            
		this.robotName=config.name();
		this.robotId=config.id();
	}
    
    @Deactivate
	void stop() {
		reg.unregister();
		worker.shutdown();
		try {
			worker.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}


   
   @Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("rb1_base_"+config.id()+"/serviceController");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		
		System.out.println("\n The Robot services are registering....for Robot "+config.id());
		MessageFactory msgfactory =  connectedNode.getTopicMessageFactory();  

		availibility =new AvailibilityComponent(connectedNode,robotName) {};
		availibility.register();
		System.out.println("availibility registered.");
		ar_pose_marker=new Ar_pose_markerComponent(connectedNode,robotName) {};
		ar_pose_marker.register();
		System.out.println("ar_pose_marker registered."); 
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
				pose.setTheta(cooridinate.getZ());
				pose.setX(cooridinate.getX());
				pose.setY(cooridinate.getY());
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
		System.out.println("GoToComponent service registed.");
		
		pickComponent=new PickComponent(connectedNode,msgfactory,robotName) {
			@Override
			public PickPetitionRequest constructMsg_pickRun() {
				robot_local_control_msgs.PickPetitionRequest pickRequest=pickRun.serviceClient.newMessage();
				Pick procedure= msgfactory.newFromType(Pick._TYPE);
			//	procedure.setPickFrameId(pickFrameId);
				pickRequest.setProcedure(procedure);
				return pickRequest;
			}
		};
		pickComponent.register();
		System.out.println("PickComponent service registed.");
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
		System.out.println("PlaceComponent service registed.");
		
		broadCastReady();
		
	/*	while(checkDoorStatus) {
			autoDetectDoor();  // when it returns, means the robot has past the door, no need to detect 
		}*/
		
		while(!isWorkDone) {
			wait(10);
		}
		
		System.out.println("ROBOT "+robotId +" exit onStart()......... ");
		
	}
	
	
	public void broadCastReady()
	{
		RobotReady rbc=new RobotReady();
		rbc.robotId=robotId;
		rbc.isReady=true;
		eventBus.deliver(rbc);
		
	}

	@Override
	public void notify(RobotCommand event) {
		
		System.out.println(" >> Robot "+this.config.id()+" received an event: " + event.getClass());
		

		if (event instanceof WriteGOTO) {
			WriteGOTO writeGOTO = (WriteGOTO) event;
			currentMission = writeGOTO.mission;
			worker.execute(() ->{
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				// mission=UNLOAD/PLACE_C/PLACE_L/PLACE_R. First check the command is correctly send or not 
				int sendResult = writeGOTO(writeGOTO.pickPoseID, writeGOTO.mission);
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = writeGOTO.mission;
				if(sendResult == 1)  // sending goto ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(writeGOTO.mission);	// goto Query
					if((callResp != null)) {
						if(callResp.current_state.equals("finished")) {
							System.out.println("robot "+robotId+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if(callResp.last_event.equals("abort")) {
								queryreturnedvalue.value = 0;
								System.out.println("robot "+robotId+" query WriteGOTO action finished, but last_event = abort, return value = 0");
							}else {
								queryreturnedvalue.value = 1;
							}
							break;
						} else if(callResp.current_state.equals("unknown")) {
							
							System.out.println("robot "+robotId+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							queryreturnedvalue.value = 0;
							System.out.println("robot "+robotId+" query WriteGOTO action gets the Unknown state, return value = 0");
							break;
						} else {
							wait(2);
						}
					}else {
						System.out.println("robot "+robotId+" query WriteGOTO no response, timeout!");
						break;
					}
				}
				}else {
					System.out.println("robot "+robotId+" sends WriteGOTO failed!");
				}
				eventBus.deliver(queryreturnedvalue);  // only finished is possible
				}
			);
			
		}else if (event instanceof Cancel) {
			Cancel cancel = (Cancel) event;
			worker.execute(() -> cancel(cancel.mission));
			
		} else if (event instanceof PickCart) {
			PickCart pickCart = (PickCart) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				int sendResult = pickCart(pickCart.markerID);
				
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = pickCart.mission;  // "PICK"
				
				if(sendResult == 1)  // sensing pick ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(pickCart.mission);	// pick Query
					if((callResp != null)) {
						if(callResp.current_state.equals("finished")/* || callResp.current_state.equals("unknown")*/) {
							
							System.out.println("robot "+robotId+" PickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if(callResp.last_event.equals("abort")) {
								queryreturnedvalue.value = 0;
								System.out.println("robot "+robotId+" query PickCart action finished, but last_event = abort, return value = 0");
							}else {
								queryreturnedvalue.value = 1;
							}
							break;
						}else if(callResp.current_state.equals("unknown")) {
							
							System.out.println("robot "+robotId+" PickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							queryreturnedvalue.value = 0;
							System.out.println("robot "+robotId+" query PickCart action gets the Unknown state, return value = 0");
							break;
						} else {
							wait(2);
						}
					}else {
						System.out.println("robot "+robotId+" query PickCart no response, timeout!");
						break;
					}
				}
				}else {
					System.out.println("robot "+robotId+" sends PickCart failed!");
				}
				eventBus.deliver(queryreturnedvalue);
				});
			
		} else if (event instanceof PlaceCART) {
			PlaceCART plceCart = (PlaceCART) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				int sendResult = placeCART();
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = plceCart.mission;   // "PLACE"
				if(sendResult == 1)  // sensing place ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(plceCart.mission);	// place Query
					if((callResp != null)) {
						if(callResp.current_state.equals("finished")/* || callResp.current_state.equals("unknown")*/) {
							System.out.println("robot "+robotId+" PlaceCART gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							if(callResp.last_event.equals("abort")) {
								queryreturnedvalue.value = 0;
								System.out.println("robot "+robotId+" query plceCart action finished, but last_event = abort, return value = 0");
							}else {
								queryreturnedvalue.value = 1;
							}
							break;
						}else if(callResp.current_state.equals("unknown")) {
							
							System.out.println("robot "+robotId+" PlaceCART gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							queryreturnedvalue.value = 0;
							System.out.println("robot "+robotId+" query PlaceCART action gets the Unknown state, return value = 0");
							break;
						} else {
							wait(2);
						}
					}else {
						System.out.println("robot "+robotId+" query PlaceCART no response, timeout!");
						break;
					}
				}
				}else {
					System.out.println("robot "+robotId+" sends PlaceCART failed!");
				}
				eventBus.deliver(queryreturnedvalue);
			//	checkDoorStatus = false;
				isWorkDone = true;  // TODO
				System.out.println("ROBOT "+robotId +" finishs this iteration......... ");
				});
						
		} else if (event instanceof QueryState) {	// Edge Node also can receive additional QueryState from other entities
			QueryState querySate = (QueryState) event;
			worker.execute(() ->  {
					QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
					queryreturnedvalue.robotId = querySate.robotId;
					queryreturnedvalue.mission = querySate.mission;
					CallResponse callResp = queryState(querySate.mission);
					
					if((callResp != null)) {	
						
							System.out.println("robot "+robotId+" queryState() gets response: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if (callResp.current_state.compareTo("finished") == 0) {
								queryreturnedvalue.value = 1;
					        }
					        if (callResp.current_state.compareTo("queued") == 0) {
					        	queryreturnedvalue.value = 2;
					        }
					        if (callResp.current_state.compareTo("running") == 0) {
					        	queryreturnedvalue.value = 3;
					        }
					        if (callResp.current_state.compareTo("paused") == 0) {
					        	queryreturnedvalue.value = 4;
					        }
					        if (callResp.current_state.compareTo("unknown") == 0) {
					        	queryreturnedvalue.value = 5;
					        }
						
					}else {
						System.out.println("robot "+robotId+" queryState() no response, timeout!");
					}
					
					eventBus.deliver(queryreturnedvalue);
					
					System.out.println(" >> Robot "+this.config.id()+" reply with QueryStateValueReturn: " + queryreturnedvalue.value);			
			});
							
		} else if (event instanceof CheckMarker) {
			worker.execute(() -> {
					CheckValueReturn checkreturnedvalue =new CheckValueReturn ();
					checkreturnedvalue.robotId = event.robotId;
					checkreturnedvalue.value=checkMarkers();
					eventBus.deliver(checkreturnedvalue);									
					System.out.println(" >> Robot "+this.config.id()+" reply with CheckValueReturn: " + checkreturnedvalue.value);	
			});						
		} else {
			System.out.println("Argh! Robot "+this.config.id()+" Received an unknown event type " + event.getClass());
								
		}
		
	}

	private int writeGOTO(int pickPoseID, String mission) {
		Cooridinate cooridinate=robotWarehouse.GetCooridinate(pickPoseID, mission);
		this.cooridinate=cooridinate;
		robot_local_control_msgs.GoToPetitionRequest GoTorequest=goToComponent.constructMsg_gotoRun();
		int writeResult=goToComponent.call_gotoRun(GoTorequest)[0]; // returnVal[0] => result, returnVal[1] => state
		return writeResult;

	}
	
	private int placeCART(){
		robot_local_control_msgs.PlacePetitionRequest Placerequest= placeComponent.constructMsg_placeRun();
		return placeComponent.call_placeRun(Placerequest)[0];
	}
	
	private int pickCart(int markerID){
	//	String cartName= robotWarehouse.getCartName(markerID);
	//	this.pickFrameId=cartName;
		robot_local_control_msgs.PickPetitionRequest Pickrequest= pickComponent.constructMsg_pickRun();
		return pickComponent.call_pickRun(Pickrequest)[0];
	}
	
	private int cancel(int mission){
		int state;
		switch(mission){
			case 3:
				procedures_msgs.ProcedureQueryRequest Gotocancel= goToComponent.constructMsg_gotoCancle();
				state=goToComponent.call_gotoCancle(Gotocancel)[0];
				break;
			case 7:
				procedures_msgs.ProcedureQueryRequest Pickcancel= pickComponent.constructMsg_pickCancle();
				state=pickComponent.call_pickCancle(Pickcancel)[0];
				break;
			case 11:
				procedures_msgs.ProcedureQueryRequest Placecancel= placeComponent.constructMsg_placeCancle();
				state=placeComponent.call_placeCancle(Placecancel)[0];
				break;
			default:
				state=0;
				break;	
		}
		System.out.println("Finish cancel");
		return state;
	}
	

	private CallResponse queryState(String mission){
		CallResponse callResp = null;
		
		if(mission.equalsIgnoreCase("UNLOAD") || mission.equalsIgnoreCase("PLACE_C") || mission.equalsIgnoreCase("PLACE_L") || mission.equalsIgnoreCase("PLACE_R")) {
			ProcedureQueryRequest Gotoquery=goToComponent.constructMsg_gotoQuery();
			callResp=goToComponent.call_gotoQuery(Gotoquery);
		} else if(mission.equalsIgnoreCase("PICK")) {
			procedures_msgs.ProcedureQueryRequest Pickquery= pickComponent.constructMsg_pickQuery();
			callResp=pickComponent.call_pickQuery(Pickquery);
		} else if(mission.equalsIgnoreCase("PLACE")) {
			procedures_msgs.ProcedureQueryRequest Placequery= placeComponent.constructMsg_placeQuery();
			callResp=placeComponent.call_placeQuery(Placequery);
		} else {
			System.out.println("\n -->Robot "+robotId+" queryState() not match mission  !!!!");
		}
		return callResp;		
	}
	
	private int checkMarkers(){
		List<AlvarMarker> markerList = ar_pose_marker.get_poseMarker_value().getMarkers();
		System.out.println("\n -->Robot "+robotId+" see Markers size = "+ markerList.size()+", and marker ID = "+ markerList.get(0).getId());
		return markerList.get(0).getId();
		
	}
	
	
	public void wait(int t) {
		try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

/*	public void autoDetectDoor() {
		Status status = null;
		geometry_msgs.Pose2D currentPose = null;
		String operation_state = null;
		String navigation_status_type = null;
		double x, y, z = 0;
		int markerID = 0;

		if (currentMission!=null && !currentMission.equals("UNLOAD")) { // it's on the way to Storage area, instead of the Unload area
			System.out.println("ROBOT "+robotId +" currentMission = "+currentMission);
			while (true) {
				status = availibility.get_availiblity_value();
				currentPose = status.getLocalizationStatus().getPose().getPose();
				operation_state = status.getOperationState();
				navigation_status_type = status.getNavigationStatus().getType(); // None

				if (currentPose != null && operation_state != null && navigation_status_type != null) {
					if (operation_state.equals("idle") && navigation_status_type.equals("None")) {
						// means robot is doing nothing, it may arrived target, or stop in front of
						// DOOR.
						System.out.println("ROBOT "+robotId +" get idle+None......... ");
						x = currentPose.getX();
						y = currentPose.getY();
						z = currentPose.getTheta();
						if (y > -1 && y < 1) { // closed to the DOOR
							System.out.println("ROBOT "+robotId +" stop in front of Door");
							markerID = checkMarkers();
							if (markerID == 1) {
								System.out.println("\n -->Robot " + robotId + " reports a DoorFound event.........");
								DoorFound df = new DoorFound();
								df.robotId = this.robotId;
								eventBus.deliver(df);
							}
						} else {
							checkDoorStatus = false;
							break; // robot stops at storage area, but not in front of the door
						}
					} else {
						// robot is moving
						wait(2);
					}
				}
			}
		}
	}
	*/
	
}