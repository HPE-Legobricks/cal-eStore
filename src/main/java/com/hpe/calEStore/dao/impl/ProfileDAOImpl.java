package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.ProfileDAO;
import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */

@Repository
public class ProfileDAOImpl extends AbstractDAO<Serializable, UserProfile> implements ProfileDAO {

	
	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.ProfileDAO#saveOrUpdateProfile()
	 * 
	 * Save or update user profile based on the requirement.
	 * 
	 */
	@Override
	@Transactional
	public void saveOrUpdateUserProfile(UserProfile user) throws ProfileNotSavedOrUpdatedException {
		saveOrUpdate(user);
	}

	
	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.ProfileDAO#approveOrRejectUserProfile(com.hpe.adpq.dao.UserProf)
	 * 
	 * Update user profile
	 * 
	 */
	@Override
	@Transactional
	public void approveOrRejectUserProfile(UserProfile profile) throws UserProfileManageException {
		update(profile);
    	
	}


	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.ProfileDAO#batchUpdateProfile(com.hpe.adpq.dao.UserProf)
	 * 
	 * multiple profile update.
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void batchUpdateProfile(List<UserProfile> userProfiles) throws UserProfileManageException {

		for(UserProfile profile: userProfiles){
				approveOrRejectUserProfile(profile);
		}
	}


	

}
