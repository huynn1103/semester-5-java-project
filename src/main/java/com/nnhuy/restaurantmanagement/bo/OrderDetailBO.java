package com.nnhuy.restaurantmanagement.bo;

import java.util.HashMap;

import com.nnhuy.restaurantmanagement.bean.OrderDetailBean;
import com.nnhuy.restaurantmanagement.dao.OrderDetailDAO;

public class OrderDetailBO {
	public OrderDetailBean getOrderDetail(int foodID, int orderID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.getOrderDetail(foodID, orderID);
	}

	public OrderDetailBean getOrderDetail(int orderDetailID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.getOrderDetail(orderDetailID);
	}

	public boolean insertOrderDetail(int foodID, int orderID, int amount) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.insertOrderDetail(foodID, orderID, amount);
	}

	public boolean modifyAmount(int orderDetailID, int value) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.modifyAmount(orderDetailID, value);
	}

	public boolean updateOrderDetail(int orderDetailID, String status) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.updateOrderDetail(orderDetailID, status);
	}

	public HashMap<Integer, Integer> getRemainOrder(int orderID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.getRemainOrder(orderID);
	}

	public boolean deleteRemainOrder(int orderID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.deleteRemainOrder(orderID);
	}

	public int getTotalOfOrderDetail(int orderDetailID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.getTotalOfOrderDetail(orderDetailID);
	}

	public boolean deleteOrderDetail(int orderDetailID) {
		OrderDetailDAO oddao = new OrderDetailDAO();
		return oddao.deleteOrderDetail(orderDetailID);
	}
}
