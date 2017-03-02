package com.hpe.calEStore.model;

/**
 * @author nagampun
 *
 */
public class OrderStatusDM {

	private int ordersOrdered;
	private int ordersInprogress;
	private int ordersShipped;
	private int ordersDelivered;
	private int ordersCancelled;

	public OrderStatusDM() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the ordersOrdered
	 */
	public int getOrdersOrdered() {
		return ordersOrdered;
	}

	/**
	 * @param ordersOrdered
	 *            the ordersOrdered to set
	 */
	public void setOrdersOrdered(int ordersOrdered) {
		this.ordersOrdered = ordersOrdered;
	}

	/**
	 * @return the ordersInprogress
	 */
	public int getOrdersInprogress() {
		return ordersInprogress;
	}

	/**
	 * @param ordersInprogress
	 *            the ordersInprogress to set
	 */
	public void setOrdersInprogress(int ordersInprogress) {
		this.ordersInprogress = ordersInprogress;
	}

	/**
	 * @return the ordersShipped
	 */
	public int getOrdersShipped() {
		return ordersShipped;
	}

	/**
	 * @param ordersShipped
	 *            the ordersShipped to set
	 */
	public void setOrdersShipped(int ordersShipped) {
		this.ordersShipped = ordersShipped;
	}

	/**
	 * @return the ordersDelivered
	 */
	public int getOrdersDelivered() {
		return ordersDelivered;
	}

	/**
	 * @param ordersDelivered
	 *            the ordersDelivered to set
	 */
	public void setOrdersDelivered(int ordersDelivered) {
		this.ordersDelivered = ordersDelivered;
	}

	/**
	 * @return the ordersCancelled
	 */
	public int getOrdersCancelled() {
		return ordersCancelled;
	}

	/**
	 * @param ordersCancelled
	 *            the ordersCancelled to set
	 */
	public void setOrdersCancelled(int ordersCancelled) {
		this.ordersCancelled = ordersCancelled;
	}

}
