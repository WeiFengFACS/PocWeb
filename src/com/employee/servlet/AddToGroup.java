package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class AddToGroup
 */
@WebServlet("/AddToGroup")
public class AddToGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToGroup() {
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
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		//managerEmail
		String userName = request.getParameter("userName");
		String managerEmail = request.getParameter("managerEmail");
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
		} else if (!ValidationUtility.checkNotNull(managerEmail)) {
			status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(managerEmail)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees, userName)) {
			status = 0;
			message = "Error: Employee information not found!";
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees, managerEmail)) {
			status = 0;
			message = "Error: Manager information not found!";
		} else {
			String role = OperationUtility.getRole(userName);
			
			Employee man = OperationUtility.selectEmployeeById(managerEmail);
			int managerGroupID = man.getGroupID();
			
			Employee emp = OperationUtility.selectEmployeeById(userName);
			int empGroupID = emp.getGroupID();
			//String role = e.getRole();
		
			if (managerGroupID == 0) {
				status = 0;
				message = "Error: You have not manage any group, add employee failed!";
			} else if (role.equals("manager")) {
				status = 0;
				//System.out.println("You cannot delete manager");
				message = "Error: You cannot add another manager to group!";
			} else if (role.equals("admin")) {
				status = 0;
				//System.out.println("You cannot delete manager");
				message = "Error: You cannot add admin to group!";
			} else if (empGroupID != 0) {
				status = 0;
				message = "Error: Employee is already in another group!";
			} else {
				DBUtility.updateGroupID(role, userName, managerGroupID);
				//DBUtility.updateGroupID(role, userName, 0);
				status = 1;
				message = "Add employee to group successfully!";
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
