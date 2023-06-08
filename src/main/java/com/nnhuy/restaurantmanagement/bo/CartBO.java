package com.nnhuy.restaurantmanagement.bo;

import java.util.ArrayList;

import com.nnhuy.restaurantmanagement.bean.CartBean;
import com.nnhuy.restaurantmanagement.dao.CartDAO;

public class CartBO {
	public ArrayList<CartBean> getAllCarts(int orderID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.getAllCarts(orderID);
	}
	
	public int numberOfCartItems(int orderID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.numberOfCartItems(orderID);
	}
	
	public int totalMoneyOfCart(int orderID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.totalMoneyOfCart(orderID);
	}
	
	public CartBean getCartByOrderDetailID(int orderDetailID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.getCartByOrderDetailID(orderDetailID);
	}
	
	public ArrayList<CartBean> getAllPurchases(int customerID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.getAllPurchases(customerID);
	}
	
	public int totalMoneyOfPurchases(int customerID) {
		CartDAO crtdao = new CartDAO();
		return crtdao.totalMoneyOfPurchases(customerID);
	}
}
