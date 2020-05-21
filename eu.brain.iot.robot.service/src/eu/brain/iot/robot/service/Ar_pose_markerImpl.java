package eu.brain.iot.robot.service;


import java.util.List;

import org.osgi.framework.BundleContext;
import org.ros.message.MessageListener;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import ar_track_alvar_msgs.AlvarMarkers;
import eu.brain.iot.robot.api.Ar_pose_marker;

public class Ar_pose_markerImpl implements Ar_pose_marker {
	private String name;
	private BundleContext context;
	private ConnectedNode node;
	private Subscriber<ar_track_alvar_msgs.AlvarMarkers> subscriber;
	private ar_track_alvar_msgs.AlvarMarkers markers;

	public Ar_pose_markerImpl(String name, ConnectedNode node) {
		this.name = name;
		this.node = node;
		this.markers = null;
	}
	public void register(){
		//To do
	    subscriber = node.newSubscriber("/"+name+"/ar_pose_marker",ar_track_alvar_msgs.AlvarMarkers._TYPE);
	    
		subscriber.addMessageListener(new MessageListener<ar_track_alvar_msgs.AlvarMarkers>() {
		@Override
		public void onNewMessage(AlvarMarkers markers) {
			updateMarkerList(markers);
		}	
		});
			
	}
	private void updateMarkerList(ar_track_alvar_msgs.AlvarMarkers markers) {
		this.markers=markers;
	}
	public ar_track_alvar_msgs.AlvarMarkers getMarkersList(){
		System.out.print("Getting the marker list");
		while(this.markers==null);
		return this.markers;
	}
}


