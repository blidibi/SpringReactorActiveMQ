package com.lukman.reactoramq.model.amq;

public class MessageQueueModel {
	private String service;
	private String eventName;
    private String parameters;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
    
}
