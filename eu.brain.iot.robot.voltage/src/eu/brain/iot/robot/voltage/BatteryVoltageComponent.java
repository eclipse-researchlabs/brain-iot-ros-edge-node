package eu.brain.iot.robot.voltage;

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
        while (status == null) {
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	status = voltage.getCurrentValue();
        }
        return status;
    }
}
