package com.preauth.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.preauth.repository.MemberRepository;
import com.preauth.shared.constants.DependencyOptions;
import com.preauth.shared.constants.TaskNames;
import com.preauth.shared.model.CoreSearchCriteria;
import com.preauth.shared.model.DependentDataCollection;
import com.preauth.shared.model.Func;
import com.preauth.shared.model.IBaseService;
import com.preauth.shared.model.Member;
import com.preauth.shared.model.Task;
import com.preauth.shared.model.TaskRequest;
import com.preauth.shared.model.TaskResponse;

@Service
public class MemberService implements IBaseService<CoreSearchCriteria,TaskRequest,DependentDataCollection, TaskResponse> {

	@Autowired
	private MemberRepository memberRepository;

	@Bean(name = TaskNames.GET_ACTIVE_MEMBER)
	@Scope("prototype")
	public TaskResponse GetActiveMember(CoreSearchCriteria Input, TaskRequest taskRequest, DependentDataCollection dependent) {
		TaskResponse response = new  TaskResponse();
		response.setCurrentTask(taskRequest.getCurrentTask());		
		response.setResponse(BuildResponse( memberRepository.findActiveMember(Input),taskRequest, Input));
		return response;		
	}
	
	

	@Bean(name = TaskNames.REGISTER_MEMBER_METHODS)
	@Scope("prototype")
	@Override
	public boolean Register(Map<String, Func<CoreSearchCriteria,TaskRequest,DependentDataCollection, TaskResponse>> registery) {

		if (!registery.containsKey(TaskNames.GET_ACTIVE_MEMBER)) {			
			registery.put(TaskNames.GET_ACTIVE_MEMBER, (CoreSearchCriteria input, TaskRequest taskRequest, DependentDataCollection dependent) 
					-> {return GetActiveMember(input,taskRequest,dependent);});
		}
		
		

		return false;
	}

	private  List<Member> BuildResponse(List<Member> members,  TaskRequest taskRequest, CoreSearchCriteria Input) {
		if(taskRequest.getDependent()!= null && taskRequest.getDependent().toUpperCase().equals(DependencyOptions.DRILLDOWN_DEPENDENCY)) {
			return BuildDrilldownLink(members, taskRequest, Input);
		}
		return members;
	}
	
	private List<Member> BuildDrilldownLink(List<Member> members,  TaskRequest taskRequest, CoreSearchCriteria Input) {		
		for (Member member : members) {
			member.setUri(taskRequest.getCurrentTask().getDrilldownTo());
			member.setUri(member.getUri().replace(":memberId", Integer.toString(member.getId())));
			member.setUri(member.getUri().replace(":product", Input.getProduct()));
		}				
		return members;
	}
}
