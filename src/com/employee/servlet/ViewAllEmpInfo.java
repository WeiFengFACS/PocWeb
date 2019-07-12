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
import com.employee.main.Main;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class ViewAllEmpInfo
 */
@WebServlet("/ViewAllEmpInfo")
public class ViewAllEmpInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAllEmpInfo() {
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
		String operation = request.getParameter("operation");

		int len = Main.Employees.size();
		int totalRecords = 0;
		int totalRequestRecords = 0;
		int totalEmpManagerView = 0;
		int totalAvailableEmp = 0;
		if (operation.equals("viewAll") || operation.equals("delete")
				|| operation.equals("request")) {
			if (len != 0) {

				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (!e.getVerification().equals("0")) {
						totalRecords++;
					} else {
						totalRequestRecords++;
					}
				}
			}
		} else {
			if (len != 0) {

				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (!e.getVerification().equals("0") && !e.getRole().equals("admin")) {
						totalEmpManagerView++;
					} else if(e.getGroupID() == 0) {
						totalAvailableEmp++;
					}
				}
			}

		}

		JSONObject JObject = new JSONObject();
		if (operation.equals("viewAll")) {
			JObject.put("totalRecords", totalRecords);
		} else if (operation.equals("delete")) {
			JObject.put("totalRecords", totalRecords);
		} else if (operation.equals("request")) {
			JObject.put("totalRecords", totalRequestRecords);
		} else if (operation.equals("managerView")) {
			JObject.put("totalRecords", totalEmpManagerView);
		} else if (operation.equals("availableEmp")) {
			JObject.put("totalRecords", totalAvailableEmp);
		}

		response.setContentType("application/json; charset=UTF-8");

		PrintWriter printout = response.getWriter();

		printout.write(JObject.toString());
		System.out.println(JObject.toString());

	}

}

// package com.peterson.employee.servlet;
//
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import net.sf.json.JSONArray;
// import net.sf.json.JSONObject;
//
// import com.peterson.employee.empClass.Employee;
// import com.peterson.employee.main.Main;
// import com.peterson.employee.main.OperationUtility;
//
// /**
// * Servlet implementation class ViewAllEmpInfo
// */
// @WebServlet("/ViewAllEmpInfo")
// public class ViewAllEmpInfo extends HttpServlet {
// private static final long serialVersionUID = 1L;
//
// /**
// * @see HttpServlet#HttpServlet()
// */
// public ViewAllEmpInfo() {
// super();
// // TODO Auto-generated constructor stub
// }
//
// /**
// * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
// response)
// */
// protected void doGet(HttpServletRequest request, HttpServletResponse
// response) throws ServletException, IOException {
// // TODO Auto-generated method stub
// }
//
// /**
// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
// response)
// */
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response) throws ServletException, IOException {
// // TODO Auto-generated method stub
//
// Main.Employees.clear();
// OperationUtility.getInfoFromDB();
//
// JSONObject JEmpInfo = new JSONObject();
// JSONArray JArray = new JSONArray();
// //
// // JSONArray list = new JSONArray();
// // list.add("foo");
// // list.add(new Integer(100));
// // list.add(new Double(1000.21));
// // list.add(new Boolean(true));
// // list.add(null);
// // System.out.print(list);
//
//
// int len = Main.Employees.size();
// if (len != 0) {
//
// for (int i = 0; i < len; i++) {
// Employee e = Main.Employees.get(i);
// JEmpInfo.put("role", e.getRole());
// JEmpInfo.put("userName", e.getEmail());
// //JEmpInfo.put("password", e.getPassword());
// JEmpInfo.put("fName", e.getFName());
// JEmpInfo.put("lName", e.getLName());
// JEmpInfo.put("empNum", e.getId());
// JEmpInfo.put("gender", e.getGender());
// JEmpInfo.put("age", e.getAge());
// JEmpInfo.put("phoneNum", e.getPhoneNum());
// JEmpInfo.put("address", e.getAddress());
// JEmpInfo.put("groupID", e.getGroupID());
// JEmpInfo.put("verification", e.getVerification());
//
// JArray.add(JEmpInfo);
// }
// }
// // System.out.println(EmployeeByGroupID.toString());
// //System.out.print(JArray);
// response.setContentType("application/json; charset=UTF-8");
//
// // response.setContentType("text/html; charset=UTF-8"); ///changed
// PrintWriter printout = response.getWriter();
//
// printout.write(JArray.toString());
//
// //
// // Object[] obj=getJsonToArray(JArray.toString());
// // System.out.println("length is " + obj.length);
// // for(int i=0;i<obj.length;i++){
// // System.out.println(obj[i]);
// //
// // }
// }
//
// //
// // public static Object[] getJsonToArray(String str) {
// // JSONArray jsonArray = JSONArray.fromObject(str);
// // return jsonArray.toArray();
// // }
//
// }
//
//
