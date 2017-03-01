/**
 * 
 */
package com.hpe.calEStore.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.calEStore.dao.OrderReportDAO;
import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.model.OrderStatusDM;
import com.hpe.calEStore.service.OrderReportService;

/**
 * @author sangaras
 *
 */
@Service("orderReportService")
@Transactional
public class OrderReportServiceImpl implements OrderReportService {

	@Autowired
	private OrderReportDAO orderReportDAO;

	@Override
	public Map<String, Integer> getActiveOrdersByStatus() {
		// TODO Auto-generated method stub
		return orderReportDAO.getActiveOrdersByStatus();
	}

	@Override
	public List<OrderReportDM> getTotalOrdersByDepartment(String statusType) {
		// TODO Auto-generated method stub
		return orderReportDAO.getTotalOrdersByDepartment(statusType);
	}

	@Override
	public int getTotalOrdersByLatestQuarter() {
		// TODO Auto-generated method stub
		return orderReportDAO.getTotalOrdersByLatestQuarter();
	}

	@Override
	public int getOpenOrdersByLatestQuarter() {
		// TODO Auto-generated method stub
		return orderReportDAO.getOpenOrdersByLatestQuarter();
	}

	@Override
	public int costOfTotalOrdersByLatestQuarter() {
		// TODO Auto-generated method stub
		return orderReportDAO.costOfTotalOrdersByLatestQuarter();
	}

	@Override
	public OrderStatisticsDM getOrderStatistics() {
		// TODO Auto-generated method stub
		return orderReportDAO.getOrderStatistics();
	}

	@Override
	public OrderStatusDM getOrderCount() {
		// TODO Auto-generated method stub
		return orderReportDAO.getOrderCount();
	}

	@Override
	public Map<String, Map<String, Integer>> getWeeklyOrderCountPerDept() {
		// TODO Auto-generated method stub
		return orderReportDAO.getWeeklyOrderCountPerDept();
	}

	@Override
	public Map<String, Map<String, Integer>> getCancelledOrdersByVendor() {
		// TODO Auto-generated method stub
		return orderReportDAO.getCancelledOrdersByVendor();
	}

	@Override
	public Map<String, Map<String, Integer>> getDeliveredOrdersByVendor() {
		// TODO Auto-generated method stub
		return orderReportDAO.getDeliveredOrdersByVendor();
	}

}
