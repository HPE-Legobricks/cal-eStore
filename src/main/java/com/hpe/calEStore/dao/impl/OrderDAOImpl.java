/**
 * 
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.OrderDAO;

import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.OrderStatus;
import com.hpe.calEStore.dao.entity.OrderStatusId;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.dao.entity.ProductOrder;
import com.hpe.calEStore.dao.entity.ProductOrderId;
import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.Status;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * @author Noothan Y V
 *
 */
@Repository
public class OrderDAOImpl extends AbstractDAO<Serializable, PurchaseOrder> implements OrderDAO {

	@Transactional
	@Override
	public UserProfile getAddressByUser(String emailId) {
		// TODO Auto-generated method stub
		UserProfile userProfile = (UserProfile) getSession().createCriteria(UserProfile.class)
				.add(Restrictions.eq("emailId", emailId)).uniqueResult();

		return userProfile;
	}

	@Transactional
	@Override
	public String getUserDepartmentName(String emailId) {
		// TODO Auto-generated method stub

		String departmentName = null;
		UserProfile userProfile = (UserProfile) getSession().createCriteria(UserProfile.class)
				.add(Restrictions.eq("emailId", emailId)).uniqueResult();

		if (userProfile != null) {
			departmentName = userProfile.getDepartment().getDepartmentName();
		}
		return departmentName;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<PurchaseOrder> getAllOrdersWithStatus(String emailId) {
		// TODO Auto-generated method stub

		List<PurchaseOrder> purchaseOrders = createEntityCriteria()
				.add(Restrictions.eq("userProfile.userId", getAddressByUser(emailId).getUserId()))
				.addOrder(Order.desc("orderDate")).list();

		return purchaseOrders;
	}

	@Transactional
	@Override
	public void saveProceessedOrder(String emailId, Map<Integer, Integer> productMap) {

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		Address address = new Address();
		UserProfile userProfile = (UserProfile) getSession().createCriteria(UserProfile.class)
				.add(Restrictions.eq("emailId", emailId)).uniqueResult();

		Iterator<Address> itr = userProfile.getAddresses().iterator();
		while (itr.hasNext()) {
			address = itr.next();

		}
		Status status = new Status();
		status.setStatusId(1);

		purchaseOrder.setAddress(address);
		purchaseOrder.setUserProfile(userProfile);
		purchaseOrder.setStatus(status);
		int orderId = (int) saveAndReturnKey(purchaseOrder);

		ProductOrder productOrder = new ProductOrder();
		ProductOrderId productOrderId = new ProductOrderId();
		Product product = new Product();

		for (Map.Entry<Integer, Integer> productIdMap : productMap.entrySet()) {
			Integer productId = productIdMap.getKey();
			Integer quantity = productIdMap.getValue();
			productOrderId.setOrderId(orderId);
			productOrderId.setProductId(productId);
			product.setProductId(productId);
			productOrder.setId(productOrderId);
			productOrder.setProduct(product);
			productOrder.setQty(quantity);
			getSession().save(productOrder);

		}
		OrderStatus orderStatus = new OrderStatus();
		OrderStatusId orderStatusId = new OrderStatusId();
		orderStatusId.setOrderId(orderId);
		orderStatusId.setStatusId(1);
		orderStatus.setId(orderStatusId);
		orderStatus.setStatus(status);
		getSession().save(orderStatus);

		// TODO Auto-generated method stub

	}

}
