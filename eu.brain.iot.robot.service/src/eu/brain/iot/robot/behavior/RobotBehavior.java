package eu.brain.iot.robot.behavior;

import java.util.Dictionary;
import java.util.Hashtable;
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
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;

import com.paremus.brain.iot.example.door.api.DoorStatus;
import com.paremus.brain.iot.example.door.api.DoorStatus.State;
import com.paremus.brain.iot.example.door.api.DoorStatusResponse;

import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.BrainIoTEvent;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.database.TaskInfo;
import eu.brain.iot.robot.database.DBAccessor;
import eu.brain.iot.robot.events.CheckMarker;
import eu.brain.iot.robot.events.CheckValueReturn;
import eu.brain.iot.robot.events.DoorFound;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCART;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotReady;
import eu.brain.iot.robot.events.RobotCommand;
import eu.brain.iot.robot.events.WriteGOTO;

@Component(
		configurationPid= "eu.brain.iot.example.robot.Robot",
		configurationPolicy=ConfigurationPolicy.REQUIRE,
		service = {NodeMain.class})
@SmartBehaviourDefinition(
		consumed = {CheckValueReturn.class, QueryStateValueReturn.class,RobotReady.class,DoorFound.class},    
		author = "LINKS", name = "Robot Behavior",
		description = "Implements a Robot Behavior.")
public class RobotBehavior extends AbstractNodeMain implements SmartBehaviour<BrainIoTEvent> {

    private int robotId;
	private boolean robotReady=false;
	private QueryStateValueReturn feedback;
	private int markerID = 0;
	private int newMarkerCounter = 0;
	private int currentMarkerCounter = 0;
	private boolean isDoorOpen = false;

	
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
	private TaskInfo taskInfo = null;

	@Reference
	private EventBus eventBus;
	
	@Reference
	private DBAccessor DBAccessor;

    @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
	    this.config=config;
	    System.out.println("\nHello!  I am robotBehavior : "+config.id()+ "  name = "+config.name());
	    
	    worker = Executors.newFixedThreadPool(10);
//	    worker = Executors.newSingleThreadExecutor();
	    Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith("."))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			
			serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter", 
		    String.format("(|(robotId=%s)(robotId=%s))", config.id(), RobotCommand.ALL_ROBOTS));
			
			System.out.println("+++++++++ filter = "+serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
			reg = context.registerService(SmartBehaviour.class, this, serviceProps);

		this.robotId=config.id();
		
	//	onStart();
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
		return GraphName.of("rb1_base_"+config.id()+"/behaviorController");
	}
    
    @Override
	public void onStart(ConnectedNode connectedNode) {
		
		boolean flag = true;
	//	worker.execute(() -> {
		while (flag) {

			if (robotReady) {
				this.taskInfo = DBAccessor.getTask();

				if (taskInfo != null) {
					
					System.out.println("-----------new task-------------");
					System.out.println("robot" + robotId + " get TaskInfo: taskId: " + taskInfo.taskId
							+ ",  pickPoseID: " + taskInfo.pickPoseID + ", placePose: " + taskInfo.placePose);
					System.out.println("------------------------");

					WriteGOTO writeGOTO = createWriteGOTO("UNLOAD");  // writeGOTO
					eventBus.deliver(writeGOTO);
					System.out.println("-->RB" + robotId + " sending goto cmd");
					if (waitFeedback(writeGOTO.mission) == 0) { // UNLOAD, state=finished
						robotReady = false;
						System.out.println("-->RB " + robotId + " execute GOTO action failed, Robot Behavior stops !!!!");
						break;
					} else {
						System.out.println("-->RB " + robotId + " GOTO unload area successfully");
					}

					CheckMarker checkMarker = createCheckMarker();  // CheckMarker
					eventBus.deliver(checkMarker);
					System.out.println("-->RB" + robotId + " sending CheckMarker");
					
					int newMarkerID = waitMarker();
					System.out.println("-->RB" + robotId + " got new MarkerID = "+newMarkerID);
					
					PickCart pickCart = createPickCart(newMarkerID);  // PickCart
					eventBus.deliver(pickCart);
					System.out.println("-->RB" + robotId + " sending pick");
					if (waitFeedback(pickCart.mission) == 0) {
						robotReady = false;
						System.out.println("-->RB " + robotId + " execute PickCart action failed, so Robot Behavior stops !!!!");
						break;
					} else {
						System.out.println("-->RB " + robotId + " PickCart successfully");
					}

					writeGOTO = createWriteGOTO(taskInfo.placePose); // writeGOTO  PLACE_C, PLACE_L, PLACE_R
					eventBus.deliver(writeGOTO);
					System.out.println("-->RB" + robotId + " sending goto");
					
			/*		int time = 15;
					try {
						System.out.println("-->Robot " + robotId + " is waiting for "+time +" s ~~~~~~~~~~~~~~~~~~~~~~~~~");
						// wait for robot stop in front of door, or pass through the door, indicated by the isDoorOpen
						TimeUnit.SECONDS.sleep(time);  // TODO SHOULD check the current position of robot in waitFeedback()
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(isDoorOpen) {  // robot ever stopped in front of door, need to send GOTO again.
						eventBus.deliver(writeGOTO);
						System.out.println("\n -->RB" + robotId + " sending goto again.....after door is open");
					}
			*/		
					
					if (waitFeedback(taskInfo.placePose) == 0) {
						robotReady = false;
						System.out.println("-->RB " + robotId + " execute GOTO action failed, Robot Behavior stops !!!!");
						break;
					} else {
						System.out.println("-->RB " + robotId + " GOTO storage area successfully");
					}

					PlaceCART placeCart = createPlaceCART();  // PlaceCART
					eventBus.deliver(placeCart);
					System.out.println("-->RB" + robotId + " sending PlaceCART");

					if (waitFeedback(placeCart.mission) == 0) {
						robotReady = false;
						System.out.println("-->RB " + robotId + " execute PlaceCART action failed, Robot Behavior stops !!!!");
						break;
					} else {
						System.out.println("-->RB " + robotId + " place cart successfully");
					}
					

				} else {
					flag = false; // break, all tasks are done
				}
				
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	//	});
		
		if(flag == false) {
			System.out.println("-->Tasks are done. Robot Behavior " + robotId + " exit !!!"); 
		} else {
			System.out.println("-->RB " + robotId+"  exit because of failure in robot!!!"); 
		}
	}
   
	private WriteGOTO createWriteGOTO(String mission) {
		WriteGOTO writeGOTO = new WriteGOTO();
		writeGOTO.robotId=robotId;
		writeGOTO.mission=mission;  //UNLOAD, PLACE_C, PLACE_L, PLACE_R
		writeGOTO.pickPoseID=taskInfo.pickPoseID;
		return writeGOTO;
	}
	
	private PickCart createPickCart(int markerID) {
		PickCart pc= new PickCart();
		pc.robotId=robotId;
		pc.markerID=markerID;
		return pc;
	}
	
	private PlaceCART createPlaceCART() {
		PlaceCART placeCart=new PlaceCART();
		placeCart.robotId=robotId;
		return placeCart;
	}
	
	private CheckMarker createCheckMarker() {
		CheckMarker checkMarker=new CheckMarker();
		checkMarker.robotId=robotId;
		return checkMarker;
	}

	
	@Override
	public void notify(BrainIoTEvent event) {
		
		if (event instanceof RobotReady) {
			RobotReady rbc = (RobotReady) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotId + " received RobotReady event");
				robotReady = rbc.isReady;
			});

		} else if (event instanceof QueryStateValueReturn) {
			QueryStateValueReturn qs = (QueryStateValueReturn) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotId + " receive query return value = "+qs.value);
				this.feedback = qs;
			});

		}  else if (event instanceof CheckValueReturn) {
			CheckValueReturn cvr = (CheckValueReturn) event;
			worker.execute(() -> {
				System.out.println("-->RB" + robotId + " receive Check Marker return, marker ID = "+cvr.value);
				this.markerID = cvr.value;
				this.newMarkerCounter += 1;
			});

		} else if (event instanceof DoorFound) {
			worker.execute(() -> {
				DoorStatus status = new DoorStatus();
				status.doorId = DoorStatus.ALL_DOORS;
				status.targetState = State.OPEN;
				
				System.out.println("-->RB" + robotId + " SENDing DoorStatus = OPEN");
				eventBus.deliver(status);
			});
		} else if (event instanceof DoorStatusResponse) {
			worker.execute(() -> {
				DoorStatusResponse response = (DoorStatusResponse) event;
				if(response.state == State.OPEN) {
					isDoorOpen = true;
					System.out.println("-->RB" + robotId + " door is opened successfully!!!!");
				}
			});
		}
		
	}
	
	public int waitFeedback(String mission) {
		System.out.println("-->RB" + robotId + " in waiting feedback");
		while (true) {
			if (feedback != null) {
				if (feedback.mission.equals(mission) && feedback.value == 1) {
					feedback = null;
					return 1;
				} else
					return 0;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

/*	public int waitMarker() {
		System.out.println("-->RB" + robotId + " is waiting for pose Marker");
		while (true) {
			if (markerID!=0) {
			//	this.currentMarkerCounter = this.newMarkerCounter;
				return markerID;
			} else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
	
	public int waitMarker() {
		System.out.println("-->RB" + robotId + " is waiting for pose Marker");
		while (true) {
			if (this.currentMarkerCounter != this.newMarkerCounter) {
				this.currentMarkerCounter = this.newMarkerCounter;
				return markerID;
			} else {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
