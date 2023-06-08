package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.nnhuy.restaurantmanagement.bean.OrderBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class OrderDAO {
	public boolean createOrder(int customerID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"INSERT	INTO Orders (CustomerID, OrderDate) VALUES (?, GETDATE())");

			int idx = 1;
			stmt.setInt(idx++, customerID);

			int nRows = stmt.executeUpdate();
			
			stmt.close();

			if (nRows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public OrderBean getOrder(int customerID) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT *\n" + "FROM Orders\n" + "WHERE CustomerID = (?) AND Status = 'Ordering'");
			int idx = 1;
			stmt.setInt(idx++, customerID);

			ResultSet rs = stmt.executeQuery();

			OrderBean ord = null;

			if (rs.next()) {
				int orderID = rs.getInt(1);
				Date orderDate = rs.getDate(3);
				String status = rs.getString(4);
				ord = new OrderBean(orderID, customerID, orderDate, status);
			}

			rs.close();
			stmt.close();

			return ord;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateOrder(int orderID, String status) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection()
					.prepareStatement("UPDATE Orders SET Status = (?) WHERE OrderID = (?)");

			int idx = 1;
			stmt.setString(idx++, status);
			stmt.setInt(idx++, orderID);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
