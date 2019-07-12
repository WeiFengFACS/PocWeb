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

import com.employee.empClass.Developer;
import com.employee.empClass.Employee;
import com.employee.empClass.Manager;
import com.employee.empClass.QA;
import com.employee.main.Main;
import com.employee.main.MySqlConnector;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetTimesheetData
 */
@WebServlet("/GetTimesheetData")
public class GetTimesheetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  Statement stmt = null;
	public  ResultSet rs = null;
	public  Connection conn = null;
	public  String st = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTimesheetData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String operation = request.getParameter("operation");
		System.out.println("inside get timesheet data");
		
//		System.out.println(getDate);
//		String date = "";
//		String[] date_arr = getDate.split(" ");
//		for (int i = 1; i <4; i ++){
//			if (i != 3){
//			date += date_arr[i] + "/";
//			} else {
//				date += date_arr[i];
//			}
//		}
//		
//		System.out.println(date);
		
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT * FROM timesheets WHERE email = '" + email + "' and date = '" + date + "'";
			// System.out.println(st);
			rs = stmt.executeQuery(st);
			System.out.println(st);
			while (rs.next()) {
				String monR = rs.getString("monR");
				String monOT = rs.getString("monOT");
				
				String tueR = rs.getString("tueR");
				String tueOT = rs.getString("tueOT");
				
				String wedR = rs.getString("wedR");
				String wedOT = rs.getString("wedOT");
				
				String thuR = rs.getString("thuR");
				String thuOT = rs.getString("thuOT");
				
				String friR = rs.getString("friR");
				String friOT = rs.getString("friOT");
				
				String satR = rs.getString("satR");
				String satOT = rs.getString("satOT");
				
				String sunR = rs.getString("sunR");
				String sunOT = rs.getString("sunOT");
				
				String approve = rs.getString("approve");
				String URL = rs.getString("URL");
				String submit = rs.getString("submit");
				String totalHr = rs.getString("totalHr");
				String client = rs.getString("client");
				
				response.setContentType("application/json; charset=UTF-8");

				PrintWriter printout = response.getWriter();
					JSONObject JObject = new JSONObject();
				
				if(submit.equals("Not Due Yet") || operation.equals("view")){
					
						JObject.put("result", 1);
						JObject.put("monR", monR);
						JObject.put("monOT", monOT);
						JObject.put("tueR", tueR);
						JObject.put("tueOT", tueOT);
						JObject.put("wedR", wedR);
						JObject.put("wedOT", wedOT);
						JObject.put("thuR", thuR);
						JObject.put("thuOT", thuOT);
						JObject.put("friR", friR);
						JObject.put("friOT", friOT);
						JObject.put("satR", satR);
						JObject.put("satOT", satOT);
						JObject.put("sunR", sunR);
						JObject.put("sunOT", sunOT);
						JObject.put("approve", approve);
						JObject.put("URL", URL);
						JObject.put("submit", submit);
						JObject.put("totalHr", totalHr);
						JObject.put("client", client);
						
				} else {
					JObject.put("result", 0);
				}
				printout.write(JObject.toString());
				System.out.println("!!!!!!!!!!!!!!!11" + JObject.toString());
				
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
		
		
		
		
		
	}

}
