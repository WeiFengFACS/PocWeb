package com.employee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		  Cookie[] cookies = request.getCookies();
		  if(cookies!=null){
		   for(int i=0;i<cookies.length;i++)
		    if("usermail".equals(cookies[i].getName())){
		     cookies[i].setMaxAge(0);
		     cookies[i].setValue(null);
		     response.addCookie(request.getCookies()[i]);
		     
		    response.sendRedirect("Login.html");
		    }
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		  Cookie[] cookies = request.getCookies();
		  if(cookies!=null){
		   for(int i=0;i<cookies.length;i++)
		    if("usermail".equals(cookies[i].getName())){
		     cookies[i].setMaxAge(0);
		     cookies[i].setValue(null);
		     response.addCookie(request.getCookies()[i]);
		     
		    response.sendRedirect("Login.html");
		    }
		  }
	}

}
