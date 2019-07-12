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
 * Servlet implementation class GetAvailableEmp
 */
@WebServlet("/GetAvailableEmp")
public class GetAvailableEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAvailableEmp() {
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
		System.out.println("Inside servlet view available emp");

		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		System.out.println("total employee size is " + Main.Employees.size());
		// double pageS = 10;

		JSONObject JEmpInfo = new JSONObject();
		JSONArray JArray = new JSONArray();
		JSONObject JObject = new JSONObject();

		PrintWriter printout = response.getWriter();

		String getPage = request.getParameter("page");
		String rowPerPage = request.getParameter("rowPerPage");
		String operation = request.getParameter("operation");

		int page = Integer.parseInt(getPage);
		int row = Integer.parseInt(rowPerPage);
		double pageS = row;

		List<Employee> EmployeeList = new ArrayList<Employee>();
		int len = Main.Employees.size();
		double totalRecords = 0;

		// if (groupID != 0) {
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (e.getGroupID() == 0 & !e.getVerification().equals("0")
						& !e.getRole().equals("admin")
						& !e.getRole().equals("manager")) {
					totalRecords++;
					EmployeeList.add(e);
				}
			}
		}
		System.out.println("!!!!!!!!!!!!!!!total++++++ " + totalRecords);
		int totalRecord = (int) totalRecords;

		if (operation.equals("getInfo")) {
			double totalPage = Math.ceil(totalRecords / pageS);
			int startPage = (int) (page * pageS);
		
			System.out.println("!!!!!!!!!!!!!!!total record send back"
					+ totalRecord);
			int pageSize = (int) pageS;
			Main.approvedEmployees.clear();
			
			DBUtility.getAvailableEmpSql(startPage, pageSize);
			int approveLen = Main.approvedEmployees.size();
			System.out.println("approven length is" + approveLen);

			if (approveLen != 0) {

				for (int i = 0; i < approveLen; i++) {
					Employee e = Main.approvedEmployees.get(i);

					JEmpInfo.put("role", e.getRole());
					JEmpInfo.put("userName", e.getEmail());
					JEmpInfo.put("fName", e.getFName());
					JEmpInfo.put("lName", e.getLName());
					JEmpInfo.put("empNum", e.getId());
					JEmpInfo.put("gender", e.getGender());
					JEmpInfo.put("age", e.getAge());
					JEmpInfo.put("phoneNum", e.getPhoneNum());
					JEmpInfo.put("address", e.getAddress());

					JArray.add(JEmpInfo);

				}
			} else {
				JEmpInfo.put("role", "check");
				JEmpInfo.put("userName", "check");
				JEmpInfo.put("fName", "check");
				JEmpInfo.put("lName", "check");
				JEmpInfo.put("empNum", "check");
				JEmpInfo.put("gender", "check");
				JEmpInfo.put("age", "check");
				JEmpInfo.put("phoneNum", "check");
				JEmpInfo.put("address", "check");

				JArray.add(JEmpInfo);

			}
		}
		System.out.println(operation);
		if (operation.equals("getNum")) {

			JObject.put("totalRecords", totalRecord);
			System.out.println("AFTER PUT DATA");
			printout.write(JObject.toString());
			System.out.println(JObject.toString());
		} else {
			printout.write(JArray.toString());
			System.out.println(JArray.size());
		}
		// } else {
		// JObject.put("totalRecords", 0);
		// printout.write(JObject.toString());
		// }
		response.setContentType("application/json; charset=UTF-8");

		// printout.write(JArray.toString());
		

	}

}
