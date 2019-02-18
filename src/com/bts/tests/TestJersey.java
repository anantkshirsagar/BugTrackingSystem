package com.bts.tests;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;

@Path("testJersey")

public class TestJersey {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("fetch")
	public String fetchDevnagriEntity() {
		StoreDevnagari storeDevnagari = new StoreDevnagari();
		String devnagariEntity = storeDevnagari.retrieveDevnagariData();
		return devnagariEntity;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add")
	public void addDevnagriEntity(String jsonObj) {
		StoreDevnagari storeDevnagari = new StoreDevnagari();
		System.out.println(" =>" + jsonObj);
		// String devnagariEntity = storeDevnagari.storeDevnagariData(jsonObj);
//		return jsonObj;
	}

	// public DevnagariEntity addDevnagriEntity(DevnagariEntity jsonObj, @Context
	// HttpServletResponse serverResponse) {
	// StoreDevnagari storeDevnagari = new StoreDevnagari();
	// System.out.println(jsonObj);
	// // String devnagariEntity = storeDevnagari.storeDevnagariData(jsonObj);
	// return jsonObj;
	// }

}
