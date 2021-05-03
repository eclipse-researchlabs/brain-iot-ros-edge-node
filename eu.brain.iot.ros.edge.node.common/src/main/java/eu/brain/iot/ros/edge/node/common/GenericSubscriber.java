/*******************************************************************************
 * *  Copyright (C) 2021 LINKS Foundation
 * 
 * This file is based on the ROSOSGi open-source project which is a part of DIANNE  -  Framework for distributed artificial neural networks
 * 
 * DIANNE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package eu.brain.iot.ros.edge.node.common;

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
