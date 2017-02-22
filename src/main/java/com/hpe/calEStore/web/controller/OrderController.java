/**
 * 
 */
package com.hpe.calEStore.web.controller;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.dao.entity.Address;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.service.OrderService;

/**
 * @author Noothan Y V
 *
 */

@Controller
public class OrderController {
	final static Logger logger = Logger
            .getLogger(OrderController.class);


	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/viewcartItems", method = RequestMethod.GET)
	public ModelAndView viewCartItems() {
		ModelAndView mv = new ModelAndView("cart.detail");
		mv.addObject("shipToAddress","test");
		return mv;
	}
	

	@RequestMapping(value = "/cartDetail", method = RequestMethod.GET)
	public ModelAndView displayAddressOfUser() {
		Address address= new Address();
		ModelAndView mv = new ModelAndView("cart.detail");
		UserProfile usrAdd = orderService.getAddressByUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<Address> userAddresses= usrAdd.getAddresses();
		 logger.info("user id noothan"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		 logger.info("username noothan " + SecurityContextHolder.getContext().getAuthentication().getName());
		Iterator iter = userAddresses.iterator();
		StringBuffer shipToAddress= new StringBuffer();
		while (iter.hasNext()) {
			address=(Address) iter.next();
			shipToAddress.append(address.getAddressLine1()).append("\n");
			shipToAddress.append(address.getAddressLine2()).append("\n").append(",");
			shipToAddress.append(address.getCity()).append("\n");
			shipToAddress.append(address.getState()).append("\n");
			shipToAddress.append(address.getZipCode());
			logger.debug(""+shipToAddress.toString());
		}
		
		mv.addObject("shipToAddress",shipToAddress);
		
		return mv;
	}

	@RequestMapping(value = "/oderTrack", method = RequestMethod.GET)
	public ModelAndView trackOrders() {
		ModelAndView mv = new ModelAndView("order.detail");
		return mv;

	}
	
	@RequestMapping(value = "/oderConfirmation", method = RequestMethod.GET)
	public ModelAndView orderConfirmation() {
		
		ModelAndView mv = new ModelAndView("cart.confirm");
		UserProfile usrAdd = orderService.getAddressByUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<Address> userAddresses= usrAdd.getAddresses();
		
		String shipToAddress= getAddress(userAddresses);
		
		mv.addObject("shipToAddress",shipToAddress);
		
		return mv;
	}
	
		public String getAddress(Set<Address> userAddresses){
			Address address= new Address();
			Iterator<Address> iter = userAddresses.iterator();
			StringBuffer shipToAddress= new StringBuffer();
			while (iter.hasNext()) {
				address=(Address) iter.next();
				shipToAddress.append(address.getAddressLine1()).append("\n");
				shipToAddress.append(address.getAddressLine2()).append("\n").append(",");
				shipToAddress.append(address.getCity()).append("\n");
				shipToAddress.append(address.getState()).append("\n");
				shipToAddress.append(address.getZipCode());
			    System.out.println(":::::::::::::::::::::::::::::::::"+shipToAddress.toString());
			}
			
			return null;
	
}
}
