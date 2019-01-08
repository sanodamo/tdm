package com.preauth.shared.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Task {
	private String bean;
	private String action;
	private String drilldownTo;
	
	@XmlAttribute(name="bean")
	public String getBean() {
		return bean;
	}
	public void setBean(String bean) {
		this.bean = bean;
	}
	
	@XmlAttribute(name="action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@XmlAttribute(name="drilldownTo")
	public String getDrilldownTo() {
		return drilldownTo;
	}
	public void setDrilldownTo(String drilldownTo) {
		this.drilldownTo = drilldownTo;
	}
	
}
