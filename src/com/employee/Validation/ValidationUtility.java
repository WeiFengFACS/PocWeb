package com.employee.Validation;

import java.util.List;

import com.employee.empClass.Employee;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

public class ValidationUtility { ////Admin checked

	public static boolean checkCharacter(String input) {
		if (input.matches("^[A-Za-z]+$")) {
			return true;
		} else {
			System.out.println("---ERROR: Please Enter the Character \r\n");
			return false;
		}
	}

	public static boolean checkInteger(String input) {
		if (input.matches("^[0-9]+$")) {
			return true;
		} else {
			System.out.println("---ERROR: Please Enter the Integer \r\n");
			return false;
		}
	}

	public static boolean checkNotNull(String input) {
		if (input!= null && input.length() > 0) {
			return true;
		} else {
			System.out.println("---ERROR: Please Enter the value \r\n");
			return false;
		}
	}

//	public static Boolean checkIsID(String input) {
//		if (input.matches("9[0-9]{3}") || input.matches("1[0-9]{3}")
//				|| input.matches("2[0-9]{3}") || input.matches("3[0-9]{3}")
//				|| input.matches("4[0-9]{3}") || input.matches("9[0-9]{3}")) {
//			return true;
//		} else {
//			System.out
//					.println("---ERROR: Please Enter the valid Employee ID. \r\n");
//			// System.out
//			// .println("(4-digits ID: Manager starts with 1, Developer starts with 2, QA starts with 3) \r\n");
//			return false;
//		}
//	}

	public static Boolean checkSaved() {
		String str = null;
		do {
			System.out
					.println("Do you want to make the action? 1 for Yes, 0 for No");
			str = OperationUtility.readString("");
		} while (!ValidationUtility.checkSaveInput(str));
		if (str.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean checkSaveInput(String input) {
		if (input.matches("1") || input.matches("0")) {
			return true;
		} else {
			System.out.println("---ERROR: Please enter either 1 or 0 \r\n");
			return false;
		}
	}

	public static Boolean checkInputRange(String input) {

		if (input.length() >= 1 && input.length() <= 2) {
			return true;
		} else {
			System.out
					.println("---ERROR: Please enter valid integer within 0 - 99");
			return false;
		}
	}

	public static Boolean checkEmail(String input) {
		if (input
				.matches("^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
			return true;
		} else {
			System.out.println("---ERROR: Please enter valid email address");
			return false;
		}
	}

	public static Boolean checkPhoneNum(String input) {
		if (input.matches("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")) {
			return true;
		} else {
			System.out
					.println("---ERROR: Please enter the format like XXX-XXX-XXXX");
			return false;
		}
	}

	public static Boolean checkGender(String input) {
		if (input.toUpperCase().matches("FEMALE")
				|| input.toUpperCase().matches("MALE")) {
			return true;
		} else {
			System.out
					.println("---ERROR: Please enter either female or male. \r\n");
			return false;
		}
	}

	public static Boolean checkEmpIDExisted(List<Employee> Employees, int empID) {
		Boolean checker = false;
		if (empID == 9999) {
			checker = true;
		} else {
		int len = Employees.size();
		int getID = 0;
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				getID = Employees.get(i).getId();
				if (empID == getID) {
					checker = true;
				}
			}
		} } 
		return checker; 
		
	}

	public static Boolean checkGroupIDExisted(int groupID) {
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		int len = Main.Employees.size();
		int gID = 0;
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				gID = Main.Employees.get(i).getGroupID();
				if (gID == groupID) {
					System.out.println("This group ID has already assigned to other manager");
					return true;
				}
			}
		} 
		return false; 
	}
	
	public static Boolean checkEmailExisted(List<Employee> Employees,
			String email) {
		
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		int len = Employees.size();
		if (email.equals("admin@admin.com")){
			return true;
		} else {
		String getEmail = null;
		if (len != 0) {
			for (int i = 0; i < len; i++) {
				getEmail = Employees.get(i).getEmail();
				if (email.equals(getEmail)) {
//					System.out
//							.println("Email address is already existed! \r\n");
					return true;
				}
			}
		}

		return false;
	}
	}
	

	public static String checkRoleInput(String role){
		role = role.toLowerCase();
		switch(role){
		case "a": return "admin"; 
		case "admin" : return role;
		case "m": return "manager"; 
		case "manager": return role;
		case "d": return "developer"; 
		case "developer": return role;
		case "q": return "qa"; 
		case "qa": return role;
		case "t": return "training"; 
		case "training": return role;
		default: System.out.println("---ERROR: Please enter the valid role. \r\n");
		return "null";
		}
		
	}
	
	public static Boolean checkDept(String dept) {
		dept = dept.toLowerCase();
		if (dept.equals("manager") || dept.equals("developer")
				|| dept.equals("qa") || dept.equals("training") || dept.equals("admin")) {
			return true;
		} else {
			System.out.println("Department name does not exist! \r\n");
			return false;
		}
	}

	

//	public static Boolean checkVerification(int empID) {
//		String verification = OperationUtility.selectEmployeeById(empID)
//				.getVerification();
//		if (!verification.equals("0")) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	public static Boolean checkVerification(String email) {
		String verification = OperationUtility.selectEmployeeById(email)
				.getVerification();
		if (!verification.equals("0")) {
			return true;
		} else {
			return false;
		}
	}
	

//	public static Boolean checkPassword(int empID, String password) {
//		String pws = OperationUtility.selectEmployeeById(empID).getPassword();
//		if (password.equals(pws)) {
//			return true;
//		} else {
//			System.out.println("Password is incorrect! Please try again. \r\n");
//			return false;
//		}
	
	public static Boolean checkPassword(String email, String password) {
//System.out.println("!!!!!!email " + email);
		
		
		
//		System.out.println("PSW " + OperationUtility.selectEmployeeById(email).toString());
//		System.out.println("--------------!!!!!!!!!!!!!!!--------------");
		//System.out.println()
		String pws = OperationUtility.selectEmployeeById(email).getPassword();
//		System.out.println("!!!!!!!!!!!!!!! entered password" + password);
//		System.out.println("!!!!!!!!!!!!!!!   psw = " + pws);
//		System.out.println("check whether is same: " + pws.equals("1234"));
		if (password.equals(pws)) {
			return true;
		} else {
			System.out.println("Password is incorrect! Please try again. \r\n");
			return false;
		}

	}
	
}
