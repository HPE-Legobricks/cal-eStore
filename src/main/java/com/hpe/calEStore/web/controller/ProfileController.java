package com.hpe.calEStore.web.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
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
import com.hpe.calEStore.service.ProductService;
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
	ProductService productService;


	
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
	public ModelAndView showUserRegistration(WebRequest request, Model model, HttpServletRequest hrequest){
		
		User user = new User();
		model.addAttribute("user", user);
		
		initModelList(model);

		if(hrequest.getSession().getAttribute("sessionDepartments") == null){
			hrequest.getSession().setAttribute("sessionDepartments", service.getDepartments());
		}
		
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
	public ModelAndView registerNewUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result, WebRequest request, Errors errors, Model model, HttpServletRequest req){
		
		
		validator.validate(user, result);
		
		if (result.hasErrors()) {
			
				initModelList(model);
		        return new ModelAndView("new.profile", "user", user);
		 }
		 
		  List<Department> listOfDepartments =  (List<Department>) req.getSession().getAttribute("sessionDepartments");
			
		  EqualPredicate nameEqlPredicate = new EqualPredicate(user.getDepartment().getDepartmentName());
	      BeanPredicate beanPredicate = new BeanPredicate("departmentName", nameEqlPredicate);
	      Collection<Department> filteredCollection = CollectionUtils.select(listOfDepartments, beanPredicate);
		  
	      user.getDepartment().setDepartmentId(filteredCollection.iterator().next().getDepartmentId());
	      user.getDepartment().setDepartmentName(filteredCollection.iterator().next().getDepartmentName());
	      
		    
			try {
				service.registerUser(user);
				model.addAttribute("success", "Profile submitted. Waiting for estore Administrator approval.");
				
		} catch (MailNotSentException | HibernateException | ProfileNotSavedOrUpdatedException | MailException | MessagingException e) {
			
			model.addAttribute("auth", "PROFILE NOT SUBMITTED: "+e.getMessage());
			return new ModelAndView("redirect:/userregistration");
		}
		model.addAttribute("success", "Profile submitted. Waiting for estore Administrator approval.");
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
		return new ModelAndView("manage.user");
	}
	
	
	
	/**
	 * @param id
	 * @param approveOrDeny
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approveOrReject")
	public ModelAndView approveBulletin(WebRequest request, @RequestParam(value = "approveParam") String approveOrDeny, Model model, HttpServletRequest req) {
		
		
		Object[] arObject = (Object[]) req.getSession().getAttribute("userInSession");
		String[] userIdentificationCheckbox = request.getParameterValues("selectedUserBox");
		String[] arrayOfUserChecked = null;
		
		if(arObject == null){
			
			if(userIdentificationCheckbox == null){
				
				model.addAttribute("error", "notselected");
				return new ModelAndView("redirect:/renderPaginationViewAll");
			}
			else{
			/*	List<String> list = new ArrayList<String>(Arrays.asList(""));
				list.addAll(Arrays.asList(userIdentificationCheckbox));
				arrayOfUserChecked = list.toArray(new String[]{});*/
				
				approveOrDeny(model, req, approveOrDeny, userIdentificationCheckbox);
			}
		}
		else{
			
			arrayOfUserChecked =  Arrays.copyOf(arObject, arObject.length, String[].class);
			if(userIdentificationCheckbox != null){
				
				List<String> list = new ArrayList<String>(Arrays.asList(arrayOfUserChecked));
				list.addAll(Arrays.asList(userIdentificationCheckbox));
				arrayOfUserChecked = list.toArray(new String[]{});
				
				approveOrDeny(model, req, approveOrDeny, arrayOfUserChecked);
			}
			else{
				approveOrDeny(model, req, approveOrDeny, arrayOfUserChecked);
			}
		}
		
	    return new ModelAndView("redirect:/renderPaginationViewAll");
	}
	
	
	/**
	 * @param model
	 * @param req
	 * @param approveOrDeny
	 * @param arrayOfUserChecked 
	 */
	private void approveOrDeny(Model model, HttpServletRequest req, String approveOrDeny, String[] arrayOfUserChecked) {
		
		// user clicked "approve"
	    if (approveOrDeny.equalsIgnoreCase("approveVal")) {
				try {
					service.approveOrRejectUserProfileWith(arrayOfUserChecked, APPROVE);
				} catch (MailException | MessagingException | NumberFormatException | ProfileNotSavedOrUpdatedException | UserProfileManageException | MailNotSentException e) {
					model.addAttribute("message", "Problem");
				}
			model.addAttribute("approvedOrRejected", "Approved.");
			model.addAttribute("message", "An E-mail has been sent to the user containing his/her credentials. ");
    	
        
	    // user clicked "reject"	
	    } else if (approveOrDeny.equalsIgnoreCase("denyVal")) {
				try {
					service.approveOrRejectUserProfileWith(arrayOfUserChecked, REJECT);
				} catch (MailException | MessagingException | NumberFormatException | ProfileNotSavedOrUpdatedException | UserProfileManageException | MailNotSentException e) {
					model.addAttribute("message", "Problem");
				}
				
				model.addAttribute("approvedOrRejected", "Rejected.");
				model.addAttribute("message", "An E-mail has been sent to the user.");
		}
		if(req.getSession().getAttribute("userInSession")!=null){
			req.getSession().removeAttribute("userInSession");  
		}
	}





	/**
	 * @param tagName
	 * @return
	 */
	@RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
	public @ResponseBody
	List<Department> getTags(@RequestParam String tagName, HttpServletRequest request) {
		

		List<Department> listOfDepartments = new ArrayList<Department>();
		Object object = request.getSession().getAttribute("sessionDepartments");
		
		if(object != null && object instanceof List){
			
			listOfDepartments = (List<Department>) object;
		}
		
		List<Department> returningListOfDepartments = new ArrayList<Department>();
		
		Department department = null;
		
		for(Department d: listOfDepartments){
			
			if (d.getDepartmentName().contains(tagName)) {
				
				department = new Department();
				department.setDepartmentName(d.getDepartmentName());
				department.setDepartmentId(d.getDepartmentId());
				returningListOfDepartments.add(department);
			}
		}
		return returningListOfDepartments;
	}
	
	
	/**
	 * @param request
	 * @param model
	 * @param hrequest
	 * @return
	 */
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView showForgotPasswordPage(WebRequest request, Model model, HttpServletRequest hrequest){
		
		return new ModelAndView("forgotPassword.form");
	}
	
	
	/**
	 * @param id
	 * @param approveOrDeny
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendpasswordmail")
	public ModelAndView sendNewPassword(Model model, HttpServletRequest request) {
		
		String returnMessage;
		try {
			returnMessage = service.forgotPasswordSendemail(request.getParameter("password"));
			
		} catch (MailException | MessagingException | MailNotSentException e) {
			
			model.addAttribute("problem", "exception");
			return new ModelAndView("redirect:/forgotpassword");
		}
		
		if(returnMessage.equals("false")){
			
			model.addAttribute("failed", returnMessage);
			return new ModelAndView("redirect:/forgotpassword");
		}
		else{
			
			model.addAttribute("passed", returnMessage);
			return new ModelAndView("redirect:/forgotpassword");
		}
	}
	
	
	
	/**
	 * @param model
	 */
	private void initModelList(Model model) {
		
		List<String> cityList = new ArrayList<String>();
		cityList.add("AL");
		cityList.add("AK");
		cityList.add("AZ");
		cityList.add("AR");
		cityList.add("CA");
		cityList.add("CO");
		cityList.add("CT");
		cityList.add("DE");
		cityList.add("DC");
		cityList.add("FL");
		cityList.add("GA");
		cityList.add("HI");
		cityList.add("ID");
		cityList.add("IL");
		cityList.add("IN");
		cityList.add("IA");
		cityList.add("KS");
		cityList.add("KY");
		cityList.add("LA");
		cityList.add("ME");
		cityList.add("MD");
		cityList.add("MA");
		cityList.add("MI");
		cityList.add("MN");
		cityList.add("MS");
		cityList.add("MO");
		cityList.add("MT");
		cityList.add("NE");
		cityList.add("NV");
		cityList.add("NH");
		cityList.add("NJ");
		cityList.add("NM");
		cityList.add("NY");
		cityList.add("NC");
		cityList.add("ND");
		cityList.add("OH");
		cityList.add("OK");
		cityList.add("OR");
		cityList.add("PA");
		cityList.add("RI");
		cityList.add("SC");
		cityList.add("SD");
		cityList.add("TN");
		cityList.add("TX");
		cityList.add("UT");
		cityList.add("VT");
		cityList.add("WA");
		cityList.add("WV");
		cityList.add("WI");
		cityList.add("WY");
		
	    model.addAttribute("city", cityList);
	}
	
	/**
	 * @param id
	 * @param approveOrDeny
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/publishProducts")
	public ModelAndView approveProducts(WebRequest request, @RequestParam(value = "approveParam") String approveOrDeny, Model model, HttpServletRequest req) {
		
		System.out.println(" Suman >>>>>>>>>>>>");		Object[] arObject = (Object[]) req.getSession().getAttribute("userInSession");
		String[] userIdentificationCheckbox = request.getParameterValues("selectedUserBox");
		String[] arrayOfUserChecked = null;
		
		if(arObject == null){
			
			if(userIdentificationCheckbox == null){
				
				model.addAttribute("error", "notselected");
				return new ModelAndView("redirect:/renderPaginationViewAllProducts");
			}
			else{
			/*	List<String> list = new ArrayList<String>(Arrays.asList(""));
				list.addAll(Arrays.asList(userIdentificationCheckbox));
				arrayOfUserChecked = list.toArray(new String[]{});*/
				
				publishProducts(model, req, approveOrDeny, userIdentificationCheckbox);
			}
		}
		else{
			
			arrayOfUserChecked =  Arrays.copyOf(arObject, arObject.length, String[].class);
			if(userIdentificationCheckbox != null){
				
				List<String> list = new ArrayList<String>(Arrays.asList(arrayOfUserChecked));
				list.addAll(Arrays.asList(userIdentificationCheckbox));
				arrayOfUserChecked = list.toArray(new String[]{});
				
				publishProducts(model, req, approveOrDeny, arrayOfUserChecked);
			}
			else{
				publishProducts(model, req, approveOrDeny, arrayOfUserChecked);
			}
		}
		
	    return new ModelAndView("redirect:/renderPaginationViewAllProducts");
		
		//return null;
		
	}
	
	
	/**
	 * @param model
	 * @param req
	 * @param approveOrDeny
	 * @param arrayOfUserChecked 
	 */
	private void publishProducts(Model model, HttpServletRequest req, String approveOrDeny, String[] arrayOfUserChecked) {
		
		// user clicked "approve"
		Integer[] intarray=new Integer[arrayOfUserChecked.length];
	    int i=0;
	    for(String str:arrayOfUserChecked){
	        intarray[i]=Integer.parseInt(str);//Exception in this line
	        i++;
	    }
	    
	    List<Integer> list = new ArrayList<Integer>();
	    list = Arrays.asList(intarray);
	    if (approveOrDeny.equalsIgnoreCase("approveVal")) {
				
			productService.publishProducts(list);
				
			model.addAttribute("approvedOrRejected", "Published.");
			model.addAttribute("message", "Requested products are been published. ");
    	
        
	    // user clicked "reject"	
	    } 
		if(req.getSession().getAttribute("userInSession")!=null){
			req.getSession().removeAttribute("userInSession");  
		}
	}
	
}


	
