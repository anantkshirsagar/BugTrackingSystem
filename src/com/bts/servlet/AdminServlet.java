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
import com.bts.entities.TypeWrapper;
import com.bts.services.ConnectionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
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

		if (typeWrapper.getType().equals("DEVELOPER_LIST")) {
			List<DeveloperEntity> developerEntity = connectionService.getEntityManager()
					.createQuery("SELECT d FROM DeveloperEntity d").getResultList();
			out.write(gson.toJson(developerEntity));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
