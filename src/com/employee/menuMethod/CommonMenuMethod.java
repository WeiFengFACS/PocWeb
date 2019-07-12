package com.employee.menuMethod;

import java.util.ArrayList;
import java.util.List;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Employee;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

public class CommonMenuMethod { ////admin checked
	static String str = null;
	static String field = null;
	static String role = null;
	//static int empID = 0;
	static String email = null;

//	public static void addEmp() {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		do {
//			System.out.println("Please Enter the Employee ID");
//			System.out
//			.println("(4-digits ID: Manager starts with 1, "
//					+ "Developer starts with 2, QA starts with 3, " +
//					"Trainer starts with 4, admin starts with 5)");
//			str = OperationUtility.readString("");
//		} while (!ValidationUtility.checkIsID(str));
//		//
//		// get role name from input empID;
//		role = OperationUtility.getRole(str);
//		empID = Integer.parseInt(str);
//
//		if (!ValidationUtility.checkEmpIDExisted(Main.Employees, empID)) {
//			OperationUtility.addEmpDetail(role, empID);
//
//		} else {
//			System.out.println("Employee is already in the database. \r\n");
//			addEmp();
//		}
//
//	}

	public static void addEmp() {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		OperationUtility.addEmpDetail();

	}
	
	public static void viewEmp() {

		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		if (Main.Employees.size() == 0) {
			System.out.println("No Employee Information in database. \r\n");
			// Menu.managerMenu();
		} else {
			System.out.println(Main.Employees.toString() + "\r\n");
			// Menu.managerMenu();
		}
	}
	
	public static List<Employee> viewEmpByGroupID(int groupID) {
		// Employee e = selectEmployeeById(empID);
		// int groupID = e.getGroupID();
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		
		List<Employee> EmployeeByGroupID = new ArrayList<Employee>();

		int len = Main.Employees.size();
		if (len != 0) {

			for (int i = 0; i < len; i++) {
				Employee eList = Main.Employees.get(i);
				if (eList.getGroupID() == groupID && !eList.getVerification().equals("false")) {
					EmployeeByGroupID.add(eList);
				}
			}
			// System.out.println(EmployeeByGroupID.toString());

		}
		return EmployeeByGroupID;
	}
	
	public static List<Employee> viewAvailableEmp() {
		// Employee e = selectEmployeeById(empID);
		// int groupID = e.getGroupID();
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		
		List<Employee> EmployeeByGroupID = new ArrayList<Employee>();

		int len = Main.Employees.size();
		if (len != 0) {

			for (int i = 0; i < len; i++) {
				Employee eList = Main.Employees.get(i);
				if (eList.getGroupID() == 0 && !eList.getVerification().equals("false")
						&& !eList.getRole().equals("manager") && !eList.getRole().equals("admin")) {
					EmployeeByGroupID.add(eList);
				}
			}
			// System.out.println(EmployeeByGroupID.toString());

		}
		return EmployeeByGroupID;
	}

}
