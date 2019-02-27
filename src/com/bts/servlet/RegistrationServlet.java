package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.Employee;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.DatabaseService;
import com.bts.utils.DBConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TypeWrapper typeWrapper = gson.fromJson(jsonObj, TypeWrapper.class);
		PrintWriter out = response.getWriter();

		switch (typeWrapper.getType()) {
		case DEVELOPER_REGISTRATION:
			DeveloperEntity developerEntity = typeWrapper.getDeveloperEntity();
			if (developerEntity.getId() > 0)
				new DatabaseService().editEmployee(developerEntity);
			else
				new DatabaseService().registerEmployee(developerEntity);
			out.write(gson.toJson(developerEntity));
			break;
		case TESTER_REGISTRATION:
			TesterEntity testerEntity = typeWrapper.getTesterEntity();
			new DatabaseService().registerEmployee(testerEntity);
			out.write(gson.toJson(testerEntity));
			break;
		case TESTER_LOGIN:
			Employee testerLogin = new TesterEntity();
			testerLogin = new DatabaseService().getEmployeeLoginByEmail(testerLogin, typeWrapper.getEmail(),
					DBConstants.TESTER_ENTITY);
			out.write(gson.toJson(testerLogin));
			break;
		case DEVELOPER_LOGIN:
			Employee developerLogin = new DeveloperEntity();
			developerLogin = new DatabaseService().getEmployeeLoginByEmail(developerLogin, typeWrapper.getEmail(),
					DBConstants.DEVELOPER_ENTITY);
			out.write(gson.toJson(developerLogin));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
