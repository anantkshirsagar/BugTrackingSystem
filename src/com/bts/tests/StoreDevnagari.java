package com.bts.tests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StoreDevnagari {
	// public static void main(String[] args) {
	// StoreDevnagari.storeDevnagariData();
	// //StoreDevnagari.retrieveDevnagariData();
	// StoreDevnagari.fetchList();
	// }
	public static String str;

	public StoreDevnagari() {
	}

	public StoreDevnagari(String str) {
		this.str = str;
	}

	public static void fetchList() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		List<DevnagariEntity> devnagriEntity = entitymanager.createQuery("SELECT d FROM DevnagariEntity d").getResultList();
		System.out.println("List=>");
		for (DevnagariEntity devnagariEntity : devnagriEntity) {
			System.out.println("ID=>" + devnagariEntity.getId());
			System.out.println("Test=>" + devnagariEntity.getDevnagariText());
		}
	}

	public static String retrieveDevnagariData() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		DevnagariEntity devnagariEntity = entitymanager.find(DevnagariEntity.class, 1);
		System.out.println(" ID = " + devnagariEntity.getId());
		System.out.println(" TEXT = " + devnagariEntity.getDevnagariText());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(devnagariEntity);
	}

	public static String storeDevnagariData(String jsonObj) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		DevnagariEntity devnagariEntity = gson.fromJson(jsonObj.toString(), DevnagariEntity.class);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		//DevnagariEntity devnagariEntity = new DevnagariEntity();
		//devnagariEntity.setId(106);
		//devnagariEntity.setDevnagariText("PQRS");

		entitymanager.persist(devnagariEntity);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
		return gson.toJson(devnagariEntity);
	}
	
	public static void main(String[] args) {
		retrieveDevnagariData();
	}
}


// -----------------------------------------------------------------------------------------------------
/*
 * Steps to create database:
 * 
 * Go to cmd line or MySQL workbench and run the following command. > create
 * database devnagari; > use devnagari;
 * 
 * Here your database is created. Run the file StoreDevnagari.java file your
 * data will get stored into "devnagarientity" table. This table is
 * automatically get created by the JPA. You do no need to create this table in
 * the database manually.
 * 
 * Question: How JPA recognizes the table name. Table name is nothing but the
 * POJO class name. In your database table is created automatically with the
 * POJO class's name.
 * 
 */

// -----------------------------------------------------------------------------------------------------
/*
 * Following code is not Useful CREATE DATABASE `devnagari` DEFAULT CHARACTER
 * SET utf8 COLLATE utf8_general_ci; USE `devnagari`;
 * 
 * Use This to set table encoding properties -> ALTER TABLE tablename CONVERT TO
 * CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 * 
 * OR
 * 
 * CREATE DATABASE `devnagari` DEFAULT CHARACTER SET utf8mb4 COLLATE
 * utf8mb4_unicode_ci; USE `devnagari`;=>this works fine no need to mention
 * uncoding in mysql url
 * 
 * 
 * 
 * ALTER TABLE MY_TABLE MODIFY COLUMN id INT auto_increment; for autoincrement
 * of id
 */
