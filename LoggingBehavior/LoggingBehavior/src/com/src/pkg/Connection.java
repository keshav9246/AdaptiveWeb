package com.src.pkg;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	
	public static java.sql.Connection getConnection()
	{
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/adaptiveweb";
	String user ="root";
	String password = "Tiger";
	java.sql.Connection conn = null;
	
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn = DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
	}
}
