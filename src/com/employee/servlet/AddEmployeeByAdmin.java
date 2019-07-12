package com.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Developer;
import com.employee.empClass.Employee;
import com.employee.empClass.Manager;
import com.employee.empClass.QA;

import com.employee.main.DBUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

/**
 * Servlet implementation class AddEmployeeByAdmin
 */
@WebServlet("/AddEmployeeByAdmin")
public class AddEmployeeByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String role = null;
	static String message = null;
	static int status = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeByAdmin() {
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
		System.out.println("inside add emp by admin");
		
		String email = request.getParameter("userName");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String role = request.getParameter("role");
		//String empNum = request.getParameter("empNum");
	
		String gender = request.getParameter("gender");
		String ageS = request.getParameter("age");
		
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address");
		
		String verification = "0";
		int groupID = 0;

		String add1 = request.getParameter("add1");
		String add2 = request.getParameter("add2");
		
		Random random = new Random();
		int empID = 0;
		do{
			empID = random.nextInt(9000);
		} while (ValidationUtility.checkEmpIDExisted(Main.Employees, empID));
//		String num = null;
//		String experiences = null;
//		int numOfEmp = 0;
//		int exp = 0;
//		String tool = null;
//		String projectName = null;
//		int numOfStudent = 0;
		// String

		if (!ValidationUtility.checkNotNull(email)) {
			status = 0;
			message = "Error: Please enter the user name/email address!";
		} else if (!ValidationUtility.checkEmail(email)) {
			status = 0;
			message = "Error: Please enter the valid email!";
		} else if (ValidationUtility.checkEmailExisted(Main.Employees, email)) {
			status = 0;
			message = "Error: Email is already in the database!";
		} else if (!ValidationUtility.checkNotNull(password)) {
			status = 0;
			message = "Error: Please enter the password!";
		} else if (!ValidationUtility.checkNotNull(fName)) {
			status = 0;
			message = "Error: Please enter the first name!";
		} else if (!ValidationUtility.checkCharacter(fName)) {
			status = 0;
			message = "Please enter valid first name";
		} else if (!ValidationUtility.checkNotNull(lName)) {
			status = 0;
			message = "Error: Please enter the last name!";
		} else if (!ValidationUtility.checkCharacter(lName)) {
			status = 0;
			message = "Please enter valid last name";
		} else if (!ValidationUtility.checkNotNull(role)) {
			status = 0;
			message = "Please enter the role: (A/M/D/Q/T)";
		} else if (ValidationUtility.checkRoleInput(role).equals("null")) {
			status = 0;
			message = "Please enter valid role: (A/M/D/Q/T)";
		} 
//		else if (!ValidationUtility.checkNotNull(empNum)) {
//			status = 0;
//			message = "Error: Please enter the employee number!";
//		} else if (!ValidationUtility.checkInteger(empNum)) {
//			status = 0;
//			message = "Error: Please enter valid employee number!";
//		} else if (ValidationUtility.checkEmpIDExisted(Main.Employees, Integer.parseInt(empNum))) {
//			status = 0;
//			message = "Error: Employee number is already in database!";
//		} 
		else if (!ValidationUtility.checkNotNull(gender)) {
			status = 0;
			message = "Error: Please enter the gender!";
		} else if (!ValidationUtility.checkGender(gender)) {
			status = 0;
			message = "Error: Please enter valid gender: (Female/Male)!";
		} else if (!ValidationUtility.checkNotNull(ageS)) {
			status = 0;
			message = "Error: Please enter the age!";
		} else if (!ValidationUtility.checkInputRange(ageS) || !ValidationUtility.checkInteger(ageS)) {
			status = 0;
			message = "Error: Please enter valid age!";
		} else if (!ValidationUtility.checkNotNull(phoneNum)) {
			status = 0;
			message = "Error: Please enter the phone number!";
		} else if (!ValidationUtility.checkPhoneNum(phoneNum)) {
			status = 0;
			message = "Error: Please enter valid phone number!";
		} else if (!ValidationUtility.checkNotNull(address)) {
			status = 0;
			message = "Error: Please enter the address!";
		} else {
			//int empID = Integer.parseInt(empNum);
			int age = Integer.parseInt(ageS);
			role = ValidationUtility.checkRoleInput(role).toLowerCase();//get full role name
			fName = fName.toLowerCase();
			lName = lName.toLowerCase();
			gender = gender.toLowerCase();
			address = address.toLowerCase();
			
			
			switch (role) {
			case "admin":
				Employee emp = new Employee(role, empID, password, fName,
						lName, gender, age, email, phoneNum, address,
						verification, groupID);
				DBUtility.addApprovedEmpSql(emp);
				status = 1;
				message = "Register Successfully! \r\nEmployee Number is: " + empID;
				break;
				
			case "manager":
				if (!ValidationUtility.checkNotNull(add1)) {
					status = 0;
					message = "Error: Please enter the department name!";
				} else if (!ValidationUtility.checkNotNull(add2)) {
					status = 0;
					message = "Error: Please enter  number of employees!";
				} else if (!ValidationUtility.checkInteger(add2)) {
					status = 0;
					message = "Error: Please enter valid number!";
				} else {
					add1 = add1.toLowerCase();
					add2 = add2.toLowerCase();
					int numOfEmp = Integer.parseInt(add2);
					Manager man = new Manager(role, empID, password, fName, lName,
							gender, age, email, phoneNum, address, verification,
							groupID, add1, numOfEmp);
					DBUtility.addApprovedEmpSql(man);
					status = 1;
					message = "Register Successfully! \r\nEmployee Number is: " + empID;
				}
				break;
				
			case "developer":
				if (!ValidationUtility.checkNotNull(add1)) {
					status = 0;
					message = "Error: Please enter the technology!";
				} else if (!ValidationUtility.checkNotNull(add2)) {
					status = 0;
					message = "Error: Please enter years of experiences!";
				} else if (!ValidationUtility.checkInputRange(add2)
						|| !ValidationUtility.checkInteger(add2)) {
					status = 0;
					message = "Error: Please enter valid years!";
				} else {
					add1 = add1.toLowerCase();
					add2 = add2.toLowerCase();
					int exp = Integer.parseInt(add2);
					Developer dev = new Developer(role, empID, password, fName,
							lName, gender, age, email, phoneNum, address,
							verification, groupID, add1, exp);
					DBUtility.addApprovedEmpSql(dev);
					status = 1;
					message = "Register Successfully! \r\nEmployee Number is: " + empID;
				}
				break;
				
			case "qa":
				if (!ValidationUtility.checkNotNull(add1)) {
					status = 0;
					message = "Error: Please enter the tool name!";
				} else if (!ValidationUtility.checkNotNull(add2)) {
					status = 0;
					message = "Error: Please enter project name!";
				} else {
					add1 = add1.toLowerCase();
					add2 = add2.toLowerCase();
					QA qa = new QA(role, empID, password, fName, lName, gender,
							age, email, phoneNum, address, verification, groupID,
							add1, add2);
					DBUtility.addApprovedEmpSql(qa);
					status = 1;
					message = "Register Successfully! \r\nEmployee Number is: " + empID;
				}
				break;
				
			
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter printout = response.getWriter();
			JSONObject JObject = new JSONObject();
			JObject.put("ResponseChecker", status);
			JObject.put("Message", message);
			printout.write(JObject.toString());
		
	
	}

}
