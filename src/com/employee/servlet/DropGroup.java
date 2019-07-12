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
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class DropGroup
 */
@WebServlet("/DropGroup")
public class DropGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropGroup() {
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
		//String groupID = request.getParameter("groupID");
		String email = request.getParameter("userName");
		
		Employee e = OperationUtility.selectEmployeeById(email);
		int groupID = e.getGroupID();
		if (groupID == 0) {
			message = "Error: You haven't in any group. Drop group failed!";
		} else {
				DBUtility.dropGroupID("manager", groupID);
				DBUtility.dropGroupID("developer", groupID);
				DBUtility.dropGroupID("qa", groupID);
				DBUtility.dropGroupID("training", groupID);
				//DBUtility.dropGroupID("admin", groupID);
				message = "Drop group successfully!";
				
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		
			JSONObject JObject = new JSONObject();
//			JObject.put("ResponseChecker", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
	}

}
