package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.List;














import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import antlr.StringUtils;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.ProfileDAO;
import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author mishrani
 *
 */

@Repository
public class ProfileDAOImpl extends AbstractDAO<Serializable, UserProfile> implements ProfileDAO {

	
	/**
	 * @return
	 * @throws UserProfileManageException
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserProfile> getUserProfile() throws UserProfileManageException {
		
		Criteria criteria = createEntityCriteria();
		return (List<UserProfile>)criteria.add(Restrictions.eq("statusInd", "N")).list();
	}
	
	
	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.ProfileDAO#saveOrUpdateProfile()
	 * 
	 * Save or update user profile based on the requirement.
	 * 
	 */
	@Override
	@Transactional
	public int saveOrUpdateUserProfile(UserProfile user) throws ProfileNotSavedOrUpdatedException {
		
		String hqlUpdate    = "update UserProfile profile set profile.statusInd = :status where profile.userId = :id";
		return getSession().createQuery( hqlUpdate ).setString( "status", user.getStatusInd() ).setString( "id", user.getUserId().toString() )
				 .executeUpdate();
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.hpe.adpq.dao.ProfileDAO#saveOrUpdateProfile()
	 * 
	 * Save or update user profile based on the requirement.
	 * 
	 */
	@Override
	@Transactional
	public void saveUserProfile(UserProfile user) throws ProfileNotSavedOrUpdatedException {
		
			Address address = user.getAddresses().iterator().next();
			String queryString    = "insert into address(user_id, address_line1, address_line2, city, state, zip_code, is_dflt_ind) values("+user.getUserId()+","+"'"+address.getAddressLine1()+"'"+","+"'"+address.getAddressLine2()+"'"+","+"'"+address.getCity()+"'"+","+"'"+address.getState()+"'"+","+address.getZipCode()+","+"'"+address.getIsDfltInd()+"'"+")";
			getSession().createSQLQuery(queryString).executeUpdate();
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
	
	
	@Override
	public UserProfile getUserProfileByID(Integer id) throws UserProfileManageException{
		
		return getByKey(id);
	}


	/* (non-Javadoc)
	 * @see com.hpe.calEStore.dao.ProfileDAO#getDepartments()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="products")
	public List<Department> getDepartments() {
		
		String hqlQuery = "from Department";
		return getSession().createQuery(hqlQuery).list();
	}


	/* (non-Javadoc)
	 * @see com.hpe.calEStore.dao.ProfileDAO#isExists(java.lang.String)
	 */
	@Override
	public boolean isExists(String email) {
		
		String hqlQuery = "from UserProfile where emailId = :em";
		if(getSession().createQuery(hqlQuery).setParameter("em", email).uniqueResult() != null){
			return true;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see com.hpe.calEStore.dao.ProfileDAO#isMobileExists(java.lang.String)
	 */
	@Override
	public boolean isMobileExists(String mobileNumber) {
		
		String hqlQuery = "from UserProfile where mobileNumber = :mb";
		if(getSession().createQuery(hqlQuery).setParameter("mb", mobileNumber).uniqueResult() != null){
			return true;
		}
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hpe.calEStore.dao.ProfileDAO#forgotPasswordSendemail(java.lang.String)
	 */
	@Override
	public String forgotPasswordSendemail(String email, String password) {
		
		Criteria criteria = createEntityCriteria();
		Object object = criteria.add(Restrictions.eq("emailId", email)).uniqueResult();
		
		if(object == null){
			return "false";
		}
		else{
			String hqlUpdate = "update UserProfile profile set profile.password = :password where profile.emailId = :email";
			int rowUpdated = getSession().createQuery( hqlUpdate ).setString("email", email).setString("password", password).executeUpdate();
			
			if(rowUpdated == 1){
				return "true";
			}
		}
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see com.hpe.calEStore.dao.ProfileDAO#saveAndReturnID(com.hpe.calEStore.dao.entity.UserProfile)
	 */
	public Integer saveAndReturnID(UserProfile up){
		return (Integer) saveAndReturnKey(up);
	}
}