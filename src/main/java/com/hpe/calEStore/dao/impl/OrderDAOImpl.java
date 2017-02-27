/**
 * 
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
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

	private static final String EMAIL_ID = "emailId";

	@Transactional
	@Override
	public UserProfile getAddressByUser(String emailId) {

		return (UserProfile) getSession().createCriteria(UserProfile.class)
				.add(Restrictions.eq(EMAIL_ID, emailId)).uniqueResult();


	}

	@Transactional
	@Override
	public String getUserDepartmentName(String emailId) {


		String departmentName = null;
		UserProfile userProfile = (UserProfile) getSession().createCriteria(UserProfile.class)
				.add(Restrictions.eq(EMAIL_ID, emailId)).uniqueResult();

		if (userProfile != null) {
			departmentName = userProfile.getDepartment().getDepartmentName();
		}
		return departmentName;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<PurchaseOrder> getAllOrdersWithStatus(String emailId) {


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
				.add(Restrictions.eq(EMAIL_ID, emailId)).uniqueResult();

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
		for (Map.Entry<Integer, Integer> productIdMap : productMap.entrySet()) {
			Integer productId = productIdMap.getKey();
			Integer quantity = productIdMap.getValue();
			ProductOrder productOrder = new ProductOrder();
			ProductOrderId productOrderId = new ProductOrderId();
			Product product = new Product();
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



	}

	@Override
	public void updateOrderStatus(int orderId) {



		Query query = getSession().createQuery(
				"update PurchaseOrder purchaseorder set purchaseorder.status.statusId=5 where purchaseorder.orderId=:id");

		query.setParameter("id", orderId);
		query.executeUpdate();

		OrderStatus orderStatus = new OrderStatus();
		Status status = new Status();
		status.setStatusId(5);
		OrderStatusId orderStatusId = new OrderStatusId();
		orderStatusId.setOrderId(orderId);
		orderStatusId.setStatusId(5);
		orderStatus.setId(orderStatusId);
		orderStatus.setStatus(status);
		getSession().save(orderStatus);

	}

}
