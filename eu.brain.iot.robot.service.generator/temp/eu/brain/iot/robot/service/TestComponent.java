package eu.brain.iot.robot.service;

import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import robot_local_control_msgs.Status;
import std_msgs.Float64;

public class TestComponent {
    private ConnectedNode node;
    private GenericSubscriber<Status> availiblity;
    private String robotName;
    public Publisher<Float64> door;

    public TestComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        availiblity = new GenericSubscriber<Status>(node);
        availiblity.register((("/"+ robotName)+"/robot_local_control/state"), "robot_local_control_msgs/Status");
        door = node.newPublisher("/door1/door_controller/command", "std_msgs/Float64");
        door.setLatchMode(true);
    }

    public Status get_availiblity_value() {
        while (availiblity.getCurrentValue() == null);
        return availiblity.getCurrentValue();
    }

    public void publish_door_Msg(Float64 msg) {
        door.publish(msg);
    }

    /**
     * Default message constructor for door,need to be modified when usage chaged
     */
    public Float64 construct_door_Msg(String doorAction) {
        Float64 msg;
        msg = door.newMessage();
        if (doorAction.compareTo("open") == 0) {
            msg.setData(1.0);
        } else {
            msg.setData(0.0);
        }
        return msg;
    }
}
