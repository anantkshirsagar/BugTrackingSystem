package com.bts.tests;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.ProjectEntity;
import com.bts.services.ConnectionService;

public class TestEmployee {
	public static void main(String[] args) {
		ProjectEntity project1 = new ProjectEntity();
		project1.setProjectName("Project-1");
		
		ProjectEntity project2 = new ProjectEntity();
		project2.setProjectName("Project-2");
		
		DeveloperEntity developerEntity = new DeveloperEntity();
		developerEntity.setDepartment("Developer");
		
		project1.setDeveloperEntity(developerEntity);
		project2.setDeveloperEntity(developerEntity);
		
		
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		connectionService.save(developerEntity);
		connectionService.save(project1);
		connectionService.save(project2);
		connectionService.commitAndCloseTransaction();
	}
}
