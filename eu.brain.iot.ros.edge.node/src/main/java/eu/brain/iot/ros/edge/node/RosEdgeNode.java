package eu.brain.iot.ros.edge.node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
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

import org.apache.commons.lang.exception.ExceptionUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import ar_track_alvar_msgs.AlvarMarker;
import be.iminds.iot.ros.api.Ros;
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.events.*;
import eu.brain.iot.robot.events.QueryStateValueReturn.CurrentState;
import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.api.Coordinate;
import eu.brain.iot.robot.api.RobotCommand;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
		immediate=true,
		service = {NodeMain.class})
@SmartBehaviourDefinition(
		consumed = {WriteGoTo.class, Cancel.class, PickCart.class, PlaceCart.class, QueryState.class, CheckMarker.class },    
		author = "LINKS", name = "ROS Edge Node",
		description = "Implements a remote Smart Robot.")
public class RosEdgeNode extends AbstractNodeMain implements SmartBehaviour<RobotCommand>{
	
    private String robotName;
    private int robotID;
    private String robotIP;
    private AvailibilityComponent availibility;
    private PoseMarkerComponent ar_pose_marker;
    private GoToComponent  goToComponent;
    private PickComponent pickComponent;
    private PlaceComponent placeComponent;
 //   private BatteryVoltageComponent batteryVoltageComponent;
	private Coordinate coordinate;
	private String pickFrameId;

	private ExecutorService worker;
	private ServiceRegistration<?> reg;
	private boolean isWorkDone = false;
	
	@Reference
	private Ros ros;
	
	@Reference
	private EventBus eventBus;
	
	@Reference
	private CartMapper cartMapper;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(RosEdgeNode.class.getSimpleName());
	
//	private BufferedWriter out = null;
	private static volatile FileWriter out = null;
	
    @Activate
	void activate(BundleContext context, Map<String,Object> props){
    	
			this.robotName = ros.getRobotName();
			this.robotID = ros.getRobotId();
			this.robotIP = ros.getRobotIP();
			
	/*		RandomAccessFile writer = null;
			FileChannel channel = null;*/
			
		try {
	/*		writer = new RandomAccessFile("/opt/fabric/resources/roscore1.log", "rw");
			channel = writer.getChannel();
			ByteBuffer buff = ByteBuffer.wrap("Hello!  I am ROS Edge Node ".getBytes(StandardCharsets.UTF_8));
			channel.write(buff);*/
			out = new FileWriter("/opt/fabric/resources/rosnode.log", true);
		//	out = new BufferedWriter(new FileWriter("/opt/fabric/resources/rosnode.log", true));

			String UUID = context.getProperty("org.osgi.framework.uuid");
			
    	out.write("\nHello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID);
    	out.flush();
    	
    	logger.info("\nHello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID);
    	
    	System.out.println("\nHello!  I am ROS Edge Node : "+robotID+ "  name = "+robotName+ "  IP = "+robotIP+ ",  UUID = "+UUID);
    	
	    worker = Executors.newFixedThreadPool(10);

		Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith(".")).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));

		serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter",
				String.format("(|(robotID=%s)(robotID=%s))", robotID, RobotCommand.ALL_ROBOTS));

		logger.info("+++++++++ Ros Edge Node filter = " + serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
		out.write("\n+++++++++ Ros Edge Node filter = " + serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
		reg = context.registerService(SmartBehaviour.class, this, serviceProps);
		
			} catch (IOException e) {
				logger.error("\n ROS Edge Node Write log.txt Exception: {}", ExceptionUtils.getStackTrace(e));
			} finally {
				if (out != null) {
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						logger.error("\n ROS Edge Node close log.txt Exception: {}", ExceptionUtils.getStackTrace(e));
					}
				}
			}

	}

   @Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("robot_"+robotID+"/serviceController");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		
		logger.info("\n The ROS Edge Node is registering....for Robot "+robotID);
		System.out.println("\n The ROS Edge Node is registering....for Robot "+robotID);
		
		try {
		out = new FileWriter("/opt/fabric/resources/rosnode.log", true);
		out.write("\n The ROS Edge Node is registering....for Robot "+robotID);
		
		MessageFactory msgfactory =  connectedNode.getTopicMessageFactory();  

		availibility =new AvailibilityComponent(connectedNode,robotName) {};
		availibility.register();
		logger.info("availibility registered.");
		out.write("\navailibility registered.");
		
		ar_pose_marker=new PoseMarkerComponent(connectedNode,robotName) {};
		ar_pose_marker.register();
		logger.info("ar_pose_marker registered."); 
		out.write("\nar_pose_marker registered.");
		
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
		logger.info("GoToComponent service registed.");
		out.write("\nGoToComponent service registed.");
		
		pickComponent=new PickComponent(connectedNode,msgfactory,robotName) {
			@Override
			public PickPetitionRequest constructMsg_pickRun() {
				robot_local_control_msgs.PickPetitionRequest pickRequest=pickRun.serviceClient.newMessage();
				Pick procedure= msgfactory.newFromType(Pick._TYPE);
				procedure.setPickFrameId(pickFrameId); // TODO 1, to be used in real robot, 2 is in queryer
				pickRequest.setProcedure(procedure);
				return pickRequest;
			}
		};
		pickComponent.register();
		logger.info("PickComponent service registed.");
		out.write("\nPickComponent service registed.");
		
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
		logger.info("PlaceComponent service registed.");
		out.write("\nPlaceComponent service registed.");
		
		broadCastReady();

		} catch(IOException e) { // for write to log file
			logger.error("\n ROS Edge Node IOException: {}", ExceptionUtils.getStackTrace(e));
	/*		if (out != null) {
				try {
					out.close();
				} catch (IOException ei) {
					logger.error("\n ROS Edge Node Close log.txt Exception: {}", ExceptionUtils.getStackTrace(ei));
				}
			}*/
		} catch(Exception e) {
			logger.error("\n ROS Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
			try {
				out.write("\n ROS Edge Node Exception: "+ ExceptionUtils.getStackTrace(e));
			} catch (IOException e1) {
				logger.error("\n ROS Edge Node Close log.txt Exception: {}", ExceptionUtils.getStackTrace(e1));
			/*	if (out != null) {
					try {
						out.close();
					} catch (IOException ei) {
						logger.error("\n ROS Edge Node Close log.txt Exception: {}", ExceptionUtils.getStackTrace(ei));
					}
				}*/
			}
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException ei) {
					logger.error("\n ROS Edge Node Close log.txt Exception: {}", ExceptionUtils.getStackTrace(ei));
				}
			}
		}
	}
	
	
	public void broadCastReady() throws IOException
	{
		RobotReadyBroadcast rbc=new RobotReadyBroadcast();  // TODO  RobotReady not exist in real robot, now only for local test
		rbc.robotID = robotID;
		rbc.robotIP = robotIP;
		rbc.isReady = true;
		eventBus.deliver(rbc);
		logger.info(" >>> robot_"+robotID+" broadCast Ready info");
		RosEdgeNode.out.write("\n >>> robot_"+robotID+" broadCast Ready info");
		
	}

	@Override
	public void notify(RobotCommand event) {
		
		try {
			out = new FileWriter("/opt/fabric/resources/rosnode.log", true);
			
		logger.info(" >>> Robot "+robotID+" received an event: " + event.getClass().getSimpleName()+" with robotID = "+event.robotID);
		
		out.write("\n >>> Robot "+robotID+" received an event: " + event.getClass().getSimpleName()+" with robotID = "+event.robotID);
		

		if (event instanceof WriteGoTo) {
			
			WriteGoTo writeGoTo = (WriteGoTo) event;
			worker.execute(() ->{
				logger.info(" >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate);
				try {
					RosEdgeNode.out.write("\n >>> Robot "+robotID+" received GoTo: " + writeGoTo.coordinate);
				} catch (IOException e) {
					logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
				}
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
							
							logger.info(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							try {
								RosEdgeNode.out.write("\n >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
							} catch (IOException e) {
								logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
							}
							
							if(callResp.last_event.equals("abort")) {
								logger.info(" >>> robot "+robotID+" query WriteGOTO action finished, but last_event = abort, so send CurrentState = unknown!");
								try {
									RosEdgeNode.out.write("\n >>> robot "+robotID+" query WriteGOTO action finished, but last_event = abort, so send CurrentState = unknown!");
								} catch (IOException e) {
									logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
								}
							//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
								queryReturnedValue.currentState = CurrentState.unknown;
								break;
							}
							queryReturnedValue.currentState = CurrentState.finished;
							break;
							
						} else if(callResp.current_state.equals("unknown")) {
							
							logger.info(" >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
									+", last_event="+callResp.last_event+", message is: "+callResp.message);
							try {
								RosEdgeNode.out.write("\n >>> robot "+robotID+" WriteGOTO gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
							} catch (IOException e) {
								logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
							}
							queryReturnedValue.currentState = CurrentState.unknown;
							break;
						} else {
							wait(2);
						}
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" query WriteGOTO no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
						try {
							RosEdgeNode.out.write("\n >>> robot "+robotID+" query WriteGOTO no response, timeout!");
						} catch (IOException e) {
							logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
						}
						break;
					}
				  }  // while end
				} else {
					queryReturnedValue.currentState = CurrentState.unknown;
					logger.info(" >>> robot "+robotID+" sends WriteGOTO failed! Return CurrentState.unknown");
					try {
						RosEdgeNode.out.write("\n >>> robot "+robotID+" sends WriteGOTO failed! Return CurrentState.unknown");
					} catch (IOException e) {
						logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
					}
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
								
								logger.info(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								try {
									RosEdgeNode.out.write("\n >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
											+", last_event="+callResp.last_event+", message is: "+callResp.message);
								} catch (IOException e) {
									logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
								}
								
								if(callResp.last_event.equals("abort")) {
									logger.info("robot "+robotID+" query pickCart action finished, but last_event = abort, so send CurrentState = unknown!");
									try {
										RosEdgeNode.out.write("\nrobot "+robotID+" query pickCart action finished, but last_event = abort, so send CurrentState = unknown!");
									} catch (IOException e) {
										logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
									}
								//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
									queryReturnedValue.currentState = CurrentState.unknown;
									break;
								}
								queryReturnedValue.currentState = CurrentState.finished;
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								logger.info(" >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								try {
									RosEdgeNode.out.write("\n >>> robot "+robotID+" pickCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
											+", last_event="+callResp.last_event+", message is: "+callResp.message);
								} catch (IOException e) {
									logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
								}
								queryReturnedValue.currentState = CurrentState.unknown;
								break;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							logger.info(" >>> robot "+robotID+" query pickCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
							try {
								RosEdgeNode.out.write("\n >>> robot "+robotID+" query pickCart no response, timeout!");
							} catch (IOException e) {
								logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
							}
							break;
						}
					  }  // while end
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" sends pickCart failed! Return CurrentState.unknown");
						try {
							RosEdgeNode.out.write("\n >>> robot "+robotID+" sends pickCart failed! Return CurrentState.unknown");
						} catch (IOException e) {
							logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
						}
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
								
								logger.info(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								
								if(callResp.last_event.equals("abort")) {
									logger.info("robot "+robotID+" query PlaceCart action finished, but last_event = abort, so send CurrentState = unknown!");
								//	continue; // the case might be one action is running, another action cmd is also received, 2nd cmd will be abort.
									queryReturnedValue.currentState = CurrentState.unknown;
									break;
								}
								queryReturnedValue.currentState = CurrentState.finished;
								break;
							} else if(callResp.current_state.equals("unknown")) {
								
								logger.info(" >>> robot "+robotID+" PlaceCart gets CallResponse: result="+callResp.result+", current_state="+callResp.current_state
										+", last_event="+callResp.last_event+", message is: "+callResp.message);
								queryReturnedValue.currentState = CurrentState.unknown;
								break;
							} else {
								wait(2);
							}
						}else {
							queryReturnedValue.currentState = CurrentState.unknown;
							logger.info(" >>> robot "+robotID+" query PlaceCart no response, timeout!"); //TODO, let it query max=3 times, ==> may be done in goToComponent
							break;
						}
					  } // while end
					}else {
						queryReturnedValue.currentState = CurrentState.unknown;
						logger.info(" >>> robot "+robotID+" sends PlaceCart failed! Return CurrentState.unknown");
					}
					eventBus.deliver(queryReturnedValue);
			//	checkDoorStatus = false;
				isWorkDone = true;  // TODO
				logger.info(" >>> ROBOT "+robotID +" finishs this iteration......... ");
				});
						
		} else if (event instanceof QueryState) {	// Edge Node also can receive additional QueryState from other entities
			
			QueryState querySate = (QueryState) event;
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
							
		} else if (event instanceof CheckMarker) {
			worker.execute(() -> {
				MarkerReturn markerReturn =new MarkerReturn ();
				markerReturn.robotID = event.robotID;
				markerReturn.markerID=checkMarkers();
				eventBus.deliver(markerReturn);									
				logger.info(" >>> Robot "+robotID+" reply with MarkerReturn: " + markerReturn.markerID);	
			});						
		} else {
			logger.info(" >>> Argh! Robot "+robotID+" Received an unknown event type " + event.getClass());
								
		}
		
		} catch (IOException e) {
			logger.error("\n ROS Edge Node write rosnode.txt Exception: {}", ExceptionUtils.getStackTrace(e));
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException ei) {
					logger.error("\n ROS Edge Node Close log.txt Exception: {}", ExceptionUtils.getStackTrace(ei));
				}
			}
		}
		
	}

	private String writeGOTO(String coordinate) {
		
	//	this.coordinate=coordinate;
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
		
		while(markerList!=null) {
			logger.info("\n >>> Robot "+robotID+" see Markers List size = "+ markerList.size());
			
			if(markerList.size()>=1) {
				logger.info("\n >>> Robot "+robotID+" see first Marker ID = "+ markerList.get(0).getId());
				return markerList.get(0).getId();
			}
			else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					logger.error("\nRos Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
				}
				markerList = ar_pose_marker.get_poseMarker_value().getMarkers();
			}
		}
		logger.info("\n >>> Robot "+robotID+" doesn't see Marker List is empty, return default marker =100");
		return 100;
		
	}
	
	
	public void wait(int t) {
		try {
			TimeUnit.SECONDS.sleep(t);
		} catch (InterruptedException e) {
			logger.error("\nRos Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
		}
	}
	
	  @Deactivate
		void stop() {
		  try {
			out.close();
		} catch (IOException e) {
			logger.error("\nRos Edge Node Exception: {}", ExceptionUtils.getStackTrace(e));
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
		}
}