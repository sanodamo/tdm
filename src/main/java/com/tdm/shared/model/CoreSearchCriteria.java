package com.preauth.shared.model;

public class CoreSearchCriteria {
	private String clientId;
	private String scenarioName;
	private String product;
	private String drillDownKey;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDrillDownKey() {
		return drillDownKey;
	}
	public void setDrillDownKey(String drillDownKey) {
		this.drillDownKey = drillDownKey;
	}	
}
