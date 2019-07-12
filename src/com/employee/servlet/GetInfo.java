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
 * Servlet implementation class GetInfo
 */
@WebServlet("/GetInfo")
public class GetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfo() {
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

				//double pageS = 10;
		JSONObject JObject = new JSONObject();
		JSONArray JArray = new JSONArray();
				String getPage = request.getParameter("page");
				String rowPerPage = request.getParameter("rowPerPage");
				String getInfo = request.getParameter("info");
				System.out.println("get info is " + getInfo);
		//System.out.println("get number is " + rowPerPage);		
				int page = Integer.parseInt(getPage);
				int row = Integer.parseInt(rowPerPage);
				double pageS = row;
		//System.out.println("page number is " + page);		
//				int page = 0;
				List<Employee> EmployeeList = new ArrayList<Employee>();
				
				int len = Main.Employees.size();
				double totalRecords = 0;
		System.out.println("all emp list" + len);
				switch(getInfo){
				case "viewAll" :
					if (len != 0) {
						for (int i = 0; i < len; i++) {
							Employee e = Main.Employees.get(i);
							if (!e.getVerification().equals("0")) {
								totalRecords ++;
								EmployeeList.add(e);
								
							}
						}
					}; break;
				case "requestApproval":
					if (len != 0) {
						for (int i = 0; i < len; i++) {
							Employee e = Main.Employees.get(i);
							if (e.getVerification().equals("0")) {
								totalRecords ++;
								EmployeeList.add(e);
							}
						}
					}; break;
//				case: 
				}
				
		//System.out.println("Approved emp list" + EmployeeList.size());		
				double totalPage = Math.ceil(totalRecords/pageS);
				int tPage = (int)totalPage;
				for (int i = 1; i <= tPage; i++) {
					JObject.put("pageID", i);
					JArray.add(JObject);
				}
				
				
				response.setContentType("application/json; charset=UTF-8");

				PrintWriter printout = response.getWriter();

				
					System.out.println(getInfo);
					printout.write(JArray.toString());
//					JObject.put("totalPage", totalPage);
//					JObject.put("totalRecords", totalRecords);
//					JObject.put("page", page);
//					printout.write(JObject.toString());
					System.out.println(JArray.toString());
				
	}

}
