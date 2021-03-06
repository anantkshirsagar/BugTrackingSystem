package com.bts.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import com.bts.entities.Application;
import com.bts.entities.Bug;
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

	@SuppressWarnings("unchecked")
	public Employee getEmployeeLoginByEmail(Employee employee, String email, String entityType) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		employee.setEmail(email);
		Query query = connectionService.getEntityManager()
				.createQuery("SELECT t FROM " + entityType + " t WHERE t.email=:email")
				.setParameter("email", employee.getEmail());
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

	public ProjectEntity getProjectEntityByProjectId(int projectId) {
		ProjectEntity projectEntity = new ProjectEntity();
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		projectEntity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS, projectId);
		connectionService.commitAndCloseTransaction();
		return projectEntity;
	}

	public DeveloperEntity editDeveloper(DeveloperEntity developerEntity) {
		ConnectionService connectionService = new ConnectionService();
		int id = developerEntity.getId();
		connectionService.beginTransaction();
		DeveloperEntity entity = (DeveloperEntity) connectionService.find(DBConstants.DEVELOPER_ENTITY_CLASS, id);
		entity.setApproved(developerEntity.isApproved());
		connectionService.commitAndCloseTransaction();
		return developerEntity;
	}

	public TesterEntity editTester(TesterEntity testerEntity) {
		ConnectionService connectionService = new ConnectionService();
		int id = testerEntity.getId();
		connectionService.beginTransaction();
		TesterEntity entity = (TesterEntity) connectionService.find(DBConstants.TESTER_ENTITY_CLASS, id);
		entity.setApproved(testerEntity.isApproved());
		connectionService.commitAndCloseTransaction();
		return testerEntity;
	}

	@SuppressWarnings("unchecked")
	public List<DeveloperEntity> fetchDeveloperList() {
		List<DeveloperEntity> developerList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM DeveloperEntity t WHERE t.isApproved=1";
		developerList = connectionService.getEntityManager().createQuery(query).getResultList();
		connectionService.commitAndCloseTransaction();
		return developerList;
	}

	@SuppressWarnings("unchecked")
	public List<TesterEntity> fetchTesterList() {
		List<TesterEntity> testerList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM TesterEntity t WHERE t.isApproved=1";
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

	@SuppressWarnings("unchecked")
	public List<ProjectEntity> fetchCompletedProjectList() {
		List<ProjectEntity> projectList = null;
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		String query = "SELECT t FROM ProjectEntity t WHERE t.status=:status";
		projectList = connectionService.getEntityManager().createQuery(query)
				.setParameter("status", DBConstants.ProjectStatus.COMPLETED).getResultList();
		connectionService.commitAndCloseTransaction();
		return projectList;
	}

	public void updateProject(ProjectEntity projectEntity) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		ProjectEntity entity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS,
				projectEntity.getId());

		List<Integer> developerIds = projectEntity.getDeveloperIdList();
		List<Integer> testerIds = projectEntity.getTesterIdList();

		developerIds.stream().forEach(developerId -> {
			DeveloperEntity existingDeveloper = (DeveloperEntity) connectionService
					.find(DBConstants.DEVELOPER_ENTITY_CLASS, developerId);
			existingDeveloper.getProjectIdList().add(projectEntity.getId());
		});

		testerIds.stream().forEach(testerId -> {
			TesterEntity existingTester = (TesterEntity) connectionService.find(DBConstants.TESTER_ENTITY_CLASS,
					testerId);
			existingTester.getProjectIdList().add(projectEntity.getId());
		});

		entity.getDeveloperIdList().addAll(developerIds);
		entity.getTesterIdList().addAll(testerIds);
		connectionService.commitAndCloseTransaction();
	}

	public List<ProjectEntity> fetchProjectListByDeveloperId(int developerId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		DeveloperEntity developerEntity = (DeveloperEntity) connectionService.find(DBConstants.DEVELOPER_ENTITY_CLASS,
				developerId);
		List<ProjectEntity> projectList = new ArrayList<>();
		developerEntity.getProjectIdList().stream().forEach(projectId -> {
			ProjectEntity projectEntity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS,
					projectId);
			projectList.add(projectEntity);
		});
		return projectList;
	}

	public List<ProjectEntity> fetchProjectListByTesterId(int testerId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		TesterEntity testerEntity = (TesterEntity) connectionService.find(DBConstants.TESTER_ENTITY_CLASS, testerId);
		List<ProjectEntity> projectList = new ArrayList<>();

		testerEntity.getProjectIdList().stream().forEach(projectId -> {
			ProjectEntity projectEntity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS,
					projectId);
			projectList.add(projectEntity);
		});
		return projectList;
	}

	public DeveloperEntity fetchDeveloperById(int developerId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		DeveloperEntity developerEntity = (DeveloperEntity) connectionService.find(DBConstants.DEVELOPER_ENTITY_CLASS,
				developerId);
		return developerEntity;
	}

	public TesterEntity fetchTesterById(int testerId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		TesterEntity testerEntity = (TesterEntity) connectionService.find(DBConstants.TESTER_ENTITY_CLASS, testerId);
		return testerEntity;
	}

	public ProjectEntity updateBugList(int projectId, int testerId, String testerName, Bug bug) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		ProjectEntity entity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS, projectId);
		bug.setTesterId(testerId);
		bug.setTesterName(testerName);
		List<Bug> bugList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(entity.getBugList())) {
			entity.getBugList().add(bug);
		} else {
			bugList.add(bug);
			entity.setBugList(bugList);
		}
		connectionService.commitAndCloseTransaction();
		return entity;
	}

	public ProjectEntity updateBugStatus(int projectId, List<Bug> bugList) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		ProjectEntity entity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS, projectId);
		entity.setBugList(bugList);
		connectionService.commitAndCloseTransaction();
		return entity;
	}

	public ProjectEntity updateApplicationList(int projectId, Application application) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		ProjectEntity entity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS, projectId);

		if (CollectionUtils.isNotEmpty(entity.getApplicationList())) {
			entity.getApplicationList().add(application);
		} else {
			List<Application> applicationList = new ArrayList<Application>();
			applicationList.add(application);
			entity.setApplicationList(applicationList);
		}
		connectionService.commitAndCloseTransaction();
		return entity;
	}

	public void markProjectToComplete(int projectId) {
		ConnectionService connectionService = new ConnectionService();
		connectionService.beginTransaction();
		if (projectId >= 0) {
			ProjectEntity projectEntity = (ProjectEntity) connectionService.find(DBConstants.PROJECT_ENTITY_CLASS,
					projectId);
			
			projectEntity.setEndDate(new Date());
			projectEntity.setStatus(DBConstants.ProjectStatus.COMPLETED);
		}
		connectionService.commitAndCloseTransaction();
	}
}