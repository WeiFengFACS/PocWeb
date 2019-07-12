package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.employee.empClass.Employee;
import com.employee.main.DBUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class ManagerViewAllEmp
 */
@WebServlet("/ManagerViewAllEmp")
public class ManagerViewAllEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerViewAllEmp() {
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
		System.out.println("Inside servlet");

		Main.Employees.clear();
		OperationUtility.getInfoFromDB();

		// double pageS = 10;

		JSONObject JEmpInfo = new JSONObject();
		JSONArray JArray = new JSONArray();

		String getPage = request.getParameter("page");
		String rowPerPage = request.getParameter("rowPerPage");
		String getInfo = request.getParameter("info");
		System.out.println("get info is " + getInfo);
		// System.out.println("get number is " + rowPerPage);
		int page = Integer.parseInt(getPage);
		int row = Integer.parseInt(rowPerPage);
		double pageS = row;
		System.out.println("page number is " + page);
		// int page = 0;
		List<Employee> EmployeeList = new ArrayList<Employee>();

		int len = Main.Employees.size();
		double totalRecords = 0;
		System.out.println("all emp list" + len);
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (!e.getVerification().equals("0") && !e.getRole().equals("admin")) {
					totalRecords++;
					EmployeeList.add(e);
				}
			}
		}
		double totalPage = Math.ceil(totalRecords / pageS);
		int startPage = (int) (page * pageS);
		System.out.println("total records are " + totalRecords);
		System.out.println("total page is " + totalPage);
		int pageSize = (int) pageS;
		
		Main.approvedEmployees.clear();
		DBUtility.getManagerApprovedEmpSql(startPage, pageSize);

		int approveLen = Main.approvedEmployees.size();
		// System.out.println("approven length is" + approveLen);

		if (approveLen != 0) {

			for (int i = 0; i < approveLen; i++) {
				Employee e = Main.approvedEmployees.get(i);

				JEmpInfo.put("role", e.getRole());
				JEmpInfo.put("userName", e.getEmail());
				// JEmpInfo.put("password", e.getPassword());
				JEmpInfo.put("fName", e.getFName());
				JEmpInfo.put("lName", e.getLName());
				JEmpInfo.put("empNum", e.getId());
				JEmpInfo.put("groupID", e.getGroupID());
				// JEmpInfo.put("totalPage", totalPage);
				// JEmpInfo.put("totalRecords", totalRecords);
				// JEmpInfo.put("page", page);

				JArray.add(JEmpInfo);

			}
		}
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter printout = response.getWriter();

		printout.write(JArray.toString());
	}

}
