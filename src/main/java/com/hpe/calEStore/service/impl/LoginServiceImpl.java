package com.hpe.calEStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.calEStore.dao.LoginDAO;
import com.hpe.calEStore.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public boolean checkLogin(String userName, String userPassword) {
		System.out.println("In Service class...Check Login");
		return true; //loginDAO.checkLogin(userName, userPassword);
	}
}