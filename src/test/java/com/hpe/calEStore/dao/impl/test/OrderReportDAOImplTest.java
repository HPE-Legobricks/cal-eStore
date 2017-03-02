/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpe.calEStore.dao.OrderReportDAO;
import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.model.OrderStatusDM;

/**
 * Test class for ProductCompareDAO
 *
 * @author Suman S
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderReportDAOImplTest {

	@Autowired
	private OrderReportDAO orderReportDAO;

	/**
	 * Test method to verify the Product Aspect List has values
	 */

	@Test
	public void getTotalOrdersByDepartmentTest() {
		try {
			// Map<String, Integer> userProfile =
			// orderReportDAO.getActiveOrdersByStatus();

			List<OrderReportDM> orderReportDM = orderReportDAO
					.getTotalOrdersByDepartment("Delivered");
			if (!(orderReportDM == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getActiveOrdersByStatusTest() {
		try {

			Map<String, Integer> ordersByStatus = orderReportDAO
					.getActiveOrdersByStatus();

			if (!(ordersByStatus == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void costOfTotalOrdersByLatestQuarterTest() {
		try {

			int amount = orderReportDAO.costOfTotalOrdersByLatestQuarter();

			if (!(amount == 0)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getOpenOrdersByLatestQuarterTest() {
		try {

			int count = orderReportDAO.getOpenOrdersByLatestQuarter();

			if (!(count == 0)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getTotalOrdersByLatestQuarterTest() {
		try {

			int count = orderReportDAO.getTotalOrdersByLatestQuarter();

			if (!(count == 0)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getOrderStatisticsTest() {
		try {
			OrderStatisticsDM orderStatistics = orderReportDAO
					.getOrderStatistics();
			if (!(orderStatistics == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getOrderCountTest() {
		try {
			OrderStatusDM orderCount = orderReportDAO.getOrderCount();
			if (!(orderCount == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getWeeklyOrderCountPerDeptTest() {
		try {
			Map<String, Map<String, Integer>> orderCountPerDept = orderReportDAO
					.getWeeklyOrderCountPerDept();
			if (!(orderCountPerDept == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getCancelledOrdersByVendorTest() {
		try {
			Map<String, Map<String, Integer>> cancelledOrdersBVendor = orderReportDAO
					.getCancelledOrdersByVendor();
			if (!(cancelledOrdersBVendor == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getDeliveredOrdersByVendorTest() {
		try {
			Map<String, Map<String, Integer>> deliveredOrdersByVendor = orderReportDAO
					.getDeliveredOrdersByVendor();
			if (!(deliveredOrdersByVendor == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}