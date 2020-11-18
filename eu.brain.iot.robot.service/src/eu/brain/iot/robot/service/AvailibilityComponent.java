package eu.brain.iot.robot.service;

import java.util.concurrent.TimeUnit;
import org.ros.node.ConnectedNode;
import robot_local_control_msgs.Status;

public abstract class AvailibilityComponent {
    private ConnectedNode node;
    private GenericSubscriber<Status> availiblity;
    private String robotName;
    Status status = null;

    public AvailibilityComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
        availiblity = new GenericSubscriber<Status>(node);
        availiblity.register((("/"+ robotName)+"/robot_local_control/state"), "robot_local_control_msgs/Status");
    }

    public Status get_availiblity_value() {
        status = availiblity.getCurrentValue();
        while (status == null) {
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	status = availiblity.getCurrentValue();
        }
        return status;
    }
}


/* Availability: mission is started by the robot.

{
    "operation_state": "moving",
    "robot_state": "standby",
    "navigation_status": {
        "state": "",
        "type": "GoToComponent,"
    },
    "localization_status": {
        "node": "-1",
        "reliable": true,
        .......
 */

/*   GoTo mission almost done.
 * {
    "operation_state": "idle",
    "robot_state": "standby",
    "navigation_status": {
        "state": "",
        "type": "GoToComponent,"
    },
    "localization_status": {
    	
    	*/

/* mission is done
{
    "operation_state": "idle",
    "robot_state": "standby",
    "navigation_status": {
        "state": "",
        "type": "None"
    },
    "localization_status": {
        "node": "-1",
        "reliable": true,
*/