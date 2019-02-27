package com.bts.entities;

import com.bts.utils.DBConstants;

public class Bug {
	private long id;
	private String bugDescription;
	private String typeOfTesting;
	private String typeOfTestingScrenario;
	private String action;
	private String input;
	private String expectedResult;
	private String actualResult;
	private DBConstants.Severity severity;
	private DBConstants.Priority priority;
	private DBConstants.Status bugStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getTypeOfTesting() {
		return typeOfTesting;
	}

	public void setTypeOfTesting(String typeOfTesting) {
		this.typeOfTesting = typeOfTesting;
	}

	public String getTypeOfTestingScrenario() {
		return typeOfTestingScrenario;
	}

	public void setTypeOfTestingScrenario(String typeOfTestingScrenario) {
		this.typeOfTestingScrenario = typeOfTestingScrenario;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}

	public DBConstants.Severity getSeverity() {
		return severity;
	}

	public void setSeverity(DBConstants.Severity severity) {
		this.severity = severity;
	}

	public DBConstants.Priority getPriority() {
		return priority;
	}

	public void setPriority(DBConstants.Priority priority) {
		this.priority = priority;
	}

	public DBConstants.Status getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(DBConstants.Status bugStatus) {
		this.bugStatus = bugStatus;
	}
}
