/**
 * 
 */
package com.hpe.calEStore.dao;

import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author Noothan
 *
 */
public interface OrderDAO {

	UserProfile getAddressByUser(String emailId);

	String getUserDepartmentName(String emailId);
}
