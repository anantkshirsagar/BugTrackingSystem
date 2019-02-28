package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bts.services.ConnectionService;
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
		ConnectionService connectionService = new ConnectionService();
		PrintWriter out = response.getWriter();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonObjectStr);
			String callType = jsonObject.get("callType").toString();
			System.out.println(callType);
			
			/*
			switch(callType){
			case "RETRIEVE":
				connectionService.beginTransaction();
				//Write code fetch developer, tester and project from database and show in the manager's page
				//Provide check box to select developer to assign to a particular project
				//Same for tester. And then 
				break;
			case "SAVE":
				break;
			default:
			}
			*/
		} catch (Exception e) {
			System.out.println(" ManagerAssignProjectServlet Exception: " +e);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
