package com.hpe.calEStore.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mishrani
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDAO<PK extends Serializable, T> {
    
    /**
     * 
     */
    private final Class<T> persistentClass;
     
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    /**
     * 
     */
    @Resource(name = "hibernate4AnnotatedSessionFactory")
    @Autowired
	protected SessionFactory sessionFactory;
 
    /**
     * @return
     */
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
 
    /**
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    /**
     * @param entity
     */
    public void persist(T entity) {
        getSession().persist(entity);
    }
 
    /**
     * @param entity
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    /**
     * @return
     */
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
    
    /**
     * 
     */
    public void saveOrUpdate(T entity){
    	getSession().saveOrUpdate(entity);
    	
    }
    
    
    /**
     * @param entity
     */
    protected void update(T entity) {
    	getSession().update(entity);
	}

     
}