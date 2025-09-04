package com.cluster;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;

public class Customer_login extends HttpServlet{
	Connection con= null;
	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql:localhost:3306varunmysql", "root", "Cluster");
			System.out.println("Connection Established");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Caught Exception:" +e);
			e.printStackTrace();
		}
		
	}
}
