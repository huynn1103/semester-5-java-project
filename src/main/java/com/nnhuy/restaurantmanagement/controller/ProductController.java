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
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.nnhuy.restaurantmanagement.bean.CountryBean;
import com.nnhuy.restaurantmanagement.bean.FoodBean;
import com.nnhuy.restaurantmanagement.bean.OrderBean;
import com.nnhuy.restaurantmanagement.bo.CartBO;
import com.nnhuy.restaurantmanagement.bo.CountryBO;
import com.nnhuy.restaurantmanagement.bo.FoodBO;
import com.nnhuy.restaurantmanagement.bo.OrderBO;

@Controller

public class ProductController {
	@RequestMapping("/AllFoods")
	public String showAllFoods(Model model, HttpServletRequest request,
			@RequestParam(value = "Country", defaultValue = "") String country) {
		FoodBO fbo = new FoodBO();
		ArrayList<FoodBean> lstFoods = null;
		if (country == "") {
			lstFoods = fbo.getAllFoods();
		} else {
			CountryBO ctybo = new CountryBO();
			CountryBean cty = ctybo.getCountryFromName(country);
			lstFoods = fbo.getFoodsFromCountry(cty.getCountryID());
		}

		model.addAttribute("lstFoods", lstFoods);

		HttpSession session = request.getSession();
		if (session.getAttribute("ctm") != null) {
			int customerID = (int) session.getAttribute("customerID");

			OrderBO ordbo = new OrderBO();
			OrderBean ord = ordbo.getOrder(customerID);
			int orderID = ord.getOrderID();

			CartBO crtbo = new CartBO();
			int numberOfCartItems = crtbo.numberOfCartItems(orderID);
			model.addAttribute("numberOfCartItems", numberOfCartItems);
		}

		return "products";
	}

	@RequestMapping("/Welcome")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("ctm") != null) {
			int customerID = (int) session.getAttribute("customerID");

			OrderBO ordbo = new OrderBO();
			OrderBean ord = ordbo.getOrder(customerID);
			int orderID = ord.getOrderID();

			CartBO crtbo = new CartBO();
			int numberOfCartItems = crtbo.numberOfCartItems(orderID);

			model.addAttribute("numberOfCartItems", numberOfCartItems);
		}
		
		FoodBO fbo = new FoodBO();
		ArrayList<FoodBean> lstBestPriceFoods = fbo.getBestPriceFoods();
		ArrayList<FoodBean> lstBestSellerFoods = fbo.getBestSellerFoods();
		
		model.addAttribute("lstBestPriceFoods", lstBestPriceFoods);
		model.addAttribute("lstBestSellerFoods", lstBestSellerFoods);
		
		return "forward:";
	}

	@RequestMapping("/Search")
	public void search(HttpServletRequest request, HttpServletResponse response, @RequestParam String key) {
		try {			
			Gson gson = new Gson();
			FoodBO fbo = new FoodBO();
			ArrayList<FoodBean> lstFoods = fbo.getFoodsFromKey(key);
			String jsonLstFoods = gson.toJson(lstFoods);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(jsonLstFoods);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
