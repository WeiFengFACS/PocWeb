package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.main.DBUtility;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateURL
 */
@WebServlet("/UpdateURL")
public class UpdateURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateURL() {
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
		String date = request.getParameter("date");
		String URL = request.getParameter("URL");
		
		DBUtility.updateURL(userName, date, URL);
		status = 1;
		message = "update Successfully";
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		JSONObject JObject = new JSONObject();
		JObject.put("Status", status);
		JObject.put("Message", message);
		printout.write(JObject.toString());
	}

}
