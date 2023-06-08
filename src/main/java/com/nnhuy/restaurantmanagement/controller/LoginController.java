package com.nnhuy.restaurantmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nnhuy.restaurantmanagement.bean.CustomerBean;
import com.nnhuy.restaurantmanagement.bo.CustomerBO;
import com.nnhuy.restaurantmanagement.bo.OrderBO;

@Controller

public class LoginController {
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String showLoginPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("ctm") == null) {
			return "login";
		} else {
			return "redirect:Welcome";
		}
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam String email, @RequestParam String password,
			HttpServletRequest request) {
		CustomerBO ctmbo = new CustomerBO();
		CustomerBean ctm = ctmbo.getCustomerByEmail(email);
		if (ctm != null && ctm.getPassword().length() != 0 && BCrypt.checkpw(password, ctm.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("ctm", ctm);
			session.setAttribute("customerID", ctm.getCustomerID());
			session.setAttribute("method", "Log in");
			return "redirect:AllFoods";
		} else {
			model.addAttribute("errorLogin", "Login failed! Invalid email or password.");
			return "login";
		}
	}

	@RequestMapping(value = "/Signin", method = RequestMethod.POST)
	public void signin(@RequestParam String email, @RequestParam String customerName, HttpServletRequest request,
			HttpServletResponse response) {
		CustomerBO ctmbo = new CustomerBO();
		CustomerBean ctm = ctmbo.getCustomerByEmail(email);
		if (ctm == null) {
			ctmbo.register(customerName, "", email, "");
			ctm = ctmbo.getCustomerByEmail(email);
			OrderBO odrbo = new OrderBO();
			odrbo.createOrder(ctm.getCustomerID());
		}
		HttpSession session = request.getSession();
		session.setAttribute("ctm", ctm);
		session.setAttribute("customerID", ctm.getCustomerID());
	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(Model model, @RequestParam(name = "fullname") String customerName,
			@RequestParam String address, @RequestParam String email, @RequestParam String password,
			HttpServletRequest request) {
		CustomerBO ctmbo = new CustomerBO();
		String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		if (ctmbo.register(customerName, address, email, hashPassword)) {
			CustomerBean ctm = ctmbo.getCustomerByEmail(email);
			OrderBO odrbo = new OrderBO();
			odrbo.createOrder(ctm.getCustomerID());
			HttpSession session = request.getSession();
			session.setAttribute("ctm", ctm);
			session.setAttribute("customerID", ctm.getCustomerID());
			session.setAttribute("method", "Log in");
			return "redirect:AllFoods";
		} else {
			model.addAttribute("errorRegister", "Register failed! Email is duplicate.");
			return "login";
		}
	}

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:";
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
