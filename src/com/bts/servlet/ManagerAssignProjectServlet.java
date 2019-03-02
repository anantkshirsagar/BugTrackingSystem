package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.EmployeeWrapper;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.ConnectionService;
import com.bts.services.DatabaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/ManagerAssignProjectServlet")
public class ManagerAssignProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerAssignProjectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObjectStr = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		PrintWriter out = response.getWriter();
		TypeWrapper typeWrapper = gson.fromJson(jsonObjectStr, TypeWrapper.class);

		switch (typeWrapper.getType()) {
		case WRAPPER_LIST:
			DatabaseService databaseService = new DatabaseService();
			List<DeveloperEntity> developerList = databaseService.fetchDeveloperList();
			List<TesterEntity> testerList = databaseService.fetchTesterList();
			List<ProjectEntity> projectList = databaseService.fetchProjectList();
			EmployeeWrapper employeeWrapper = new EmployeeWrapper();
			employeeWrapper.setDeveloperList(developerList);
			employeeWrapper.setProjectList(projectList);
			employeeWrapper.setTesterList(testerList);
			out.println(gson.toJson(employeeWrapper));
			break;
		case SAVE_EMPLOYEE_WRAPPER:
			EmployeeWrapper wrapper = typeWrapper.getEmployeeWrapper();
			List<DeveloperEntity> selectedDevelopers = wrapper.getDeveloperList();
			List<TesterEntity> selectedTesters = wrapper.getTesterList();
			ProjectEntity projectEntity = typeWrapper.getProjectEntity();
			projectEntity.getDeveloperList().addAll(selectedDevelopers);
			projectEntity.getTesterList().addAll(selectedTesters);
			new DatabaseService().saveProject(projectEntity);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
