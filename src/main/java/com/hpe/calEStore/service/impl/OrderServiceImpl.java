/**
 * 
 */
package com.hpe.calEStore.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hpe.calEStore.dao.OrderDAO;
import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.service.OrderService;

/**
 * @author sangaras
 *
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public UserProfile getAddressByUser(String emailId) {
		// TODO Auto-generated method stub
		return orderDAO.getAddressByUser(emailId);
	}

	@Override
	public String getUserDepartmentName(String emailId) {
		// TODO Auto-generated method stub
		return orderDAO.getUserDepartmentName(emailId);
	}

	@Override
	public List<PurchaseOrder> getAllOrdersWithStatus(String emailId) {
		// TODO Auto-generated method stub
		return orderDAO.getAllOrdersWithStatus(emailId);
	}

	@Override
	public void saveProceessedOrder(String emailId, Map<Integer, Integer> productMap) {
		// TODO Auto-generated method stub
		orderDAO.saveProceessedOrder(emailId, productMap);
		
	}

}
