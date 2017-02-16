package com.hpe.calEStore.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * @author mishrani
 *
 */

@Repository
public class BaseDAOImpl {
	
	/**
	 * 
	 */
	@Resource(name = "hibernate4AnnotatedSessionFactory")
	protected SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return org.hibernate.Session
	 */
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	
	
}
