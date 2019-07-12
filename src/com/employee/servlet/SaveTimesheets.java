package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.employee.main.DBUtility;

/**
 * Servlet implementation class SaveTimesheets
 */
@WebServlet("/SaveTimesheets")
public class SaveTimesheets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveTimesheets() {
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
		String getDate = request.getParameter("date");
		String getEndDate = request.getParameter("endDate");
		
//		String monR = request.getParameter("monR");
		String monR = request.getParameter("monR");
		System.out.println("monday regular hours is !!!!" + monR);
		String monOT = request.getParameter("monOT");
		
		String tueR = request.getParameter("tueR");
		String tueOT = request.getParameter("tueOT");
		
		String wedR = request.getParameter("wedR");
		String wedOT = request.getParameter("wedOT");
		
		String thuR = request.getParameter("thuR");
		String thuOT = request.getParameter("thuOT");
		
		String friR = request.getParameter("friR");
		String friOT = request.getParameter("friOT");
		
		String satR = request.getParameter("satR");
		String satOT = request.getParameter("satOT");
		
		String sunR = request.getParameter("sunR");
		String sunOT = request.getParameter("sunOT");
		
		String approve = request.getParameter("approve");
		String URL = request.getParameter("URL");
		String submit = request.getParameter("submit");
		String totalHr = request.getParameter("totalHr");
		String regularHr = request.getParameter("regularHr");
		String otHr = request.getParameter("otHr");
		String client = request.getParameter("client");
		
//		System.out.println(getDate);
		String date = "";
		String[] date_arr = getDate.split(" ");
		for (int i = 1; i <4; i ++){
			if (i != 3){
			date += date_arr[i] + "/";
			} else {
				date += date_arr[i];
			}
		}
		
		String endDate = "";
		String[] endDate_arr = getEndDate.split(" ");
		for (int i = 1; i <4; i ++){
			if (i != 3){
				endDate += endDate_arr[i] + "/";
			} else {
				endDate += endDate_arr[i];
			}
		}
		
		System.out.println(date);

		System.out.println("start date is " + date);
		System.out.println("check is " + DBUtility.checkTimesheetExistSql(email, date));
		boolean checker = DBUtility.checkTimesheetExistSql(email, date);
		if (checker == true){
			status = 1;
			message = "Save Successfully";
			System.out.println("inside servlet: call update ~~~~");
			DBUtility.updateTimesheetSql(email, date, endDate, monR, monOT, tueR, tueOT, wedR, wedOT, thuR, thuOT,
					friR, friOT, satR, satOT, sunR, sunOT, approve, URL, submit, totalHr, regularHr, otHr, client);
			
		} else {
			status = 1;
			message = "Save Successfully";
//			String newDate = "";
//			String[] date_arr = date.split(" ");
//			for (int i = 1; i <4; i ++){
//				if (i != 3){
//					newDate += date_arr[i] + "-";
//				} else {
//					newDate += date_arr[i];
//				}
//			}
			System.out.println("inside servlet: call create!!! ");
			DBUtility.createTimesheetSql(email, date, endDate, monR, monOT, tueR, tueOT, wedR, wedOT, thuR, thuOT,
			friR, friOT, satR, satOT, sunR, sunOT, approve, URL, submit, totalHr, regularHr, otHr, client);
			
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
