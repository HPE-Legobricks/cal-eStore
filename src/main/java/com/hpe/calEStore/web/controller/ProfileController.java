package com.hpe.calEStore.web.controller;


import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.dao.ProfileNotSavedOrUpdatedException;
import com.hpe.calEStore.dao.UserProfileManageException;
import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.model.User;
import com.hpe.calEStore.service.MailNotSentException;
import com.hpe.calEStore.service.ProfileService;
import com.hpe.calEStore.validator.UserValidator;

/**
 * @author mishrani
 *
 */
@Controller
public class ProfileController {

	
	/**
	 * 
	 */
	@Autowired
	ProfileService service;

	
	@Autowired
	UserValidator validator;
	
	
	
	/**
	 * 
	 */
	private static final String NEW_PROFILE_CREATE_PAGE = "new.profile";
	
	/**
	 * 
	 */
	private static final String LOGIN_PAGE = "login.form";


	private static final String APPROVE = "A";
	private static final String REJECT  = "I";

	
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userregistration", method = RequestMethod.GET)
	public ModelAndView showUserRegistration(WebRequest request, Model model){
		
		User user = new User();
		model.addAttribute("user", user);
		
		return new ModelAndView(NEW_PROFILE_CREATE_PAGE);
	}
	
	
	
	/**
	 * @param user
	 * @param result
	 * @param request
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/registernewuser")
	public ModelAndView registerNewUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result, WebRequest request, Errors errors, Model model){
		
		validator.validate(user, result);
		
		if (result.hasErrors()) {
		        return new ModelAndView("new.profile", "user", user);
		 }
		 
			try {
				service.registerUser(user);
				model.addAttribute("success", "Profile inserted. Waiting for admin approval.");
				
		} catch (MailNotSentException | HibernateException | ProfileNotSavedOrUpdatedException | MailException | MessagingException e) {
			
			model.addAttribute("auth", "PROFILE NOT INSERTED: "+e.getMessage());
			//String errorMsg = "PROFILE NOT INSERTED: "+e.getMessage();
			return new ModelAndView("redirect:/userregistration");
		}
		model.addAttribute("success", "Profile inserted. Waiting for admin approval.");
		return new ModelAndView("redirect:/userregistration");
		
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/renderAdminReview")
	public ModelAndView renderListOfUsersForAdminApproval(WebRequest request, Model model){ 
		
		User user = new User();
		
		List<User> listOfPendingUserProfiles = null;
		try {
			listOfPendingUserProfiles = service.fetchPendingUserProfiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(listOfPendingUserProfiles  != null){
			user.getUserCheckBoxList().addAll(listOfPendingUserProfiles);
		}
		else{
			model.addAttribute("message", "You do not have any pending profile to see.");
		}
		
		model.addAttribute("user", user);
		return new ModelAndView("admin.audit");
	}
	
	
	
	/**
	 * @param id
	 * @param approveOrDeny
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approveOrReject", method = RequestMethod.POST)
	public ModelAndView approveBulletin(WebRequest request, @RequestParam(value = "approveParam") String approveOrDeny, Model model) {
		
		String[] userIdentification = request.getParameterValues("selectedUserBox");
		
		if(userIdentification == null){
			
			model.addAttribute("error", "notselected");
			return new ModelAndView("redirect:/renderAdminReview");
		}
		
		
		// user clicked "approve"
	    if (approveOrDeny.equalsIgnoreCase("approveVal")) {
					try {
						service.approveOrRejectUserProfileWith(userIdentification, APPROVE);
					} catch (MailException | MessagingException | NumberFormatException | ProfileNotSavedOrUpdatedException | UserProfileManageException | MailNotSentException e) {
						model.addAttribute("message", "Problem");
					}
				model.addAttribute("message", "An E-mail has been sent to the user containing his/her credentials. ");
	    	
	        
	    // user clicked "reject"	
	    } else if (approveOrDeny.equalsIgnoreCase("denyVal")) {
						try {
							service.approveOrRejectUserProfileWith(userIdentification, REJECT);
						} catch (MailException | MessagingException | NumberFormatException | ProfileNotSavedOrUpdatedException | UserProfileManageException | MailNotSentException e) {
							model.addAttribute("message", "Problem");
						}
				}
				model.addAttribute("message", "An E-mail has been sent to the user.");
	        
	    return new ModelAndView("redirect:/renderAdminReview");
	}
	
	
	/**
	 * @param tagName
	 * @return
	 */
	@RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
	public @ResponseBody
	List<Department> getTags(@RequestParam String tagName) {

		return service.getDepartments();
		
	/*	Department department = new Department();
		
		department.setDepartmentId(1);
		department.setDepartmentName("dept1");
		
		Department department1 = new Department();
		department1.setDepartmentId(2);
		department1.setDepartmentName("dept2");
		
		List<Department> list = new ArrayList<Department>();
		list.add(department);
		list.add(department1);*/
		
		//return list;

	}

	
}
