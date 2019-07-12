package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.employee.Validation.ValidationUtility;
import com.employee.main.Main;
import com.employee.main.MySqlConnector;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String role = null;
	static String message = null;
	static int status = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		//Writer wr = response.getWriter();

		String email = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("username is " + email);
		//JSONObject JEmpInfo = new JSONObject();
		// String[] email = HttpServletRequest.getParameterValues("userName")�?
		// System.out.println("Get email inside servlet: " + email);
		// String email = responseText.userName;
		//
		// wr.write(" inside servlet email is: " + email + "\r\n");
		// wr.write("inside servlet password is: " + password + "\r\n");

		// System.out.println("email is " + email);
		// System.out.println("pass is " + password);
		// System.out.println("checker: " +
		// ValidationUtility.checkPassword(email, password));
		// String password = "0000";
		// System.out.println("email is: " + email);
		if (!ValidationUtility.checkNotNull(email)) {
			status = 0;
			message = "Error: Please enter the user name!";
		} else if (!ValidationUtility.checkEmail(email)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (!ValidationUtility.checkNotNull(password)) {
			status = 0;
			message = "Error: Please enter the password!";
		} else if (email.equals("admin@admin.com")) {
			System.out.println("Password is: " + password);
			System.out
					.println("check pass match: " + ("admin").equals("admin"));
			if (password.equals("admin")) {
System.out.println("!!!!!!!!!!!!!!!admin's password is " + password);				
				role = "admin";
				status = 1;
				message = "You are log in as Admin";
			} else {
				status = 0;
				message = "Error: Password incorrect, please try again!";
			}
		} else if (!ValidationUtility.checkEmailExisted(Main.Employees, email)) {
			status = 0;
			message = "Error: Email is not in the database, please sign up first!";
		} else {
			if (ValidationUtility.checkVerification(email)) {
				System.out.println("Email inside checker: " + email);
				System.out.println("Check password "
						+ ValidationUtility.checkPassword(email, password));
				if (ValidationUtility.checkPassword(email, password)) {
					role = OperationUtility.getRole(email);
					
					 switch (role) {
					 case "admin":
					 // Menu.adminMenu();
					 status = 1;
					 message = "admin";
					 break;
					 case "manager":
						 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
					 // Menu.managerMenu(email);
					 status = 1;
					 message = "manager";
					 break;
					 default:
					 // Menu.empMenu(role, email);
					 status = 1;
					 message = "employee";
					 break;
					 }
				
				} else {
					
					status = 0;
					message = "Error: Password incorrect, please try again!";
					System.out.println("status is " + status);
				}
			} else {
				// System.out
				// .println("Administrator hasn't approed you request. Please contact the administrator\r\n");
				status = 0;
				message = "Error: Admin hasn't approved you request.";
				// Menu.mainMenu();
			}
		}
		// System.out.println(message);

		// wr.write("User Name: " + message);
		// wr.write("message: " + message);
		// wr.write("Create Json here");

		// create JSON
		response.setContentType("application/json; charset=UTF-8");

		// response.setContentType("text/html; charset=UTF-8"); ///changed
		PrintWriter printout = response.getWriter();
//		if (status == 0) {
			JSONObject JObject = new JSONObject();
			JObject.put("ResponseChecker", status);
			JObject.put("Message", message);
			JObject.put("Role", role);
			System.out.println(JObject.toString());
			printout.write(JObject.toString());
			
			 Cookie userCookie = new Cookie("usermail", email);
		       userCookie.setMaxAge(60*60*24*30); 
		       response.addCookie(userCookie);
			
	}
	
	
	

}
