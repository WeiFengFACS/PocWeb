package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.employee.main.MySqlConnector;

/**
 * Servlet implementation class SubmitTimesheet
 */
@WebServlet("/SubmitTimesheet")
public class SubmitTimesheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Statement stmt = null;
	public ResultSet rs = null;
	public Connection conn = null;
	public String st = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitTimesheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = request.getParameter("userName");
		String getDate = request.getParameter("date");
		String operation = request.getParameter("operation");
		String submit = null;
		
		System.out.println("date is " + getDate);
		
		if(operation.equals("submit")){
			submit = "Pending for Approval";
		} else if (operation.equals("approve")){
			submit = "Approved";
		} else if (operation.equals("disapprove")){
			submit = "Not Due Yet";
		}
		
		System.out.println(getDate);
		String date = "";
		String[] date_arr = getDate.split(" ");
		for (int i = 1; i <4; i ++){
			if (i != 3){
			date += date_arr[i] + "/";
			} else {
				date += date_arr[i];
			}
		}
		
		System.out.println(date);
		
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "UPDATE timesheets SET submit = '" + submit + "' WHERE email = '"
					+ email + "' and date = '" + date + "'";
			// st = "SELECT * FROM timesheets WHERE email = '" + email +
			// "' and date = '" + date + "'";
			// System.out.println(st);
			stmt.executeUpdate(st);
			System.out.println(st);

			response.setContentType("application/json; charset=UTF-8");

			PrintWriter printout = response.getWriter();
			JSONObject JObject = new JSONObject();
			JObject.put("result", 1);
			printout.write(JObject.toString());
			System.out.println("!!!!!!!!!!!!!!!11" + JObject.toString());

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}

}
