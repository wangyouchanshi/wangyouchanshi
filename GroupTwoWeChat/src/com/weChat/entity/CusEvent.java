package com.weChat.entity;
/**
 * 
 * @author THINK
 * 多客服接收值并转成xml所用
 */
public class CusEvent extends BaseMessage{
	
	private String Event;
	private String EventKey;
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	
	
}
