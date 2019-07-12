package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.employee.main.DBUtility;

/**
 * Servlet implementation class CheckTimesheetExist
 */
@WebServlet("/CheckTimesheetExist")
public class CheckTimesheetExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckTimesheetExist() {
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
		
		String email = request.getParameter("userName");
		String getDate = request.getParameter("date");
		
		String date = "";
		String[] date_arr = getDate.split(" ");
		for (int i = 1; i <4; i ++){
			if (i != 3){
			date += date_arr[i] + "/";
			} else {
				date += date_arr[i];
			}
		}
		
		
		boolean checker = DBUtility.checkTimesheetExistSql(email, date);
	
		if (checker == true){
			status = 0;
			message = "Timesheet is already in database";
			
		} else {
			status = 1;
			message = "Create new timesheet";
		}
		
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter printout = response.getWriter();
			JSONObject JObject = new JSONObject();
			JObject.put("ResponseChecker", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
			System.out.println(JObject.toString());
		
	}

}
