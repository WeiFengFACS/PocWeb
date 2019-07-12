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
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
	int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroup() {
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
		//Writer wr = response.getWriter();

		String groupID = request.getParameter("groupID");
		String email = request.getParameter("userName");
		//JSONObject JEmpInfo = new JSONObject();
		
		System.out.println(groupID + " + " + email);
		
		Employee e = OperationUtility.selectEmployeeById(email);
		int gID = e.getGroupID();
		if (gID == 0){
		
		
		if (!ValidationUtility.checkNotNull(groupID)) {
			status = 0;
			message = "Error: Please enter the group ID!";
		} else if (ValidationUtility.checkGroupIDExisted(Integer.parseInt(groupID))){
			status = 0;
			message = "Error: This group ID has already assigned to other manager!";
		} else {
			DBUtility.updateGroupID("manager", email, Integer.parseInt(groupID));
			status = 1;
			message = "Create group successfully!";
		}
		
		
	} else {
		message = "Error: You have already in a group. Create group failed !";
	}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		
			JSONObject JObject = new JSONObject();
			JObject.put("Status", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
	}
}
