package com.employee.menuMethod;

import java.util.ArrayList;
import java.util.List;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Employee;
import com.employee.main.DBUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

public class AdminMenuMethod { // //admin checked

	static String str = null;
	static String field = null;
	static String role = null;
	static String email = null;

	// static int empID = 0;
	// public static void approveEmp() {
	// List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
	// // System.out.println(empNotApproval.toString());
	//
	// OperationUtility.printPartialInfo(empNotApproval);
	//
	// do {
	// System.out.println("Please Enter the Employee ID");
	// System.out
	// .println("(4-digits ID: Manager starts with 1, "
	// + "Developer starts with 2, QA starts with 3, " +
	// "Trainer starts with 4, admin starts with 5)");
	// str = OperationUtility.readString("");
	// } while (!ValidationUtility.checkIsID(str));
	//
	// empID = Integer.parseInt(str);
	// role = OperationUtility.getRole(str);
	// String veri = null;
	// switch(role){
	// case "manager": veri = "1"; break;
	// case "developer": veri = "2"; break;
	// case "qa": veri = "3"; break;
	// case "training": veri = "4"; break;
	// case "admin": veri = "9"; break;
	// }
	// //System.out.println("!!!!!!!!!!!!" + veri);
	// //List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
	//
	// if (ValidationUtility.checkEmpIDExisted(empNotApproval, empID)) {
	// if (ValidationUtility.checkSaved()) {
	// // Employee emp = OperationUtility.selectEmployeeById(empID);
	// DBUtility.approveEmpSql(role, empID, veri);
	// System.out.println("You have approved this request! \r\n");
	// }
	// } else {
	// System.out.println("Employee Information Not Found. \r\n");
	// }
	//
	// }

	public static void approveEmp() {
		List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
		// System.out.println();
		// System.out.println(empNotApproval.toString());
		String empEmail = null;
		//System.out.println("size: " + empNotApproval);
		
		if (empNotApproval == null) {
			System.out.println("No request at this time. \r\n");
		} else if (empNotApproval.size() == 0){
			System.out.println("No request at this time. \r\n");
		} else{
			OperationUtility.printPartialInfo(empNotApproval);
		
				 empEmail = OperationUtility.askForInput();
			role = OperationUtility.getRole(empEmail);
			String veri = null;

			// System.out.println("!!!!!!!!!!!!" + veri);
			// List<Employee> empNotApproval =
			// OperationUtility.getEmpNotApprove();

			if (ValidationUtility.checkEmailExisted(empNotApproval, empEmail)) {
				switch (role) {
				case "manager":
					veri = "1";
					break;
				case "developer":
					veri = "2";
					break;
				case "qa":
					veri = "3";
					break;
				case "training":
					veri = "4";
					break;
				case "admin":
					veri = "9";
					break;
				}
				if (ValidationUtility.checkSaved()) {
					// Employee emp =
					// OperationUtility.selectEmployeeById(empID);
					DBUtility.approveEmpSql(role, empEmail, veri);
					System.out.println("You have approved this request! \r\n");
				}
			} else {
				System.out.println("Employee Information Not Found. \r\n");
			}
		} 
			
	}

	public static void approveEmpAll() {
		// TODO Auto-generated method stub
		List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
		// System.out.println(empNotApproval.toString());
		// OperationUtility.printPartialInfo(empNotApproval);
		// System.out.println("check" + (empNotApproval.size() != 0));
		if (empNotApproval == null) {
			System.out.println("No request at this time. \r\n");
		} else if (empNotApproval.size() == 0){
			System.out.println("No request at this time. \r\n");
		} else{
			OperationUtility.printPartialInfo(empNotApproval);
			do {
				System.out
						.println("Do you want to approved all the requests? (1 for yes, 0 for no)");
				str = OperationUtility.readString("");
				// System.out.println("Check inside loop: " + str);
			} while (!ValidationUtility.checkSaveInput(str));

			if (str.equals("1")) {
				DBUtility.approveEmpSql("manager", "1");
				DBUtility.approveEmpSql("developer", "2");
				DBUtility.approveEmpSql("qa", "3");
				DBUtility.approveEmpSql("training", "4");
				DBUtility.approveEmpSql("admin", "9");
				System.out.println("You have approved all the requests!");
			}
		} 
	}

	public static void denyEmp() {

		List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
		String empEmail = null;
		// OperationUtility.printPartialInfo(empNotApproval);
		if (empNotApproval == null) {
			System.out.println("No request at this time. \r\n");
		} else if (empNotApproval.size() == 0){
			System.out.println("No request at this time. \r\n");
		} else{
			OperationUtility.printPartialInfo(empNotApproval);
				 empEmail = OperationUtility.askForInput();

			role = OperationUtility.getRole(empEmail);

			if (ValidationUtility.checkEmailExisted(empNotApproval, empEmail)) {
				if (ValidationUtility.checkSaved()) {
					// Employee emp =
					// OperationUtility.selectEmployeeById(empID);
					DBUtility.deleteEmpSql(role, empEmail);
					System.out.println("You have denied this requests.\r\n");
				}
			} else {
				System.out.println("Employee Information Not Found. \r\n");
			}
		} 
	}

	public static void denyEmpAll() {
		List<Employee> empNotApproval = OperationUtility.getEmpNotApprove();
		// System.out.println(empNotApproval.toString());
		// OperationUtility.printPartialInfo(empNotApproval);
		if (empNotApproval == null) {
			System.out.println("No request at this time. \r\n");
		} else if (empNotApproval.size() == 0){
			System.out.println("No request at this time. \r\n");
		} else{
			OperationUtility.printPartialInfo(empNotApproval);
			do {
				System.out
						.println("Do you want to approved all the requests? (1 for yes, 0 for no)");
				str = OperationUtility.readString("");
			} while (!ValidationUtility.checkSaveInput(str));

			if (str.equals("1")) {
				DBUtility.deneyEmpSql("manager");
				DBUtility.deneyEmpSql("developer");
				DBUtility.deneyEmpSql("qa");
				DBUtility.deneyEmpSql("training");
				DBUtility.deneyEmpSql("admin");
				System.out.println("You have denied all the requests!");
			}
		} 
	}

	// public static void removeEmp() {
	// Main.Employees.clear();
	// OperationUtility.getInfoFromDB();
	// do {
	// System.out.println("Please Enter the Employee ID");
	// System.out
	// .println("(4-digits ID: Manager starts with 1, "
	// + "Developer starts with 2, QA starts with 3, " +
	// "Trainer starts with 4, admin starts with 5)");
	// str = OperationUtility.readString("");
	// } while (!ValidationUtility.checkIsID(str));
	//
	// empID = Integer.parseInt(str);
	// role = OperationUtility.getRole(str);
	//
	// if (ValidationUtility.checkEmpIDExisted(Main.Employees, empID)) {
	// // remove emp info;
	// if (ValidationUtility.checkSaved()) {
	// Employee emp = OperationUtility.selectEmployeeById(empID);
	// // Employee empSelectId = ManageInfo.selectEmployeeById(empID);
	// // System.out.println("Delete Employee!!!!!!!!!!!!!!!!!!!!!!!!!\r\n"
	// // + emp.toString());
	// //Main.Employees.remove(emp); // Delete from arraylist;
	//
	// DBUtility.deleteEmpSql(role, empID); // Delete from DB;
	// System.out.println("Delete Successfully! \r\n");
	// // Menu.managerMenu();
	// }
	// // else {
	// // Menu.managerMenu();
	// // }
	// } else {
	// System.out.println("Employee Information Not Found. \r\n");
	// // Menu.managerMenu();
	// }
	//
	// }

	public static void removeEmp() {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();

		String empEmail = null;
			 empEmail = OperationUtility.askForInput();

		 
		role = OperationUtility.getRole(empEmail);

		if (ValidationUtility.checkEmailExisted(Main.Employees, empEmail)) {
			// remove emp info;
			if (ValidationUtility.checkSaved()) {
				// Employee emp = OperationUtility.selectEmployeeById(empEmail);

				DBUtility.deleteEmpSql(role, empEmail); // Delete from DB;
				System.out.println("Delete Successfully! \r\n");
				// Menu.managerMenu();
			}
			// else {
			// Menu.managerMenu();
			// }
		} else {
			System.out.println("Employee Information Not Found. \r\n");
			// Menu.managerMenu();
		}

	}

	public static void viewEmpbyGroupID() {

		do {
			System.out.println("Please enter the group ID: ");
			str = OperationUtility.readString("");
		} while (!ValidationUtility.checkNotNull(str));
		int groupID = Integer.parseInt(str);

		if (CommonMenuMethod.viewEmpByGroupID(groupID).size() != 0) {
			OperationUtility.printPartialInfo(CommonMenuMethod
					.viewEmpByGroupID(groupID));
		} else {
			System.out.println("No group infromation found \r\n");
		}

	}

	public static void viewAllGroup() {
		// TODO Auto-generated method stub
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		//Employee e = null;
		List<Employee> getEmp = new ArrayList<Employee>();
		int len = Main.Employees.size();
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				Employee e = Main.Employees.get(i);
				if (e.getGroupID() != 0 && e.getRole().equals("manager")) {
					System.out.println("Group ID: " + e.getGroupID()
							+ "  ||  Email: " + e.getEmail() + "\r\n");
					getEmp.add(e);
				}
			}
		}
		if(getEmp.size() == 0){
			System.out.println("No group information at this time. \r\n");
		}

	}

}
