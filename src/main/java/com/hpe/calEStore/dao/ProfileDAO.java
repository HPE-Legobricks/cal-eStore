package com.hpe.calEStore.dao;

import java.util.List;

import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
public abstract interface ProfileDAO {

	/**
	 * @param up 
	 * 
	 */
	public void saveOrUpdateUserProfile(UserProfile a) throws ProfileNotSavedOrUpdatedException;
	
	
	
	/**
	 * @param profile
	 * @throws Exception
	 */
	public void approveOrRejectUserProfile(UserProfile profile) throws UserProfileManageException;
	
	
	/**
	 * @param userProfile
	 * @throws Exception
	 */
	void batchUpdateProfile(List<UserProfile> userProfile) throws UserProfileManageException;
}
