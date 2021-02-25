package eu.brain.iot.ros.edge.node;

import eu.brain.iot.robot.service.GenericSubscriber;
import org.ros.node.ConnectedNode;
import robot_local_control_msgs.Status;

public abstract class AvailibilityComponent {
    private ConnectedNode node;
    private GenericSubscriber<Status> availability;
    private Status status = null;
    private String robotName;

    public AvailibilityComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        availability = new GenericSubscriber<Status>(node);
        availability.register((("/"+ robotName)+"/robot_local_control/state"), "robot_local_control_msgs/Status");
    }

    public Status get_availability_value() {
        status = availability.getCurrentValue();
        while (status == null) {
            System.out.println(" availability get empty value, read again.......");
            try {
                sleep(2);
            } catch (final InterruptedException e) {
                throw e;
            }
            status = availability.getCurrentValue();
        }
        return status;
    }
}
