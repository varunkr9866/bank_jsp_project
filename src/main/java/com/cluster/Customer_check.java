package com.cluster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Customer_check extends HttpServlet{
	Connection con = null;
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("got the driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/varunmysql", "root", "Cluster");
			System.out.println("Connection Established");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Caught Exception:"+ e);
			e.printStackTrace();
		}
		
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName =req.getParameter("username");
		String password = req.getParameter("password");
		Statement st =null;
		ResultSet rs = null;
		try {
			st =con.createStatement();
			rs =st.executeQuery("SELECT ACCNUM,PSW FROM VARUN_BANK;");
			
		} catch (SQLException e) {
			System.out.println("Caught Exception:"+ e);
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Caught Exception:"+ e);
			e.printStackTrace();
		}
	}
}
