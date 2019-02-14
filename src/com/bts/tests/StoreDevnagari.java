package com.bts.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StoreDevnagari {
//	public static void main(String[] args) {
//		StoreDevnagari.storeDevnagariData();
//		//StoreDevnagari.retrieveDevnagariData();
//		StoreDevnagari.fetchList();
//	}
	public static String str;
	public StoreDevnagari() {
	}

	public StoreDevnagari(String str) {
		this.str = str;
	}

	public static void fetchList() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		List<DevnagariEntity> devnagriEntity = entitymanager
				.createQuery("SELECT d FROM DevnagariEntity d", DevnagariEntity.class).getResultList();
		System.out.println("List=>");
		for (DevnagariEntity devnagariEntity : devnagriEntity) {
			System.out.println("ID=>" + devnagariEntity.getId());
			System.out.println("Test=>" + devnagariEntity.getDevnagariText());
		}
	}

	public static void retrieveDevnagariData() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();
		DevnagariEntity devnagariEntity = entitymanager.find(DevnagariEntity.class, 106);

		System.out.println(" ID = " + devnagariEntity.getId());
		System.out.println(" TEXT = " + devnagariEntity.getDevnagariText());
	}

	public static void storeDevnagariData() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		DevnagariEntity devnagariEntity = new DevnagariEntity();
		//devnagariEntity.setId(106);
		devnagariEntity.setDevnagariText(str);

		entitymanager.persist(devnagariEntity);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}

//-----------------------------------------------------------------------------------------------------
/*
 * Steps to create database:
 * 
 * Go to cmd line or MySQL workbench and run the following command.
 * > create database devnagari;
 * > use devnagari;
 * 
 * Here your database is created. Run the file StoreDevnagari.java file your data will get stored into
 * "devnagarientity" table. This table is automatically get created by the JPA. You do no need to create
 * this table in the database manually.
 * 
 * Question: How JPA recognizes the table name.
 * Table name is nothing but the POJO class name. In your database table is created automatically with
 * the POJO class's name.
 * 
 */

//-----------------------------------------------------------------------------------------------------
/*
 * Following code is not Useful 
 * CREATE DATABASE `devnagari` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; USE `devnagari`;
 * 
 * Use This to set table encoding properties -> ALTER TABLE tablename CONVERT TO
 * CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
 * 
 * OR
 * 
 * CREATE DATABASE `devnagari` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; USE `devnagari`;=>this works fine no need to mention uncoding in mysql url
 * 
 * 
 * 
 * ALTER TABLE MY_TABLE MODIFY COLUMN id INT auto_increment;
 * for autoincrement of id
 */
