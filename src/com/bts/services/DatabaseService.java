package com.bts.services;

import java.util.List;

import javax.persistence.Query;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.Employee;
import com.bts.entities.ProjectEntity;

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
		// String query = "SELECT t FROM " + entityType + " t WHERE t.email=:email";
		employee.setEmail(email);
		Query query = connectionService.getEntityManager()
				.createQuery("SELECT t FROM " + entityType + " t WHERE t.email=:email")
				.setParameter("email", employee.getEmail());
		/*
		 * employee = (Employee) connectionService.getEntityManager().createQuery(query)
		 * .setParameter("email", employee.getEmail()).getSingleResult();
		 */
		List<Employee> list = query.getResultList();
		connectionService.commitAndCloseTransaction();
		return list.size() > 0 ? list.get(0) : null;
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
		projectEntity = (ProjectEntity) connectionService.getEntityManager().createQuery(query)
				.setParameter("email", projectEntity.getProjectName()).getSingleResult();
		connectionService.commitAndCloseTransaction();
		return projectEntity;
	}

	public DeveloperEntity editEmployee(DeveloperEntity developerEntity) {
		ConnectionService connectionService = new ConnectionService();
		int id = developerEntity.getId();
		connectionService.beginTransaction();
		Class DEVELOPER_ENTITY = DeveloperEntity.class;
		DeveloperEntity entity = (DeveloperEntity) connectionService.find(DEVELOPER_ENTITY, id);
		entity.setApproved(developerEntity.isApproved());
		connectionService.commitAndCloseTransaction();
		return developerEntity;

	}
}