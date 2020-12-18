package eu.brain.iot.warehouse.events;

import eu.brain.iot.robot.api.RobotCommand;

public class NewStoragePointResponse extends RobotCommand{

	public int markerID;  // cart marker ID, is used to identify the Place position.
	
	public boolean hasNewPoint = false;
	
	public String storagePoint;
	
	// fixed point in the picking side in front of Door where robot will stop here for checking the door is open or not, on the way to storage area  
	public String storageAuxliaryPoint;
	
}