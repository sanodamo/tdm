package com.preauth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.preauth.configuration.BeanRegistry;
import com.preauth.shared.CascadingTaskExecutor;
import com.preauth.shared.ParallelTaskExecutor;
import com.preauth.shared.constants.DependencyOptions;
import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.DependentDataCollection;
import com.preauth.shared.model.Scenario;
import com.preauth.shared.model.ScenarioList;
import com.preauth.shared.model.Task;
import com.preauth.shared.model.TaskRequest;
import com.preauth.shared.model.TaskResponse;

@RestController
public class BaseApiController {

	@Autowired
	@Qualifier("preAuthJdbc")
	protected JdbcTemplate jdbc;

	@Autowired
	private BeanRegistry beanRegistry;
	
	public Map<String, Object> Execute(CoreSearchCriteria criteria) {
		
		ScenarioList scenarioList = beanRegistry.getScenarioList();

		Optional<Scenario> list = scenarioList.getScenario().stream().filter(item -> item.getName().equals(criteria.getScenarioName()))
				.findFirst();
		
		if(list.get().getDependent() != null && list.get().getDependent().toUpperCase().equals(DependencyOptions.CASCADING_DEPENDENCY)){
			return cascadingTaskExecution(list,criteria);
		}		
		else 
		{
			return parallelTaskExecution(list,criteria);
		}
		
	}
	
	private Map<String, Object> cascadingTaskExecution(Optional<Scenario> list, CoreSearchCriteria criteria) {
		Map<String, Object> testData = new HashMap<String, Object>();
		
		List<Callable<Object>> taskList = new ArrayList<>();
		CascadingTaskExecutor cascadingTaskExecutor = null;
		TaskRequest taskRequest = null;
		String dependency = list.get().getDependent(); 
		
		DependentDataCollection dependents = new DependentDataCollection();;
		
		for (Task task : list.get().getTask()) {
			taskRequest = new TaskRequest();			
			dependents.setData(testData);
			taskRequest.setCurrentTask(task);
			taskRequest.setDependent(dependency);
			cascadingTaskExecutor = new CascadingTaskExecutor(criteria, taskRequest,beanRegistry, dependents);
			TaskResponse response = null;
			
			try {
				response = (TaskResponse) cascadingTaskExecutor.call();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
			if (response != null && response.getCurrentTask() != null
					&& !testData.containsKey(response.getCurrentTask().getBean())) {
				testData.put(response.getCurrentTask().getBean(), response.getResponse());
			}			
						
		}		
		
		return testData;
	} 
	
	private Map<String, Object> parallelTaskExecution(Optional<Scenario> list, CoreSearchCriteria criteria) {
		Map<String, Object> testData = new HashMap<String, Object>();
		ExecutorService executor = Executors.newFixedThreadPool(list.get().getTask().size());
		List<Callable<Object>> taskList = new ArrayList<>();
		ParallelTaskExecutor parallelTaskExecutor = null;
		TaskRequest taskRequest = null;
		String dependency = list.get().getDependent(); 
		
		for (Task task : list.get().getTask()) {
			taskRequest = new TaskRequest();
			taskRequest.setCurrentTask(task);
			taskRequest.setDependent(dependency);
			parallelTaskExecutor = new ParallelTaskExecutor(criteria, taskRequest, beanRegistry);
			taskList.add(parallelTaskExecutor);
		}

		List<Future<Object>> results = null;
		try {
			results = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();

		for (Future<Object> result : results) {
			try {
				TaskResponse response = (TaskResponse) result.get();

				if (response != null && response.getCurrentTask() != null
						&& !testData.containsKey(response.getCurrentTask().getBean())) {
					testData.put(response.getCurrentTask().getBean(), response.getResponse());
				}

			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return testData;
	} 
}
