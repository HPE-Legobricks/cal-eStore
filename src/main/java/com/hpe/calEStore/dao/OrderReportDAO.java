/**
 * 
 */
package com.hpe.calEStore.dao;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;

/**
 * @author sangaras
 *
 */
public interface OrderReportDAO {

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
