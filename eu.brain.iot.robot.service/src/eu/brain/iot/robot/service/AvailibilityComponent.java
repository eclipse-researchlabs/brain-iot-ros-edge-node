package eu.brain.iot.robot.service;

import org.ros.node.ConnectedNode;
import robot_local_control_msgs.Status;

public abstract class AvailibilityComponent {
    private ConnectedNode node;
    private GenericSubscriber<Status> availiblity;
    private String robotName;

    public AvailibilityComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        availiblity = new GenericSubscriber<Status>(node);
        availiblity.register((("/"+ robotName)+"/robot_local_control/state"), "robot_local_control_msgs/Status");
    }

    public Status get_availiblity_value() {
        while (availiblity.getCurrentValue() == null);
        return availiblity.getCurrentValue();
    }
}
