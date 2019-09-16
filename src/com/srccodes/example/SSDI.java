package com.srccodes.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SSDI
 */
@WebServlet("/SSDI")
public class SSDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSDI() {
        super();
        
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Driver loaded!");
    	}catch(ClassNotFoundException e) {
    		System.out.println("Loading class failed.");
        }
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement statement;
    	String url = "jdbc:mysql://localhost:3306/test_database?serverTimezone=EST5EDT";
    	String user = "admin";
    	String password = "password";
    	
    	try(Connection dbConnection = DriverManager.getConnection(url, user, password)){
    		statement = dbConnection.createStatement();
    		System.out.println("Database connected!");
    		PrintWriter printWriter = response.getWriter();
        	ResultSet rs = statement.executeQuery("select * from football_players");
        	printWriter.println("Here are some football players:");
        	while(rs.next()) {
        		printWriter.println(rs.getInt(1) + "  " + rs.getString(2));
        	}
    	}catch(SQLException e) {
			System.out.println("SQL Query Failed");
			System.out.println(e);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
