package com.preauth.shared.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

public class Scenario {
	private String name;
	private String description;
	private String api;
	private String dependent;
	private List<Task> task;
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	@XmlAttribute(name="api")
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	@XmlAttribute(name="dependent")
	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	
}
