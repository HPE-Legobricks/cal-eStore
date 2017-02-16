package com.hpe.calEStore.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hpe.calEStore.service.LoginService;
import com.hpe.calEStore.web.form.LoginForm;

@Controller
@RequestMapping("loginform")
public class LoginController {

	@Autowired
	public LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "login.form";
	}

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
