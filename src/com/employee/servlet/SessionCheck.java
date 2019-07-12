package com.employee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCheck
 */
@WebServlet("/SessionCheck")
public class SessionCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionCheck() {
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
		String userName= request.getParameter("userName");
//		String role= request.getParameter("role");
System.out.println("username in check session is " + userName);		
	
		HttpSession session =request.getSession(false);
		System.out.println("session is " + session);	
		if(session==null){
			response.getWriter().write("false");
			response.getWriter().flush();
			
		}		
		else if(session.getAttribute("userName")==null){
			System.out.println("usernamenull="+session.getAttribute("userName"));
			response.getWriter().write("false");
			response.getWriter().flush();
		}
		else if(!((String) session.getAttribute("userName")).equals(userName)){
			System.out.println("usernamenotnull="+session.getAttribute("userName"));
			response.getWriter().write("false");
			response.getWriter().flush();
			
		}
		else {
			response.getWriter().write("true");
			response.getWriter().flush();
		}
//		else if(session.getAttribute("userName")==null || session.getAttribute("role")==null ){
//			System.out.println("usernamenull="+session.getAttribute("userName")+"role="+session.getAttribute("role"));
//			response.getWriter().write("false");
//			response.getWriter().flush();
//		}
//		else if(!((String) session.getAttribute("userName")).equals(userName) ||!((String) session.getAttribute("role")).equals(role) ){
//			System.out.println("usernamenotnull="+session.getAttribute("userName")+"role="+session.getAttribute("role"));
//			response.getWriter().write("false");
//			response.getWriter().flush();
//			
//		}
//		else {
//			response.getWriter().write("true");
//			response.getWriter().flush();
//		}
	}

}
