package com.nnhuy.restaurantmanagement.bean;

import java.util.Date;

public class OrderBean {
	private int orderID;
	private int customerID;
	private Date orderDate;
	private String status;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderBean() {
		super();
	}

	public OrderBean(int orderID, int customerID, Date orderDate, String status) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.orderDate = orderDate;
		this.status = status;
	}
}
