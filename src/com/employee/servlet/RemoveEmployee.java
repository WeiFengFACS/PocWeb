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
 * Servlet implementation class RemoveEmployee
 */
@WebServlet("/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String message = null;
       int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveEmployee() {
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

		String userName = request.getParameter("userName");

		 
	

		if (!ValidationUtility.checkNotNull(userName)) {
			status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(userName)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (ValidationUtility.checkEmailExisted(Main.Employees, userName)) {
			// remove emp info;
			String role = OperationUtility.getRole(userName);
			
			Employee emp = OperationUtility.selectEmployeeById(userName);
			int groupID = emp.getGroupID();
			
			if(role.equals("manager") && groupID != 0){
				status = 0;
				message = "You cannot delete a manager who manages the group";
			} else {
				DBUtility.deleteEmpSql(role, userName); // Delete from DB;
				status = 1;
				message = "Delete Successfully!";
			}
		} else {
			status = 0;
			message = "Employee Information Not Found.";
			// Menu.managerMenu();
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		
			JSONObject JObject = new JSONObject();
			JObject.put("Status", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
		
	}

}
