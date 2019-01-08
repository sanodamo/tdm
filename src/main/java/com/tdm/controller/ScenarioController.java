package com.preauth.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.preauth.configuration.BeanRegistry;
import com.preauth.shared.model.ScenarioList;

@RestController
public class ScenarioController {

	@Autowired
	private BeanRegistry beanRegistry;

	@RequestMapping(value = "/api/scenarios", method = RequestMethod.GET)
	public Map<String, Object> getScenarios() {
		Map<String, Object> testScenarios = new HashMap<String, Object>();
		ScenarioList scenarioList = beanRegistry.getScenarioList();
		if(scenarioList != null) {
			testScenarios.put("Scenarios", scenarioList.getScenario());
			return testScenarios;
		}
			
		return null;
	}
}
