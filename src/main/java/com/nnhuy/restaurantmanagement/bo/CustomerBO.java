package com.nnhuy.restaurantmanagement.bo;

import com.nnhuy.restaurantmanagement.bean.CustomerBean;
import com.nnhuy.restaurantmanagement.dao.CustomerDAO;

public class CustomerBO {
	public CustomerBean getCustomerByEmail(String email) {
		CustomerDAO ctmdao = new CustomerDAO();
		return ctmdao.getCustomerByEmail(email);
	}
	
	public boolean register(String customerName, String address, String email, String password) {
		CustomerDAO ctmdao = new CustomerDAO();
		return ctmdao.register(customerName, address, email, password);
	}
}
