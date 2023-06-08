package com.nnhuy.restaurantmanagement.bean;

public class CustomerBean {
	private int customerID;
	private String customerName;
	private String address;
	private String phone;
	private String email;
	private String password;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomerBean() {
		super();
	}

	public CustomerBean(int customerID, String customerName, String address, String phone, String email,
			String password) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
}
