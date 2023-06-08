package com.nnhuy.restaurantmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nnhuy.restaurantmanagement.bean.CountryBean;
import com.nnhuy.restaurantmanagement.common.Database;

public class CountryDAO {
	public CountryBean getCountryFromName(String countryName) {
		Database database = new Database();
		try {
			PreparedStatement stmt = database.getConnection().prepareStatement(
					"SELECT *\n" + "FROM Countries\n" + "WHERE CountryName = (?)");
			int idx = 1;
			stmt.setString(idx++, countryName);

			ResultSet rs = stmt.executeQuery();

			CountryBean cty = null;

			if (rs.next()) {
				int countryID = rs.getInt(1);
				cty = new CountryBean(countryID, countryName);
			}

			rs.close();
			stmt.close();

			return cty;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
