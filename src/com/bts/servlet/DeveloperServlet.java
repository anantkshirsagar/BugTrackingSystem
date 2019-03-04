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
import com.bts.entities.TypeWrapper;
import com.bts.services.DatabaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/DeveloperServlet")
public class DeveloperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeveloperServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObjectStr = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TypeWrapper typeWrapper = gson.fromJson(jsonObjectStr, TypeWrapper.class);
		PrintWriter out = response.getWriter();

		DeveloperEntity developerEntity = typeWrapper.getDeveloperEntity();
		List<ProjectEntity> projectList = new DatabaseService().fetchProjectListByDeveloperId(developerEntity.getId());
		//projectList.stream().forEach(project -> project.setDeveloperList(null));
		if (CollectionUtils.isNotEmpty(projectList)) {
			out.write(gson.toJson(projectList));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
