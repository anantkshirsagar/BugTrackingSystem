package com.bts.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.Employee;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;
import com.bts.utils.DBConstants;

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
		DeveloperEntity entity = (DeveloperEntity) connectionService.find(DBConstants.DEVELOPER_ENTITY_CLASS, id);
		entity.setApproved(developerEntity.isApproved());
		connectionService.commitAndCloseTransaction();
		return developerEntity;
	}

	@SuppressWarnings("unchecked")
	public List<DeveloperEntity> fetchDeveloperList() {
		List<DeveloperEntity> developerList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM DeveloperEntity t";
		developerList = connectionService.getEntityManager().createQuery(query).getResultList();
		connectionService.commitAndCloseTransaction();
		return developerList;
	}

	@SuppressWarnings("unchecked")
	public List<TesterEntity> fetchTesterList() {
		List<TesterEntity> testerList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM TesterEntity t";
		testerList = connectionService.getEntityManager().createQuery(query).getResultList();
		connectionService.commitAndCloseTransaction();
		return testerList;
	}

	@SuppressWarnings("unchecked")
	public List<ProjectEntity> fetchProjectList() {
		List<ProjectEntity> projectList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM ProjectEntity t WHERE t.status=:status";
		projectList = connectionService.getEntityManager().createQuery(query)
				.setParameter("status", DBConstants.ProjectStatus.NOT_COMPLETED).getResultList();
		connectionService.commitAndCloseTransaction();
		return projectList;
	}

	public void updateProjectEntity(ProjectEntity projectEntity) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		ProjectEntity entity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS,
				projectEntity.getId());
		List<DeveloperEntity> developers = projectEntity.getDeveloperList();
		List<TesterEntity> testers = projectEntity.getTesterList();

		List<ProjectEntity> projectList = new ArrayList<ProjectEntity>();
		projectList.add(projectEntity);

		developers.stream().forEach(developer -> {
			DeveloperEntity existingDeveloper = (DeveloperEntity) connectionService
					.find(DBConstants.DEVELOPER_ENTITY_CLASS, developer.getId());
			List<ProjectEntity> assignedProjectsList = existingDeveloper.getProjectList();
			if (CollectionUtils.isNotEmpty(assignedProjectsList)) {
				assignedProjectsList.addAll(projectList);
				existingDeveloper.setProjectList(assignedProjectsList);
			} else {
				existingDeveloper.setProjectList(projectList);
			}
		});

		testers.stream().forEach(tester -> {
			TesterEntity existingTester = (TesterEntity) connectionService.find(DBConstants.TESTER_ENTITY_CLASS,
					tester.getId());
			List<ProjectEntity> assignedProjectsList = existingTester.getProjectList();
			if (CollectionUtils.isNotEmpty(assignedProjectsList)) {
				assignedProjectsList.addAll(projectList);
				existingTester.setProjectList(assignedProjectsList);
			} else {
				existingTester.setProjectList(projectList);
			}
		});

		projectEntity.setDeveloperList(developers);
		projectEntity.setTesterList(testers);
		entity.setDeveloperList(projectEntity.getDeveloperList());
		entity.setTesterList(projectEntity.getTesterList());
		connectionService.commitAndCloseTransaction();
	}

	public List<ProjectEntity> fetchProjectListByDeveloperId(int developerId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		DeveloperEntity developerEntity = (DeveloperEntity) connectionService.find(DBConstants.DEVELOPER_ENTITY_CLASS,
				developerId);
		List<ProjectEntity> projectList = developerEntity.getProjectList();
		return projectList;
	}
}