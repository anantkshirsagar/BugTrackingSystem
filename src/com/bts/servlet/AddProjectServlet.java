package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.ProjectEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.DatabaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddProjectServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ProjectEntity projectEntity = gson.fromJson(jsonObj, ProjectEntity.class);
		PrintWriter out = response.getWriter();
		
		DatabaseService databaseService = new DatabaseService();
		databaseService.saveProject(projectEntity);
		out.println(jsonObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
