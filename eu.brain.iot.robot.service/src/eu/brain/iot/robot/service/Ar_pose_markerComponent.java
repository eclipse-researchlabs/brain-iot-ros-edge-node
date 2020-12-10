package eu.brain.iot.robot.service;

import ar_track_alvar_msgs.AlvarMarkers;

import java.util.concurrent.TimeUnit;

import org.ros.node.ConnectedNode;

public abstract class Ar_pose_markerComponent {
    private ConnectedNode node;
    private GenericSubscriber<AlvarMarkers> poseMarker;
    private String robotName;

    public Ar_pose_markerComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        poseMarker = new GenericSubscriber<AlvarMarkers>(node);
        poseMarker.register((("/"+ robotName)+"/ar_pose_marker"), "ar_track_alvar_msgs/AlvarMarkers");
    }

    public AlvarMarkers get_poseMarker_value() {
    	AlvarMarkers markers = poseMarker.getCurrentValue();
        while (markers == null) {
        	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	markers = poseMarker.getCurrentValue();
        }
        return markers;
    }
}
