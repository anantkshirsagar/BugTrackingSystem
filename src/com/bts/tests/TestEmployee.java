package com.bts.tests;

import java.util.ArrayList;
import java.util.List;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.ProjectEntity;
import com.bts.services.ConnectionService;

public class TestEmployee {
	public static void main(String[] args) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		
		ProjectEntity project1 = new ProjectEntity();
		project1.setProjectName("Project-1");
		ProjectEntity project2 = new ProjectEntity();
		project2.setProjectName("Project-2");
		ProjectEntity project3 = new ProjectEntity();
		project3.setProjectName("Project-3");
		
		//Save to database
		connectionService.save(project1);
		connectionService.save(project2);
		connectionService.save(project3);
		
		
		List<ProjectEntity> projectList1 = new ArrayList<>();
		projectList1.add(project1);
		projectList1.add(project2);
		
		List<ProjectEntity> projectList2 = new ArrayList<>();
		projectList2.add(project2);
		projectList2.add(project3);
		
		
		DeveloperEntity developer1 = new DeveloperEntity();
		developer1.setDepartment("Developer");
		developer1.setProjectList(projectList1);
		
		DeveloperEntity developer2 = new DeveloperEntity();
		developer2.setDepartment("Developer");
		developer2.setProjectList(projectList2);
		
		//Save to database
		connectionService.save(developer1);
		connectionService.save(developer2);
		
		connectionService.commitAndCloseTransaction();
		
	}
}
