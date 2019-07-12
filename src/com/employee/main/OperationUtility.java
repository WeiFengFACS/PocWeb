package com.employee.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.employee.Validation.UpdateFieldValidation;
import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Developer;
import com.employee.empClass.Employee;
import com.employee.empClass.Manager;
import com.employee.empClass.QA;


public class OperationUtility {  ////admin checked

	static String str = null;
	static String field = null;
	static String role = null;
	//static int empID = 0;
	static String email = null;

	public static void getInfoFromDB() {
		DBUtility.getEmpSql("manager");
		DBUtility.getEmpSql("developer");
		DBUtility.getEmpSql("qa");
		//DBUtility.getEmpSql("training");
		DBUtility.getEmpSql("admin");
	}

//	public static void login() {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//
//		System.out.println("------Login Page------");
//		do {
//			do {
//				System.out.println("Please Enter the Employee ID");
//				System.out
//						.println("(4-digits ID: Manager starts with 1, "
//								+ "Developer starts with 2, QA starts with 3, " +
//								"Trainer starts with 4, admin starts with 5)");
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkIsID(str));
//			empID = Integer.parseInt(str);
//			if (!ValidationUtility.checkEmpIDExisted(Main.Employees, empID)) {
//				System.out.println("Employee information not found");
//			}
//		} while (!ValidationUtility.checkEmpIDExisted(Main.Employees, empID));
//
//		String password = "0000";
//
//		if (empID == 9999) {
//			do {
//				do {
//					System.out.println("Please Enter the Password: ");
//					password = OperationUtility.readString("");
//				} while (!ValidationUtility.checkNotNull(password));
//				if (!password.equals("1111")) {
//					System.out.println("Password incorrect, please try again!");
//				}
//			} while (!password.equals("1111"));
//
//			Menu.adminMenu();
//		} else {
//			role = OperationUtility.getRole(str);
//
//			if (ValidationUtility.checkVerification(empID)) {
//				do {
//					do {
//						System.out.println("Please Enter the Password: ");
//						password = OperationUtility.readString("");
//					} while (!ValidationUtility.checkNotNull(password));
//				} while (!ValidationUtility.checkPassword(empID, password));
//				switch (role) {
//				case "manager":
//					Menu.managerMenu(empID);
//					break;
//				default:
//					Menu.empMenu(role, empID);
//					break;
//				}
//			} else {
//				System.out
//						.println("Administrator hasn't approed you request. Please contact the administrator\r\n");
//				Menu.mainMenu();
//			}
//		}
//
//	}

	public static void login() {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();

		System.out.println("------Login Page------");
		 

		 do {
			 email = askForInput();
			 if (!ValidationUtility.checkEmailExisted(Main.Employees, email)) {
					System.out.println("Employee information not found");
				}
			} while (!ValidationUtility.checkEmailExisted(Main.Employees, email));
	
		 
		String password = "0000";
//System.out.println("email is: " + email);
		if (email.equals("admin@admin.com")) {
			do {
				do {
					System.out.println("Please Enter the Password: ");
					password = OperationUtility.readString("");
				} while (!ValidationUtility.checkNotNull(password));
				if (!password.equals("admin")) {
					System.out.println("Password incorrect, please try again!");
				}
			} while (!password.equals("admin"));

			Menu.adminMenu();
		} else {
			//role = OperationUtility.getRole(str);

			if (ValidationUtility.checkVerification(email)) { /////////!!!!!!!!!!!!!
				do {
					do {
						System.out.println("Please Enter the Password: ");
						password = OperationUtility.readString("");
					} while (!ValidationUtility.checkNotNull(password));
				} while (!ValidationUtility.checkPassword(email, password));
				role = getRole(email);
				switch (role) {
				case "admin": 
					Menu.adminMenu();
					break;
				case "manager":
					Menu.managerMenu(email);
					break;
				default:
					Menu.empMenu(role, email);
					break;
				}
			} else {
				System.out
						.println("Administrator hasn't approed you request. Please contact the administrator\r\n");
				Menu.mainMenu();
			}
		}

	}
	
//	public static void addEmpDetail(String role, int empID) {
//
//		String password = null;
//		String fName = null;
//		String lName = null;
//		String gender = null;
//		String ageS = null;
//		int age = 0;
//		String email = null;
//		String phoneNum = null;
//		String address = null;
//		String verification = "false";
//		int groupID = 0;
//
//		String dept = null;
//		String tech = null;
//		String num = null;
//		String experiences = null;
//		int numOfEmp = 0;
//		int exp = 0;
//		String tool = null;
//		String projectName = null;
//		int numOfStudent = 0;
//		// String
//
//		do {
//			System.out.println("Please Enter Your Password: ");
//			password = OperationUtility.readString("");
//			// System.out.println(checker);
//		} while (!ValidationUtility.checkNotNull(password));
//
//		do {
//			System.out.println("Please Enter Your First Name: ");
//			fName = OperationUtility.readString("");
//			// System.out.println(checker);
//		} while (!ValidationUtility.checkCharacter(fName));
//
//		do {
//			System.out.println("Please Enter Your Last Name: ");
//			lName = OperationUtility.readString("");
//		} while (!ValidationUtility.checkCharacter(lName));
//		do {
//			System.out.println("Please Enter Gender: (Female/Male)");
//			gender = OperationUtility.readString("");
//			// System.out.println(checker);
//		} while (!ValidationUtility.checkGender(gender));
//
//		do {
//			System.out.println("Please Enter Your Age: ");
//			ageS = OperationUtility.readString("");
//
//		} while (!ValidationUtility.checkInputRange(ageS)
//				|| !ValidationUtility.checkInteger(ageS));
//		age = Integer.parseInt(ageS);
//		do {
//			do {
//				System.out.println("Please Enter Your Email Address: ");
//				email = OperationUtility.readString("");
//			} while (!ValidationUtility.checkEmail(email));
//		} while (ValidationUtility.checkEmailExisted(Main.Employees, email));
//		do {
//			System.out.println("Please Enter Your Phone Number: ");
//			phoneNum = OperationUtility.readString("");
//			// System.out.println(checker);
//		} while (!ValidationUtility.checkPhoneNum(phoneNum));
//
//		do {
//			System.out.println("Please Enter Your Address: ");
//			address = OperationUtility.readString("");
//		} while (!ValidationUtility.checkNotNull(address));
//		// age= Integer.parseInt(ageS);
//		// DBUtility.AddEmp(empID, fName, lName);
//
//		// System.out.println(role);
//		// char
//		switch (role) {
//		case "manager":
//			do {
//				System.out.println("Please Enter the Department Name: ");
//				dept = OperationUtility.readString("");
//			} while (!ValidationUtility.checkNotNull(dept));
//			do {
//				System.out.println("Please Enter the Number of Employees: ");
//				num = OperationUtility.readString("");
//				// System.out.println(num);
//			} while (!ValidationUtility.checkInteger(num));
//			numOfEmp = Integer.parseInt(num);
//			break;
//		case "developer":
//			do {
//				System.out.println("Please Enter the Technology: ");
//				tech = OperationUtility.readString("");
//				// System.out.println(tech);
//			} while (!ValidationUtility.checkNotNull(tech));
//			do {
//				System.out.println("Please Enter the Years of Experiences: ");
//				experiences = OperationUtility.readString("");
//				// System.out.println("Check outside vali"
//				// +ValidationUtility.checkInputRange(experiences));
//			} while (!ValidationUtility.checkInputRange(experiences)
//					|| !ValidationUtility.checkInteger(experiences));
//			exp = Integer.parseInt(experiences);
//			break;
//		case "qa":
//			do {
//				System.out.println("Please Enter the Tool Name: ");
//				tool = OperationUtility.readString("");
//			} while (!ValidationUtility.checkNotNull(tool));
//			do {
//				System.out.println("Please Enter the Current Project Name: ");
//				projectName = OperationUtility.readString("");
//			} while (!ValidationUtility.checkNotNull(projectName));
//			break;
//
//		case "training":
//			do {
//				System.out.println("Please Enter the Project Name: ");
//				projectName = OperationUtility.readString("");
//			} while (!ValidationUtility.checkNotNull(projectName));
//			do {
//				System.out.println("Please Enter the Number of Students: ");
//				num = OperationUtility.readString("");
//			} while (!ValidationUtility.checkInteger(num));
//			numOfStudent = Integer.parseInt(num);
//			break;
//		}
//		System.out.println("---Employee Information---");
//		System.out.println("Role: " + role);
//		System.out.println("Employee ID: " + empID);
//		System.out.println("Password: " + password);
//		System.out.println("First Name: " + fName);
//		System.out.println("Last Name: " + lName);
//		System.out.println("Gender: " + gender);
//		System.out.println("Age: " + age);
//		System.out.println("Email: " + email);
//		System.out.println("Phone Number: " + phoneNum);
//		System.out.println("Address: " + address);
//
//		switch (role) {
//		case "manager":
//			System.out.println("Department Name: " + dept);
//			System.out.println("Number of Employees: " + num);
//			break;
//
//		case "developer":
//			System.out.println("Technology: " + tech);
//			System.out.println("Experiences: " + exp + "\r\n");
//			break;
//
//		case "qa":
//			System.out.println("Tool Name: " + tool);
//			System.out.println("Current Project Name: " + projectName + "\r\n");
//			break;
//
//		case "training":
//
//			System.out.println("Project Name: " + projectName);
//			System.out.println("Number of Students: " + num + "\r\n");
//			break;
//		}
//
//		if (ValidationUtility.checkSaved()) {
//			switch (role) {
//			
//			case "admin":
//				Employee emp = new Employee(role, empID, password, fName,
//						lName, gender, age, email, phoneNum, address,
//						verification, groupID);
//				Main.Employees.add(emp);
//				// DBUtility.addManagerSql(man);
//				DBUtility.addEmpSql(emp);
//				//SendMail(email);
//				break;
//				
//			case "manager":
//				Manager man = new Manager(role, empID, password, fName, lName,
//						gender, age, email, phoneNum, address, verification,
//						groupID, dept, numOfEmp);
//				Main.Employees.add(man);
//				// DBUtility.addManagerSql(man);
//				DBUtility.addEmpSql(man);
//				//SendMail(email);
//				break;
//
//			case "developer":
//				Developer dev = new Developer(role, empID, password, fName,
//						lName, gender, age, email, phoneNum, address,
//						verification, groupID, tech, exp);
//				Main.Employees.add(dev);
//				// DBUtility.addDeveloperSql(dev);
//				DBUtility.addEmpSql(dev);
//				//SendMail(email);
//				break;
//
//			case "qa":
//				QA qa = new QA(role, empID, password, fName, lName, gender,
//						age, email, phoneNum, address, verification, groupID,
//						tool, projectName);
//				Main.Employees.add(qa);
//				// DBUtility.addDeveloperSql(dev);
//				DBUtility.addEmpSql(qa);
//			//	SendMail(email);
//				break;
//
//			case "training":
//				Training tr = new Training(role, empID, password, fName, lName,
//						gender, age, email, phoneNum, address, verification,
//						groupID, projectName, numOfStudent);
//				Main.Employees.add(tr);
//				// DBUtility.addDeveloperSql(dev);
//				DBUtility.addEmpSql(tr);
//				//SendMail(email);
//				break;
//			}
//			// DBUtility.addEmpSql(role, empID, fName, lName, details, role);
//			System.out.println("Save Successfully! \r\n");
//			// Menu.login();
//			// Menu.menuOption();
//
//		}
//	}

	public static void addEmpDetail() {
		
		
		String email = null;
		String password = null;
		int empID = 0;
		String fName = null;
		String lName = null;
		String gender = null;
		String ageS = null;
		int age = 0;
		
		String phoneNum = null;
		String address = null;
		String verification = "0";
		int groupID = 0;

		String dept = null;
		String tech = null;
		String num = null;
		String experiences = null;
		int numOfEmp = 0;
		int exp = 0;
		String tool = null;
		String projectName = null;
		int numOfStudent = 0;
		// String

		do {
			email = askForInput().toLowerCase();
//			System.out.println("email is" + email);
//			System.out.println("check validation is" + ValidationUtility.checkEmailExisted(Main.Employees, email));
			if (ValidationUtility.checkEmailExisted(Main.Employees, email)) {
				System.out.println("Email is already in the database \r\n");
			}
		} while (ValidationUtility.checkEmailExisted(Main.Employees, email));
		do {
			System.out.println("Please Enter Your Password: ");
			password = OperationUtility.readString("").toLowerCase();
			// System.out.println(checker);
		} while (!ValidationUtility.checkNotNull(password));
		do{
		do {
			System.out.println("Please Enter Your Role(A/M/D/Q/T): ");
			str = OperationUtility.readString("");
			// System.out.println(checker);
		} while (!ValidationUtility.checkNotNull(str));
		} while(ValidationUtility.checkRoleInput(str).equals("null"));
		role = ValidationUtility.checkRoleInput(str).toLowerCase();
		do {
			do {
				System.out.println("Please Enter the Employee ID");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkInteger(str));
			empID = Integer.parseInt(str);
			if (ValidationUtility.checkEmpIDExisted(Main.Employees, empID)) {
				System.out.println("Employee ID is alreay in the database \r\n");
			}
		} while (ValidationUtility.checkEmpIDExisted(Main.Employees, empID));
		do {
			System.out.println("Please Enter Your First Name: ");
			fName = OperationUtility.readString("").toLowerCase();
			// System.out.println(checker);
		} while (!ValidationUtility.checkCharacter(fName));

		do {
			System.out.println("Please Enter Your Last Name: ");
			lName = OperationUtility.readString("").toLowerCase();
		} while (!ValidationUtility.checkCharacter(lName));
		do {
			System.out.println("Please Enter Gender: (Female/Male)");
			gender = OperationUtility.readString("").toLowerCase();
			// System.out.println(checker);
		} while (!ValidationUtility.checkGender(gender));

		do {
			System.out.println("Please Enter Your Age: ");
			ageS = OperationUtility.readString("");

		} while (!ValidationUtility.checkInputRange(ageS)
				|| !ValidationUtility.checkInteger(ageS));
		age = Integer.parseInt(ageS);
	
		do {
			System.out.println("Please Enter Your Phone Number: ");
			phoneNum = OperationUtility.readString("").toLowerCase();
			// System.out.println(checker);
		} while (!ValidationUtility.checkPhoneNum(phoneNum));

		do {
			System.out.println("Please Enter Your Address: ");
			address = OperationUtility.readString("").toLowerCase();
		} while (!ValidationUtility.checkNotNull(address));
		// age= Integer.parseInt(ageS);
		// DBUtility.AddEmp(empID, fName, lName);

		// System.out.println(role);
		// char
		
		switch (role) {
		case "manager":
			do {
				System.out.println("Please Enter the Department Name: ");
				dept = OperationUtility.readString("").toLowerCase();
			} while (!ValidationUtility.checkNotNull(dept));
			do {
				System.out.println("Please Enter the Number of Employees: ");
				num = OperationUtility.readString("");
				// System.out.println(num);
			} while (!ValidationUtility.checkInteger(num));
			numOfEmp = Integer.parseInt(num);
			break;
		case "developer":
			do {
				System.out.println("Please Enter the Technology: ");
				tech = OperationUtility.readString("").toLowerCase();
				// System.out.println(tech);
			} while (!ValidationUtility.checkNotNull(tech));
			do {
				System.out.println("Please Enter the Years of Experiences: ");
				experiences = OperationUtility.readString("");
				// System.out.println("Check outside vali"
				// +ValidationUtility.checkInputRange(experiences));
			} while (!ValidationUtility.checkInputRange(experiences)
					|| !ValidationUtility.checkInteger(experiences));
			exp = Integer.parseInt(experiences);
			break;
		case "qa":
			do {
				System.out.println("Please Enter the Tool Name: ");
				tool = OperationUtility.readString("").toLowerCase();
			} while (!ValidationUtility.checkNotNull(tool));
			do {
				System.out.println("Please Enter the Current Project Name: ");
				projectName = OperationUtility.readString("").toLowerCase();;
			} while (!ValidationUtility.checkNotNull(projectName));
			break;

//		case "training":
//			do {
//				System.out.println("Please Enter the Project Name: ");
//				projectName = OperationUtility.readString("").toLowerCase();
//			} while (!ValidationUtility.checkNotNull(projectName));
//			do {
//				System.out.println("Please Enter the Number of Students: ");
//				num = OperationUtility.readString("");
//			} while (!ValidationUtility.checkInteger(num));
//			numOfStudent = Integer.parseInt(num);
//			break;
		}
		System.out.println("---Employee Information---");
		System.out.println("Role: " + role);
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		System.out.println("Employee ID: " + empID);
		System.out.println("First Name: " + fName);
		System.out.println("Last Name: " + lName);
		System.out.println("Gender: " + gender);
		System.out.println("Age: " + age);
		System.out.println("Phone Number: " + phoneNum);
		System.out.println("Address: " + address);

		switch (role) {
		case "manager":
			System.out.println("Department Name: " + dept);
			System.out.println("Number of Employees: " + num);
			break;

		case "developer":
			System.out.println("Technology: " + tech);
			System.out.println("Experiences: " + exp + "\r\n");
			break;

		case "qa":
			System.out.println("Tool Name: " + tool);
			System.out.println("Current Project Name: " + projectName + "\r\n");
			break;

		case "training":

			System.out.println("Project Name: " + projectName);
			System.out.println("Number of Students: " + num + "\r\n");
			break;
		}

		if (ValidationUtility.checkSaved()) {
			switch (role) {
			
			case "admin":
				Employee emp = new Employee(role, empID, password, fName,
						lName, gender, age, email, phoneNum, address,
						verification, groupID);
				Main.Employees.add(emp);
				// DBUtility.addManagerSql(man);
				DBUtility.addEmpSql(emp);
				//SendMail(email);
				break;
				
			case "manager":
				Manager man = new Manager(role, empID, password, fName, lName,
						gender, age, email, phoneNum, address, verification,
						groupID, dept, numOfEmp);
				Main.Employees.add(man);
				// DBUtility.addManagerSql(man);
				DBUtility.addEmpSql(man);
				//SendMail(email);
				break;

			case "developer":
				Developer dev = new Developer(role, empID, password, fName,
						lName, gender, age, email, phoneNum, address,
						verification, groupID, tech, exp);
				Main.Employees.add(dev);
				// DBUtility.addDeveloperSql(dev);
				DBUtility.addEmpSql(dev);
				System.out.println("iiiiis called");
				//SendMail("andyliulovemore@gmail.com");
				break;

			case "qa":
				QA qa = new QA(role, empID, password, fName, lName, gender,
						age, email, phoneNum, address, verification, groupID,
						tool, projectName);
				Main.Employees.add(qa);
				// DBUtility.addDeveloperSql(dev);
				DBUtility.addEmpSql(qa);
			//SendMail(email);
				break;

//			case "training":
//				Training tr = new Training(role, empID, password, fName, lName,
//						gender, age, email, phoneNum, address, verification,
//						groupID, projectName, numOfStudent);
//				Main.Employees.add(tr);
//				// DBUtility.addDeveloperSql(dev);
//				DBUtility.addEmpSql(tr);
//				//SendMail(email);
//				break;
			}
			// DBUtility.addEmpSql(role, empID, fName, lName, details, role);
			System.out.println("Save Successfully! \r\n");
			// Menu.login();
			// Menu.menuOption();

		}
	}
//	public static Employee selectEmployeeById(int id) {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		int len = Main.Employees.size();
//		if (len != 0) {
//			Employee emp = new Employee();
//			for (int i = 0; i < len; i++) {
//				Employee e = Main.Employees.get(i);
//				if (e.getId() == id) {
//					emp = e;
//				}
//			}
//			return emp;
//		} else {
//			return null;
//		}
//	}

	public static Employee selectEmployeeById(String email) {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		int len = Main.Employees.size();
		if (len != 0) {
			Employee emp = new Employee();
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (e.getEmail().equals(email)) {
					emp = e;
				}
			}
			return emp;
		} else {
			return null;
		}
	}
	
	public static List<Employee> getEmpByDept(String dept) {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		int len = Main.Employees.size();
		if (len != 0) {
			List<Employee> EmployeeByDept = new ArrayList<Employee>();
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (e.getRole().equals(dept)) {
					EmployeeByDept.add(e);
				}
			}
			// System.out.println(EmployeeByDept.toString());
			return EmployeeByDept;
		} else {
			return null;
		}

	}

	public static void printPartialInfo(List<Employee> emp) {
		int len = emp.size();
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				Employee e = emp.get(i);
				System.out.println("Role: " + e.getRole()
						+ " ||  Email: "
						+ e.getEmail() + "\r\n");
			}
		}

	}

	
	
//	public static void updateEmpDetail(String role, int empID) {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		Employee emp = OperationUtility.selectEmployeeById(empID);
//		// System.out.println("Employee is already in the database.");
//		System.out.println(emp.toString());
//		// Ask Update information
//		switch (role) {
//		case "manager":
//			do {
//				System.out.println("Please Enter the Field You Want to Update");
//				System.out
//						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
//								+ "d for department/n for number of employees/r for return to previous menu");
//				field = OperationUtility.readString("");
//				// System.out.println("Before validation " + field);
//				// System.out.println(ValidationUtility.checkManagerField(field));
//			} while (UpdateFieldValidation.checkManagerField(field).equals(
//					"null"));
//			// System.out.println("After Vali" +
//			// ValidationUtility.checkManagerField(field));
//			field = UpdateFieldValidation.checkManagerField(field);
//			break;
//
//		case "developer":
//			do {
//				System.out.println("Please Enter the Field You Want to Update");
//				System.out
//						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
//								+ "t for technology/x for experiences/r for return to previous menu");
//				field = OperationUtility.readString("");
//			} while (UpdateFieldValidation.checkDeveloperField(field).equals(
//					"null"));
//			field = UpdateFieldValidation.checkDeveloperField(field);
//			break;
//
//		case "qa": // ////
//			do {
//				System.out.println("Please Enter the Field You Want to Update");
//				System.out
//						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
//								+ "t for tools/n for project name/r for return to previous menu");
//				field = OperationUtility.readString("");
//			} while (UpdateFieldValidation.checkQAField(field).equals("null"));
//			field = UpdateFieldValidation.checkQAField(field);
//			break;
//
//		case "training": // ////
//			do {
//				System.out.println("Please Enter the Field You Want to Update");
//				System.out
//						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
//								+ "j for project name/n for number of students/r for return to previous menu");
//				field = OperationUtility.readString("");
//			} while (UpdateFieldValidation.checkTrainingField(field).equals(
//					"null"));
//			field = UpdateFieldValidation.checkTrainingField(field);
//			break;
//
//		}
//		// Ask changed info
//		if (field.equals("return") && role.equals("manager")) {
//			Menu.managerMenu(empID);
//		} else if (field.equals("return") && !role.equals("manager")) {
//			Menu.empMenu(role, empID);
//		} else if (field.equals("email")) {
//			do {
//				do {
//					System.out.println("Please Enter Your Email Address: ");
//					str = OperationUtility.readString("");
//				} while (!ValidationUtility.checkEmail(str));
//			} while (ValidationUtility.checkEmailExisted(Main.Employees, str));
//		} else if (field.equals("phonenum")) {
//			do {
//				System.out.println("Please Enter the New Information");
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkPhoneNum(str));
//		} else if (field.equals("experiences")) {
//			do {
//				System.out.println("Please Enter the New Information");
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkInputRange(str)
//					|| !ValidationUtility.checkInteger(str));
//		} else if (field.equals("numofemp") || field.equals("numofstudent")) {
//			do {
//				System.out.println("Please Enter the New Information");
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkInteger(str));
//		} else {
//			do {
//				System.out.println("Please Enter the New Information");
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkNotNull(str));
//
//		}
//
//		if (ValidationUtility.checkSaved()) {
//			DBUtility.updateEmpSql(role, empID, field, str);
//			System.out.println("Update Successfully! \r\n");
//		} else {
//		}
//
//	}
//	
	public static void updateEmpDetail(String role, String email) {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		Employee emp = OperationUtility.selectEmployeeById(email);
		// System.out.println("Employee is already in the database.");
		System.out.println(emp.toString());
		// Ask Update information
		switch (role) {
		case "manager":
			do {
				System.out.println("Please Enter the Field You Want to Update");
				System.out
						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
								+ "d for department/n for number of employees/r for return to previous menu");
				field = OperationUtility.readString("");
				// System.out.println("Before validation " + field);
				// System.out.println(ValidationUtility.checkManagerField(field));
			} while (UpdateFieldValidation.checkManagerField(field).equals(
					"null"));
			// System.out.println("After Vali" +
			// ValidationUtility.checkManagerField(field));
			field = UpdateFieldValidation.checkManagerField(field);
			break;

		case "developer":
			do {
				System.out.println("Please Enter the Field You Want to Update");
				System.out
						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
								+ "t for technology/x for experiences/r for return to previous menu");
				field = OperationUtility.readString("");
			} while (UpdateFieldValidation.checkDeveloperField(field).equals(
					"null"));
			field = UpdateFieldValidation.checkDeveloperField(field);
			break;

		case "qa": // ////
			do {
				System.out.println("Please Enter the Field You Want to Update");
				System.out
						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
								+ "t for tools/n for project name/r for return to previous menu");
				field = OperationUtility.readString("");
			} while (UpdateFieldValidation.checkQAField(field).equals("null"));
			field = UpdateFieldValidation.checkQAField(field);
			break;

//		case "training": // ////
//			do {
//				System.out.println("Please Enter the Field You Want to Update");
//				System.out
//						.println("w for password/e for email/p for phone numebr/a for address/\r\n"
//								+ "j for project name/n for number of students/r for return to previous menu");
//				field = OperationUtility.readString("");
//			} while (UpdateFieldValidation.checkTrainingField(field).equals(
//					"null"));
//			field = UpdateFieldValidation.checkTrainingField(field);
//			break;

		}
		// Ask changed info
		if (field.equals("return") && role.equals("manager")) {
			Menu.managerMenu(email);
		} else if (field.equals("return") && !role.equals("manager")) {
			Menu.empMenu(role, email);
		} else if (field.equals("email")) {
			do {
				do {
					System.out.println("Please Enter Your Email Address: ");
					str = OperationUtility.readString("");
				} while (!ValidationUtility.checkEmail(str));
			} while (ValidationUtility.checkEmailExisted(Main.Employees, str));
		} else if (field.equals("phonenum")) {
			do {
				System.out.println("Please Enter the New Information");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkPhoneNum(str));
		} else if (field.equals("experiences")) {
			do {
				System.out.println("Please Enter the New Information");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkInputRange(str)
					|| !ValidationUtility.checkInteger(str));
		} else if (field.equals("numofemp") || field.equals("numofstudent")) {
			do {
				System.out.println("Please Enter the New Information");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkInteger(str));
		} else {
			do {
				System.out.println("Please Enter the New Information");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkNotNull(str));

		}
		str = str.toLowerCase();
		if (ValidationUtility.checkSaved()) {
			DBUtility.updateEmpSql(role, email, field, str);
			System.out.println("Update Successfully! \r\n");
		} else {
		}

	}
	


	public static List<Employee> getEmpNotApprove() {

		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
//System.out.println(Main.Employees.size());
		int len = Main.Employees.size();
		if (len != 0) {
			List<Employee> EmployeeNotApprove = new ArrayList<Employee>();
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (e.getVerification().equals("0")) {
					EmployeeNotApprove.add(e);
					// System.out.println("\r\nRole: " + e.getRole()
					// + "  ||  Employee ID: " + e.getId() +
					// "  ||  First Name: "
					// + e.getFName() + "  ||  Last Name: " + e.getLName()
					// + "  ||  Email: " + e.getEmail());
				}
			}
			//System.out.println(EmployeeNotApprove.size());
			return EmployeeNotApprove;
		} else {
			return null;
		}

	}

//	public static String getRole(String id) {
//		String role = null;
//		if (id.matches("0[0-9]{3}")) {
//			role = "admin";
//		} else if (id.matches("1[0-9]{3}")) {
//			role = "manager";
//		} else if (id.matches("2[0-9]{3}")) {
//			role = "developer";
//		} else if (id.matches("3[0-9]{3}")) {
//			role = "qa";
//		} else if (id.matches("4[0-9]{3}")) {
//			role = "training";
//		} else {
//			role = "admin";
//		}
//		return role;
//	}
//	
	public static String getRole(String email) {
		String role =  OperationUtility.selectEmployeeById(email).getRole();
		return role;
	}

	public static String readString(String prompt) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(prompt);
		return scanner.nextLine();
	}
	
	
	
	public static void SendMail(String emaillAddress){
		
		//System.out.println("SendEmail is called");
		
		 // Recipient's email ID needs to be mentioned.
	      String to = "emailaddress@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = emaillAddress;

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.put("mail.smtp.starttls.enable", "true"); 
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.user", "emailaddress@gmail.com"); // User name
	      properties.put("mail.smtp.password", "password"); // password
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.smtp.auth", "true");
	      // Get the default Session object.
	      Session session = Session.getInstance(properties, new GMailAuthenticator("emailaddress@gmail.com", "password"));
	     

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from,"New Employee"));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("You have received a new application.");

	         // Now set the actual message
	         message.setText("You have received a new application, please check in the system.");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (Exception mex) {
	         mex.printStackTrace();
	      }
		
	}

//	public static void SendMail(String emailAddress) {
//		System.out.println("is called");
//		
//		SimpleEmail email = new SimpleEmail();
//		//email.setTLS(true);
//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);
//		email.setAuthenticator(new DefaultAuthenticator(
//				"andyliulovemore@gmail.com", "20042739"));
//		email.setSSLOnConnect(true);
//
//		try {
//			email.setFrom("aliceliulovemore@gmail.com");
//			email.setSubject("Thanks for the registration");
//			email.setMsg("Congratulation! Register successfully");
//			email.addTo(emailAddress);
//			email.send();
//
//		} catch (EmailException e) {
//			e.printStackTrace();
//		}
//	}
//	
	public static String askForInput(){
			do {
				System.out.println("Please Enter the user ID/email");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkEmail(str));
			//empID = Integer.parseInt(str);
			
		return str;
	}

}

class GMailAuthenticator extends Authenticator {
    String user;
    String pw;
    public GMailAuthenticator (String username, String password)
    {
       super();
       this.user = username;
       this.pw = password;
    }
   public PasswordAuthentication getPasswordAuthentication()
   {
      return new PasswordAuthentication(user, pw);
   }
}
