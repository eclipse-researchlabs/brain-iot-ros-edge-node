package eu.brain.iot.robot.topic.config;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicConfig {
	@SerializedName("RosClasses")
	@Expose
	private List<RosClass> rosClasses = null;

	public List<RosClass> getRosClasses() {
	return rosClasses;
	}

	public void setRosClasses(List<RosClass> rosClasses) {
	this.rosClasses = rosClasses;
	}
}
