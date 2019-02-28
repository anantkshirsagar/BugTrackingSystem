package com.bts.utils;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;

public interface DBConstants {
	String APP_NAME = "BugTrackingSystem";
	
	//Entity Type
	String DEVELOPER_ENTITY = "DeveloperEntity";
	String TESTER_ENTITY = "TesterEntity";
	
	//Classes
	Class DEVELOPER_ENTITY_CLASS = DeveloperEntity.class;
	Class TESTER_ENTITY_CLASS = TesterEntity.class;
	Class PROJECT_ENTITY_CLASS = ProjectEntity.class;
	
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
