package eu.brain.iot.robot.topic.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topic {
	@SerializedName("Role")
	@Expose
	private String role;
	@SerializedName("ReferenceName")
	@Expose
	private String referenceName;
	@SerializedName("TopicName")
	@Expose
	private String topicName;
	@SerializedName("TopicType")
	@Expose
	private String topicType;
	@SerializedName("MessageType")
	@Expose
	private String messageType;

	public String getRole() {
	return role;
	}

	public void setRole(String role) {
	this.role = role;
	}
	public String getReferenceName() {
	return referenceName;
	}
	
	public void setReferenceName(String referenceName) {
	this.referenceName = referenceName;
	}

	public String getTopicName() {
	return topicName;
	}

	public void setTopicName(String topicName) {
	this.topicName = topicName;
	}

	public String getTopicType() {
	return topicType;
	}

	public void setTopicType(String topicType) {
	this.topicType = topicType;
	}
	
	public String getMessageType() {
	return messageType;
	}

	public void setMessageType(String messageType) {
	this.messageType = messageType;
	}
}
