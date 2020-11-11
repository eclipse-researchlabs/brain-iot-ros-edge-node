package eu.brain.iot.robot.service;

import org.osgi.framework.BundleContext;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.message.MessageFactory;
import org.ros.node.ConnectedNode;
import org.ros.node.service.ServiceClient;
import org.ros.node.topic.Publisher;

import eu.brain.iot.robot.api.DoorController;
import std_msgs.Float64;

public class DoorControllerImpl implements DoorController{
	
//	private String name;
	private ConnectedNode node;
	private MessageFactory Factory;
	private Publisher<std_msgs.Float64> publisherDoor;
	
	public DoorControllerImpl(ConnectedNode node,MessageFactory Factory) {
		
		this.node=node;
		this.Factory=Factory;	
	}
	
	public void register() {
		publisherDoor=node.newPublisher("/door1/door_controller/command",std_msgs.Float64._TYPE); 
		publisherDoor.setLatchMode(true);
	}	
	
	public void DoorOpen(){
		Float64 f=publisherDoor.newMessage();
		f.setData(1.0);
		publisherDoor.publish(f);	
		System.out.println("Send Door Open Command");
	}
	
	public void DoorClose() {
		Float64 f=publisherDoor.newMessage();
		f.setData(0.0);
		publisherDoor.publish(f);
	}
	
      
}
