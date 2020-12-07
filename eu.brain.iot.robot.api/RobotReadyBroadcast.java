package eu.brain.iot.robot.events;

import eu.brain.iot.robot.api.RobotCommand;

//when ros edge node has been initialized, it will automatically broadcast its availability with the robot IP adderss, 
// this IP will be used to create the DockingRequest event by robot behaviour for asking to warehouse backend 
// for the docking  point of this robot

public class RobotReadyBroadcast extends RobotCommand {
	
	public String robotIP;
	
	public boolean isReady;
}
