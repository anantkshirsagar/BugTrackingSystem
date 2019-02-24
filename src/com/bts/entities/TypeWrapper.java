package com.bts.entities;

public class TypeWrapper {
	private String type;
	private String email;

	private DeveloperEntity developerEntity;
	private TesterEntity testerEntity;

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
}
