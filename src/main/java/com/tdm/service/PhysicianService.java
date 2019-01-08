package com.preauth.service;

import java.util.List;
import java.util.Map;
import java.util.function.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.preauth.repository.MemberRepository;
import com.preauth.shared.constants.TaskNames;
import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.DependentDataCollection;
import com.preauth.shared.model.Func;
import com.preauth.shared.model.IBaseService;
import com.preauth.shared.model.Member;
import com.preauth.shared.model.TaskRequest;
import com.preauth.shared.model.TaskResponse;

@Service
public class PhysicianService implements IBaseService<CoreSearchCriteria,TaskRequest, DependentDataCollection, TaskResponse> {

	@Autowired
	private MemberRepository memberRepository;

	@Bean(name = TaskNames.GET_ANY_PHYSICIAN)
	@Scope("prototype")
	public TaskResponse GetAnyPhysician(CoreSearchCriteria Input, TaskRequest taskRequest, DependentDataCollection dependent) {
		TaskResponse response = new  TaskResponse();
		response.setCurrentTask(taskRequest.getCurrentTask());
		response.setResponse(memberRepository.findActiveMember(Input));
		return response;		
	}

	@Bean(name = TaskNames.REGISTER_PHYSICIAN_METHODS)
	@Scope("prototype")
	@Override
	public boolean Register(Map<String, Func<CoreSearchCriteria,TaskRequest,DependentDataCollection, TaskResponse>> registery) {

		if (!registery.containsKey(TaskNames.GET_ANY_PHYSICIAN)) {			
			registery.put(TaskNames.GET_ANY_PHYSICIAN, (CoreSearchCriteria input, TaskRequest taskRequest,DependentDataCollection dependent) -> {return GetAnyPhysician(input,taskRequest,dependent);});
		}
		return false;
	}
}
