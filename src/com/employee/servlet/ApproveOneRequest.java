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

/**
 * Servlet implementation class approveOne
 */
@WebServlet("/ApproveOneRequest")
public class ApproveOneRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveOneRequest() {
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
		// Writer wr = response.getWriter();
System.out.println("inside approve servlet");
		// String role = request.getParameter("role");
		String userName = request.getParameter("userName");
		List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
System.out.println("username is " + userName);
		if (empNotApproval == null) {
			message = "No request at this time.";
		} else if (empNotApproval.size() == 0) {
			message = "No request at this time.";
		} else if (!ValidationUtility.checkNotNull(userName)) {
			// status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(userName)) {
			// status = 0;
			message = "Error: Please enter the valid email!";
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees,
				userName)) {
			// status = 0;
			message = "Error: Employee information not found!";
		} else {
			OperationUtility.printPartialInfo(empNotApproval);

			// empEmail = OperationUtility.askForInput();
			String role = OperationUtility.getRole(userName);
			String veri = null;

			if (ValidationUtility.checkEmailExisted(empNotApproval, userName)) {
				switch (role) {
				case "manager":
					veri = "1";
					break;
				case "developer":
					veri = "2";
					break;
				case "qa":
					veri = "3";
					break;
				case "training":
					veri = "4";
					break;
				case "admin":
					veri = "9";
					break;
				}
				DBUtility.approveEmpSql(role, userName, veri);
				message = "You have approved this request!";

			} else {
				System.out.println("Employee Information Not Found. \r\n");
			}
		}

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		JSONObject JObject = new JSONObject();
		//JObject.put("Status", status);
		JObject.put("Message", message);
		printout.write(JObject.toString());
	}

}
