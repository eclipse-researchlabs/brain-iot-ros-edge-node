package eu.brain.iot.robot.service;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
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
import procedures_msgs.ProcedureQueryRequest;

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
    private AvailibilityImpl availibility;
    private Ar_pose_markerImpl ar_pose_marker;
    private GoToComponentImpl  GoToComponent;
    private PickComponentImpl PickComponent;
    private PlaceComponentImpl PlaceComponent;
	DoorControllerImpl door_controller;
	private BundleContext context;
	private Warehouse robotWarehouse;

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
	    
	    worker = Executors.newSingleThreadExecutor();
	    
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
		return GraphName.of(config.name()+"/serviceController");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		
		System.out.println("\n The Robot services are registering....for Robot "+config.id());
		MessageFactory msgfactory =  connectedNode.getTopicMessageFactory();  
		
		availibility =new AvailibilityImpl(robotName,connectedNode);
		availibility.register();
		System.out.println("availibility registered.");
		ar_pose_marker=new Ar_pose_markerImpl(robotName, connectedNode);
		ar_pose_marker.register();
		System.out.println("ar_pose_marker registered.");                     
		GoToComponent=new GoToComponentImpl(robotName, connectedNode,msgfactory);
		GoToComponent.register();
		System.out.println("GoToComponent service registed.");
		PickComponent=new PickComponentImpl(robotName,connectedNode,msgfactory);
		PickComponent.register();
		System.out.println("PickComponent service registed.");
		PlaceComponent=new PlaceComponentImpl(robotName,connectedNode,msgfactory);
		PlaceComponent.register();
		System.out.println("PlaceComponent service registed.");
		door_controller=new DoorControllerImpl(robotName,connectedNode,msgfactory);
		door_controller.register();
		System.out.println("Door service registed.");
		
		try{
			this.robotWarehouse=new Warehouse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

//		try {
//			test();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public void test() throws InterruptedException {
		System.out.println(robotId+"GET the current status:" + availibility.getCurrentStatus().getRobotState());
//		System.out.println(robotId+"GOT the markers:"+ ar_pose_marker.getMarkersList().getMarkers().size());
		door_controller.DoorOpen();
		taskMoveCart(robotId);
//		pickCart(1);
//		queryState(3);
//		writeGOTO(robotName,1);
//		queryState(3);	 	
//		writeGOTO(robotName,1);
//		writeGOTO(robotName,1);
//		cancel(3);
//		queryState(3);
//		queryState(3);
//		cancel(3);
//		queryState(7);
//		queryState(7);
//		writeGOTO(robotName,1);
//		cancel(3);
//		queryState(7);
//		pickCart(1);
//		System.out.println("query:"+queryState(7));
//		cancel(7);
//		cancel(7);
//		queryState(7);
//		placeCART();
//		cancel(11);
//		robot_local_control_msgs.GoToPetitionRequest GoTorequest=GoToComponent.constructGoToMessage();
//		GoToComponent.call_GoTo(GoTorequest);
//	    ProcedureQueryRequest queryStateRequest=GoToComponent.constructQueryStateMessage();
//		GoToComponent.call_Query_State(queryStateRequest);
//		GoToComponent.call_GoTo(GoTorequest);
//		GoToComponent.call_Query_State(queryStateRequest);
//		ProcedureQueryRequest cancelRequest=GoToComponent.constructCancleMessage();
//		GoToComponent.call_Cancel(cancelRequest);
//		GoToComponent.call_Query_State(queryStateRequest);
//		robot_local_control_msgs.PickPetitionRequest pickRequest=PickComponent.constructPickMessage("rb1_base_a_cart2_contact");
//		PickComponent.call_Pick(pickRequest);
//		procedures_msgs.ProcedureQueryRequest queryPickStateRequest=PickComponent.constructPickQuerySateMessage();
//		PickComponent.call_Query_State(queryPickStateRequest);
//		procedures_msgs.ProcedureQueryRequest cancelPickRequest=PickComponent.constructPickCancleMessage();
//		PickComponent.call_Cancel(cancelPickRequest);
//		PickComponent.call_Query_State(queryPickStateRequest);
//		robot_local_control_msgs.PlacePetitionRequest placeRequest =PlaceComponent.constructPlaceRequest(); 
//		PlaceComponent.call_Place(placeRequest);
        
//        writeGOTO(robotName,1);
//		door_controller.DoorClose();
		
	}


	@Override
	public void notify(RobotCommand event) {
		
		System.out.println(" >> Robot "+this.config.id()+" received an event: " + event.getClass());
		

		if (event instanceof WriteGOTO) {
			WriteGOTO wgoto = (WriteGOTO) event;
			worker.execute(() ->{ 
				QueryStateValueReturn queryreturnedvalue =new QueryStateValueReturn();
				queryreturnedvalue.value=writeGOTO(wgoto.mission);//First check the command is correctly send or not
				queryreturnedvalue.robotId = robotId;
				queryreturnedvalue.mission = wgoto.mission;
				if(queryreturnedvalue.value==1)//the command is correctly received
				{
					queryreturnedvalue.value=-1;
					while(queryreturnedvalue.value!=1 && queryreturnedvalue.value!=5 && queryreturnedvalue.value!=0 )
					{
						queryreturnedvalue.value =queryState(3);
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
						queryreturnedvalue.value =queryState(7);
					}
				}
				eventBus.deliver(queryreturnedvalue);
				});
			
		} else if (event instanceof PlaceCART) {
			PlaceCART plcacart = (PlaceCART) event;
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
						queryreturnedvalue.value =queryState(11);
					}
				}
				eventBus.deliver(queryreturnedvalue);
				});
						
		} else if (event instanceof QueryState) {
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
		Request req=robotWarehouse.Get_Request(robotId,mission);
		robot_local_control_msgs.GoToPetitionRequest GoTorequest=GoToComponent.constructGoToMessage(req);
		int writeResult=GoToComponent.call_GoTo(GoTorequest);
		return writeResult;

	}
	
	private int placeCART(){
		robot_local_control_msgs.PlacePetitionRequest Placerequest= PlaceComponent.constructPlaceRequest();
		return PlaceComponent.call_Place(Placerequest);
	}
	
	private int pickCart(int cartId){
		String cart= robotWarehouse.getCart(cartId);
		robot_local_control_msgs.PickPetitionRequest Pickrequest= PickComponent.constructPickMessage(cart);
		return PickComponent.call_Pick(Pickrequest);
	}
	
	private int cancel(int mission){
		int state;
		switch(mission){
			case 3:
				procedures_msgs.ProcedureQueryRequest Gotocancel= GoToComponent.constructCancleMessage();
				state=GoToComponent.call_Cancel(Gotocancel);
				break;
			case 7:
				procedures_msgs.ProcedureQueryRequest Pickcancel= PickComponent.constructPickCancleMessage();
				state=PickComponent.call_Cancel(Pickcancel);
				break;
			case 11:
				procedures_msgs.ProcedureQueryRequest Placecancel= PlaceComponent.constructPlaceCancleMessage();
				state=PlaceComponent.call_Cancel(Placecancel);
				break;
			default:
				state=0;
				break;	
		}
		return state;
	}
	

	private int queryState(int mission){
		int state;
		switch(mission){
			case 3:
			    ProcedureQueryRequest Gotoquery=GoToComponent.constructQueryStateMessage();
			    state=GoToComponent.call_Query_State(Gotoquery);
				break;
			case 7:
				procedures_msgs.ProcedureQueryRequest Pickquery= PickComponent.constructPickQuerySateMessage();
				state=PickComponent.call_Query_State(Pickquery);
				break;
			case 11:
				procedures_msgs.ProcedureQueryRequest Placequery= PlaceComponent.constructPlaceQuerySateMessage();
				state=PlaceComponent.call_Query_State(Placequery);
				break;
			default:
				state=0;
				break;
		}
		return state;		
	}
	
	private int checkMarkers(int RobotId, int obj){
		return ar_pose_marker.getMarkersList().getMarkers().size();
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