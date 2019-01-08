package com.preauth.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.preauth.configuration.BeanRegistry;
import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.DependentDataCollection;
import com.preauth.shared.model.TaskRequest;

@Service
@Scope("prototype") 
public class CascadingTaskExecutor implements Callable<Object> {
	
	private BeanRegistry beanRegistry;
	private DependentDataCollection dependentData; 
	private CoreSearchCriteria criteria;
	private TaskRequest currentTask;

	public CascadingTaskExecutor(CoreSearchCriteria criteria, TaskRequest currentTask, BeanRegistry beanRegistry,DependentDataCollection dependentData ) {
		this.criteria = criteria;
		this.currentTask = currentTask;
		this.beanRegistry = beanRegistry;
		this.dependentData = dependentData;
	}

	@Override
	public Object call() throws Exception {
		return beanRegistry.getRegistry().get(currentTask.getCurrentTask().getAction()).apply(criteria, currentTask, dependentData);
	}

}