package com.hpe.calEStore.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.ProfileDAO;
import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.mapper.UserProfileToUserModelMapper;
import com.hpe.calEStore.mapper.UserToUserProfileModelMapper;
import com.hpe.calEStore.model.User;
import com.hpe.calEStore.service.MailNotSentException;
import com.hpe.calEStore.service.MailService;
import com.hpe.calEStore.service.ProfileService;
import com.hpe.calEStore.util.CustomDataSourceForMySQL;
import com.hpe.calEStore.util.DecodeUtil;
import com.hpe.calEStore.util.RandomPasswordGeneratorUtil;

@Service
public class ProfileServiceImpl implements ProfileService{

	final static Logger logger = Logger
			.getLogger(CustomDataSourceForMySQL.class);

	/**
	 * 
	 */
	@Autowired
	ProfileDAO dao;
	
	
	@Autowired
	MailService service;
	
	
	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#registerUser()
	 */
	@Override
	@Transactional
	public void registerUser(User user) throws ProfileNotSavedOrUpdatedException, MessagingException {
		
		UserProfile profile = new UserToUserProfileModelMapper(user).getUserProfile();
		
		Address address = new Address();
		address.setAddressLine1(user.getAddress().getAddressLine1());
		address.setAddressLine2(user.getAddress().getAddressLine2());
		address.setState(user.getAddress().getState());
		address.setCity(user.getAddress().getCity());
		address.setZipCode(user.getAddress().getZipCode());
		address.setIsDfltInd("Y");
		
		Set<Address> setOfAddress = new HashSet<Address>();
		setOfAddress.add(address);
		
		Department department = new Department();
		department.setDepartmentId(user.getDepartment().getDepartmentId());
		department.setDepartmentName(user.getDepartment().getDepartmentName());
		profile.setDepartment(department);
		
		profile.setAddresses(setOfAddress);
		Integer id = dao.saveAndReturnID(profile);
		
		profile.setUserId(id);
        dao.saveUserProfile(profile);
        
        try{
        	service.sendMail("calestore@outlook.com", user.getEmailId(), "Profile registered", "Hi,\n\n Congratulations, your request to access Cal eStore has been submitted.\t\n\n For questions please reach out to ADPQ@state.ca.gov \t\n\n Regards,\t\n\n CaleStore Team");
        }
        catch(Exception ex){
        	logger.error("Error while sending email:",ex);
        }
	}



	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#fetchPendingUserProfiles()
	 */
	@Override
	@Transactional
	public List<User> fetchPendingUserProfiles() throws Exception {
		
		List<UserProfile> listOfUserProfile = dao.getUserProfile();
		List<User> userList = null;
		
		if(!listOfUserProfile.isEmpty()){
			
			userList = new ArrayList<User>();
			for(UserProfile profile: listOfUserProfile){
				userList.add((User) new UserProfileToUserModelMapper().fromEntity(profile));
			}
		}
		return userList;
	}


	@Override
	@Transactional
	public void approveOrRejectUserProfileWith(String[] userID, String vote) throws ProfileNotSavedOrUpdatedException, NumberFormatException, UserProfileManageException, MailNotSentException, MailException, MessagingException {
		
		UserProfile user = null;
		for(String id: userID){
			user = new UserProfile();
			user.setUserId(Integer.parseInt(id));
			user.setStatusInd(vote);
			if(dao.saveOrUpdateUserProfile(user) == 1){
				
				user = dao.getUserProfileByID(Integer.parseInt(id));
				try{
					if(vote.equalsIgnoreCase("A"))
					{
						
						service.sendMail("calestore@outlook.com", user.getEmailId(), "Profile Approved", "Hi,\n\n"+
								"Congratulations,your request to access Cal eStore has been approved.\t\n\n"+
								"Please use the below credentials to log in to the Cal eStore web application run by STATE OF CALIFORNIA Department of "+ 
								"Technology Procurement Team.\t\n\n"+
								"User ID: "+user.getEmailId()+" \t\nPassword: "+DecodeUtil.decodeWithBase64(user.getPassword())+
								"\t\n\nhttp://13.64.248.119:8080/calestore/loginform\t\n\n"+
								"For questions please reach out to ADPQ@state.ca.gov"+
								"\n\n Regards,\n\n CaleStore Team"); 
		
						//service.sendMail("calestore@outlook.com", user.getEmailId(), "Profile Approved!", "Please use " +user.getEmailId()+ " and " + DecodeUtil.decodeWithBase64(user.getPassword()) +" as your credentials to login. Thanks." );
					}
					else{

						//service.sendMail("calestore@outlook.com", user.getEmailId(), "Profile Rejected!", "Your profile has been rejected by the administrator. Thanks." );
						service.sendMail("calestore@outlook.com", user.getEmailId(), "Profile Rejected", "Hi,\n\n"+
								"Your request to access Cal eStore has been rejected by the Administrator.\t\n\n"+
								"For questions please reach out to ADPQ@state.ca.gov"+
								"\n\n Regards,\n\n CaleStore Team"); 
					}
				}
				catch(Exception ex){
					logger.error("Error while sending email:",ex);
				}
			}
		}
	}



	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#getDepartments()
	 */
	@Override
	@Transactional
	public List<Department> getDepartments() {
		return dao.getDepartments();
	}



	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#isExists(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean isExists(String password) {
		return dao.isExists(password);
	}



	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#isMobileExists(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean isMobileExists(String mobileNumber) {
		return dao.isMobileExists(mobileNumber);
	}
	


	/* (non-Javadoc)
	 * @see com.hpe.calEStore.service.ProfileService#forgotPasswordSendemail(java.lang.String)
	 */
	@Override
	@Transactional
	public String forgotPasswordSendemail(String email) throws MailNotSentException, MailException, MessagingException {
		
		String defaultEncryptedPassword = DecodeUtil.encodedeWithBase64(RandomPasswordGeneratorUtil.randomPassword());
		String message = dao.forgotPasswordSendemail(email, defaultEncryptedPassword);
		if(message.equals("true")){
			
			/*Hi,

			Congratulations, your request to reset the password for Cal eStore has been approved.

			Please use the below credentials to log in to the Cal eStore web application run by STATE OF CALIFORNIA Department of Technology Procurement Team.

		*/	
			service.sendMail("calestore@outlook.com", email, "Forgot Password", "Hi,\n\n"+
									"Congratulations, your request to reset the password for Cal eStore has been approved.\t\n\n"+
									"Please use the below credentials to log in to the Cal eStore web application run by STATE OF CALIFORNIA Department of "+ 
									"Technology Procurement Team.\t\n\n"+
									"User ID: "+email+" \t\nPassword: "+DecodeUtil.decodeWithBase64(defaultEncryptedPassword)+
									"\t\n\nhttp://13.64.248.119:8080/calestore/loginform\t\n\n"+
									"For questions please reach out to ADPQ@state.ca.gov"+
									"\n\n Regards,\n\n CaleStore Team"); 
			
			//service.sendMail("nihar1213@gmail.com", email, "Forgot Password", "Please use password "+DecodeUtil.decodeWithBase64(defaultEncryptedPassword)+" as your new password to login. Thanks.");
		}
		return message;
	}

	
}
