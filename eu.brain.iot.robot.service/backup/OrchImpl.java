package eu.brain.iot.robot.service;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;

import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.database.DBAccessor;
import eu.brain.iot.robot.events.Cancel;
import eu.brain.iot.robot.events.CheckValueReturn;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCART;
import eu.brain.iot.robot.events.QueryState;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotCommand;
import eu.brain.iot.robot.events.WriteGOTO;
import eu.brain.iot.robot.service.RobotService.Config;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.felix.service.command.CommandProcessor;

@Component(
		property= {
				CommandProcessor.COMMAND_SCOPE + ":String=Orchestrator",	
		        CommandProcessor.COMMAND_FUNCTION + ":String=test" ,
		        CommandProcessor.COMMAND_FUNCTION + ":String=cancel",
		        CommandProcessor.COMMAND_FUNCTION + ":String=query",
		        CommandProcessor.COMMAND_FUNCTION + ":String=database"/*,
		        CommandProcessor.COMMAND_FUNCTION + ":String=openDoor" */
		},
		immediate=false/*,
		service = {NodeMain.class}*/
	)
@SmartBehaviourDefinition(consumed = {CheckValueReturn.class, QueryStateValueReturn.class},
filter = "(timestamp=*)",    
author = "UGA", name = "Smart Orchestrator",
description = "Implements a remote Smart Orchestrator.")
public class OrchImpl /* extends AbstractNodeMain */implements SmartBehaviour<RobotCommand> {

	
	private ExecutorService worker;
	
    @Reference
    private  EventBus eventBus;
    
    @Reference
	private DBAccessor DBAccessor;
    
// ---------------------------------down-------------------------   
 /*   @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
    	worker = Executors.newFixedThreadPool(10);
    }
 
    @Override
   	public GraphName getDefaultNodeName() {
   		return GraphName.of("Orchestrator");
   	}

    @Override
	public void onStart(ConnectedNode connectedNode) {
    	door_controller=new DoorComponent("",connectedNode);
		door_controller.register();
		System.out.println("\n ======> door_controller registered in Orchestrator  ");
    }

    public void openDoor() {
    	worker.execute(() -> door_controller.publish_door_Msg(door_controller.construct_door_Msg("open")));
    	System.out.println(" Publishing openDoor to the topic........");
    }
    
     @Deactivate
	void stop() {
		worker.shutdown();
		try {
			worker.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
	
    */
 // ---------------------------------up-------------------------
    
	@Override
	public void notify( RobotCommand event) {
		if(event instanceof QueryStateValueReturn) {
			QueryStateValueReturn qsvr = (QueryStateValueReturn) event;
			System.out.println("************************************");
			System.out.println("RobotId="+qsvr.robotId+" mission="+qsvr.mission+" result="+qsvr.value+ "(1 => finished)");
			System.out.println("************************************");
		}
	}
	
	public void test(String behavior,int robotId, String mission)
	{
		System.out.println("inside test!!");
	//	worker.execute(() ->{
		if(behavior.compareTo("goto")==0)
		{
			WriteGOTO wgoto = new WriteGOTO();
			wgoto.robotId=robotId;
			wgoto.cartId=robotId;
			wgoto.mission=mission;
			eventBus.deliver(wgoto);
		
		}
		else if(behavior.compareTo("pick")==0)
		{
			PickCart pk =new PickCart();
			pk.cartID=robotId;
			pk.robotId=robotId;
			pk.mission = "PICK";
			eventBus.deliver(pk);
		}
		else if(behavior.compareTo("place")==0)
		{
			PlaceCART pc =new PlaceCART();
			pc.cart=robotId;
			pc.robotId=robotId;
			pc.mission = "PLACE";
			eventBus.deliver(pc);
		}
		else
		{
			System.out.println("Error command!");
			System.out.println("Command structure: test behavior id mission");
			System.out.println("behavior include: goto, pick, place");
		}
	//	});
	}
	
	
	public void cancel(String behavior,int id)
	{
		System.out.println("inside cancel!!");
	//	worker.execute(() ->{
		Cancel can = new Cancel();
		can.robotId=id;
		if(behavior.compareTo("goto")==0)
		{
			can.mission=3;
			eventBus.deliver(can);
		}
		else if(behavior.compareTo("pick")==0)
		{
			can.mission=7;
			eventBus.deliver(can);
		}
		else if(behavior.compareTo("place")==0)
		{
			can.mission=11;
			eventBus.deliver(can);
		}
		else
		{
			System.out.println("Error command!");
			System.out.println("Command structure: cancel behavior id");
			System.out.println("behavior include: goto, pick, place");
		}
	//	});
	}
	
	public void query(String behavior,int robotId)
	{
		System.out.println("inside query!!");
	//	worker.execute(() ->{
		QueryState que = new QueryState();
		que.robotId=robotId;
		if(behavior.compareTo("goto")==0)
		{
			que.mission="UNLOAD";
			eventBus.deliver(que);
		}
		else if(behavior.compareTo("pick")==0)
		{
			que.mission="PICK";
			eventBus.deliver(que);
		}
		else if(behavior.compareTo("place")==0)
		{
			que.mission="PLACE";
			eventBus.deliver(que);
		}
		else
		{
			System.out.println("Error command!");
			System.out.println("Command structure: query behavior id");
			System.out.println("behavior include: goto, pick, place");
		}
	//	});
	}
	

	public void database() {
		DBAccessor.printDB();
	}
   
	
}