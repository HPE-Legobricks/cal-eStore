package com.hpe.calEStore.model;

public class OrderStatisticsDM {

	private int totalOrders;
	private int openOrders;
	private double totalOrderCost;
	private String highSpendingDeptName;

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public int getOpenOrders() {
		return openOrders;
	}

	public void setOpenOrders(int openOrders) {
		this.openOrders = openOrders;
	}

	public double getTotalOrderCost() {
		return totalOrderCost;
	}

	public void setTotalOrderCost(double totalOrderCost) {
		this.totalOrderCost = totalOrderCost;
	}

	public String getHighSpendingDeptName() {
		return highSpendingDeptName;
	}

	public void setHighSpendingDeptName(String highSpendingDeptName) {
		this.highSpendingDeptName = highSpendingDeptName;
	}

}
