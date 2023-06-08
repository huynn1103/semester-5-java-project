package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nnhuy.restaurantmanagement.bean.FoodBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class FoodDAO {
	public ArrayList<FoodBean> getAllFoods() {
		Database database = new Database();
		ArrayList<FoodBean> list = new ArrayList<FoodBean>();
		try {
			String sql = "SELECT * FROM Foods ORDER BY FoodName";
			PreparedStatement stmt = database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				String foodName = rs.getString(2);
				int amount = rs.getInt(3);
				int price = rs.getInt(4);
				String image = rs.getString(5);
				int countryID = rs.getInt(6);
				FoodBean food = new FoodBean(foodID, foodName, amount, price, image, countryID);
				list.add(food);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FoodBean> getFoodsFromCountry(int countryID) {
		Database database = new Database();
		ArrayList<FoodBean> list = new ArrayList<FoodBean>();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT * FROM Foods WHERE CountryID = (?) ORDER BY FoodName");
			int idx = 1;
			stmt.setInt(idx++, countryID);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				String foodName = rs.getString(2);
				int amount = rs.getInt(3);
				int price = rs.getInt(4);
				String image = rs.getString(5);
				FoodBean food = new FoodBean(foodID, foodName, amount, price, image, countryID);
				list.add(food);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FoodBean> getFoodsFromKey(String key) {
		Database database = new Database();
		ArrayList<FoodBean> list = new ArrayList<FoodBean>();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT F.FoodID, F.FoodName, F.Amount, F.Price, F.Image, F.CountryID\n" +
					"FROM Foods AS F JOIN\n" +
					"Countries AS C ON F.CountryID = C.CountryID\n" +
					"WHERE F.FoodName LIKE (?) OR C.CountryName LIKE (?)\n" +
					"ORDER BY F.FoodName");
			int idx = 1;
			stmt.setString(idx++, '%' + key + '%');
			stmt.setString(idx++, '%' + key + '%');

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				String foodName = rs.getString(2);
				int amount = rs.getInt(3);
				int price = rs.getInt(4);
				String image = rs.getString(5);
				int countryID = rs.getInt(6);
				FoodBean food = new FoodBean(foodID, foodName, amount, price, image, countryID);
				list.add(food);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FoodBean> getBestPriceFoods() {
		Database database = new Database();
		ArrayList<FoodBean> list = new ArrayList<FoodBean>();
		try {
			String sql = "SELECT * FROM Foods ORDER BY Price";
			PreparedStatement stmt = database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				String foodName = rs.getString(2);
				int amount = rs.getInt(3);
				int price = rs.getInt(4);
				String image = rs.getString(5);
				int countryID = rs.getInt(6);
				FoodBean food = new FoodBean(foodID, foodName, amount, price, image, countryID);
				list.add(food);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FoodBean> getBestSellerFoods() {
		Database database = new Database();
		ArrayList<FoodBean> list = new ArrayList<FoodBean>();
		try {
			String sql = "SELECT F.FoodID, F.FoodName, F.Amount, F.Price, F.Image, F.CountryID\n" +
						"FROM Foods AS F LEFT JOIN\n" +
						"OrderDetails AS OD ON F.FoodID = OD.FoodID\n" +
						"GROUP BY F.FoodID, F.FoodName, F.Amount, F.Price, F.Image, F.CountryID\n" +
						"ORDER BY SUM(OD.Amount) DESC";
			PreparedStatement stmt = database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodID = rs.getInt(1);
				String foodName = rs.getString(2);
				int amount = rs.getInt(3);
				int price = rs.getInt(4);
				String image = rs.getString(5);
				int countryID = rs.getInt(6);
				FoodBean food = new FoodBean(foodID, foodName, amount, price, image, countryID);
				list.add(food);
			}

			rs.close();
			stmt.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
