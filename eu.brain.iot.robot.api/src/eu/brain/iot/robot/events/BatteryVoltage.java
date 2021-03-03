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

import java.sql.Time;

import eu.brain.iot.robot.api.RobotCommand;

// topic: /turtlebot_id/mobile_base/sensors/core
// message: kobuki_msgs/SensorState message

public class BatteryVoltage extends RobotCommand {

	public String index; // timestamp format: yyyy-MM-dd HH:mm:ss  org.ros.message.Time getStamp();
	public double target; // voltage     byte getBattery();
}