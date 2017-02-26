/**
 * 
 */
package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.model.OrderReportDM;

/**
 * @author sangaras
 *
 */
public interface OrderReportService {

	Map<String, Integer> getActiveOrdersByStatus();

	List<OrderReportDM> getTotalOrdersByDepartment(String statusType);
	
	int getTotalOrdersByLatestQuarter();
	
	int getOpenOrdersByLatestQuarter();
	
	int costOfTotalOrdersByLatestQuarter();

}
