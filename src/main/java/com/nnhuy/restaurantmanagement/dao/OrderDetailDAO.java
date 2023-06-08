package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.nnhuy.restaurantmanagement.bean.OrderDetailBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class OrderDetailDAO {
	public OrderDetailBean getOrderDetail(int foodID, int orderID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT *\n" + "FROM OrderDetails\n" + "WHERE FoodID = (?) AND" + " OrderID = (?)");
			int idx = 1;
			stmt.setInt(idx++, foodID);
			stmt.setInt(idx++, orderID);

			ResultSet rs = stmt.executeQuery();

			OrderDetailBean od = null;

			if (rs.next()) {
				int orderDetailID = rs.getInt(1);
				int amount = rs.getInt(4);
				String status = rs.getString(5);
				od = new OrderDetailBean(orderDetailID, foodID, orderID, amount, status);
			}

			rs.close();
			stmt.close();

			return od;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public OrderDetailBean getOrderDetail(int orderDetailID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT *\n" + "FROM OrderDetails\n" + "WHERE OrderDetailID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderDetailID);

			ResultSet rs = stmt.executeQuery();

			OrderDetailBean od = null;

			if (rs.next()) {
				int foodID = rs.getInt(2);
				int orderID = rs.getInt(3);
				int amount = rs.getInt(4);
				String status = rs.getString(5);
				od = new OrderDetailBean(orderDetailID, foodID, orderID, amount, status);
			}

			rs.close();
			stmt.close();

			return od;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertOrderDetail(int foodID, int orderID, int amount) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("INSERT INTO OrderDetails (FoodID, OrderID, Amount) VALUES (?, ?, ?)");

			int idx = 1;
			stmt.setInt(idx++, foodID);
			stmt.setInt(idx++, orderID);
			stmt.setInt(idx++, amount);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean modifyAmount(int orderDetailID, int value) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"UPDATE OrderDetails SET Amount = Amount + (?) WHERE OrderDetailID = (?)");

			int idx = 1;
			stmt.setInt(idx++, value);
			stmt.setInt(idx++, orderDetailID);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOrderDetail(int orderDetailID, String status) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("UPDATE OrderDetails SET Status = (?) WHERE OrderDetailID = (?)");

			int idx = 1;
			stmt.setString(idx++, status);
			stmt.setInt(idx++, orderDetailID);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public HashMap<Integer, Integer> getRemainOrder(int orderID) {
		Database database = new Database();
		HashMap<Integer, Integer> info = new HashMap<Integer, Integer>();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("SELECT FoodID, Amount\n" +
					"FROM OrderDetails\n" +
					"WHERE OrderID = (?) AND Status = 'Ordering'");
			int idx = 1;
			stmt.setInt(idx++, orderID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				int amount = rs.getInt(2);
				info.put(foodID, amount);
			}
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteRemainOrder(int orderID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("DELETE FROM OrderDetails WHERE OrderID = (?) AND Status = 'Ordering'");

			int idx = 1;
			stmt.setInt(idx++, orderID);
			
			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getTotalOfOrderDetail(int orderDetailID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT F.Price * OD.Amount\n" +
					"FROM OrderDetails AS OD JOIN\n" +
					"Foods AS F ON OD.FoodID = F.FoodID\n" +
					"WHERE OrderDetailID = (?)");
			int idx = 1;
			stmt.setInt(idx++, orderDetailID);

			ResultSet rs = stmt.executeQuery();

			int totalOfOrderDetail = 0;

			if (rs.next()) {
				totalOfOrderDetail = rs.getInt(1);
			}

			rs.close();
			stmt.close();

			return totalOfOrderDetail;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean deleteOrderDetail(int orderDetailID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("DELETE FROM OrderDetails WHERE OrderDetailID = (?)");

			int idx = 1;
			stmt.setInt(idx++, orderDetailID);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
