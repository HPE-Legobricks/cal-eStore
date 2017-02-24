package com.hpe.calEStore.dao.impl;


import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.LoginDAO;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
@Repository
public class LoginDAOImpl extends AbstractDAO<Serializable, UserProfile> implements LoginDAO {


	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.LoginDAO#getUserByUsername(java.lang.String)
	 */
	@Override
	public UserProfile getUserByUsername(String username) {
		return getByUsername(username);
	}

	/**
	 * @param username
	 * @return
	 */
	private UserProfile getByUsername(String username) {
		
		Criteria criteria = createEntityCriteria();
		return (UserProfile) criteria.add(Restrictions.eq("emailId", username)).uniqueResult();
		
	}

}