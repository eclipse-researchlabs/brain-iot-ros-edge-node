package eu.brain.iot.robot.service;



import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import eu.brain.iot.eventing.annotation.SmartBehaviourDefinition;
import eu.brain.iot.eventing.api.EventBus;
import eu.brain.iot.eventing.api.SmartBehaviour;
import eu.brain.iot.robot.events.CheckValueReturn;
import eu.brain.iot.robot.events.PickCart;
import eu.brain.iot.robot.events.PlaceCART;
import eu.brain.iot.robot.events.QueryStateValueReturn;
import eu.brain.iot.robot.events.RobotCommand;
import eu.brain.iot.robot.events.WriteGOTO;
import org.apache.felix.service.command.CommandProcessor;

@Component(
		property= {
				CommandProcessor.COMMAND_SCOPE + ":String=Orchestrator",	
		        CommandProcessor.COMMAND_FUNCTION + ":String=test" 	
		},
		immediate=true
	)
@SmartBehaviourDefinition(consumed = {CheckValueReturn.class, QueryStateValueReturn.class},
filter = "(timestamp=*)",    
author = "UGA", name = "Smart Orchestrator",
description = "Implements a remote Smart Orchestrator.")
public class OrchImpl  implements SmartBehaviour<RobotCommand> {

    
    @Reference
    private  EventBus eventBus;
 

	@Override
	public void notify( RobotCommand event) {
		if(event instanceof QueryStateValueReturn) {
			QueryStateValueReturn qsvr = (QueryStateValueReturn) event;
			System.out.println("************************************");
			System.out.println("RobotId="+qsvr.robotId+" mission="+qsvr.mission+" result="+qsvr.value);
			System.out.println("************************************");
		}
	}
	
	public void test(String behavior,int id, int mission)
	{
		System.out.println("inside test!!");
		if(behavior.compareTo("goto")==0)
		{
			WriteGOTO wgoto = new WriteGOTO();
			wgoto.robotId=id;
			wgoto.mission=mission;
			eventBus.deliver(wgoto);
		}
		else if(behavior.compareTo("pick")==0)
		{
			PickCart pk =new PickCart();
			pk.cart=mission;
			pk.robotId=id;
			eventBus.deliver(pk);
		}
		else if(behavior.compareTo("place")==0)
		{
			PlaceCART pc =new PlaceCART();
			pc.cart=mission;
			pc.robotId=id;
			eventBus.deliver(pc);
		}
		else
		{
			System.out.println("Error command!");
			System.out.println("Command structure: test behavior id mission");
			System.out.println("behavior include: goto, pick, place");
		}
		
	}
	
	
	
}