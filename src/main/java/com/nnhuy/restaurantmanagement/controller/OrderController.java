package com.nnhuy.restaurantmanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.nnhuy.restaurantmanagement.bean.CartBean;
import com.nnhuy.restaurantmanagement.bean.OrderBean;
import com.nnhuy.restaurantmanagement.bean.OrderDetailBean;
import com.nnhuy.restaurantmanagement.bo.CartBO;
import com.nnhuy.restaurantmanagement.bo.OrderBO;
import com.nnhuy.restaurantmanagement.bo.OrderDetailBO;

@Controller

public class OrderController {
	@RequestMapping("/Cart")
	public String showCart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int customerID = (int) session.getAttribute("customerID");

		OrderBO ordbo = new OrderBO();
		OrderBean ord = ordbo.getOrder(customerID);
		int orderID = ord.getOrderID();

		CartBO crtbo = new CartBO();
		ArrayList<CartBean> lstCarts = crtbo.getAllCarts(orderID);
		int numberOfCartItems = crtbo.numberOfCartItems(orderID);
		int totalMoneyOfCart = crtbo.totalMoneyOfCart(orderID);

		model.addAttribute("lstCarts", lstCarts);
		model.addAttribute("numberOfCartItems", numberOfCartItems);
		model.addAttribute("totalMoneyOfCart", totalMoneyOfCart);

		return "cart";
	}

	@RequestMapping("/UpdateMoneyOfCart")
	public void updateMoneyOfCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jsonOrderDetailIDs = request.getParameter("jsonOrderDetailIDs");
			Gson gson = new Gson();
			String[] OrderDetailIDs = gson.fromJson(jsonOrderDetailIDs, String[].class);

			int orderDetailID = 0, totalMoneyOfCart = 0;
			OrderDetailBO odbo = new OrderDetailBO();
			for (String e : OrderDetailIDs) {
				orderDetailID = Integer.parseInt(e);
				totalMoneyOfCart += odbo.getTotalOfOrderDetail(orderDetailID);
			}

			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(totalMoneyOfCart);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/Order", method = RequestMethod.POST)
	public void order(HttpServletRequest request, HttpServletResponse response, @RequestParam int foodID) {
		try {
			HttpSession session = request.getSession();
			int customerID = (int) session.getAttribute("customerID");

			OrderBO ordbo = new OrderBO();
			OrderBean ord = ordbo.getOrder(customerID);
			int orderID = ord.getOrderID();

			OrderDetailBO odbo = new OrderDetailBO();
			OrderDetailBean od = odbo.getOrderDetail(foodID, orderID);
			if (od == null) {
				odbo.insertOrderDetail(foodID, orderID, 1);
			} else {
				odbo.modifyAmount(od.getOrderdetailID(), 1);
			}

			CartBO crtbo = new CartBO();
			int numberOfCartItems = crtbo.numberOfCartItems(orderID);

			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(numberOfCartItems);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/IncreaseAmount", method = RequestMethod.POST)
	public void increaseAmount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int orderDetailID) {
		try {
			OrderDetailBO odbo = new OrderDetailBO();
			odbo.modifyAmount(orderDetailID, 1);
			OrderDetailBean od = odbo.getOrderDetail(orderDetailID);

			HttpSession session = request.getSession();
			int customerID = (int) session.getAttribute("customerID");
			OrderBO ordbo = new OrderBO();
			OrderBean ord = ordbo.getOrder(customerID);
			int orderID = ord.getOrderID();
			CartBO crtbo = new CartBO();
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(crtbo.numberOfCartItems(orderID) + " ");
			out.print(od.getAmount() + " ");
			out.print(odbo.getTotalOfOrderDetail(orderDetailID));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/DecreaseAmount", method = RequestMethod.POST)
	public void decreaseAmount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int orderDetailID) {
		try {
			OrderDetailBO odbo = new OrderDetailBO();
			int amount = odbo.getOrderDetail(orderDetailID).getAmount();
			if (amount != 1) {
				odbo.modifyAmount(orderDetailID, -1);
				OrderDetailBean od = odbo.getOrderDetail(orderDetailID);

				HttpSession session = request.getSession();
				int customerID = (int) session.getAttribute("customerID");
				OrderBO ordbo = new OrderBO();
				OrderBean ord = ordbo.getOrder(customerID);
				int orderID = ord.getOrderID();
				CartBO crtbo = new CartBO();

				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				out.print(crtbo.numberOfCartItems(orderID) + " ");
				out.print(od.getAmount() + " ");
				out.print(odbo.getTotalOfOrderDetail(orderDetailID));
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/DeleteOrderDetail", method = RequestMethod.POST)
	public void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int orderDetailID) {
		try {
			OrderDetailBO odbo = new OrderDetailBO();
			odbo.deleteOrderDetail(orderDetailID);

			HttpSession session = request.getSession();
			int customerID = (int) session.getAttribute("customerID");
			OrderBO ordbo = new OrderBO();
			OrderBean ord = ordbo.getOrder(customerID);
			int orderID = ord.getOrderID();
			CartBO crtbo = new CartBO();

			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(crtbo.numberOfCartItems(orderID) + " ");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
