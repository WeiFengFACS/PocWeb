package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class GetManagerByGroup
 */
@WebServlet("/GetManagerByGroup")
public class GetManagerByGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetManagerByGroup() {
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
		
		JSONObject JEmpInfo = new JSONObject();
		JSONArray JArray = new JSONArray();
		
		int len = Main.Employees.size();
		
		if (len != 0) {

			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if(e.getRole().equals("manager") && e.getGroupID() != 0){
					JEmpInfo.put("role", e.getRole());
					JEmpInfo.put("userName", e.getEmail());
					// JEmpInfo.put("password", e.getPassword());
					JEmpInfo.put("fName", e.getFName());
					JEmpInfo.put("lName", e.getLName());
					JEmpInfo.put("empNum", e.getId());
					JEmpInfo.put("groupID", e.getGroupID());
					JArray.add(JEmpInfo);
				}
			}
		}
		// System.out.println(EmployeeByGroupID.toString());
//System.out.print("array size is " + JArray.size());
		response.setContentType("application/json; charset=UTF-8");

		// response.setContentType("text/html; charset=UTF-8"); ///changed
		PrintWriter printout = response.getWriter();

		//printout.write(JArray.toString());

			printout.write(JArray.toString());
		
	}

}
