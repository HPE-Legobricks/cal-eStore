package com.hpe.calEStore.validator;

import java.util.regex.Pattern;

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
		
		
		if(StringUtils.isEmpty(userObject.getFirstName())){
			errors.rejectValue("firstName", "fname.notEmpty");
		}
		if(StringUtils.isEmpty(userObject.getLastName())){
			errors.rejectValue("lastName", "lname.notEmpty");
		}
		if(StringUtils.isEmpty(userObject.getDepartment().getDepartmentName())){
			errors.rejectValue("department.departmentName", "dept.notEmpty");
		}
		
		
		//Mobile validation
		if(StringUtils.isEmpty(userObject.getMobileNumber())){
			errors.rejectValue("mobileNumber", "mobile.notEmpty");
		}
		else{
			if(userObject.getMobileNumber().length() < 12){
				errors.rejectValue("mobileNumber", "mobile.wrongFormat");
			}
		}
		
		
		//E-mail ID validation
		if(StringUtils.isEmpty(userObject.getEmailId())){
			errors.rejectValue("emailId", "email.notEmpty");
		}
		else{
			Pattern pattern = Pattern.compile(".+@.+\\..+");
			if(!pattern.matcher(userObject.getEmailId()).matches()){
				errors.rejectValue("emailId", "email.wrongFormat");
			}
			else{
				
				if(service.isExists(userObject.getEmailId())){
					errors.rejectValue("emailId", "email.duplicate");
				}
			}
		}
		
		
		if(StringUtils.isEmpty(userObject.getPassword())){
			errors.rejectValue("password", "pass.notEmpty");
		}
		else{
			if(userObject.getPassword().length() < 8 ){
				errors.rejectValue("password", "pass.min");
			}
		}
		
		if(StringUtils.isEmpty(userObject.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "cpass.notEmpty");
		}
		
		
		if(!(userObject.getPassword().equals(userObject.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "pass.notSame");
		}
		
		
		//Address
		if(StringUtils.isEmpty(userObject.getAddress().getAddressLine1())){
			errors.rejectValue("address.addressLine1", "addr1.notEmpty");
		}
		if(StringUtils.isEmpty(userObject.getAddress().getState())){
			errors.rejectValue("address.state", "state.notEmpty");
		}
		if(StringUtils.isEmpty(userObject.getAddress().getCity())){
			errors.rejectValue("address.city", "city.notEmpty");
		}
		if(StringUtils.isEmpty(userObject.getAddress().getZipCode())){
			errors.rejectValue("address.zipCode", "zip.notEmpty");
		}
		else{
			if(userObject.getAddress().getZipCode().length() < 10){
				errors.rejectValue("address.zipCode", "zip.wrongFormat");
			}
		}
		
		
	
	}

}
