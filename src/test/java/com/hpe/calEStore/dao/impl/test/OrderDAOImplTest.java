/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpe.calEStore.dao.OrderDAO;
import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * Test class for ProductCompareDAO
 *
 * @author Suman S
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderDAOImplTest {

	@Autowired
	private OrderDAO orderDAO;

	/**
	 * Test method to verify the Product Aspect List has values
	 */

	@Test
	public void getAddressByUserTest() {
		try {

			UserProfile userProfile = orderDAO.getAddressByUser("luser@hpe.com");
			if (!(userProfile == null)) {
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
	public void getUserDepartmentNameTest() {
		try {

			String departmentName = orderDAO.getUserDepartmentName("luser@hpe.com");
			if (!(departmentName == null)) {
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
	public void getAllOrdersWithStatusTest() {
		try {

			List<PurchaseOrder> ordersWithStatusMap = orderDAO.getAllOrdersWithStatus("user@user.com");
			if (!(ordersWithStatusMap == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Rollback(false)
	@Test
	public void saveProceessedOrderTest() {
		try {
			String emailId = "user@user.com";
			Map<Integer, Integer> productListMap = new HashMap<Integer, Integer>();
			productListMap.put(41, 1);
			productListMap.put(42, 1);

			orderDAO.saveProceessedOrder(emailId, productListMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
