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
import com.employee.main.Main;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class ViewEmpByID
 */
@WebServlet("/ViewEmpByID")
public class ViewEmpByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String role = null;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmpByID() {
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
		JSONObject JEmpInfo = new JSONObject();
		
		if (!ValidationUtility.checkNotNull(userName)) {
			status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(userName)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees, userName)) {
			status = 0;
			message = "Error: Employee information not found!";
		}else {
			//role = OperationUtility.getRole(userName);
			Employee e = OperationUtility.selectEmployeeById(userName);
			status = 1;
			JEmpInfo.put("ResponseChecker", status);
			
			JEmpInfo.put("role", e.getRole());
			JEmpInfo.put("userName", e.getEmail());
			//JEmpInfo.put("password", e.getPassword());
			JEmpInfo.put("fName", e.getFName());
			JEmpInfo.put("lName", e.getLName());
			JEmpInfo.put("empNum", e.getId());
			JEmpInfo.put("gender", e.getGender());
			JEmpInfo.put("age", e.getAge());
			JEmpInfo.put("phoneNum", e.getPhoneNum());
			JEmpInfo.put("address", e.getAddress());
			JEmpInfo.put("groupID", e.getGroupID());
			
			
			//System.out.println(e.getImage());
		}
		
		response.setContentType("application/json; charset=UTF-8");

		// response.setContentType("text/html; charset=UTF-8"); ///changed
		PrintWriter printout = response.getWriter();
		if (status == 0) {
			JSONObject JObject = new JSONObject();
			JObject.put("ResponseChecker", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
		} else {
			printout.write(JEmpInfo.toString());
		}
		
		
		
		
	}

}
