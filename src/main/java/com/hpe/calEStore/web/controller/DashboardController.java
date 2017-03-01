package com.hpe.calEStore.web.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.service.ProductService;

@Controller
@ApiIgnore
public class DashboardController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request)
			throws JSONException, JsonParseException, JsonMappingException,
			IOException {

		ModelAndView mv = new ModelAndView("admin.dashboard");

		ObjectMapper mapper = new ObjectMapper();

		OrderStatisticsDM orderStatisticsDm = mapper.readValue(new URL(
				"http://localhost:8080/calEStore/getOrderStatistics"),
				OrderStatisticsDM.class);

		mv.addObject("orderStatistics", orderStatisticsDm);

		Map<String, Map<String, Integer>> weeklyOrderPerDeptMap = mapper
				.readValue(
						new URL(
								"http://localhost:8080/calEStore/getWeeklyOrderCountPerDept"),
						Map.class);
		mv.addObject("weeklyOrderPerDeptMap", weeklyOrderPerDeptMap);

		Map<String, Integer> orderDataMap = (Map<String, Integer>) weeklyOrderPerDeptMap
				.values().toArray()[0];
		mv.addObject("orderDataMap", orderDataMap);
		return mv;
	}

}
