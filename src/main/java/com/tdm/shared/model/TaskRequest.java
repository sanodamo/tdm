package com.preauth.shared.model;

public class TaskRequest {
	private Task  CurrentTask;
	private String Dependent;
	
	public Task getCurrentTask() {
		return CurrentTask;
	}

	public void setCurrentTask(Task currentTask) {
		CurrentTask = currentTask;
	}

	public String getDependent() {
		return Dependent;
	}

	public void setDependent(String dependent) {
		Dependent = dependent;
	}
}
