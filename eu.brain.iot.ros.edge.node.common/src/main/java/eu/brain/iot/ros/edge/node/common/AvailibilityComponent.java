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
