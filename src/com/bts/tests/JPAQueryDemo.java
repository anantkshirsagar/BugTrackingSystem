package com.bts.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAQueryDemo {
	public static void main(String[] args) {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("");
		EntityManager entityManager = managerFactory.createEntityManager();

	}
}
