/**
 * 
 */
package com.hpe.calEStore.dao;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.model.OrderReportDM;

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

}
