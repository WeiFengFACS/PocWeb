package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IsLogedin
 */
public class IsLogedin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsLogedin() {
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
		
		response.setContentType("text/html");
		   PrintWriter pw = response.getWriter();
		
		
		Cookie[] cookies = request.getCookies();
		  if(cookies!=null){
		   for(int i=0;i<cookies.length;i++){
		    if("usermail".equals(cookies[i].getName()))
		     if(cookies[i].getValue()!=null && !cookies[i].getValue().isEmpty()){
		    	 pw.write("1");return;
		     }
		  }
		   pw.write("0");return;
	}
	}
}
