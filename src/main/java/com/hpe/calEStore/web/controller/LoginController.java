package com.hpe.calEStore.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.web.form.LoginForm;

@Controller
@RequestMapping("loginform")
public class LoginController {

	/**
	 * @param model
	 * @return
	 * 
	 *         Custom Login page appears on hitting 'CONTEXT PATH'
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "login.form";
	}

	/**
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = "/createprofile", method = RequestMethod.GET)
	public ModelAndView createProfile() {

		System.out.println("static: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal());
		return new ModelAndView("redirect:/loginform/createnewprofile");
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/createnewprofile", method = RequestMethod.GET)
	public String displayProductCatalogue() {

		return "new.profile";
	}

	/**
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/", "/accessgranted**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpSession session) {

		System.out.println("User logged in as: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		@SuppressWarnings("unchecked")
		List<GrantedAuthority> granted = (List<GrantedAuthority>) auth
				.getAuthorities();
		String role;

		for (int i = 0; i < granted.size();) {
			role = granted.toArray()[i] + "";
			System.out.println("ROLE VERIFIED IS " + i + " is -> " + role);

			if (role.equals("ROLE_A")) {
				System.out.println("IDENTIFIED AS:: ADMINISTRATOR: = " + role);
				// return new ModelAndView("redirect:/renderPaginationViewAll");
				return new ModelAndView("redirect:/adminDashboard");
			} else {
				System.out.println("IDENTIFIED AS:: USER: = " + role);
				session.setAttribute("prodCompList", new ArrayList<Integer>());
				session.setAttribute("cartItemsMap",
						new HashMap<Integer, Integer>());
				return new ModelAndView("redirect:/productCatalogue");
			}
		}
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response, Model model, SessionStatus status) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		status.setComplete();
		return "redirect:/loginform?logout=success";
	}

	/**
	 * @param loginForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model) {

		if (result.hasErrors()) {
			return "loginform";
		}

		boolean userExists = true;
		if (userExists) {
			model.put("loginForm", loginForm);
			return "loginsuccess";
		} else {
			result.rejectValue("userName", "invaliduser");
			return "loginform";
		}

	}

}
