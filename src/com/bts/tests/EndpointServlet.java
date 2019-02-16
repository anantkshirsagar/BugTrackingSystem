package com.bts.tests;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.bts.services.EndpointServiceFactory;

@WebServlet("/EndpointServlet")
public class EndpointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EndpointServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		
		EndpointServiceFactory endpoint = new EndpointServiceFactory();
		Class className = StudentEntity.class;
		StudentEntity studentEntity = (StudentEntity)endpoint.parseJsonToJava(jsonObj, className);
		System.out.println(studentEntity.getId());
		System.out.println(studentEntity.getName());
		System.out.println(studentEntity.getPercentage());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
