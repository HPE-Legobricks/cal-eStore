/**
 * 
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.OrderDAO;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author Noothan Y V
 *
 */
@Repository
public class OrderDAOImpl extends AbstractDAO<Serializable, UserProfile> implements OrderDAO {

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public UserProfile getAddressByUser(String emailId) {
		// TODO Auto-generated method stub
		UserProfile userProfile = (UserProfile) createEntityCriteria().add(Restrictions.eq("emailId", emailId))
				.uniqueResult();

		return userProfile;
	}

	@Override
	public String getUserDepartmentName(String emailId) {
		// TODO Auto-generated method stub

		String departmentName = null;
		UserProfile userProfile = (UserProfile) createEntityCriteria().add(Restrictions.eq("emailId", emailId))
				.uniqueResult();

		if (userProfile != null) {
			departmentName = userProfile.getDepartment().getDepartmentName();
		}
		return departmentName;
	}

}
