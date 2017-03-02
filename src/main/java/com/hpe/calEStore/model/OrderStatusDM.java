package com.hpe.calEStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author nagampun
 *
 */
public class OrderStatusDM {

	private int ordersInitiated;
	private int ordersInprogress;
	private int ordersShipped;
	private int ordersDelivered;
	private int ordersCancelled;

	public int getOrdersInitiated() {
		return ordersInitiated;
	}

	public void setOrdersInitiated(int ordersInitiated) {
		this.ordersInitiated = ordersInitiated;
	}

	public int getOrdersInprogress() {
		return ordersInprogress;
	}

	public void setOrdersInprogress(int ordersInprogress) {
		this.ordersInprogress = ordersInprogress;
	}

	public int getOrdersShipped() {
		return ordersShipped;
	}

	public void setOrdersShipped(int ordersShipped) {
		this.ordersShipped = ordersShipped;
	}

	@JsonIgnore
	public int getOrdersDelivered() {
		return ordersDelivered;
	}

	public void setOrdersDelivered(int ordersDelivered) {
		this.ordersDelivered = ordersDelivered;
	}

	@JsonIgnore
	public int getOrdersCancelled() {
		return ordersCancelled;
	}

	public void setOrdersCancelled(int ordersCancelled) {
		this.ordersCancelled = ordersCancelled;
	}

}
