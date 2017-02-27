/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author Noothan
 *
 */
public interface OrderService {

	UserProfile getAddressByUser(String emailId);

	String getUserDepartmentName(String emailId);

	List<PurchaseOrder> getAllOrdersWithStatus(String emailId);

	void saveProceessedOrder(String emailId, Map<Integer, Integer> productMap);
	
	void updateOrderStatus(int orderId);
}
