package eu.brain.iot.robot.service.config;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RosClass {
	@SerializedName("ClassName")
	@Expose
	private String className;
	@SerializedName("ServceClients")
	@Expose
	private List<ServceClient> servceClients = null;

	public String getClassName() {
	return className;
	}

	public void setClassName(String className) {
	this.className = className;
	}

	public List<ServceClient> getServceClients() {
	return servceClients;
	}

	public void setServceClients(List<ServceClient> servceClients) {
	this.servceClients = servceClients;
	}
}
