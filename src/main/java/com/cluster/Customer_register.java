package com.cluster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class Customer_register extends HttpServlet{
		Connection con = null;
		ServletContext ctx = null;
		public void init(ServletConfig config) throws ServletException {
			ctx = config.getServletContext();
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
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			
			int tempNum = Integer.parseInt(ctx.getInitParameter("accnum"));
			int accNum = tempNum + 1;
			String strfullname =req.getParameter("fullname");
			String stremail =req.getParameter("email");
			String strphone =req.getParameter("phone");
			String strpassword =req.getParameter("password");
			
			
			PreparedStatement pst = null;
			
			try {
				pst = con.prepareStatement("INSERT INTO VARUN_BANK VALUES(?,?,?,?,?);");
				pst.setInt(1, accNum);
				pst.setString(2, strfullname);
				pst.setString(3, stremail);
				pst.setString(4, strphone);
				pst.setString(5, strpassword);
				int i = pst.executeUpdate();
				if (i == 1) {
					RequestDispatcher rdp = req.getRequestDispatcher("./jsp/register.jsp");
					rdp.forward(req, res);
				} else {
					RequestDispatcher rdp = req.getRequestDispatcher("./jsp/retry.jsp");
					rdp.forward(req, res);
				}
			} catch (SQLException e) {
				System.out.println("Caught Exception:"+ e);
				e.printStackTrace();
			}
			finally {
				try {
					pst.close();
				} catch (SQLException e) {
					System.out.println("Caught Exception:"+ e);
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
