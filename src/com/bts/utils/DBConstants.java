package com.bts.utils;

public interface DBConstants {
	String APP_NAME = "BugTrackingSystem";
	
	//Entity Type
	String DEVELOPER_ENTITY = "DeveloperEntity";
	String TESTER_ENTITY = "TesterEntity";
	
	//Enum
	enum Severity {
		BLOCKER, CRITICAL, MAJOR, MINOR, IMPROVEMENT
	}
	
	enum Status{
		REJECT, DUPLICATE, POSTPONED, NOT_FIXED, FIXED, UNABLE_TO_REPRODUCE
	}
	
	enum Priority {
		HIGH, MEDIUM, LOW
	}
}
