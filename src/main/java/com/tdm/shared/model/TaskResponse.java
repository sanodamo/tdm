package com.preauth.shared.model;

import java.util.List;

public class TaskResponse {
	private Task CurrentTask;
	private Object Response;
	
	public Task getCurrentTask() {
		return CurrentTask;
	}

	public void setCurrentTask(Task currentTask) {
		CurrentTask = currentTask;
	}

	public Object getResponse() {
		return Response;
	}

	public void setResponse(Object response) {
		Response = response;
	}
	
	
	
}
