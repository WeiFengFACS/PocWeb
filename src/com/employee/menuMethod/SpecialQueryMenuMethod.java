package com.employee.menuMethod;

import java.util.ArrayList;
import java.util.List;

import com.employee.Validation.ValidationUtility;
import com.employee.empClass.Developer;
import com.employee.empClass.Employee;
import com.employee.empClass.QA;
import com.employee.main.Main;
import com.employee.main.OperationUtility;

public class SpecialQueryMenuMethod {  ////Admin checked
	static String str = null;
	static String field = null;
	static String role = null;
	//static int empID = 0;
	
	public static void viewEmpByDept() {///checked 
//		Main.Employees.clear();
//		OperationUtility.getInfoFromDB();
		do {
			do {
				System.out.println("Please Enter the Department Name: ");
				System.out.println("manager/developer/qa/training/admin");////
				str = OperationUtility.readString("");
				// System.out.println(checker);
			} while (!ValidationUtility.checkNotNull(str));
		} while (!ValidationUtility.checkDept(str));
		List<Employee> e = OperationUtility.getEmpByDept(str);
		System.out.println(e.toString());
	}
	
	public static void viewEmpById() { //checked
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		
		String empEmail = null;
		 do {
			  empEmail = OperationUtility.askForInput();
			 if (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail)) {
					System.out.println("Employee information not found");
				}
			} while (!ValidationUtility.checkEmailExisted(Main.Employees, empEmail));
		// System.out.println("Inside menu " + empID);
		Employee e = OperationUtility.selectEmployeeById(empEmail);
		System.out.println(e.toString());
	}
	
	public static void getEmpByCondition() {
		// do{
		Main.Employees.clear();
		OperationUtility.getInfoFromDB();
		
		List<Employee> EmployeeByDept = null;
		do {
			System.out.println("Query Base on Field?: ");
			System.out
					.println("lname - last name/fname - first name/g - gender/\r\n"
							+ "a - age/exp - experiences/tech - technology/\r\n"
							+ "t - tools/qp - QA project name/tp - Training project name");
			// System.out.println("manager/developer/qa/training");
			str = OperationUtility.readString("");
			// System.out.println(checker);
		} while (!ValidationUtility.checkNotNull(str));
		str = str.toLowerCase();
		int len = Main.Employees.size();
		if (len != 0) {
			List<Employee> EmployeeByCond = new ArrayList<Employee>();

			switch (str) {

			case "lname":
				do {
					System.out.println("Please enter the last name");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkNotNull(str));
				str = str.toLowerCase();
				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (e.getLName().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "fname":
				do {
					System.out.println("Please enter the first name");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkNotNull(str));
				str = str.toLowerCase();
				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (e.getFName().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "g":
				do {
					System.out
							.println("Please enter the condition. (female/male)");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkGender(str));
				str = str.toLowerCase();
				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (e.getGender().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "a":
				do {
					System.out
							.println("Please enter the youngest age you want to see");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkInputRange(str));
				int ageY = Integer.parseInt(str);
				do {
					System.out
							.println("Please enter the oldest age you want to see");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkInputRange(str));
				int ageO = Integer.parseInt(str);

				for (int i = 0; i < len; i++) {
					Employee e = Main.Employees.get(i);
					if (ageY <= e.getAge() && e.getAge() <= ageO) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "exp":
				do {
					System.out.println("Please enter the experience condition");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkInputRange(str));
				int exp = Integer.parseInt(str);

				EmployeeByDept = OperationUtility.getEmpByDept("developer");

				for (int i = 0; i < EmployeeByDept.size(); i++) {
					Developer e = (Developer) EmployeeByDept.get(i);
					if (e.getExp() >= exp) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "tech":
				do {
					System.out.println("Please enter the technology: ");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkNotNull(str));
				str = str.toLowerCase();
				EmployeeByDept = OperationUtility.getEmpByDept("developer");

				for (int i = 0; i < EmployeeByDept.size(); i++) {
					Developer e = (Developer) EmployeeByDept.get(i);
					if (e.getTech().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "t":
				do {
					System.out.println("Please enter the tool name:");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkNotNull(str));
				str = str.toLowerCase();
				EmployeeByDept = OperationUtility.getEmpByDept("qa");

				for (int i = 0; i < EmployeeByDept.size(); i++) {
					QA e = (QA) EmployeeByDept.get(i);
					if (e.getTool().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

			case "qp":
				do {
					System.out.println("Please enter the project name:");
					str = OperationUtility.readString("");
					// System.out.println(checker);
				} while (!ValidationUtility.checkNotNull(str));
				str = str.toLowerCase();
				EmployeeByDept = OperationUtility.getEmpByDept("qa");

				for (int i = 0; i < EmployeeByDept.size(); i++) {
					QA e = (QA) EmployeeByDept.get(i);
					if (e.getProject().equals(str)) {
						EmployeeByCond.add(e);
					}
				}
				break;

//			case "tp":
//				do {
//					System.out.println("Please enter the project name:");
//					str = OperationUtility.readString("");
//					// System.out.println(checker);
//				} while (!ValidationUtility.checkNotNull(str));
//				str = str.toLowerCase();
//				EmployeeByDept = OperationUtility.getEmpByDept("training");
//
//				for (int i = 0; i < EmployeeByDept.size(); i++) {
//					Training e = (Training) EmployeeByDept.get(i);
//					if (e.getProjectName().equals(str)) {
//						EmployeeByCond.add(e);
//					}
//				}
//				break;

			}
			if (EmployeeByCond.size() == 0) {
				System.out
						.println("No Employee Qualified for this Condition!\r\n");
			} else {
				System.out.println(EmployeeByCond.toString());
			}
		}

	}

}
