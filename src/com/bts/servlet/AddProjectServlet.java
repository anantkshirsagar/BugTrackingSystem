package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.ConnectionService;
import com.bts.services.DatabaseService;
import com.bts.utils.DBConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProjectServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TypeWrapper typeWrapper = gson.fromJson(jsonObj, TypeWrapper.class);
		// ProjectEntity projectEntity = gson.fromJson(jsonObj, ProjectEntity.class);
		PrintWriter out = response.getWriter();
		DatabaseService databaseService = new DatabaseService();
		ConnectionService connectionService = new ConnectionService();
		switch (typeWrapper.getType()) {
		case ADD_PROJECT:
			ProjectEntity projectEntity = typeWrapper.getProjectEntity();
			projectEntity.setStatus(DBConstants.ProjectStatus.NOT_COMPLETED);
			projectEntity.setStartDate(new Date());
			databaseService.saveProject(projectEntity);
			out.println(gson.toJson(jsonObj));
			break;
		case UPDATE_PROJECT:
			ProjectEntity prEntity = databaseService.updateBugList(typeWrapper.getProjectId(),
					typeWrapper.getTesterEntity().getId(), typeWrapper.getTesterEntity().getFullName(),
					typeWrapper.getBug());
			out.println(gson.toJson(prEntity));
			break;
		case GET_PROJ_BY_ID:
			ProjectEntity projectEntity2 = databaseService.getProjectEntityByProjectId(typeWrapper.getProjectEntity().getId());
			out.println(gson.toJson(projectEntity2));
			break;
		case UPDATE_BUG_LIST:
			ProjectEntity project = databaseService.updateBugStatus(typeWrapper.getProjectId(), typeWrapper.getBugList());
			out.println(gson.toJson(project));
			break;
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
