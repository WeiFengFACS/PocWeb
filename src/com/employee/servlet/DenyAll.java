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
 * Servlet implementation class DenyAll
 */
@WebServlet("/DenyAll")
public class DenyAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DenyAll() {
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
		
		DBUtility.deneyEmpSql("manager");
		DBUtility.deneyEmpSql("developer");
		DBUtility.deneyEmpSql("qa");
		DBUtility.deneyEmpSql("training");
		DBUtility.deneyEmpSql("admin");
		message = "You have denied all the requests!";
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		JSONObject JObject = new JSONObject();
		JObject.put("Message", message);
		printout.write(JObject.toString());
		
		
	}

}
