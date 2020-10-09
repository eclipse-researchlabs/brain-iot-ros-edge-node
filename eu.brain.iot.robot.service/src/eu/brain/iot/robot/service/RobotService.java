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
import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.events.Cancel;
import eu.brain.iot.robot.events.CheckMarker;
import eu.brain.iot.robot.events.CheckValueReturn;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCART;
import eu.brain.iot.robot.events.QueryState;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotCommand;
import eu.brain.iot.robot.events.WriteGOTO;
import eu.brain.iot.robot.warehouse.Request;
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
import robot_local_control_msgs.Twist2D;
import std_msgs.Header;

import java.util.concurrent.TimeUnit;

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
//	DoorComponent door_controller;
	private BundleContext context;
	private Warehouse robotWarehouse;
	private Request req;
	private String pickFrameId;
	@ObjectClassDefinition
	public static @interface Config {
		@AttributeDefinition(description = "The IP of the robot")
		String host();

		@AttributeDefinition(description = "The Port of the robot")
		int port();

		@AttributeDefinition(description = "The name of the robot")
		String name();

		@AttributeDefinition(description = "The identifier for the robot")
		int id();

	}

	private Config config;
	private ExecutorService worker;
	private ServiceRegistration<?> reg;

	@Reference
	private EventBus eventBus;

    @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
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
				pose.setTheta(req.pose.getTheta());
				pose.setX(req.pose.getX());
				pose.setY(req.pose.getY());
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
	//	door_controller=new DoorComponent(robotName,connectedNode);
	//	door_controller.register();
	//	System.out.println("Door service registed.");
		
		try{
			this.robotWarehouse=new Warehouse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	//	test();
	}
	
	
	public void test()
	{
		System.out.print("\n robot "+config.id()+" testing availibility: result = ");
		System.out.print(availibility.get_availiblity_value().getRobotStatus().getPose().getPose().getX());
		System.out.println(" ==> Done");
		System.out.print("\n robot "+config.id()+" testing marker: result = ");
		System.out.print("marker result = "+ar_pose_marker.get_poseMarker_value().getMarkers().size());
		System.out.println(" ==> Done");
	//	System.out.print("\n robot "+config.id()+" testing door");
	//	door_controller.publish_door_Msg(door_controller.construct_door_Msg("open"));
	//	System.out.println(" ==> Done");
		
	}



	@Override
	public void notify(RobotCommand event) {
		
		System.out.println(" >> Robot "+this.config.id()+" received an event: " + event.getClass());
		

		if (event instanceof WriteGOTO) {
			WriteGOTO wgoto = (WriteGOTO) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				queryreturnedvalue.value=writeGOTO(wgoto.mission);//First check the command is correctly send or not=> returnVal[0] means the response result(ok/error) 
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = wgoto.mission;
				if(queryreturnedvalue.value==1)		//the command is correctly received, result=ok
				{  	// then to check if the CMD is finished or not, then return the 'finished/unknown' state
					queryreturnedvalue.value=-1;
					while(queryreturnedvalue.value!=1 && queryreturnedvalue.value!=5 && queryreturnedvalue.value!=0 )
					{	//(1=finished, 2=queued, 3=running, 4=paused, 5=unknown)
						queryreturnedvalue.value =queryState(3); // goto Query, returnVal[1] means the response state numbers
					}
				}
				eventBus.deliver(queryreturnedvalue);
				}
			);
			
		}else if (event instanceof Cancel) {
			Cancel cancel = (Cancel) event;
			worker.execute(() -> cancel(cancel.mission));
			
		} else if (event instanceof PickCart) {
			PickCart pickcart = (PickCart) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				queryreturnedvalue.value=pickCart(pickcart.cart);
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = 7;
				if(queryreturnedvalue.value==1)
				{
					queryreturnedvalue.value=-1;
					while(queryreturnedvalue.value!=1 && queryreturnedvalue.value!=5 && queryreturnedvalue.value!=0)
					{
						queryreturnedvalue.value =queryState(7);	// pick Query
					}
				}
				eventBus.deliver(queryreturnedvalue);
				});
			
		} else if (event instanceof PlaceCART) {
			PlaceCART placeCart = (PlaceCART) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				queryreturnedvalue.value=placeCART();
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = 11;
				if(queryreturnedvalue.value==1)
				{
					queryreturnedvalue.value=-1;
					while(queryreturnedvalue.value!=1 && queryreturnedvalue.value!=5 && queryreturnedvalue.value!=0)
					{
						queryreturnedvalue.value =queryState(11);	// place Query
					}
				}
				eventBus.deliver(queryreturnedvalue);
				});
						
		} else if (event instanceof QueryState) {	// Edge Node also can receive additional QueryState from other entities
			QueryState querySate = (QueryState) event;
			worker.execute(() ->  {
					QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
					queryreturnedvalue.robotId = querySate.robotId;
					queryreturnedvalue.mission = querySate.mission;
					queryreturnedvalue.value =queryState(querySate.mission);
					eventBus.deliver(queryreturnedvalue);
					
					System.out.println(" >> Robot "+this.config.id()+" reply with QueryStateValueReturn: " + queryreturnedvalue.value);			
			});
							
		} else if (event instanceof CheckMarker) {
			worker.execute(() -> {
					CheckValueReturn checkreturnedvalue =new CheckValueReturn ();
					checkreturnedvalue.robotId = event.robotId;
					checkreturnedvalue.value=checkMarkers(event.robotId,5);
					eventBus.deliver(checkreturnedvalue);									
					System.out.println(" >> Robot "+this.config.id()+" reply with CheckValueReturn: " + checkreturnedvalue.value);	
			});						
		} else {
			System.out.println("Argh! Robot "+this.config.id()+" Received an unknown event type " + event.getClass());
								
		}
		
	}

	private int writeGOTO(int mission) {
		Request reqGet=robotWarehouse.Get_Request(robotId,mission);
		this.req=reqGet;
		robot_local_control_msgs.GoToPetitionRequest GoTorequest=goToComponent.constructMsg_gotoRun();
		int writeResult=goToComponent.call_gotoRun(GoTorequest)[0]; // returnVal[0] => result, returnVal[1] => state
		return writeResult;

	}
	
	private int placeCART(){
		robot_local_control_msgs.PlacePetitionRequest Placerequest= placeComponent.constructMsg_placeRun();
		return placeComponent.call_placeRun(Placerequest)[0];
	}
	
	private int pickCart(int cartId){
		String cart= robotWarehouse.getCart(cartId);
		this.pickFrameId=cart;
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
	

	private int queryState(int mission){
		int state;
		switch(mission){
			case 3:
				ProcedureQueryRequest Gotoquery=goToComponent.constructMsg_gotoQuery();
				state=goToComponent.call_gotoQuery(Gotoquery)[1];
				break;
			case 7:
				procedures_msgs.ProcedureQueryRequest Pickquery= pickComponent.constructMsg_pickQuery();
				state=pickComponent.call_pickQuery(Pickquery)[1];
				break;
			case 11:
				procedures_msgs.ProcedureQueryRequest Placequery= placeComponent.constructMsg_placeQuery();
				state=placeComponent.call_placeQuery(Placequery)[1];
				break;
			default:
				state=0;
				break;
		}
		return state;		
	}
	
	private int checkMarkers(int RobotId, int obj){
		return ar_pose_marker.get_poseMarker_value().getMarkers().size();
	}
	
	public void taskMoveCart(int cartID) throws InterruptedException
	{
//		while(writeGOTO(4)!=1)
//		{
//			TimeUnit.SECONDS.sleep(1);
//		}
//		while (queryState(3)!=1)
//		{
//			TimeUnit.SECONDS.sleep(1);
//		}
//		pickCart(cartID);
//		while(queryState(7)!=1)
//		{
//			TimeUnit.SECONDS.sleep(1);
//		}
//		switch(robotId)
//		{
//			case 1: writeGOTO(1);break;
//			case 2: writeGOTO(2);break;
//			case 3: writeGOTO(3);break;
//			default:break;
//		}
//		while (queryState(3)!=1)
//		{
//			TimeUnit.SECONDS.sleep(1);
//		}
//		placeCART();
		while (true)
		{
			queryState(3);
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

	
}