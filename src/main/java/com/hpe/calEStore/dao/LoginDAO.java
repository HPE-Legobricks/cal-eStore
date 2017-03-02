package com.hpe.calEStore.dao;

import com.hpe.calEStore.dao.entity.UserProfile;

public interface LoginDAO {

	/**
	 * @param username
	 * @return
	 */
	public UserProfile getUserByUsername(String username);
}