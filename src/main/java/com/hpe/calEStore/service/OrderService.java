/**
 * 
 */
package com.hpe.calEStore.service;

import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author Noothan
 *
 */
public interface OrderService {

	UserProfile getAddressByUser(String emailId);

	String getUserDepartmentName(String emailId);
}
