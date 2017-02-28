package com.hpe.calEStore.web.controller;

import java.io.IOException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.service.ProductService;

@Controller
public class DashboardController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard() throws JSONException,
			JsonParseException, JsonMappingException, IOException {

		ModelAndView mv = new ModelAndView("admin.dashboard");
		//
		/*
		 * RestTemplate restTemplate = new RestTemplate(); String fooResourceUrl
		 * = "http://localhost:8088/calEStore/getOrderStatistics";
		 * ResponseEntity<String> response =
		 * restTemplate.getForEntity(fooResourceUrl, String.class);
		 */

		ObjectMapper mapper = new ObjectMapper();
		
		OrderStatisticsDM orderStatisticsDm = mapper.readValue(
				new URL("http://localhost:8080/calestore/getOrderStatistics"),
				OrderStatisticsDM.class);

		mv.addObject("orderStatistics", orderStatisticsDm);
		return mv;
	}

}
