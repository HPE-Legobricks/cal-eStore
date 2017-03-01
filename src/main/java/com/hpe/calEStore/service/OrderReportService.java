package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;

public interface OrderReportService {

	Map<String, Integer> getActiveOrdersByStatus();

	List<OrderReportDM> getTotalOrdersByDepartment(String statusType);

	int getTotalOrdersByLatestQuarter();

	int getOpenOrdersByLatestQuarter();

	int costOfTotalOrdersByLatestQuarter();

	OrderStatisticsDM getOrderStatistics();

	Map<String, Integer> getOrderCount();

	List<Map<String, Map<String, Integer>>> getWeeklyOrderCountPerDept();

	List<Map<String, Map<String, Integer>>> getCancelledOrdersByVendor();

	List<Map<String, Map<String, Integer>>> getDeliveredOrdersByVendor();

}
