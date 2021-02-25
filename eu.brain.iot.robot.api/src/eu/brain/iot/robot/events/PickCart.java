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

public class PickCart extends RobotCommand {
	
	/* 
	 * Used for matching with the command of QueryStateValueReturn received by Robot Behaviour 
	 * who will know the QueryStateValueReturn is the response of which robot command(WriteGoTo, pickCart, placeCart) 
	 */
	public Command command = Command.PICK;
	
	public int markerID;   // from the cart marker ID, ROS Edge Node will get the cart name to be used in the ROS service
}
