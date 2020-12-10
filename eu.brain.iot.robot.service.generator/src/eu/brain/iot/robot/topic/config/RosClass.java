package eu.brain.iot.robot.topic.config;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RosClass {

	@SerializedName("ClassName")
	@Expose
	private String className;
	@SerializedName("Topics")
	@Expose
	private List<Topic> topics = null;
	
	public String getClassName() {
	return className;
	}
	
	public void setClassName(String className) {
	this.className = className;
	}
	
	public List<Topic> getTopics() {
	return topics;
	}
	
	public void setTopics(List<Topic> topics) {
	this.topics = topics;
}
}
