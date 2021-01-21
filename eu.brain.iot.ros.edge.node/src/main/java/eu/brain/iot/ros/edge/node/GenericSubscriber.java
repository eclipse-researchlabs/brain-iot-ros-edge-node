package eu.brain.iot.ros.edge.node;

import org.ros.message.MessageListener;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

public class GenericSubscriber<T> {
	public Subscriber<T> subscriber;
	private ConnectedNode node;
	private T currentValue;
	
	public GenericSubscriber(ConnectedNode node)
	{
		this.node=node;
		currentValue=null;
	}
	
	public void register(String topicName, String topicType)
	{
		subscriber = node.newSubscriber(topicName,topicType);
		
		subscriber.addMessageListener(new MessageListener<T>() {
			@Override
			public void onNewMessage(T value) {
				  updateCurrentValue(value);        
				}	
			});

	}
	
	
	public void updateCurrentValue(T value) {
		this.currentValue=value;
	}
	
	public T getCurrentValue()
	{
		return currentValue;
	}
	
	
}
