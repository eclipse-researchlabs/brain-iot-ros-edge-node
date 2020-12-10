package eu.brain.iot.robot.service.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServceClient {
	
	@SerializedName("serviceClientName")
	@Expose
	private String serviceClientName;
	@SerializedName("serviceName")
	@Expose
	private String serviceName;
	@SerializedName("serviceType")
	@Expose
	private String serviceType;
	@SerializedName("serviceRequestType")
	@Expose
	private String serviceRequestType;
	@SerializedName("serviceResponseType")
	@Expose
	private String serviceResponseType;
	
	public String getServiceClientName() {
	return serviceClientName;
	}
	
	public void setServiceClientName(String serviceClientName) {
	this.serviceClientName = serviceClientName;
	}
	
	public String getServiceName() {
	return serviceName;
	}
	
	public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
	}
	
	public String getServiceType() {
	return serviceType;
	}
	
	public void setServiceType(String serviceType) {
	this.serviceType = serviceType;
	}
	
	public String getServiceRequestType() {
	return serviceRequestType;
	}
	
	public void setServiceRequestType(String serviceRequestType) {
	this.serviceRequestType = serviceRequestType;
	}
	
	public String getServiceResponseType() {
	return serviceResponseType;
	}
	
	public void setServiceResponseType(String serviceResponseType) {
	this.serviceResponseType = serviceResponseType;
	}

}
