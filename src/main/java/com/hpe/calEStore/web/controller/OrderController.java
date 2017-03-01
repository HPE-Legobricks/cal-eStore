/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.dao.entity.ProductOrder;
import com.hpe.calEStore.dao.entity.PurchaseOrder;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.service.OrderService;
import com.hpe.calEStore.service.ProductService;

/**
 * @author Noothan Y V
 *
 */

@Controller
@SessionAttributes("cartItemsMap")
public class OrderController {
	final static Logger logger = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/addForCart")
	@ResponseStatus(value = HttpStatus.OK)
	public void addToCart(@RequestParam("productId") int productId,
	/* @ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap */
	HttpSession session) {
		HashMap<Integer, Integer> cartItemsMap = new HashMap<Integer, Integer>();
		if (session.getAttribute("cartItemsMap") != null) {
			cartItemsMap = (HashMap<Integer, Integer>) (session
					.getAttribute("cartItemsMap"));
			cartItemsMap.put(productId, 1);
		}
	}

	@RequestMapping(value = "/removeFromCart")
	@ResponseStatus(value = HttpStatus.OK)
	public void removeFromCart(@RequestParam("productId") int productId,
	/* @ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap */
	HttpSession session) {
		HashMap<Integer, Integer> cartItemsMap = new HashMap<Integer, Integer>();
		if (session.getAttribute("cartItemsMap") != null) {
			cartItemsMap = (HashMap<Integer, Integer>) (session
					.getAttribute("cartItemsMap"));
			cartItemsMap.remove(productId);
			System.out.println("size of the map" + cartItemsMap.size());
		}
	}

	@RequestMapping(value = "/cartDetail", method = RequestMethod.GET)
	public ModelAndView displayAddressOfUser(
			@ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("cart.detail");
		session.setAttribute("cartItemsMap", new HashMap<Integer, Integer>());
		List<Integer> productId = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : cartItemsMap.entrySet()) {
			productId.add(entry.getKey());
		}
		if (productId != null && !(productId.isEmpty())) {
			List<Product> productsInfo = productService
					.getDetailsByProductId(productId);
			System.out.println("price value "+productsInfo.get(0).getMsrpPerUnit());
			mv.addObject("productsInfo", productsInfo);
		}
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		UserProfile usrAdd = orderService.getAddressByUser(userEmail);
		Set<Address> userAddresses = usrAdd.getAddresses();
		StringBuilder shipToAddress = getAddress(userAddresses);
		mv.addObject("shipToAddress", shipToAddress);
		mv.addObject("cartItemsMap", cartItemsMap);
		return mv;
	}

	@RequestMapping(value = "/oderTrack", method = RequestMethod.GET)
	public ModelAndView trackOrders1() {
		
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!(userEmail.equals(""))) {
			List<PurchaseOrder> poList = orderService
					.getAllOrdersWithStatus(userEmail);
			System.out.println("size of order polist :" + poList.size());
			if (poList.size() == 0) {
				
				ModelAndView mv1 = new ModelAndView("order.empty");
				System.out.println("inside the polist size");
				
				return mv1;
			} else {
				ModelAndView mv = new ModelAndView("order.detail");
				mv.addObject("latestOrder", poList.get(0));
				poList.remove(0);
				mv.addObject("poList", poList);
				return mv;
			}
		}

		return null;
	}

	@RequestMapping(value = "/oderTrack2", method = RequestMethod.GET)
	public ModelAndView trackOrders() {
		ModelAndView mv = new ModelAndView("order.detail");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!(userEmail.equals(""))) {
			try {
				List<PurchaseOrder> orderDetailsMap = orderService
						.getAllOrdersWithStatus(userEmail);
				System.out.println("orderDetailsMap size: "
						+ orderDetailsMap.size());

				int currentOrderId;
				String currentOrderStatus;
				ProductOrder prodName = null;
				StringBuilder productName = new StringBuilder();
				;
				if (orderDetailsMap.size() == 1) {

					for (PurchaseOrder orderlist : orderDetailsMap) {
						currentOrderId = orderlist.getOrderId();
						currentOrderStatus = orderlist.getStatus()
								.getStatusName();
						System.out.println(":::::" + orderlist.getOrderId());
						System.out.println(":::::"
								+ orderlist.getStatus().getStatusName());
						mv.addObject("orderDetailsMap", orderDetailsMap);
						mv.addObject("currentOrderId", currentOrderId);
						mv.addObject("currentOrderStatus", currentOrderStatus);
					}
				} else {
					currentOrderId = orderDetailsMap.get(0).getOrderId();
					currentOrderStatus = orderDetailsMap.get(0).getStatus()
							.getStatusName();
					for (PurchaseOrder orderlist : orderDetailsMap) {

						Set<ProductOrder> orderList = orderlist
								.getProductOrders();

						Iterator itr = orderList.iterator();

						while (itr.hasNext()) {
							prodName = (ProductOrder) itr.next();
							productName.append(
									prodName.getProduct().getProductName())
									.append("\n");
							System.out.println("product description:"
									+ productName);
						}
					}
					// }
					// Set<ProductOrder> orderList = ((Product)
					// orderDetailsMap).getProductOrders();
					// System.out.println("size::"+orderDetailsMap.size());
					// Iterator itr = orderList.iterator();
					// productName = new StringBuilder();
					// ProductOrder prodName = null;
					// while (itr.hasNext()) {
					// prodName= (ProductOrder) itr.next();
					// productName.append(
					// prodName.getProduct().getProductName()).append(
					// "\n");
					// System.out.println("product description:" +productName);
					// }
					orderDetailsMap.remove(0);
					orderDetailsMap.get(0).getProductOrders();

					mv.addObject("orderDetailsMap", orderDetailsMap);
					mv.addObject("currentOrderId", currentOrderId);
					mv.addObject("currentOrderStatus", currentOrderStatus);
					mv.addObject("prodName", prodName);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
		return mv;

	}

	@RequestMapping(value = "/orderConfirmation", method = RequestMethod.GET)
	public ModelAndView orderConfirmation() {
		ModelAndView mv = new ModelAndView("cart.confirm");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!(userEmail.equals(""))) {
			try {
				UserProfile usrAdd = orderService.getAddressByUser(userEmail);
				Set<Address> userAddresses = usrAdd.getAddresses();
				StringBuilder shipToAddress = getAddress(userAddresses);
				mv.addObject("shipToAddress", shipToAddress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("" + e);
			}
			String userDepartmentName = orderService
					.getUserDepartmentName(userEmail);
			mv.addObject("userDepartmentName", userDepartmentName);

		}
		return mv;
	}

	public StringBuilder getAddress(Set<Address> userAddresses) {
		Address address;
		Iterator<Address> iter = userAddresses.iterator();
		StringBuilder shipToAddress = new StringBuilder();
		while (iter.hasNext()) {
			address = (Address) iter.next();
			shipToAddress.append(address.getAddressLine1()).append("\n");
			shipToAddress.append(address.getAddressLine2()).append("\n");
			shipToAddress.append(address.getCity()).append("\n");
			shipToAddress.append(address.getState()).append("\n");
			shipToAddress.append(address.getZipCode());
		}
		return shipToAddress;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveOrder", method = RequestMethod.GET)
	public ModelAndView saveOrder(String userId,
	/* @ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap */
	HttpSession session) {
		ModelAndView mv = new ModelAndView("order.thankyou");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		HashMap<Integer, Integer> cartItemsMap = (HashMap<Integer, Integer>) session
				.getAttribute("cartItemsMap");
		if (userEmail != null) {
			try {
				System.out.println("email: " + userEmail + "size:"
						+ cartItemsMap.size());
				orderService.saveProceessedOrder(userEmail, cartItemsMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			cartItemsMap = new HashMap<Integer, Integer>();
			mv.addObject("cartItemsMap", cartItemsMap);
		}
		return mv;
	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public ModelAndView cancelOrder(@RequestParam int orderId) {

		ModelAndView mv = new ModelAndView("order.detail");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		try {
			orderService.updateOrderStatus(orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

		List<PurchaseOrder> poList = orderService
				.getAllOrdersWithStatus(userEmail);
		mv.addObject("latestOrder", poList.get(0));
		poList.remove(0);
		mv.addObject("poList",poList);
	
	/*	List<PurchaseOrder> orderDetailsMap = orderService
				.getAllOrdersWithStatus(userEmail);
		int currentOrderId = orderDetailsMap.get(0).getOrderId();
		String currentOrderStatus = orderDetailsMap.get(0).getStatus()
				.getStatusName();
		Set<ProductOrder> orderList = orderDetailsMap.get(0).getProductOrders();
		Iterator itr = orderList.iterator();
		StringBuilder productName = new StringBuilder();
		while (itr.hasNext()) {
			ProductOrder prodName = (ProductOrder) itr.next();
			productName.append(prodName.getProduct().getProductName()).append(
					"\n");
		}
		orderDetailsMap.remove(0);
		orderDetailsMap.get(0).getProductOrders();
		mv.addObject("orderDetailsMap", orderDetailsMap);
		mv.addObject("currentOrderId", currentOrderId);
		mv.addObject("currentOrderStatus", currentOrderStatus);
		mv.addObject("productName", productName);*/
		return mv;
	}
}