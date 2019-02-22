package com.bts.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bts.utils.DBConstants;

public class ConnectionService {
	private EntityManagerFactory managerFactory;
	private EntityManager entityManager;

	public ConnectionService() {
		this(DBConstants.APP_NAME);
	}

	public ConnectionService(String appName) {
		setManagerFactory(Persistence.createEntityManagerFactory(appName));
		setEntityManager(getManagerFactory().createEntityManager());
	}

	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public void save(Object object) {
		getEntityManager().persist(object);
	}

	public List<Object> fetchData(String query, Class<Object> className) {
		@SuppressWarnings("unchecked")
		List<Object> categoryList = getEntityManager().createNativeQuery(query, className)
				.getResultList();
		if(categoryList != null){
			return categoryList;
		}
		return null;
	}
	
	public void remove(Object categoryEntity) {
		entityManager.remove(categoryEntity);
	}
	
	public Object find(Class<Object> className, int id) {
		Object object = entityManager.find(className, id);
		return object;
	}

	public void commitAndCloseTransaction() {
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
		getManagerFactory().close();
	}

	public EntityManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(EntityManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
