package com.asama.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {

	private static String urlCon;
	private static String user;
	private static String pass;
	private static String driver;
	private static Connection connection = null;

	static {
		Properties properties = new Properties();
		InputStream inputStream = DbUtils.class.getClassLoader().getResourceAsStream("db_config.properties");

		try {
			properties.load(inputStream);
			urlCon = properties.getProperty("url").trim();
			user = properties.getProperty("username").trim();
			pass = properties.getProperty("password").trim();
			driver = properties.getProperty("driver").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(urlCon, user, pass);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
