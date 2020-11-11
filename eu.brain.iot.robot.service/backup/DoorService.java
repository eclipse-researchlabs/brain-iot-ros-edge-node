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
import eu.brain.iot.eventing.api.BrainIoTEvent;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.events.DoorCommand;


@Component(
		configurationPid= "eu.brain.iot.example.robot.Door",
		configurationPolicy=ConfigurationPolicy.REQUIRE,
		service = {NodeMain.class})
@SmartBehaviourDefinition(
		consumed = {DoorCommand.class},    
		author = "LINKS", name = "Smart Door",
		description = "Implements a remote Door.")
public class DoorService extends AbstractNodeMain implements SmartBehaviour<BrainIoTEvent>{
	

	DoorControllerImpl door_controller;
	private BundleContext context;
	
	
	@ObjectClassDefinition
	public static @interface Config {
		@AttributeDefinition(description = "The IP of the Door")
		String host();

		@AttributeDefinition(description = "The Port of the Door")
		int port();

		@AttributeDefinition(description = "The name of the Door")
		String name();

		@AttributeDefinition(description = "The identifier for the Door")
		String id();

	}

	private Config config;

	private ServiceRegistration<?> reg;


	@Reference
	private EventBus eventBus;


    @Activate
	void activate(BundleContext context, Config config, Map<String,Object> props){
	    this.config=config;
	    System.out.println("\nHello!  I am door : "+config.id());
	    

	    Dictionary<String, Object> serviceProps = new Hashtable<>(props.entrySet().stream()
				.filter(e -> !e.getKey().startsWith("."))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			
//			serviceProps.put(SmartBehaviourDefinition.PREFIX_ + "filter", 
//		    String.format("(|(robotId=%s)(robotId=%s))", config.id(), RobotCommand.ALL_ROBOTS));
//			
//			System.out.println("+++++++++ filter = "+serviceProps.get(SmartBehaviourDefinition.PREFIX_ + "filter"));
			reg = context.registerService(SmartBehaviour.class, this, serviceProps);
            
		//	this.doorName=config.name();
	}
    
    @Deactivate
	void stop() {
		reg.unregister();
		
	}


   
   @Override
	public GraphName getDefaultNodeName() {
		return GraphName.of(config.id()+"/serviceController");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		
		System.out.println("\n The Robot services are registering....for Door "+config.id());
		MessageFactory msgfactory =  connectedNode.getTopicMessageFactory();  
		door_controller=new DoorControllerImpl(connectedNode,msgfactory);
		door_controller.register();

	}
	
	
	@Override
	public void notify(BrainIoTEvent event)
	{
		if (event instanceof DoorCommand) {
			DoorCommand dc=(DoorCommand)event;
			if(dc.cmd.equals("open"))
			{
				System.out.println("DoorService receive an open command");
				door_controller.DoorOpen();
			}
			else
			{
				door_controller.DoorClose();
				System.out.println("Door receive a close command");
			}
		}
	}
	
	
}





