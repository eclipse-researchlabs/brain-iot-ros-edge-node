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
package eu.brain.iot.robot.events;

import eu.brain.iot.robot.api.Coordinate;
import eu.brain.iot.robot.api.RobotCommand;

public class AvailabilityReturn extends RobotCommand {
	
	public OperationState operationState;
	public NavigationType navigationType;              // GoToComponent | PickComponent |  PlaceComponent | None
	public Coordinate currentPosition;

	public static enum OperationState {
		idle, moving;
	}
	
	public static enum NavigationType {
		GoToComponent, PickComponent, PlaceComponent, None;
	}
}


/* Availability: check the overall availability of robot, including the current location
 * possible values in ROS service:

when mission is being executed by the robot:
{
    "operation_state": "moving",
    "robot_state": "standby",
    "navigation_status": {
        "state": "",
        "type": "GoToComponent,"
    },
    "localization_status": {
        .......
            "pose": {
                "y": -5.4574489731562705,
                "x": 6.533845601173178,
                "theta": 1.7921496156071322
            }
        .......
            
 */

/* when  GoTo mission almost done.
 * {
    "operation_state": "idle",
    "robot_state": "standby",
    "navigation_status": {
        "state": "",
        "type": "GoToComponent,"
    },
    "localization_status": {
    	
    	*/

/* when mission is  completely done
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

