package com.bts.entities;

import java.util.List;

import com.bts.utils.Type;

public class TypeWrapper {
	private Type type;
	private String email;

	private DeveloperEntity developerEntity;
	private TesterEntity testerEntity;
	private ProjectEntity projectEntity;
	private EmployeeWrapper employeeWrapper;
	private Bug bug;
	private List<Bug> bugList;
	private int projectId;
	private Application application;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public DeveloperEntity getDeveloperEntity() {
		return developerEntity;
	}

	public void setDeveloperEntity(DeveloperEntity developerEntity) {
		this.developerEntity = developerEntity;
	}

	public TesterEntity getTesterEntity() {
		return testerEntity;
	}

	public void setTesterEntity(TesterEntity testerEntity) {
		this.testerEntity = testerEntity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeWrapper getEmployeeWrapper() {
		return employeeWrapper;
	}

	public void setEmployeeWrapper(EmployeeWrapper employeeWrapper) {
		this.employeeWrapper = employeeWrapper;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public List<Bug> getBugList() {
		return bugList;
	}

	public void setBugList(List<Bug> bugList) {
		this.bugList = bugList;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
}
