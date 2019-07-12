package com.employee.menuMethod;

import java.util.ArrayList;
import java.util.List;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Employee;
import com.employee.main.DBUtility;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

public class ManagerMenuMethod {  ////admin checked

	static String str = null;
	static String field = null;
	static String role = null;
	//static int empID = 0;
	static String email = null;
	static String empEmail = null;
//	public static void updateEmp() {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//	
//		do {
//			System.out.println("Please Enter the Employee ID");
//			System.out
//			.println("(4-digits ID: Manager starts with 1, "
//					+ "Developer starts with 2, QA starts with 3, " +
//					"Trainer starts with 4, admin starts with 5)");
//			str = OperationUtility.readString("");
//		} while (!ValidationUtility.checkIsID(str));
//
//		role = OperationUtility.getRole(str);
//		empID = Integer.parseInt(str);
//
//		if (ValidationUtility.checkEmpIDExisted(Main.Employees, empID)) {
//			OperationUtility.updateEmpDetail(role, empID);
//		} else {
//			System.out.println("Employee Information Not Found. \r\n");
//			updateEmp();
//		}
//	}
	
	public static void updateEmp() {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
	
		do {
			  empEmail = OperationUtility.askForInput();
			 if (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail)) {
					System.out.println("Employee information not found \r\n");
				}
			} while (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail));
		role = OperationUtility.getRole(email);

		if (ValidationUtility.checkEmailExisted(Main.Employees, email)) {
			OperationUtility.updateEmpDetail(role, email);
		} else {
			System.out.println("Employee Information Not Found. \r\n");
			updateEmp();
		}
	}

//	public static void createGroup(int empID) {
//		TODO Auto-generated method stub
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
//		
//		int groupID;
//		Employee e = OperationUtility.selectEmployeeById(empID);
//		int gID = e.getGroupID();
//		if (gID == 0) {
//			do {
//				do {
//					System.out.println("Please enter the group ID: ");
//					str = OperationUtility.readString("");
//				} while (!ValidationUtility.checkInteger(str));
//				groupID = Integer.parseInt(str);
//			} while (ValidationUtility.checkGroupIDExisted(groupID));
//			if (ValidationUtility.checkSaved()) {
//				DBUtility.updateGroupID("manager", empID, groupID);
//				System.out.println("Create group successfully!");
//			}
//		} else {
//			System.out
//					.println("You have already in a group. Create group failed \r\n");
//		}
//
//	}
	
	public static void createGroup(String email) {
		// TODO Auto-generated method stub
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		
		int groupID;
		Employee e = OperationUtility.selectEmployeeById(email);
		int gID = e.getGroupID();
		if (gID == 0) {
			do {
				do {
					System.out.println("Please enter the group ID: ");
					str = OperationUtility.readString("");
				} while (!ValidationUtility.checkInteger(str));
				groupID = Integer.parseInt(str);
			} while (ValidationUtility.checkGroupIDExisted(groupID));
			if (ValidationUtility.checkSaved()) {
				DBUtility.updateGroupID("manager", email, groupID);
				System.out.println("Create group successfully!");
			}
		} else {
			System.out
					.println("You have already in a group. Create group failed \r\n");
		}

	}
//	
//	public static void dropGroup(int empID) {
//		Employee e = OperationUtility.selectEmployeeById(empID);
//		int groupID = e.getGroupID();
//		if (groupID == 0) {
//			System.out
//					.println("You haven't in any group. Drop group failed \r\n");
//		} else {
//			if (ValidationUtility.checkSaved()) {
//				DBUtility.dropGroupID("manager", groupID);
//				DBUtility.dropGroupID("developer", groupID);
//				DBUtility.dropGroupID("qa", groupID);
//				DBUtility.dropGroupID("training", groupID);
//				//DBUtility.dropGroupID("admin", groupID);
//				System.out.println("Drop group successfully!");
//			}
//		}
//	}


	public static void dropGroup(String email) {
		Employee e = OperationUtility.selectEmployeeById(email);
		int groupID = e.getGroupID();
		if (groupID == 0) {
			System.out
					.println("You haven't in any group. Drop group failed \r\n");
		} else {
			if (ValidationUtility.checkSaved()) {
				DBUtility.dropGroupID("manager", groupID);
				DBUtility.dropGroupID("developer", groupID);
				DBUtility.dropGroupID("qa", groupID);
				DBUtility.dropGroupID("training", groupID);
				//DBUtility.dropGroupID("admin", groupID);
				System.out.println("Drop group successfully!");
			}
		}
	}
	
	public static void removeEmpFromGroup(String email) {
		Employee e = OperationUtility.selectEmployeeById(email);
		int groupID = e.getGroupID();
		if (groupID == 0) {
			System.out
					.println("You haven't in any group. Delete employee information failed \r\n");
		} else {

			//System.out.println(viewEmpByGroupID(groupID).toString());
			//System.out.println("CHECK PRINTLN");
			OperationUtility.printPartialInfo(CommonMenuMethod.viewEmpByGroupID(groupID));
			//viewEmpByGroupID.
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//should remove manager id and admin id
			

			do {
				  empEmail = OperationUtility.askForInput();
				 if (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail)) {
						System.out.println("Employee information not found \r\n");
					}
				} while (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail));
			role = OperationUtility.getRole(empEmail);
			List<Employee> viewEmpByGroupID = CommonMenuMethod.viewEmpByGroupID(groupID);

			if (ValidationUtility.checkEmailExisted(viewEmpByGroupID, empEmail) && !role.equals("manager")) {
				if (ValidationUtility.checkSaved()) {
					// Employee emp =
					// OperationUtility.selectEmployeeById(empID);
					DBUtility.updateGroupID(role, empEmail, 0);
					System.out
							.println("Delete employee from group successfully! \r\n");
				}
			} else if (role.equals("manager")){
				System.out
				.println("You cannot delete manager from this group. \r\n");
			} else {
				System.out.println("Employee Information Not Found. \r\n");
			}
		}
	}

	public static void addEmpToGroup(String email) {
		// TODO Auto-generated method stub
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		//System.out.println("Employee ID: " + empID);
		Employee e = OperationUtility.selectEmployeeById(email);
		int groupID = e.getGroupID();
		//System.out.println("Group ID: " + groupID);
		if (groupID == 0) {
			System.out
					.println("You haven't in any group. Add employee information failed \r\n");
		} else {
			//System.out.println(viewEmpByGroupID(0).toString());
			OperationUtility.printPartialInfo(CommonMenuMethod.viewAvailableEmp());
			
			
			
//			do {
//				System.out.println("Please Enter the Employee ID");
//				System.out
//				.println("(4-digits ID: Developer starts with 2, QA starts with 3, " +
//						"Trainer starts with 4)"); 
//				str = OperationUtility.readString("");
//			} while (!ValidationUtility.checkIsID(str));
//			int emp = Integer.parseInt(str);
//			role = OperationUtility.getRole(str);

			do {
				  empEmail = OperationUtility.askForInput();
				 if (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail)) {
						System.out.println("Employee information not found \r\n");
					}
				} while (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail));
			role = OperationUtility.getRole(empEmail);
			
			List<Employee> vacantEmpList = CommonMenuMethod.viewEmpByGroupID(0);

			if (ValidationUtility.checkEmailExisted(vacantEmpList, empEmail) && !role.equals("manager") 
					&& !role.equals("admin")) {
				if (ValidationUtility.checkSaved()) {
					DBUtility.updateGroupID(role, empEmail, groupID);
					System.out
							.println("Add employee to group successfully! \r\n");
				}
			} else if (role.equals("manager")){
				System.out
				.println("You cannot add another manager into this group. \r\n");
			} else if (role.equals("admin")){
				System.out
				.println("You cannot add admin into the group. \r\n");
			} else {
			
				System.out
						.println("Employee is not able to join right now. \r\n");
			}
		}
	}

	public static void viewEmp(String email) {
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		Employee e = OperationUtility.selectEmployeeById(email);
		int groupID = e.getGroupID();
		if (groupID == 0) {
			System.out
					.println("You haven't in any group. View group information failed \r\n");
		} else {
			//System.out.println(viewEmpByGroupID(groupID).toString());
			OperationUtility.printPartialInfo(CommonMenuMethod.viewEmpByGroupID(groupID));
			
		}
	}

}
