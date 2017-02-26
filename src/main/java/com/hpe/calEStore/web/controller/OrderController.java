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
	public void addToCart(
			@RequestParam("productId") int productId,
			@ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap,
			HttpSession session) {
		cartItemsMap.put(productId, 1);
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
	public ModelAndView trackOrders() {
		ModelAndView mv = new ModelAndView("order.detail");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!(userEmail.equals(""))) {
			List<PurchaseOrder> orderDetailsMap = orderService
					.getAllOrdersWithStatus(userEmail);

			int currentOrderId = orderDetailsMap.get(0).getOrderId();
			String currentOrderStatus = orderDetailsMap.get(0).getStatus()
					.getStatusName();
			Set<ProductOrder> orderList = orderDetailsMap.get(0)
					.getProductOrders();
			Iterator itr = orderList.iterator();
			StringBuilder productName = new StringBuilder();
			while (itr.hasNext()) {
				ProductOrder prodName = (ProductOrder) itr.next();
				productName.append(prodName.getProduct().getProductName())
						.append("\n");
			}
			//logger.debug("product information:" + productName);
			orderDetailsMap.remove(0);
			orderDetailsMap.get(0).getProductOrders();
			mv.addObject("orderDetailsMap", orderDetailsMap);
			mv.addObject("currentOrderId", currentOrderId);
			mv.addObject("currentOrderStatus", currentOrderStatus);
			mv.addObject("productName", productName);
		}
		return mv;

	}

	@RequestMapping(value = "/orderConfirmation", method = RequestMethod.GET)
	public ModelAndView orderConfirmation() {
		ModelAndView mv = new ModelAndView("cart.confirm");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if (!(userEmail.equals(""))) {
			UserProfile usrAdd = orderService
					.getAddressByUser(userEmail);
			Set<Address> userAddresses = usrAdd.getAddresses();
			StringBuilder shipToAddress = getAddress(userAddresses);
			String userDepartmentName = orderService
					.getUserDepartmentName(userEmail);
			mv.addObject("userDepartmentName", userDepartmentName);
			mv.addObject("shipToAddress", shipToAddress);
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

	@RequestMapping(value = "/saveOrder", method = RequestMethod.GET)
	public ModelAndView saveOrder(
			String userId,
			@ModelAttribute("cartItemsMap") HashMap<Integer, Integer> cartItemsMap) {
		ModelAndView mv = new ModelAndView("order.thankyou");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		orderService.saveProceessedOrder(userEmail, cartItemsMap);
		cartItemsMap = new HashMap<Integer, Integer>();
		return mv;
	}

	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public ModelAndView cancelOrder(@RequestParam int orderId) {

		ModelAndView mv = new ModelAndView("order.detail");
		String userEmail = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		orderService.updateOrderStatus(orderId);
		List<PurchaseOrder> orderDetailsMap = orderService
				.getAllOrdersWithStatus(userEmail);
		int currentOrderId = orderDetailsMap.get(0).getOrderId();
		String currentOrderStatus = orderDetailsMap.get(0).getStatus()
				.getStatusName();
		Set<ProductOrder> orderList = orderDetailsMap.get(0).getProductOrders();
		Iterator itr = orderList.iterator();
		StringBuilder productName = new StringBuilder();
		while (itr.hasNext()) {
			ProductOrder prodName = (ProductOrder) itr.next();
			productName.append(prodName.getProduct().getProductName()).append("\n");
		}
		orderDetailsMap.remove(0);
		orderDetailsMap.get(0).getProductOrders();
		mv.addObject("orderDetailsMap", orderDetailsMap);
		mv.addObject("currentOrderId", currentOrderId);
		mv.addObject("currentOrderStatus", currentOrderStatus);
		mv.addObject("productName", productName);
		return mv;
	}
}