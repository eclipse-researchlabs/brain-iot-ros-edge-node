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
package eu.brain.iot.warehouse.events;

import eu.brain.iot.robot.api.RobotCommand;

/*
 * The dockAuxliaryPoint field is fixed point for a specific robot in the storage side in front of Door on the way to docking area. 
 * Robot will stop here waiting for checking marker  cmd or new GoTo event if door is open.
 * */

public class DockingResponse extends RobotCommand{
	
	public boolean hasNewPoint = false;
	
	public String dockingPoint;

	public String dockAuxliaryPoint;
}
