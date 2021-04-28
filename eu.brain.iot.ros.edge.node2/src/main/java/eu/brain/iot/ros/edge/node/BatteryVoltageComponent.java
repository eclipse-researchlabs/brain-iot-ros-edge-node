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
package eu.brain.iot.ros.edge.node;

import java.util.concurrent.TimeUnit;

import org.ros.node.ConnectedNode;

import kobuki_msgs.SensorState;

public abstract class BatteryVoltageComponent {
	private ConnectedNode node;
    private GenericSubscriber<SensorState> voltage;
    private String robotName;

    public BatteryVoltageComponent(ConnectedNode node, String robotName) {
        this.node = node;
        this.robotName = robotName;
    }

    public void register() {
    	voltage = new GenericSubscriber<SensorState>(node);
    	voltage.register((("/"+ robotName)+"/mobile_base/sensors/core"), "kobuki_msgs/SensorState");
    }

    public SensorState get_voltage_value() {
    	SensorState status = voltage.getCurrentValue();
    	/*        while (status == null) {
    	System.out.println("get empty voltage, again....");
    	try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	status = voltage.getCurrentValue();
    }
*/
        return status;
    }
}
