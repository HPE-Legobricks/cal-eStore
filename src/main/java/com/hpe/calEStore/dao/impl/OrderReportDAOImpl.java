/**
 * 
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.OrderReportDAO;
import com.hpe.calEStore.dao.entity.ProductOrder;
import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.TReportType1;
import com.hpe.calEStore.dao.entity.TReportType2;
import com.hpe.calEStore.model.OrderReportDM;
import com.hpe.calEStore.model.OrderStatisticsDM;
import com.hpe.calEStore.model.StatusType;
import com.mysql.jdbc.StringUtils;

/**
 * @author sangaras
 *
 */

@Repository
public class OrderReportDAOImpl extends
		AbstractDAO<Serializable, PurchaseOrder> implements OrderReportDAO {

	@Transactional
	@Override
	public Map<String, Integer> getActiveOrdersByStatus() {
		// TODO Auto-generated method stub

		Map<String, Integer> orderMap = new HashMap<String, Integer>();
		List<PurchaseOrder> orderedOrders = getPurchaseOrder(1);
		Integer orderedOrdersList = new Integer(orderedOrders.size());
		orderMap.put(StatusType.Ordered.toString(), orderedOrdersList);
		List<PurchaseOrder> processingOrders = getPurchaseOrder(2);
		Integer processingOrdersList = new Integer(processingOrders.size());
		orderMap.put(StatusType.Processing.toString(), processingOrdersList);
		List<PurchaseOrder> shippedOrders = getPurchaseOrder(3);
		Integer shippedOrdersList = new Integer(shippedOrders.size());
		orderMap.put(StatusType.Shipped.toString(), shippedOrdersList);
		List<PurchaseOrder> deliveredOrders = getPurchaseOrder(4);
		Integer deliveredOrdersList = new Integer(deliveredOrders.size());
		orderMap.put(StatusType.Delivered.toString(), deliveredOrdersList);
		List<PurchaseOrder> cancelledOrders = getPurchaseOrder(5);
		Integer cancelledOrdersList = new Integer(cancelledOrders.size());
		orderMap.put(StatusType.Cancelled.toString(), cancelledOrdersList);

		return orderMap;
	}

	@SuppressWarnings("unchecked")
	private List<PurchaseOrder> getPurchaseOrder(int statusType) {

		return createEntityCriteria()
				.add(Restrictions.eq("status.statusId", statusType))
				.addOrder(Order.desc("orderDate")).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<OrderReportDM> getTotalOrdersByDepartment(String statusType) {
		// TODO Auto-generated method stub
		List<PurchaseOrder> totalOrders = new ArrayList<PurchaseOrder>();
		List<OrderReportDM> orderReportDMList = new ArrayList<OrderReportDM>();
		if (!StringUtils.isNullOrEmpty(statusType)) {
			if (statusType.equalsIgnoreCase("All")) {
				totalOrders = createEntityCriteria().addOrder(
						Order.asc("orderDate")).list();
			}
			if (statusType.equalsIgnoreCase(StatusType.Cancelled.toString())) {
				totalOrders = createEntityCriteria()
						.add(Restrictions.eq("status.statusId", 5))
						.addOrder(Order.asc("orderDate")).list();
			}
			if (statusType.equalsIgnoreCase(StatusType.Delivered.toString())) {
				totalOrders = createEntityCriteria()
						.add(Restrictions.eq("status.statusId", 4))
						.addOrder(Order.asc("orderDate")).list();
			}
		}

		Map<String, List<Integer>> ordersMap = new HashMap<String, List<Integer>>();

		for (PurchaseOrder purchaseOrder : totalOrders) {
			List<Integer> orderValues = ordersMap.get(purchaseOrder
					.getOrderDate().toString().substring(0, 10));

			if (orderValues == null) {
				orderValues = new ArrayList<Integer>();
				ordersMap.put(purchaseOrder.getOrderDate().toString()
						.substring(0, 10), orderValues);
			}
			orderValues.add(purchaseOrder.getOrderId());
		}

		Map<String, List<Integer>> ascSortedMap = new TreeMap<String, List<Integer>>(
				ordersMap);

		for (Map.Entry<String, List<Integer>> productIdMap : ascSortedMap
				.entrySet()) {
			String date = productIdMap.getKey();
			List<Integer> orderIds = productIdMap.getValue();
			OrderReportDM orderReportDM = new OrderReportDM();
			orderReportDM.setDate(date);
			orderReportDM
					.setDepartmentMap(getTotalOrdersByDepartment(orderIds));
			orderReportDMList.add(orderReportDM);
		}

		return orderReportDMList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	private Map<String, Integer> getTotalOrdersByDepartment(
			List<Integer> orderIds) {
		// TODO Auto-generated method stub
		List<PurchaseOrder> purchaseOrders = createEntityCriteria()
				.add(Restrictions.in("orderId", orderIds))
				.addOrder(Order.desc("orderDate")).list();

		Map<String, Integer> orderMap = new HashMap<String, Integer>();
		for (PurchaseOrder purchaseOrder : purchaseOrders) {

			Integer orderCount = orderMap.get((purchaseOrder.getUserProfile()
					.getDepartment().getDepartmentName()));
			if (orderCount == null) {
				orderCount = 1;
				orderMap.put(purchaseOrder.getUserProfile().getDepartment()
						.getDepartmentName(), 1);
			} else {

				orderMap.remove(purchaseOrder.getUserProfile().getDepartment()
						.getDepartmentName());
				orderMap.put(purchaseOrder.getUserProfile().getDepartment()
						.getDepartmentName(), ++orderCount);
			}
		}

		return orderMap;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public int getTotalOrdersByLatestQuarter() {
		// TODO Auto-generated method stub

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -90);
		Date nightyDaysAgo = cal.getTime();

		List<PurchaseOrder> purchaseOrders = createEntityCriteria().add(
				Restrictions.between("orderDate", nightyDaysAgo, new Date()))
				.list();

		return purchaseOrders.size();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public int getOpenOrdersByLatestQuarter() {
		// TODO Auto-generated method stub
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -90);
		Date nightyDaysAgo = cal.getTime();

		List<Integer> listOfProducts = new ArrayList<Integer>();
		listOfProducts.add(1);
		listOfProducts.add(2);

		List<PurchaseOrder> purchaseOrders = createEntityCriteria()
				.add(Restrictions.in("status.statusId", listOfProducts))
				.add(Restrictions.between("orderDate", nightyDaysAgo,
						new Date())).list();

		return purchaseOrders.size();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public int costOfTotalOrdersByLatestQuarter() {
		// TODO Auto-generated method stub

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -90);
		Date nightyDaysAgo = cal.getTime();

		List<Integer> listOfProducts = new ArrayList<Integer>();
		listOfProducts.add(1);
		listOfProducts.add(2);
		listOfProducts.add(3);
		listOfProducts.add(4);
		List<PurchaseOrder> purchaseOrders = createEntityCriteria()
				.add(Restrictions.in("status.statusId", listOfProducts))
				.add(Restrictions.between("orderDate", nightyDaysAgo,
						new Date())).list();
		int amount = 0;
		for (PurchaseOrder purchaseOrder : purchaseOrders) {

			Iterator productOrders = purchaseOrder.getProductOrders()
					.iterator();

			while (productOrders.hasNext()) {

				ProductOrder productOrder = (ProductOrder) productOrders.next();
				int price = (int) productOrder.getProduct().getPricePerUnit();
				price = price * productOrder.getQty();
				amount += price;

			}

		}
		return amount;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public OrderStatisticsDM getOrderStatistics() {

		// TODO Auto-generated method stub

		OrderStatisticsDM orderStatistics = new OrderStatisticsDM();
		List<TReportType1> reports = getSession()
				.createCriteria(TReportType1.class)
				.add(Restrictions.eq("userType", "Admin")).list();

		for (TReportType1 reportType1 : reports) {

			if ((reportType1.getKpiId()) == 1)
				orderStatistics.setTotalOrders(Integer.parseInt(reportType1
						.getValueForKpi().trim()));
			if ((reportType1.getKpiId()) == 2)
				orderStatistics.setOpenOrders(Integer.parseInt(reportType1
						.getValueForKpi().trim()));
			if ((reportType1.getKpiId()) == 3)
				orderStatistics.setTotalOrderCost(Integer.parseInt(reportType1
						.getValueForKpi().trim()));
			if ((reportType1.getKpiId()) == 4)
				orderStatistics.setHighSpendingDeptName(reportType1
						.getValueForKpi().trim());
		}

		return orderStatistics;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, Integer> getOrderCount() {

		Map<String, Integer> orderMap = new HashMap<String, Integer>();

		// TODO Auto-generated method stub

		List<TReportType2> tReportType2 = getSession()
				.createCriteria(TReportType2.class)
				.add(Restrictions.eq("kpiNumber", 201)).list();

		for (TReportType2 tReportTypes : tReportType2) {
			orderMap.put(tReportTypes.getStatusType(),
					tReportTypes.getValueForKpi());

		}

		return orderMap;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, Map<String, Integer>> getWeeklyOrderCountPerDept() {
		// TODO Auto-generated method stub

		Map<String, Map<String, Integer>> orderReportList = new HashMap<String, Map<String, Integer>>();

		List<TReportType2> reports = getSession()
				.createCriteria(TReportType2.class)
				.add(Restrictions.eq("statusType", "Ordered"))
				.add(Restrictions
						.eq("kpiDesc",
								"Number of placed orders by Department over a time period"))
				.add(Restrictions.eq("userType", "Admin")).list();

		Map<String, List<Integer>> ordersMap = new HashMap<String, List<Integer>>();
		for (TReportType2 reportType2 : reports) {
			List<Integer> orderValues = ordersMap.get(reportType2.getWeekId()
					.substring(0, 10));

			if (orderValues == null) {
				orderValues = new ArrayList<Integer>();
				ordersMap.put(reportType2.getWeekId().substring(0, 10),
						orderValues);
			}
			orderValues.add(reportType2.getKpiId());
		}
		Map<String, List<Integer>> ascSortedMap = new TreeMap<String, List<Integer>>(
				ordersMap);
		for (Map.Entry<String, List<Integer>> productIdMap : ascSortedMap
				.entrySet()) {
			String date = productIdMap.getKey();
			List<Integer> ids = productIdMap.getValue();
			orderReportList.put(date, getOrdersforReport(ids, "Department"));

		}

		return orderReportList;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	private Map<String, Integer> getOrdersforReport(List<Integer> ids,
			String type) {
		// TODO Auto-generated method stub
		List<TReportType2> reports = getSession()
				.createCriteria(TReportType2.class)
				.add(Restrictions.in("kpiId", ids)).list();

		Map<String, Integer> orderMap = new HashMap<String, Integer>();
		for (TReportType2 tReportType2 : reports) {
			if (type.equalsIgnoreCase("Department"))
				orderMap.put(tReportType2.getDepartmentName(),
						tReportType2.getValueForKpi());
			if (type.equalsIgnoreCase("Vendor")) {
				orderMap.put(tReportType2.getVendorName(),
						tReportType2.getValueForKpi());
			}
		}

		return orderMap;

	}

	@Override
	public Map<String, Map<String, Integer>> getCancelledOrdersByVendor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Map<String, Integer>> getDeliveredOrdersByVendor() {
		// TODO Auto-generated method stub
		return null;
	}

}
