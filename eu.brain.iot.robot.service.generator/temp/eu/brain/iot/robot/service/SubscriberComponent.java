package eu.brain.iot.robot.service;

import ar_track_alvar_msgs.AlvarMarkers;
import org.ros.node.ConnectedNode;
import robot_local_control_msgs.Status;

public class SubscriberComponent {
    private ConnectedNode node;
    private GenericSubscriber<Status> availiblity;
    private String robotName;
    private GenericSubscriber<AlvarMarkers> poseMarker;

    public SubscriberComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        availiblity = new GenericSubscriber<Status>(node);
        availiblity.register((("/"+ robotName)+"/robot_local_control/state"), "robot_local_control_msgs/Status");
        poseMarker = new GenericSubscriber<AlvarMarkers>(node);
        poseMarker.register((("/"+ robotName)+"/ar_pose_marker"), "ar_track_alvar_msgs/AlvarMarkers");
    }

    public Status get_availiblity_value() {
        while (availiblity.getCurrentValue() == null);
        return availiblity.getCurrentValue();
    }

    public AlvarMarkers get_poseMarker_value() {
        while (poseMarker.getCurrentValue() == null);
        return poseMarker.getCurrentValue();
    }
}
