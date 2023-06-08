package com.nnhuy.restaurantmanagement.bean;

public class OrderDetailBean {
	private int orderdetailID;
	private int foodID;
	private int orderID;
	private int amount;
	private String status;

	public int getOrderdetailID() {
		return orderdetailID;
	}

	public void setOrderdetailID(int orderdetailID) {
		this.orderdetailID = orderdetailID;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderDetailBean() {
		super();
	}

	public OrderDetailBean(int orderdetailID, int foodID, int orderID, int amount, String status) {
		super();
		this.orderdetailID = orderdetailID;
		this.foodID = foodID;
		this.orderID = orderID;
		this.amount = amount;
		this.status = status;
	}
}
