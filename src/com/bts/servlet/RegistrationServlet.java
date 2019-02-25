package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.ConnectionService;
import com.bts.tests.DevnagariEntity;
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
		ConnectionService connectionService = new ConnectionService();
		TypeWrapper typeWrapper = gson.fromJson(jsonObj, TypeWrapper.class);
		PrintWriter out = response.getWriter();

		switch (typeWrapper.getType()) {
		case "DEVELOPER_REGISTRATION":
			DeveloperEntity developerEntity = typeWrapper.getDeveloperEntity();
			connectionService.beginTransaction();
			connectionService.save(developerEntity);
			connectionService.commitAndCloseTransaction();
			out.write(gson.toJson(developerEntity));
			break;
		case "TESTER_REGISTRATION":
			TesterEntity testerEntity = typeWrapper.getTesterEntity();
			connectionService.beginTransaction();
			connectionService.save(testerEntity);
			connectionService.commitAndCloseTransaction();
			out.write(gson.toJson(testerEntity));
			break;
		case "TESTER_LOGIN":
			TesterEntity testerLogin = new TesterEntity();
			testerLogin.setEmail(typeWrapper.getEmail());
			testerLogin = (TesterEntity) connectionService.getEntityManager()
					.createQuery("SELECT t FROM TesterEntity t WHERE t.email=:email")
					.setParameter("email", testerLogin.getEmail()).getSingleResult();
			out.write(gson.toJson(testerLogin));
			break;
		case "DEVELOPER_LOGIN":
			DeveloperEntity developerLogin = new DeveloperEntity();
			developerLogin.setEmail(typeWrapper.getEmail());
			developerLogin = (DeveloperEntity) connectionService.getEntityManager()
					.createQuery("SELECT t FROM DeveloperEntity t WHERE t.email=:email")
					.setParameter("email", developerLogin.getEmail()).getSingleResult();
			out.write(gson.toJson(developerLogin));
			break;
		}

		/*
		 * if (typeWrapper.getType().equals("DEVELOPER_REGISTRATION")) { DeveloperEntity
		 * developerEntity = typeWrapper.getDeveloperEntity();
		 * connectionService.beginTransaction();
		 * connectionService.save(developerEntity);
		 * connectionService.commitAndCloseTransaction(); PrintWriter out =
		 * response.getWriter(); out.write(gson.toJson(developerEntity)); }
		 * 
		 * if (typeWrapper.getType().equals("TESTER_REGISTRATION")) { TesterEntity
		 * testerEntity = typeWrapper.getTesterEntity();
		 * connectionService.beginTransaction(); connectionService.save(testerEntity);
		 * connectionService.commitAndCloseTransaction(); PrintWriter out =
		 * response.getWriter(); out.write(gson.toJson(testerEntity)); }
		 * 
		 * if (typeWrapper.getType().equals("TESTER_LOGIN")) { TesterEntity testerEntity
		 * = new TesterEntity(); testerEntity.setEmail(typeWrapper.getEmail());
		 * testerEntity = (TesterEntity) connectionService.getEntityManager()
		 * .createQuery("SELECT t FROM TesterEntity t WHERE t.email=:email")
		 * .setParameter("email", testerEntity.getEmail()).getSingleResult();
		 * PrintWriter out = response.getWriter(); out.write(gson.toJson(testerEntity));
		 * }
		 * 
		 * if (typeWrapper.getType().equals("DEVELOPER_LOGIN")) { DeveloperEntity
		 * developerEntity = new DeveloperEntity();
		 * developerEntity.setEmail(typeWrapper.getEmail()); developerEntity =
		 * (DeveloperEntity) connectionService.getEntityManager()
		 * .createQuery("SELECT t FROM DeveloperEntity t WHERE t.email=:email")
		 * .setParameter("email", developerEntity.getEmail()).getSingleResult();
		 * PrintWriter out = response.getWriter();
		 * out.write(gson.toJson(developerEntity)); }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
