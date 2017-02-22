package com.hpe.calEStore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hpe.calEStore.model.User;
import com.hpe.calEStore.service.ProfileService;

/**
 * @author mishrani
 *
 */
@Component
public class UserValidator implements Validator {

	
	
	@Autowired
	ProfileService service;
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User userObject = (User)target;
		
		if(userObject.getEmailId() == null || StringUtils.isEmpty(userObject.getEmailId())){
			return;
		}
		if(userObject.getMobileNumber() == null || StringUtils.isEmpty(userObject.getMobileNumber())){
			return;
		}
		
		if(service.isExists(userObject.getEmailId())){
			errors.rejectValue("password", "email.duplicate");
		}
		if(service.isMobileExists(userObject.getMobileNumber())){
			errors.rejectValue("mobileNumber", "mobile.duplicate");
		}
	}

}
