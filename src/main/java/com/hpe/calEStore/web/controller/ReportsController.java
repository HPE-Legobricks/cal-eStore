package com.hpe.calEStore.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.service.OrderReportService;

@Api(value = "Order Statistics")
@Controller
public class ReportsController {

	@Autowired
	private OrderReportService orderReportService;

	@ApiOperation(value = "Fetch Order Statistics")
	@RequestMapping(value = "/getOrderStatistics", method = RequestMethod.GET)
	public ResponseEntity<OrderStatisticsDM> getOrderStatistics() {

		OrderStatisticsDM responseDm = orderReportService.getOrderStatistics();
		return new ResponseEntity<OrderStatisticsDM>(responseDm, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch Count of Orders per each order status")
	@RequestMapping(value = "/getOrderCountPerStatus", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Integer>> getOrderCountPerStatus() {

		Map<String, Integer> orderCountMap = null;//orderReportService.getOrderCount();
		return new ResponseEntity<Map<String, Integer>>(orderCountMap,
				HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch Count of Orders per each dept on a weekly basis")
	@RequestMapping(value = "/getWeeklyOrderCountPerDept", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Map<String, Integer>>> getWeeklyOrderCountPerDept() {
		Map<String, Map<String, Integer>> map = orderReportService
				.getWeeklyOrderCountPerDept();
		return new ResponseEntity<Map<String, Map<String, Integer>>>(map,
				HttpStatus.OK);
	}
}
