package com.employee.menuMethod;

import com.employee.empClass.Employee;
import com.employee.main.Menu;
import com.employee.main.OperationUtility;

public class EmpMenuMethod { ///admin checked

//	public static void updateEmpInfo(String role, int empID) {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		
//		OperationUtility.updateEmpDetail(role, empID);
//		Menu.empMenu(role, empID);
//	}
	
	public static void updateEmpInfo(String role, String email) {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		
		OperationUtility.updateEmpDetail(role, email);
		Menu.empMenu(role, email);
	}
	
//	public static void viewEmpByID(int empID){
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		Employee emp = OperationUtility.selectEmployeeById(empID);
//		System.out.println(emp.toString());
//	}
	
	public static void viewEmpByID(String email){
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		Employee emp = OperationUtility.selectEmployeeById(email);
		System.out.println(emp.toString());
	}
}
