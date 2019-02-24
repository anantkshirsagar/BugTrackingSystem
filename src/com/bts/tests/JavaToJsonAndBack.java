package com.bts.tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.TesterEntity;
import com.bts.services.ConnectionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JavaToJsonAndBack {
	public static void main(String[] args) {
		Albums albums = new Albums();
		albums.setTitle("Free Music Archive - Albums");
		albums.setMessage("");
		albums.setTotal("11259");
		albums.setTotal_pages(2252);
		albums.setPage(1);
		albums.setLimit("5");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println(gson.toJson(albums));

		List<String> errors = new ArrayList<>();
		errors.add("STR-1");
		errors.add("STR-2");
		albums.setErrors(errors);
		System.out.println(gson.toJson(albums));

		// String jsonInString = "{\"title\":\"XYZ\",\"message\":\"ABC\"}";
		Albums obj = gson.fromJson("{'title':'XYZ','message':'ABC'}", Albums.class);
		System.out.println(obj.getTitle());
		System.out.println(obj.getMessage());
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		DeveloperEntity devnagariEntity = gson.fromJson("{'name':'ABC'}", DeveloperEntity.class);
		entitymanager.persist(devnagariEntity);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
		ConnectionService connectionService = new ConnectionService("BugTrackingSystem");
		List<TesterEntity> testerEntity = new ArrayList<>();
		//testerEntity.setEmail("yashpshah.shah@gmail.com");
		testerEntity = connectionService.getEntityManager().createQuery("SELECT t FROM TesterEntity t WHERE t.email='yashpshah.shah@gmail.com'").getResultList();
		for (TesterEntity testerEntity2 : testerEntity) {
			System.out.println(testerEntity2.getEmail());
		}
		
		//System.out.println(testerEntity.getEmail());
	}
}
