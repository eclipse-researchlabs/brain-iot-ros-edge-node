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
import eu.brain.iot.robot.events.*;
import eu.brain.iot.robot.events.QueryStateValueReturn.CurrentState;
import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.api.Coordinate;
import geometry_msgs.Pose2D;
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


@Component(
		configurationPid= "eu.brain.iot.example.robot.Robot",
		configurationPolicy=ConfigurationPolicy.REQUIRE,
		service = {NodeMain.class})
@SmartBehaviourDefinition(
		consumed = {WriteGoTo.class, Cancel.class, PickCart.class, PlaceCart.class, QueryState.class, CheckMarker.class },    
		author = "LINKS", name = "Smart Robot",
		description = "Implements a remote Smart Robot.")
public class RobotService extends AbstractNodeMain implements SmartBehaviour<RobotCommand>{
	
    private String robotName;
    private int robotID;
    private AvailibilityComponent availibility;
    private PoseMarkerComponent ar_pose_marker;
    private GoToComponent  goToComponent;
    private PickComponent pickComponent;
    private PlaceComponent placeComponent;
	private Coordinate coordinate;
	private String pickFrameId;
	
	@ObjectClassDefinition
	public static @interface Config {
		
		@AttributeDefinition(description = "The name of the robot")
		String name();

		@AttributeDefinition(description = "The identifier for the robot")
		int id();

	}
	

	private Config config;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;
	private boolean isWorkDone = false;
	
	@Reference
	private EventBus eventBus;
	
	@Reference
	private CartMapper cartMapper;


    @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
    	
    	String UUID = context.getProperty("org.osgi.framework.uuid");
    	
	    this.config=config;
	    System.out.println("\nHello!  I am robot : "+config.id()+ "  name = "+config.name()+",  UUID = "+UUID);
	    
	    worker = Executors.newFixedThreadPool(10);

	    Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith("."))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			
			serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter", 
		    String.format("(|(robotId=%s)(robotId=%s))", config.id(), RobotCommand.ALL_ROBOTS));
			
			System.out.println("+++++++++ filter = "+serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
			reg = context.registerService(SmartBehaviour.class, this, serviceProps);
            
		this.robotName=config.name();
		this.robotID=config.id();
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
		ar_pose_marker=new PoseMarkerComponent(connectedNode,robotName) {};
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
		System.out.println("GoToComponent service registed.");
		
		pickComponent=new PickComponent(connectedNode,msgfactory,robotName) {
			@Override
			public PickPetitionRequest constructMsg_pickRun() {
				robot_local_control_msgs.PickPetitionRequest pickRequest=pickRun.serviceClient.newMessage();
				Pick procedure= msgfactory.newFromType(Pick._TYPE);
				procedure.setPickFrameId(pickFrameId);
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
		
	/*	while(!isWorkDone) {  // TODO, check if only one cart is moved using one robot, how to keep running even exiting onStart()?
			wait(10);
		}
	*/	
		System.out.println("ROBOT "+robotID +" exit onStart()......... ");
		
	}
	
	
	public void broadCastReady()
	{
		RobotReady rbc=new RobotReady();  // TODO  RobotReady not exist in real robot, now only for local test
		rbc.robotID=robotID;
		rbc.isReady=true;
		eventBus.deliver(rbc);
		
	}

	@Override
	public void notify(RobotCommand event) {
		
		System.out.println(" >> Robot "+this.config.id()+" received an event: " + event.getClass());
		

		if (event instanceof WriteGoTo) {
			
			WriteGoTo writeGoTo = (WriteGoTo) event;
			worker.execute(() ->{
				QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn(); 
				
				String sendResult = writeGOTO(writeGoTo.coordinate);
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = writeGoTo.command;
				if(sendResult.equals("ok"))  // sending goto ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(writeGoTo.command);	// goto Query
					if((callResp != null)) {
						if(callResp.current_state.equals("finished")) {
							
							System.out.println("robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							
							if(callResp.last_event.equals("abort")) {
								System.out.println("robot "+robotID+" query WriteGOTO action finished, but last_event = abort, so continue to query");
								continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
							}
							queryReturnedValue.currentState = CurrentState.finished;
							break;
						} else if(callResp.current_state.equals("unknown")) {
							
							System.out.println("robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							queryReturnedValue.currentState = CurrentState.unknown;
						} else {
							wait(2);
						}
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						System.out.println("robot "+robotID+" query WriteGOTO no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
						break;
					}
				}
				}else {
					queryReturnedValue.currentState = CurrentState.unknown;
					System.out.println("robot "+robotID+" sends WriteGOTO failed! Return CurrentState.unknown");
				}
				eventBus.deliver(queryReturnedValue);
				}
			);
			
		}else if (event instanceof Cancel) {
			Cancel cancel = (Cancel) event;
			worker.execute(() -> cancel(cancel.command));
			
		} else if (event instanceof PickCart) {
			
			PickCart pickCart = (PickCart) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
				String sendResult = pickCart(pickCart.markerID);
				
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = pickCart.command;  // "PICK"
				if(sendResult.equals("ok"))  // sensing pick ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(pickCart.command);	// pick Query
						if((callResp != null)) {
							if(callResp.current_state.equals("finished")) {
								
								System.out.println("robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								
								if(callResp.last_event.equals("abort")) {
									System.out.println("robot "+robotID+" query pickCart action finished, but last_event = abort, so continue to query");
									continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
								}
								queryReturnedValue.currentState = CurrentState.finished;
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								System.out.println("robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								queryReturnedValue.currentState = CurrentState.unknown;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							System.out.println("robot "+robotID+" query pickCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
							break;
						}
					}
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						System.out.println("robot "+robotID+" sends pickCart failed! Return CurrentState.unknown");
					}
					eventBus.deliver(queryReturnedValue);
					}
				);
			
		} else if (event instanceof PlaceCart) {
			
			PlaceCart plceCart = (PlaceCart) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
				String sendResult = placeCart();
				queryReturnedValue.robotID = robotID;
				queryReturnedValue.command = plceCart.command;   // "PLACE"
				if(sendResult.equals("ok"))  // sending place ok
				{
					CallResponse callResp;
					while(true) {
						callResp = queryState(plceCart.command);	// place Query
						if((callResp != null)) {
							if(callResp.current_state.equals("finished")) {
								
								System.out.println("robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								
								if(callResp.last_event.equals("abort")) {
									System.out.println("robot "+robotID+" query PlaceCart action finished, but last_event = abort, so continue to query");
									continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
								}
								queryReturnedValue.currentState = CurrentState.finished;
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								System.out.println("robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								queryReturnedValue.currentState = CurrentState.unknown;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							System.out.println("robot "+robotID+" query PlaceCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
							break;
						}
					}
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						System.out.println("robot "+robotID+" sends PlaceCart failed! Return CurrentState.unknown");
					}
					eventBus.deliver(queryReturnedValue);
			//	checkDoorStatus = false;
				isWorkDone = true;  // TODO
				System.out.println("ROBOT "+robotID +" finishs this iteration......... ");
				});
						
		} else if (event instanceof QueryState) {	// Edge Node also can receive additional QueryState from other entities
			
			QueryState querySate = (QueryState) event;
			worker.execute(() ->  {
					QueryStateValueReturn queryReturnedValue =new QueryStateValueReturn();
					queryReturnedValue.robotID = querySate.robotID;
					queryReturnedValue.command = querySate.command;
					CallResponse callResp = queryState(querySate.command);
					
					if((callResp != null)) {	
							System.out.println("robot "+robotID+" queryState() gets response: result="+callResp.result+", current_state="+callResp.current_state
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
						System.out.println("robot "+robotID+" queryState() no response, timeout!  ==> it's set to unknown.");
					}
					
					eventBus.deliver(queryReturnedValue);
					
					System.out.println(" >> Robot "+this.config.id()+" reply with QueryStateValueReturn: " + queryReturnedValue.currentState);			
			});
							
		} else if (event instanceof CheckMarker) {
			worker.execute(() -> {
				MarkerReturn markerReturn =new MarkerReturn ();
				markerReturn.robotID = event.robotID;
				markerReturn.markerID=checkMarkers();
				eventBus.deliver(markerReturn);									
				System.out.println(" >> Robot "+this.config.id()+" reply with MarkerReturn: " + markerReturn.markerID);	
			});						
		} else {
			System.out.println("Argh! Robot "+this.config.id()+" Received an unknown event type " + event.getClass());
								
		}
		
	}

	private String writeGOTO(Coordinate coordinate) {
		this.coordinate=coordinate;
		robot_local_control_msgs.GoToPetitionRequest GoTorequest=goToComponent.constructMsg_gotoRun();
		return goToComponent.call_gotoRun(GoTorequest);

	}
	
	private String pickCart(int markerID){
		String cartName= cartMapper.getCartName(markerID);
		this.pickFrameId=cartName;
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
		System.out.println("Finish cancel");
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
			System.out.println("\n -->Robot "+robotID+" queryState() not match any action  !!!!");
			break;	
		}
		return callResp;		
	}
	
	private int checkMarkers(){ // TODO
		List<AlvarMarker> markerList = ar_pose_marker.get_poseMarker_value().getMarkers();
		System.out.println("\n -->Robot "+robotID+" see Markers size = "+ markerList.size()+", and first marker ID = "+ markerList.get(0).getId());
		return markerList.get(0).getId();
	}
	
	
	public void wait(int t) {
		try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}