package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Employee;
import com.employee.main.DBUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;
import com.employee.menuMethod.CommonMenuMethod;

/**
 * Servlet implementation class DeleteFromGroup
 */
@WebServlet("/DeleteFromGroup")
public class DeleteFromGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	static int status = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFromGroup() {
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
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();

		String userName = request.getParameter("userName");
	//	String groupID = request.getParameter("groupID");

		// Employee e = OperationUtility.selectEmployeeById(userName);
		// int empGroupID = e.getGroupID();
		// String role = e.getRole();
		
		if (!ValidationUtility.checkNotNull(userName)) {
			status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(userName)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees, userName)) {
			status = 0;
			message = "Error: Employee information not found!";
		} else {
			Employee e = OperationUtility.selectEmployeeById(userName);
			int empGroupID = e.getGroupID();
			String role = e.getRole();
		
			if (empGroupID == 0) {
				status = 0;
				message = "Error: Employee is not in the group!";
			} else if (role.equals("manager")) {
				System.out.println("You cannot delete manager");
				status = 0;
				message = "Error: You cannot delete manager from group!";
			} else {
	
				DBUtility.updateGroupID(role, userName, 0);
				status = 1;
				message = "Delete employee from group successfully!";
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		JSONObject JObject = new JSONObject();
		JObject.put("Status", status);
		JObject.put("Message", message);
		printout.write(JObject.toString());

	}

}
