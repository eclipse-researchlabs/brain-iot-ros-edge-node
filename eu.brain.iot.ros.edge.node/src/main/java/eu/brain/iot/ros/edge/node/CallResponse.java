package eu.brain.iot.ros.edge.node;

public class CallResponse {
	public String result; // ok, error
	public String current_state;  // unknown, queued, running, finished
	public String message;
	public String last_event;       // abort, added, start, finish, 
}
