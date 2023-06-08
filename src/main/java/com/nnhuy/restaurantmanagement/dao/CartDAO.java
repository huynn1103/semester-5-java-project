package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.nnhuy.restaurantmanagement.bean.CartBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class CartDAO {
	public ArrayList<CartBean> getAllCarts(int orderID) {
		Database database = new Database();
		ArrayList<CartBean> list = new ArrayList<CartBean>();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT OD.OrderDetailID, F.FoodID, F.Image, F.FoodName, F.Price, OD.Amount, OD.Status\n" +
					"FROM OrderDetails AS OD JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE OD.OrderID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int orderDetailID = rs.getInt(1);
				int foodID = rs.getInt(2);
				String image = rs.getString(3);
				String foodName = rs.getString(4);
				int price = rs.getInt(5);
				int amount = rs.getInt(6);
				String status = rs.getString(7);
				CartBean cart = new CartBean(orderDetailID, foodID, image, foodName, price, amount, new Date(), status);
				list.add(cart);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int numberOfCartItems(int orderID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("SELECT SUM(Amount)\n" + "FROM OrderDetails\n" + "WHERE OrderID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderID);

			ResultSet rs = stmt.executeQuery();

			int numberOfCartItems = 0;

			if (rs.next()) {
				numberOfCartItems = rs.getInt(1);
			}

			rs.close();
			stmt.close();

			return numberOfCartItems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int totalMoneyOfCart(int orderID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT SUM(F.Price * OD.Amount)\n" +
					"FROM OrderDetails AS OD JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE OrderID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderID);

			ResultSet rs = stmt.executeQuery();

			int totalMoneyOfCart = 0;

			if (rs.next()) {
				totalMoneyOfCart = rs.getInt(1);
			}

			rs.close();
			stmt.close();

			return totalMoneyOfCart;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public CartBean getCartByOrderDetailID(int orderDetailID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT OD.OrderDetailID, F.Image, F.FoodName, OD.Amount, F.Price\n" +
					"FROM OrderDetails AS OD JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE OD.OrderDetailID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderDetailID);

			ResultSet rs = stmt.executeQuery();
			
			CartBean cart = null;
			if (rs.next()) {
				String image = rs.getString(2);
				String foodName = rs.getString(3);
				int amount = rs.getInt(4);
				int price = rs.getInt(5);
				cart = new CartBean(orderDetailID, 0, image, foodName, price, amount, new Date(), "");
			}

			rs.close();
			stmt.close();

			return cart;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<CartBean> getAllPurchases(int customerID) {
		Database database = new Database();
		ArrayList<CartBean> list = new ArrayList<CartBean>();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT OD.OrderDetailID, F.FoodID, F.Image, F.FoodName, F.Price, OD.Amount, O.OrderDate, OD.Status\n" +
					"FROM Orders AS O JOIN\n" +
					"OrderDetails AS OD ON O.OrderID = OD.OrderID JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE O.CustomerID = (?) AND O.Status != 'Ordering'\n" +
					"ORDER BY OD.OrderDetailID DESC\n");
			int idx = 1;
			stmt.setInt(idx++, customerID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int orderDetailID = rs.getInt(1);
				int foodID = rs.getInt(2);
				String image = rs.getString(3);
				String foodName = rs.getString(4);
				int price = rs.getInt(5);
				int amount = rs.getInt(6);
				Date orderDate = rs.getDate(7);
				String status = rs.getString(8);
				CartBean cart = new CartBean(orderDetailID, foodID, image, foodName, price, amount, orderDate, status);
				list.add(cart);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int totalMoneyOfPurchases(int customerID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT SUM(F.Price * OD.Amount)\n" +
					"FROM Orders AS O JOIN\n" +
					"OrderDetails AS OD ON O.OrderID = OD.OrderID JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE O.CustomerID = (?) AND O.Status != 'Ordering'\n");
			int idx = 1;
			stmt.setInt(idx++, customerID);

			ResultSet rs = stmt.executeQuery();

			int totalMoneyOfPurchases = 0;

			if (rs.next()) {
				totalMoneyOfPurchases = rs.getInt(1);
			}

			rs.close();
			stmt.close();

			return totalMoneyOfPurchases;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
