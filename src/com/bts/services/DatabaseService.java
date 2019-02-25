package com.bts.services;

import com.bts.entities.Employee;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;

public class DatabaseService {

	public DatabaseService() {
	}
	
	public void registerEmployee(Employee employee) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		connectionService.save(employee);
		connectionService.commitAndCloseTransaction();
	}
	
	public Employee getEmployeeLoginByEmail(Employee employee, String email, String entityType) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM " + entityType + " t WHERE t.email=:email";
		employee.setEmail(email);
		employee = (TesterEntity) connectionService.getEntityManager()
				.createQuery(query)
				.setParameter("email", employee.getEmail()).getSingleResult();
		connectionService.commitAndCloseTransaction();
		return employee;
	}
	
	public void saveProject(ProjectEntity projectEntity) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		connectionService.save(projectEntity);
		connectionService.commitAndCloseTransaction();
	}
	
	public ProjectEntity getProjectEntityByProjectName(String projectName) {
		ProjectEntity projectEntity = new ProjectEntity();
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM ProjectEntity t WHERE t.projectName=:projectName";
		projectEntity.setProjectName(projectName);
		projectEntity = (ProjectEntity) connectionService.getEntityManager()
				.createQuery(query)
				.setParameter("email", projectEntity.getProjectName()).getSingleResult();
		connectionService.commitAndCloseTransaction();
		return projectEntity;
	}
}