package com.preauth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.preauth.configuration.BeanRegistry;
import com.preauth.shared.model.CoreSearchCriteria;

@RestController
public class DefaultController extends BaseApiController {

	@Autowired
	private BeanRegistry beanRegistry;
	private CoreSearchCriteria criteria;

	@RequestMapping(value = "/api/{product}/{model}/{scenario}/{client-id}", method = RequestMethod.GET)
	public Map<String, Object> StartScenario(@PathVariable("product") String product, @PathVariable("model") String model, 
			@PathVariable("scenario") String scenario, @PathVariable("client-id") String clientId) {
		
		criteria = new CoreSearchCriteria();
		criteria.setClientId(clientId);
		criteria.setScenarioName(scenario);
		criteria.setProduct(product);
		
		return Execute(criteria);
	}
	
	@RequestMapping(value = "/api/{product}/drilldown-to/{model}/{scenario}/{id}", method = RequestMethod.GET)
	public Map<String, Object> DrillDownScenario(@PathVariable("product") String product, @PathVariable("model") String model, 
			@PathVariable("scenario") String scenario, @PathVariable("id") String id) {
		
		criteria = new CoreSearchCriteria();
		criteria.setDrillDownKey(id);
		criteria.setScenarioName(scenario);
		criteria.setProduct(product);
		criteria.setClientId("85");
		
		return Execute(criteria);
	}

}
