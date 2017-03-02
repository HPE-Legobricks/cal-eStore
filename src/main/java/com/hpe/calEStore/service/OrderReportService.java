package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.model.OrderStatusDM;

public interface OrderReportService {

	Map<String, Integer> getActiveOrdersByStatus();

	List<OrderReportDM> getTotalOrdersByDepartment(String statusType);

	int getTotalOrdersByLatestQuarter();

	int getOpenOrdersByLatestQuarter();

	int costOfTotalOrdersByLatestQuarter();

	OrderStatisticsDM getOrderStatistics();

	OrderStatusDM getOrderCount();

	Map<String, Map<String, Integer>> getWeeklyOrderCountPerDept();

	Map<String, Map<String, Integer>> getCancelledOrdersByVendor();

	Map<String, Map<String, Integer>> getDeliveredOrdersByVendor();
}
