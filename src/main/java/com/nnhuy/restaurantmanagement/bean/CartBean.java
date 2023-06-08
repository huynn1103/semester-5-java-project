package com.nnhuy.restaurantmanagement.bean;

import java.util.Date;

public class CartBean {
	private int orderDetailID;
	private int foodID;
	private String image;
	private String foodName;
	private int price;
	private int amount;
	private int total;
	private Date orderDate;
	private String status;

	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = this.getPrice() * this.getAmount();
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

	public CartBean() {
		super();
	}

	public CartBean(int orderDetailID, int foodID, String image, String foodName, int price, int amount, Date orderDate,
			String status) {
		super();
		this.orderDetailID = orderDetailID;
		this.foodID = foodID;
		this.image = image;
		this.foodName = foodName;
		this.price = price;
		this.amount = amount;
		this.total = price * amount;
		this.orderDate = orderDate;
		this.status = status;
	}

}
