package com.bts.tests;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JPACall")
public class JPACall extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public JPACall() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("myname");
		StoreDevnagari store = new StoreDevnagari(name);
		StoreDevnagari.storeDevnagariData(name);
		StoreDevnagari.fetchList();
		PrintWriter out = response.getWriter();
		out.println("Successful.StoreDevnagari");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
