package com.hpe.calEStore.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.model.User;

public abstract interface ProfileService {

	/**
	 * @param user
	 * @throws ProfileNotSavedOrUpdatedException
	 * @throws MailNotSentException 
	 * @throws MessagingException 
	 * @throws MailException 
	 */
	public void registerUser(User user) throws ProfileNotSavedOrUpdatedException, MailNotSentException, MailException, MessagingException;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<User> fetchPendingUserProfiles() throws Exception;

	/**
	 * @param userIdentification
	 * @param approve 
	 * @throws ProfileNotSavedOrUpdatedException 
	 * @throws UserProfileManageException 
	 * @throws NumberFormatException 
	 * @throws MailNotSentException
	 * @throws MessagingException 
	 * @throws MailException 
	 */
	public void approveOrRejectUserProfileWith(String[] userIdentification, String vote) throws ProfileNotSavedOrUpdatedException, NumberFormatException, UserProfileManageException, MailNotSentException, MailException, MessagingException;

	
	
	/**
	 * @return
	 */
	public List<Department> getDepartments();

	public boolean isExists(String password);

	public boolean isMobileExists(String mobileNumber);
	
	
	
	
}
