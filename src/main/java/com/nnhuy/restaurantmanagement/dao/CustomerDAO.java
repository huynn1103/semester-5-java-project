package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nnhuy.restaurantmanagement.bean.CustomerBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class CustomerDAO {
	public CustomerBean getCustomerByEmail(String email) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT *\n" + "FROM Customers\n" + "WHERE Email = (?)");
			int idx = 1;
			stmt.setString(idx++, email);

			ResultSet rs = stmt.executeQuery();

			CustomerBean ctm = null;

			if (rs.next()) {
				int customerID = rs.getInt(1);
				String customerName = rs.getString(2);
				String address = rs.getString(3);
				String phone = rs.getString(4);
				String password = rs.getString(6);
				ctm = new CustomerBean(customerID, customerName, address, phone, email, password);
			}

			rs.close();
			stmt.close();

			return ctm;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean register(String customerName, String address, String email, String password) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"INSERT INTO Customers (CustomerName, Address, Email, Password) VALUES (?, ?, ?, ?)");

			int idx = 1;
			stmt.setString(idx++, customerName);
			stmt.setString(idx++, address);
			stmt.setString(idx++, email);
			stmt.setString(idx++, password);

			int nRows = stmt.executeUpdate();
			stmt.close();

			return nRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
