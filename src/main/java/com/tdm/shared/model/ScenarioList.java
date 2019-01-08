package com.preauth.shared.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scenario-list")
public class ScenarioList {
	private List<Scenario> scenario;

	public List<Scenario> getScenario() {
		return scenario;
	}

	public void setScenario(List<Scenario> scenario) {
		this.scenario = scenario;
	}
}
