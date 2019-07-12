package com.employee.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnector {
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public static Connection Connect() {

		// Statement stmt = null;

		// final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		// final String DB_URL = "jdbc:mysql://localhost/emp";
		//
		// // Database credentials
		// final String USER = "root";
		// final String PASS = "root";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost/emp?useUnicode=true&characterEncoding=UTF-8&user=root&password=root");
			// STEP 3: Open a connection

			// The newInstance() call is a work around for some
			// broken Java implementations

			// Class.forName("com.mysql.jdbc.Driver");
			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3333/empinfo?"
			// +
			// "user=root&password=root");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/emp","root","123456");

			// Do something with the Connection
			 System.out.println("Connection successful");
			// System.out.println("Show inside "+ conn);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("driver: " + e.getMessage());
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.out.println("StackTrace: " + ex.getStackTrace());
		}
		return conn;
	}

	public static void closeConnection(Connection conn, Statement stmt,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
