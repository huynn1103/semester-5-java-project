package com.nnhuy.restaurantmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.nnhuy.restaurantmanagement.bean.CartBean;
import com.nnhuy.restaurantmanagement.bean.OrderBean;
import com.nnhuy.restaurantmanagement.bo.CartBO;
import com.nnhuy.restaurantmanagement.bo.OrderBO;
import com.nnhuy.restaurantmanagement.bo.OrderDetailBO;

@Controller

public class CheckoutController {
	@RequestMapping(value = "/Checkout", method = RequestMethod.GET)
	public String showCart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int customerID = (int) session.getAttribute("customerID");

		OrderBO ordbo = new OrderBO();
		OrderBean ord = ordbo.getOrder(customerID);
		int orderID = ord.getOrderID();

		CartBO crtbo = new CartBO();

		ArrayList<CartBean> lstCarts = new ArrayList<CartBean>();
		int totalMoneyOfCart = 0;

		String[] orderDetailIDs = request.getParameterValues("OrderDetailID[]");
		for (String e : orderDetailIDs) {
			int orderDetailID = Integer.parseInt(e);
			CartBean cart = crtbo.getCartByOrderDetailID(orderDetailID);
			lstCarts.add(cart);
			totalMoneyOfCart += cart.getTotal();
		}

		int numberOfCartItems = crtbo.numberOfCartItems(orderID);

		model.addAttribute("lstCarts", lstCarts);
		model.addAttribute("numberOfCartItems", numberOfCartItems);
		model.addAttribute("totalMoneyOfCart", totalMoneyOfCart);

		return "checkout";
	}

	@RequestMapping(value = "/Checkout", method = RequestMethod.POST)
	public void payment(HttpServletRequest request, HttpServletResponse response) {
		String jsonOrderDetailIDs = request.getParameter("jsonOrderDetailIDs");
		Gson gson = new Gson();
		String[] OrderDetailIDs = gson.fromJson(jsonOrderDetailIDs, String[].class);

		int orderDetailID = 0;
		OrderDetailBO odbo = new OrderDetailBO();
		for (String e : OrderDetailIDs) {
			orderDetailID = Integer.parseInt(e);
			odbo.updateOrderDetail(orderDetailID, "Pending payment");
		}

		HttpSession session = request.getSession();
		int customerID = (int) session.getAttribute("customerID");
		OrderBO ordbo = new OrderBO();
		OrderBean ord = ordbo.getOrder(customerID);
		int orderID = ord.getOrderID();
		HashMap<Integer, Integer> info = odbo.getRemainOrder(orderID);

		odbo.deleteRemainOrder(orderID);

		ordbo.updateOrder(orderID, "Pending payment");

		ordbo.createOrder(customerID);
		ord = ordbo.getOrder(customerID);
		orderID = ord.getOrderID();

		for (int i : info.keySet()) {
			int foodID = i;
			int amount = info.get(i);
			odbo.insertOrderDetail(foodID, orderID, amount);
		}
	}
	
	@RequestMapping(value = "/Purchase", method = RequestMethod.GET)
	public String showMyPurchase(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int customerID = (int) session.getAttribute("customerID");

		OrderBO ordbo = new OrderBO();
		OrderBean ord = ordbo.getOrder(customerID);
		int orderID = ord.getOrderID();

		CartBO crtbo = new CartBO();

		ArrayList<CartBean> lstCarts = crtbo.getAllPurchases(customerID);
		int numberOfCartItems = crtbo.numberOfCartItems(orderID);
		int totalMoneyOfPurchases = crtbo.totalMoneyOfPurchases(customerID);

		model.addAttribute("lstCarts", lstCarts);
		model.addAttribute("numberOfCartItems", numberOfCartItems);
		model.addAttribute("totalMoneyOfPurchases", totalMoneyOfPurchases);

		return "purchase";
	}
}
