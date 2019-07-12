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

import com.employee.empClass.Employee;
import com.employee.main.DBUtility;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class ApproveAll
 */
@WebServlet("/ApproveAll")
public class ApproveAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveAll() {
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
		
	//	List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
		DBUtility.approveEmpSql("manager", "1");
		DBUtility.approveEmpSql("developer", "2");
		DBUtility.approveEmpSql("qa", "3");
		DBUtility.approveEmpSql("training", "4");
		DBUtility.approveEmpSql("admin", "9");
		message = "You have approved all the requests!";
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printout = response.getWriter();
		JSONObject JObject = new JSONObject();
		JObject.put("Message", message);
		printout.write(JObject.toString());
	}

}
