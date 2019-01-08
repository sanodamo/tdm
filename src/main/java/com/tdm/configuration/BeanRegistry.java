package com.preauth.configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.DependentDataCollection;
import com.preauth.shared.model.Func;
import com.preauth.shared.model.IBaseService;
import com.preauth.shared.model.ScenarioList;
import com.preauth.shared.model.TaskRequest;

@Component
public class BeanRegistry {

	public static Map<String, Func<CoreSearchCriteria,TaskRequest,DependentDataCollection, Object>> registery;
	public static ScenarioList scenarioList;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public boolean Register() {

		Map<String, IBaseService> filters = applicationContext.getBeansOfType(IBaseService.class);
		registery = new HashMap<String, Func<CoreSearchCriteria,TaskRequest, DependentDataCollection, Object>>();
		for (String key : filters.keySet()) {
			IBaseService<CoreSearchCriteria,TaskRequest,DependentDataCollection, Object> value = filters.get(key);
			value.Register(registery);
		}

		return true;
	}

	@Bean
	public Map<String, Func<CoreSearchCriteria,TaskRequest,DependentDataCollection, Object>> getRegistry() {
		return registery;
	}

	@Bean
	public ScenarioList getScenarioList() {
		return scenarioList;
	}

	@Bean
	public boolean LoadScenarioList() {

		try {

			File file = new ClassPathResource("static/BeanConfig.xml").getFile();
			JAXBContext jaxbContext = JAXBContext.newInstance(ScenarioList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			scenarioList = (ScenarioList) jaxbUnmarshaller.unmarshal(file);

			return true;

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
