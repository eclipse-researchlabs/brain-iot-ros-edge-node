package eu.brain.iot.warehouse.events;

import eu.brain.iot.robot.api.Coordinate;
import eu.brain.iot.robot.api.RobotCommand;

/*
 * Response of warehouse backend with the new picking point info to Robot Behavior
*/

public class NewPickPointResponse extends RobotCommand{
	
	public boolean hasNewPoint = false;
	
	public Coordinate pickPoint;
	
	//public String pickPoint;
	
	

}
