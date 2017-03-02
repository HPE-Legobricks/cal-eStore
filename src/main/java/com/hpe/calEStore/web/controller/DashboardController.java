package com.hpe.calEStore.web.controller;

import java.io.IOException;
import java.net.URL;
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
import com.hpe.calEStore.config.AppConfig;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.model.OrderStatusDM;
import com.hpe.calEStore.service.ProductService;

@Controller
@ApiIgnore
public class DashboardController {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest request)
			throws JSONException, JsonParseException, JsonMappingException,
			IOException {

		ModelAndView mv = new ModelAndView("admin.dashboard");

		ObjectMapper mapper = new ObjectMapper();

		OrderStatisticsDM orderStatisticsDm = mapper.readValue(new URL(
				appConfig.getRestApiUrl() + "/getOrderStatistics"),
				OrderStatisticsDM.class);

		mv.addObject("orderStatistics", orderStatisticsDm);

		Map<String, Map<String, Integer>> weeklyOrderPerDeptMap = mapper
				.readValue(new URL(appConfig.getRestApiUrl()
						+ "/getWeeklyOrderCountPerDept"), Map.class);
		mv.addObject("weeklyOrderPerDeptMap", weeklyOrderPerDeptMap);

		Map<String, Integer> orderDataMap = (Map<String, Integer>) weeklyOrderPerDeptMap
				.values().toArray()[0];
		mv.addObject("orderDataMap", orderDataMap);

		OrderStatusDM orderStatusDm = mapper.readValue(
				new URL(appConfig.getRestApiUrl() + "/getOrderCountPerStatus"),
				OrderStatusDM.class);
		mv.addObject("orderStatusDm", orderStatusDm);


		Map<String, Map<String, Integer>> cancelledOrdersPerVendorMap = mapper
				.readValue(new URL(appConfig.getRestApiUrl()
						+ "/getWeeklyCancelledOrdersPerVendor"), Map.class);
		mv.addObject("cancelledOrdersPerVendorMap", cancelledOrdersPerVendorMap);

		Map<String, Integer> cancelledDataMap = (Map<String, Integer>) cancelledOrdersPerVendorMap
				.values().toArray()[0];
		mv.addObject("cancelledDataMap", cancelledDataMap);

		Map<String, Map<String, Integer>> deliveredOrdersPerVendorMap = mapper
				.readValue(new URL(appConfig.getRestApiUrl()
						+ "/getWeeklyDeliveredOrdersPerVendor"), Map.class);
		mv.addObject("deliveredOrdersPerVendorMap", deliveredOrdersPerVendorMap);

		Map<String, Integer> deliveredDataMap = (Map<String, Integer>) deliveredOrdersPerVendorMap
				.values().toArray()[0];
		mv.addObject("deliveredDataMap", deliveredDataMap);

		return mv;
	}

}
