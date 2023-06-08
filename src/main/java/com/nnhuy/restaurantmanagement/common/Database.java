package com.nnhuy.restaurantmanagement.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	Connection connection;

	public Database() {
//		System.out.println("CONNECTING!");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String userName = "sa";
			String password = "123";
			String databaseName = "Restaurant";

			String connectionString = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + databaseName + ";user="
					+ userName + ";password=" + password;
			connection = DriverManager.getConnection(connectionString);
//			System.out.println("CONNECTED!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}
