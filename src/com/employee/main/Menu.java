package com.employee.main;
import com.employee.menuMethod.*;

public class Menu {

	static String str = null;
	static String role = null;
	static int empID = 0;

	public static void mainMenu() {
		String str = null;

		System.out.println("Welcome to the Employee Management Application");
		System.out
				.println("Have you already registered?: (1 for yes/0 for no/2 for exit)");
		str = OperationUtility.readString("");

		if (str.equals("1")) {
			OperationUtility.login();
		} else if (str.equals("0")) {
			CommonMenuMethod.addEmp();
			mainMenu();
		} else if (str.equals("2")) {
			System.out.println("Exit Successfully!");
			System.exit(0);
		} else {
			System.out.println("---ERROR: Please enter the number within 0-2 \r\n");
			mainMenu();
		}
	}

	

//	public static void empMenu(String role, int empID) {
//		String str = null;
//		System.out.println("------Employee Menu------");
//		System.out.println("Please Select the following options:");
//		System.out.println("1 for view personal information");
//		System.out.println("2 for update personal information");
//		System.out.println("3 for log out");
//		str = OperationUtility.readString("");
//
//		if (str.equals("1")) {
//			EmpMenuMethod.viewEmpByID(empID);
//			empMenu(role, empID);
//		} else if (str.equals("2")) {
//			EmpMenuMethod.updateEmpInfo(role, empID);
//			empMenu(role, empID);
//		} else if (str.equals("3")) {
//			System.out.println("Log out Successfully!");
//			mainMenu();
//		} else {
//			System.out
//					.println("---ERROR: Please enter the number within 1-3 \r\n");
//			empMenu(role, empID);
//		}
//	}
	
	
	
	public static void empMenu(String role, String email) {
		String str = null;
		System.out.println("------Employee Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for view personal information");
		System.out.println("2 for update personal information");
		System.out.println("3 for log out");
		str = OperationUtility.readString("");

		if (str.equals("1")) {
			EmpMenuMethod.viewEmpByID(email);
			empMenu(role, email);
		} else if (str.equals("2")) {
			EmpMenuMethod.updateEmpInfo(role, email);
			empMenu(role, email);
		} else if (str.equals("3")) {
			System.out.println("Log out Successfully!");
			mainMenu();
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-3 \r\n");
			empMenu(role, email);
		}
	}
	
	//need change this part
	public static void adminMenu() {
		String str = null;
		System.out.println("------Administrator Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for approve/deny employee request");
		System.out.println("2 for add employee");
		System.out.println("3 for delete employee by employee ID");
		//add update
		System.out.println("4 for view all employee information");
		System.out.println("5 for group information");
		System.out.println("6 for log out");
		str = OperationUtility.readString("");

		if (str.equals("1")) {//approve one
			verificationMenu();
		} else if (str.equals("2")) { //add emp
			CommonMenuMethod.addEmp();
			adminMenu();
		} else if (str.equals("3")) { //remove emp
			AdminMenuMethod.removeEmp();
			adminMenu();
		} else if (str.equals("4")) { //view all emp
			CommonMenuMethod.viewEmp();
			adminMenu();
		} else if (str.equals("5")) { //edit group info
			groupInfoMenu();
		} else if (str.equals("6")) {
			System.out.println("Log out Successfully!");
			mainMenu();
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-6 \r\n");
			adminMenu();
		}
	}
	private static void groupInfoMenu() {
		// TODO Auto-generated method stub
		System.out.println("------Group Info Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for view all group with manager info");//
		System.out.println("2 for view employee infromation by group ID");//
//		System.out.println("3 for deny one employee request");//
//		System.out.println("4 for deny all employee request");//
		System.out.println("3 for return back to previous menu");
		str = OperationUtility.readString("");
		
		if (str.equals("1")) {//view group with manager info
			AdminMenuMethod.viewAllGroup();//
			groupInfoMenu();
		} else if (str.equals("2")) {// view emp info by group ID
			AdminMenuMethod.viewEmpbyGroupID();//
			groupInfoMenu();
		} else if (str.equals("3")) {
			System.out.println("Return Back to Admin Menu!");
			adminMenu();
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-3 \r\n");
			groupInfoMenu();
		}
	}

//	public static void verificationMenu(){
//		System.out.println("------Approve/Deny Request Menu------");
//		System.out.println("Please Select the following options:");
//		System.out.println("1 for approve one employee request");
//		System.out.println("2 for approve all employee request");
//		System.out.println("3 for deny one employee request");
//		System.out.println("4 for deny all employee request");
//		System.out.println("5 for return back to previous menu");
//		str = OperationUtility.readString("");
//		
//		if (str.equals("1")) {//approve one
//			AdminMenuMethod.approveEmp();
//			verificationMenu();
//		} else if (str.equals("2")) {// approve all
//			AdminMenuMethod.approveEmpAll();
//			verificationMenu();
//		} else if (str.equals("3")) {//deny one
//			AdminMenuMethod.denyEmp();
//			verificationMenu();
//		} else if (str.equals("4")) {//deny all
//			AdminMenuMethod.denyEmpAll();
//			verificationMenu();
//		} else if (str.equals("5")) {
//			System.out.println("Return Back to Admin Menu!");
//			adminMenu();
//		} else {
//			System.out
//					.println("---ERROR: Please enter the number within 1-5 \r\n");
//			verificationMenu();
//		}
//		
//	}

	//change code here
	public static void verificationMenu(){
		System.out.println("------Approve/Deny Request Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for approve one employee request");
		System.out.println("2 for approve all employee request");
		System.out.println("3 for deny one employee request");
		System.out.println("4 for deny all employee request");
		System.out.println("5 for return back to previous menu");
		str = OperationUtility.readString("");
		
		if (str.equals("1")) {//approve one
			AdminMenuMethod.approveEmp();
			verificationMenu();
		} else if (str.equals("2")) {// approve all
			AdminMenuMethod.approveEmpAll();
			verificationMenu();
		} else if (str.equals("3")) {//deny one
			AdminMenuMethod.denyEmp();
			verificationMenu();
		} else if (str.equals("4")) {//deny all
			AdminMenuMethod.denyEmpAll();
			verificationMenu();
		} else if (str.equals("5")) {
			System.out.println("Return Back to Admin Menu!");
			adminMenu();
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-5 \r\n");
			verificationMenu();
		}
		
	}
	
//	public static void managerMenu(int empID) {
//		String str = null;
//		System.out.println("------Manager Menu------");
//		System.out.println("Please Select the following options:");
//		System.out.println("1 for view all employee information");//view all group with manager id
//		System.out.println("2 for update employee information");//view emp by group
//		System.out.println("3 for special query employee information");
//		System.out.println("4 for manage group information");
//		System.out.println("5 for log out");
//		str = OperationUtility.readString("");
//
//			if (str.equals("1")) {
//			CommonMenuMethod.viewEmp();
//			managerMenu(empID);
//		} else if (str.equals("2")) {
//			ManagerMenuMethod.updateEmp();
//			managerMenu(empID);
//		} else if (str.equals("3")) {
//			Main.Employees.clear();///////!!!!!!!!!!!!
//			OperationUtility.getInfoFromDB();
//			specialQueryMenu();
//		} else if (str.equals("4")) {
//			manageGroupMenu(empID);
//		} else if (str.equals("5")) {
//			System.out.println("Log out Successfully!");
//			mainMenu();
//		} else {
//			System.out
//					.println("---ERROR: Please enter the number within 1-5 \r\n");
//			managerMenu(empID);
//		}
//	}
	
	public static void managerMenu(String email) {
		String str = null;
		System.out.println("------Manager Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for view all employee information");//view all group with manager id
		System.out.println("2 for update employee information");//view emp by group
		System.out.println("3 for special query employee information");
		System.out.println("4 for manage group information");
		System.out.println("5 for log out");
		str = OperationUtility.readString("");

			if (str.equals("1")) {
			CommonMenuMethod.viewEmp();
			managerMenu(email);
		} else if (str.equals("2")) {
			ManagerMenuMethod.updateEmp();
			managerMenu(email);
		} else if (str.equals("3")) {
			Main.Employees.clear();///////!!!!!!!!!!!!
			OperationUtility.getInfoFromDB();
			specialQueryMenu(email);
		} else if (str.equals("4")) {
			manageGroupMenu(email);
		} else if (str.equals("5")) {
			System.out.println("Log out Successfully!");
			mainMenu();
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-5 \r\n");
			managerMenu(email);
		}
	}

//	private static void manageGroupMenu(int empID) {
//		// TODO Auto-generated method stub
//		System.out.println("------Manage Group Menu------");
//		System.out.println("Please Select the following options:");
//		System.out.println("1 for create a new group");
//		System.out.println("2 for add employee");
//		System.out.println("3 for delete employee");
//		System.out.println("4 for view all employee within group");
//		System.out.println("5 for drop a group");
//		System.out.println("6 for return back to previous menu");
//		str = OperationUtility.readString("");
//		
//		if (str.equals("1")) { //create
//			ManagerMenuMethod.createGroup(empID);
//			manageGroupMenu(empID);
//		} else if (str.equals("2")) {//add emp
//			
//			ManagerMenuMethod.addEmpToGroup(empID);
//			manageGroupMenu(empID);
//		} else if (str.equals("3")) {//remove emp
//			
//			ManagerMenuMethod.removeEmpFromGroup(empID);
//			manageGroupMenu(empID);
//		} else if (str.equals("4")) { //view all
//			
//			ManagerMenuMethod.viewEmp(empID);
//			manageGroupMenu(empID);
//		} else if (str.equals("5")) { //drop
//		
//			ManagerMenuMethod.dropGroup(empID);
//			manageGroupMenu(empID);
//		} else if (str.equals("6")) {
//			System.out.println("Return Back to Manager Menu!");
//			managerMenu(empID);
//		} else {
//			System.out
//					.println("---ERROR: Please enter the number within 1-6 \r\n");
//			manageGroupMenu(empID);
//
//		}
//		
//	}
	
	private static void manageGroupMenu(String email) {
		// TODO Auto-generated method stub
		System.out.println("------Manage Group Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for create a new group");
		System.out.println("2 for add employee");
		System.out.println("3 for delete employee");
		System.out.println("4 for view all employee within group");
		System.out.println("5 for drop a group");
		System.out.println("6 for return back to previous menu");
		str = OperationUtility.readString("");
		
		if (str.equals("1")) { //create
			ManagerMenuMethod.createGroup(email);
			manageGroupMenu(email);
		} else if (str.equals("2")) {//add emp
			
			ManagerMenuMethod.addEmpToGroup(email);
			manageGroupMenu(email);
		} else if (str.equals("3")) {//remove emp
			
			ManagerMenuMethod.removeEmpFromGroup(email);
			manageGroupMenu(email);
		} else if (str.equals("4")) { //view all
			
			ManagerMenuMethod.viewEmp(email);
			manageGroupMenu(email);
		} else if (str.equals("5")) { //drop
		
			ManagerMenuMethod.dropGroup(email);
			manageGroupMenu(email);
		} else if (str.equals("6")) {
			System.out.println("Return Back to Manager Menu!");
			managerMenu(email);
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-6 \r\n");
			manageGroupMenu(email);

		}
		
	}

	public static void specialQueryMenu(String email) {
		System.out.println("------Special Query Menu------");
		System.out.println("Please Select the following options:");
		System.out.println("1 for see employee information by department");
		System.out.println("2 for see employee informaiton by ID");
		System.out.println("3 for query by special conditions");
		System.out.println("4 for return back to previous menu");
		str = OperationUtility.readString("");

		if (str.equals("1")) {
			
			SpecialQueryMenuMethod.viewEmpByDept();
			specialQueryMenu(email);
		} else if (str.equals("2")) {
			
			SpecialQueryMenuMethod.viewEmpById();
			specialQueryMenu(email);
		} else if (str.equals("3")) {
			
			SpecialQueryMenuMethod.getEmpByCondition();
			specialQueryMenu(email);
		} else if (str.equals("4")) {
			System.out.println("Return Back to Manager Menu!");
			managerMenu(email);
		} else {
			System.out
					.println("---ERROR: Please enter the number within 1-4 \r\n");
			specialQueryMenu(email);

		}
	}


}
