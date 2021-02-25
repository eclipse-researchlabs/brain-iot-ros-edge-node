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

import eu.brain.iot.robot.api.Command;
import eu.brain.iot.robot.api.RobotCommand;

/*
   This event will be automatically created by ROS Edge Node when it receives the five events,
   (only 'WriteGoTo', 'PickCart', 'PlaceCart', 'Cancel', 'QueryState') because ROS Edge Node will continuously query the execution status of the commands.
   So Robot Behavior just need to wait for the 'QueryStateValueReturn' event to check the execution status.
   
  if a 'QueryState' command is explicitly sent from Robot Behavior, after receiving it, ROS Edge Node will also execute and  reply with 'QueryStateValueReturn'.
*/

public class QueryStateValueReturn extends RobotCommand {
	
	public Command command;
	public CurrentState currentState;
	
	public static enum CurrentState {
		unknown, finished, queued, running, paused;
	}
}
