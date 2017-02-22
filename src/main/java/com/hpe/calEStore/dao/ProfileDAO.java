package com.hpe.calEStore.dao;

import java.util.List;

import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */
public abstract interface ProfileDAO {

	
	/**
	 * @return
	 * @throws UserProfileManageException
	 */
	public List<UserProfile> getUserProfile() throws UserProfileManageException;
	
	
	/**
	 * @param up 
	 * @return 
	 * 
	 */
	public int saveOrUpdateUserProfile(UserProfile a) throws ProfileNotSavedOrUpdatedException;
	
	/**
	 * @param a
	 * @throws ProfileNotSavedOrUpdatedException
	 */
	public void saveUserProfile(UserProfile a) throws ProfileNotSavedOrUpdatedException;
	
	
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


	/**
	 * @param id
	 * @return
	 * @throws UserProfileManageException
	 */
	UserProfile getUserProfileByID(Integer id) throws UserProfileManageException;


	/**
	 * @return
	 */
	public List<Department> getDepartments();


	/**
	 * @param password
	 * @return
	 */
	public boolean isExists(String password);


	/**
	 * @param mobileNumber
	 * @return
	 */
	public boolean isMobileExists(String mobileNumber);
}
