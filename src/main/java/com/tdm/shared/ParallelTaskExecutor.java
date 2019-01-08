package com.preauth.shared;

import java.util.concurrent.Callable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.preauth.configuration.BeanRegistry;
import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.TaskRequest;

@Service
@Scope("prototype") 
public class ParallelTaskExecutor implements Callable<Object> {
	
	private BeanRegistry beanRegistry;
	
	private CoreSearchCriteria criteria;
	private TaskRequest currentTask;

	public ParallelTaskExecutor(CoreSearchCriteria criteria, TaskRequest currentTask, BeanRegistry beanRegistry) {
		this.criteria = criteria;
		this.currentTask = currentTask;
		this.beanRegistry = beanRegistry;
	}

	@Override
	public Object call() throws Exception {
		return beanRegistry.getRegistry().get(currentTask.getCurrentTask().getAction()).apply(criteria, currentTask, null);
	}

}
