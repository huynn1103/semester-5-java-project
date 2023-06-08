package com.nnhuy.restaurantmanagement.bo;

import com.nnhuy.restaurantmanagement.bean.OrderBean;
import com.nnhuy.restaurantmanagement.dao.OrderDAO;

public class OrderBO {
	public boolean createOrder(int customerID) {
		OrderDAO odao = new OrderDAO();
		return odao.createOrder(customerID);
	}
	
	public OrderBean getOrder(int customerID) {
		OrderDAO odao = new OrderDAO();
		return odao.getOrder(customerID);
	}
	
	public boolean updateOrder(int orderID, String status) {
		OrderDAO odao = new OrderDAO();
		return odao.updateOrder(orderID, status);
	}
}
