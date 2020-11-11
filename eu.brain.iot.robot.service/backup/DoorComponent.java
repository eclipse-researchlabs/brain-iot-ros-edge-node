package eu.brain.iot.robot.service;

import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import std_msgs.Float64;

public abstract class DoorComponent {
    private ConnectedNode node;
    public Publisher<Float64> door;

    public DoorComponent(ConnectedNode node) {
        this.node = node;
    }

    public void register() {
        door = node.newPublisher("/door1/door_controller/command", "std_msgs/Float64");
        door.setLatchMode(true);
    }

    public void publish_door_Msg(Float64 msg) {
        door.publish(msg);
    }

    public abstract Float64 construct_door_Msg();
}
