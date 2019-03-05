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

import org.apache.commons.collections.CollectionUtils;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.DatabaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObjectStr = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TypeWrapper typeWrapper = gson.fromJson(jsonObjectStr, TypeWrapper.class);
		PrintWriter out = response.getWriter();

		switch(typeWrapper.getType()) {
		case DEVELOPER_HOME:
			DeveloperEntity developerEntity = typeWrapper.getDeveloperEntity();
			List<ProjectEntity> developerProjectList = new DatabaseService().fetchProjectListByDeveloperId(developerEntity.getId());
			
			if (CollectionUtils.isNotEmpty(developerProjectList)) {
				out.write(gson.toJson(developerProjectList));
			}
			break;
		case TESTER_HOME:
			TesterEntity testerEntity = typeWrapper.getTesterEntity();
			List<ProjectEntity> testerProjectList = new DatabaseService().fetchProjectListByDeveloperId(testerEntity.getId());
			
			if (CollectionUtils.isNotEmpty(testerProjectList)) {
				out.write(gson.toJson(testerProjectList));
			}
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
