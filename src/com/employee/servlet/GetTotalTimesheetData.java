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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.employee.main.MySqlConnector;

/**
 * Servlet implementation class GetTotalTimesheetData
 */
@WebServlet("/GetTotalTimesheetData")
public class GetTotalTimesheetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  Statement stmt = null;
	public  ResultSet rs = null;
	public  Connection conn = null;
	public  String st = null;
	public  String st1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTotalTimesheetData() {
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
		String userName = request.getParameter("userName");
		String curPage = request.getParameter("curPage");
		String rowPerPage = request.getParameter("rowPerPage");
		String operation = request.getParameter("operation");
		int totalRecords = 0;
		
		
		
		JSONObject JEmpInfo = new JSONObject();
		JSONArray JArray = new JSONArray();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		
		if(operation.equals("getTotalNumber")){
			conn = MySqlConnector.Connect();
			try {
				stmt = conn.createStatement();
				st = "SELECT * FROM timesheets WHERE email = '" + userName + "' GROUP BY date";
				// System.out.println(st);
				rs = stmt.executeQuery(st);
				
				while(rs.next()){
					totalRecords++;
				}
				System.out.println("Total records is " + totalRecords);
				
				
				JSONObject JObject = new JSONObject();
				JObject.put("totalRecords", totalRecords);
				System.out.println("AFTER PUT DATA");
				printout.write(JObject.toString());
				System.out.println(JObject.toString());
				
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			} finally {
				MySqlConnector.closeConnection(conn, stmt, rs);
			}
		} else {
			int page = Integer.parseInt(curPage);
			int row = Integer.parseInt(rowPerPage);
			double pageS = row;
			int startPage = (int) (page * pageS);
			int pageSize = (int) pageS;
			
		
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT * FROM timesheets WHERE email = '" + userName 
					+ "' GROUP BY date DESC limit " + startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
	
			while (rs.next()) {
				
				String date = rs.getString("date");
				String endDate = rs.getString("endDate");
				String submit = rs.getString("submit");
				String regularHr = rs.getString("regularHr");
				String otHr = rs.getString("otHr");
				String totalHr = rs.getString("totalHr");
				String URL = rs.getString("URL");
				String client = rs.getString("client");
				String approve = rs.getString("approve");
				
				
				JEmpInfo.put("date", date);
				JEmpInfo.put("endDate", endDate);
				JEmpInfo.put("submit", submit);
				JEmpInfo.put("regularHr", regularHr);
				JEmpInfo.put("otHr", otHr);
				JEmpInfo.put("totalHr", totalHr);
				JEmpInfo.put("URL", URL);
				JEmpInfo.put("client", client);
				JEmpInfo.put("approve", approve);
				JArray.add(JEmpInfo);
					
					
			}
			
		
			printout.write(JArray.toString());

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
		}
	}

}
