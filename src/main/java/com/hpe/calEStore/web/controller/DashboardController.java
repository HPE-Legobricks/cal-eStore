package com.hpe.calEStore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.service.ProductService;

@Controller
public class DashboardController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard() {

		ModelAndView mv = new ModelAndView("admin.dashboard");
		//mv.addObject("catalogueMap", catalogueMap);
		return mv;
	}

}
