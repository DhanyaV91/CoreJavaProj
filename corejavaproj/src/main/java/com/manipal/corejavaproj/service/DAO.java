package com.manipal.corejavaproj.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/baby_toy";
	private static String username = "root";
	private static String pwd = "MySQL@8021";
	
	private static Connection connection;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection  == null) {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, username, pwd);
		}
		return connection;
	}
}