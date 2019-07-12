package com.employee.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.Validation.ValidationUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class SessionLogIn
 */
@WebServlet("/SessionLogIn")
public class SessionLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionLogIn() {
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

		String userName = request.getParameter("userName");
		String role = request.getParameter("role");
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			System.out.println("after change=" + session);
			session.setAttribute("userName", userName);
			session.setAttribute("role", role);
			// response.sendRedirect("AdminMenu.html?email=" + userName);
			System.out.println("userName1=" + userName);

		} else {
			// System.out.println("usernametr="+session.getAttribute("username")+"role="+session.getAttribute("role"));
			System.out.println("session keep is " + session);
			System.out
					.println("userNametr=" + session.getAttribute("userName"));
			session.setAttribute("userName", userName);
			session.setAttribute("role", role);
			System.out.println("userName2=" + userName+" role "+role);
			

		}

		// String role= request.getParameter("role");//Get role
		// System.out.println("username"+username+"role"+role);

		// //HttpSession session =request.getSession(false);
		// System.out.println("before change="+session);
		// if(session==null){
		// session =request.getSession(true);
		// System.out.println("after change="+session);
		// session.setAttribute("userName", userName);
		// // session.setAttribute("role", role);
		// System.out.println("userName1="+userName);
		// }
		// else{
		// //
		// System.out.println("usernametr="+session.getAttribute("username")+"role="+session.getAttribute("role"));
		// System.out.println("userNametr="+session.getAttribute("userName"));
		// session.setAttribute("userName", userName);
		// // session.setAttribute("role", role);
		// System.out.println("userName2="+userName);
		//
		// }

		response.getWriter().write("Logged In success");
		response.getWriter().flush();
	}

}
