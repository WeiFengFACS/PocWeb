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
 * Servlet implementation class ViewAllEmp
 */
@WebServlet("/ViewAllEmp")
public class ViewAllEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAllEmp() {
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
				if (!e.getVerification().equals("0")) {
					totalRecords++;
					EmployeeList.add(e);
				}
			}
		}
		// System.out.println("Approved emp list" + EmployeeList.size());
		double totalPage = Math.ceil(totalRecords / pageS);
		// double Page = totalRecords/pageSize;
		int startPage = (int) (page * pageS);
		System.out.println("total records are " + totalRecords);
		System.out.println("total page is " + totalPage);
		// System.out.println("page size is " + pageSize);
		// System.out.println("float page is " + Page);
		// System.out.println("check ceil " + Math.ceil(1.4));
		// limit $startPage,$pageSize
		// System.out.println("startPage is " + startPage);
		int pageSize = (int) pageS;
		Main.approvedEmployees.clear();
		DBUtility.getApprovedEmpSql(startPage, pageSize);

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
		// System.out.println(EmployeeByGroupID.toString());
		// System.out.print("array size is " + JArray.size());
		response.setContentType("application/json; charset=UTF-8");

		// response.setContentType("text/html; charset=UTF-8"); ///changed
		PrintWriter printout = response.getWriter();

		// printout.write(JArray.toString());

		if (getInfo.equals("1")) {
			printout.write(JArray.toString());
		} else {
			System.out.println(getInfo);
			JSONObject JObject = new JSONObject();
			// JObject.put("totalPage", "\"" + totalPage + "\"");
			// JObject.put("totalRecords", totalRecords);
			// JObject.put("page", page);
			JObject.put("totalPage", "7");
			JObject.put("totalRecords", "34");
			JObject.put("page", "0");
			printout.write(JObject.toString());
			System.out.println(JObject.toString());
		}

		//
		// Object[] obj=getJsonToArray(JArray.toString());
		// System.out.println("length is " + obj.length);
		// for(int i=0;i<obj.length;i++){
		// System.out.println(obj[i]);
		//
		// }
	}

}
