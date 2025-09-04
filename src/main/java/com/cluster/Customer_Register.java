package com.cluster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Customer_Register extends HttpServlet{
	Connection con= null;
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql:localhost:3306varunmysql", "root", "Cluster");
			System.out.println("Connection Established");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Caught Exception:" +e);
			e.printStackTrace();
		}
	}
	@Override
	public void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}
