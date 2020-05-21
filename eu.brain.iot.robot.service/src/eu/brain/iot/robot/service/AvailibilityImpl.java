	package eu.brain.iot.robot.service;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.ros.node.topic.Subscriber;
import eu.brain.iot.robot.api.Availibility;
import robot_local_control_msgs.Status;

import org.ros.node.ConnectedNode;
import org.ros.message.MessageListener;

public class AvailibilityImpl implements Availibility {
	private String name;
	private BundleContext context;
	private ConnectedNode node;
	private Subscriber<robot_local_control_msgs.Status> subscriber;
	private robot_local_control_msgs.Status currentStatus;

	public AvailibilityImpl(String name, ConnectedNode node) {
		this.name = name;
		this.node = node;
		this.currentStatus = null;
	}

	public void register() {
	    subscriber = node.newSubscriber("/"+name+"/robot_local_control/state",robot_local_control_msgs.Status._TYPE);
	    
		subscriber.addMessageListener(new MessageListener<robot_local_control_msgs.Status>() {
		@Override
		public void onNewMessage(robot_local_control_msgs.Status status) {
			  updateCurrentStatus(status);        
			}	
		});
	    
	}
	
	public void updateCurrentStatus(robot_local_control_msgs.Status status) {
		this.currentStatus=status;
	}
	
	public robot_local_control_msgs.Status getCurrentStatus() {
		while(this.currentStatus==null);
		return this.currentStatus;
	}
}
