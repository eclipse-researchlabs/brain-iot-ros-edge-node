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

import geometry_msgs.PoseWithCovarianceStamped;

public abstract class RobotPositionComponent {
	private ConnectedNode node;
    private GenericSubscriber<PoseWithCovarianceStamped> robotPosition;
    private String robotName;
    PoseWithCovarianceStamped position = null;

    public RobotPositionComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
    	robotPosition = new GenericSubscriber<PoseWithCovarianceStamped>(node); // geometry_msgs/PoseWithCovarianceStamped
    	robotPosition.register((("/"+ robotName)+"/amcl_pose"), "robot_local_control_msgs/Status");
    }

    public PoseWithCovarianceStamped get_robotPosition_value() {
    	position = robotPosition.getCurrentValue();
        while (position == null) {
        	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	position = robotPosition.getCurrentValue();
        }
        return position;
    }
}