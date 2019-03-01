package com.bts.entities;

import java.util.List;

public class EmployeeWrapper {
	private List<DeveloperEntity> developerList;
	private List<TesterEntity> testerList;
	private List<ProjectEntity> projectList;

	public List<DeveloperEntity> getDeveloperList() {
		return developerList;
	}

	public void setDeveloperList(List<DeveloperEntity> developerList) {
		this.developerList = developerList;
	}

	public List<TesterEntity> getTesterList() {
		return testerList;
	}

	public void setTesterList(List<TesterEntity> testerList) {
		this.testerList = testerList;
	}

	public List<ProjectEntity> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectEntity> projectList) {
		this.projectList = projectList;
	}

}
