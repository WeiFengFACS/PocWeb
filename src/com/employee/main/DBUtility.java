package com.employee.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.json.simple.JSONObject;
import net.sf.json.JSONObject;

import com.employee.empClass.Developer;
import com.employee.empClass.Employee;
import com.employee.empClass.Manager;
import com.employee.empClass.QA;
import com.employee.main.MySqlConnector;
import com.employee.main.Main;




public class DBUtility {

	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static Connection conn = null;
	public static String st = null;

	public static void addEmpSql(Employee emp) {
		String role = null;
		int empID = 0;
		String password = null;
		String fName = null;
		String lName = null;
		String gender = null;
		// String ageS = null;
		int age = 0;
		String email = null;
		String phoneNum = null;
		String address = null;
		String verification = null;
		int groupID = 0;
		

		String dept = null;
		int numOfEmp = 0;

		String tech = null;
		int exp = 0;

		String tool = null;
		String projectName = null;

		int numOfStudent = 0;

		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();

			role = emp.getRole();
			empID = emp.getId();
			password = emp.getPassword();
			fName = emp.getFName();
			lName = emp.getLName();
			gender = emp.getGender();
			age = emp.getAge();
			email = emp.getEmail();
			phoneNum = emp.getPhoneNum();
			address = emp.getAddress();
			verification = emp.getVerification();
			groupID = emp.getGroupID();
			

			switch (role) {
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!1!
			case "admin":
				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + verification + "','"
						+ groupID + "')";
				 System.out.println(st);
				stmt.executeUpdate(st);
				break;
//			
			case "manager":
				Manager man = (Manager) emp;
				dept = man.getDept();
				numOfEmp = man.getNumOfEmp();
				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + dept
						+ "','" + numOfEmp + "','" + verification + "','"
						+ groupID + "')";
				 System.out.println(st);
				stmt.executeUpdate(st);
				break;

			case "developer":
				Developer dev = (Developer) emp;
				tech = dev.getTech();
				exp = dev.getExp();

				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + tech
						+ "','" + exp + "','" + verification + "','" + groupID
						+ "')";
				// System.out.println("Sql Statement: " + st);
				stmt.executeUpdate(st);
				System.out.println(st);
				break;

			case "qa":
				QA qa = (QA) emp;
				tool = qa.getTool();
				projectName = qa.getProject();

				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + tool
						+ "','" + projectName + "','" + verification + "','"
						+ groupID + "')";
				stmt.executeUpdate(st);
				System.out.println(st);
				break;

//			case "training":
//				Training tr = (Training) emp;
//
//				projectName = tr.getProjectName();
//				numOfStudent = tr.getNumOfStudent();
//
//				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
//						+ empID + "' , '" + password + "','" + fName + "','"
//						+ lName + "','" + gender + "','" + age + "','" + email
//						+ "','" + phoneNum + "','" + address + "','"
//						+ projectName + "','" + numOfStudent + "','"
//						+ verification + "','" + groupID + "')";
//				stmt.executeUpdate(st);
//				System.out.println(st);
//				break;
				
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}
//
//	public static void deleteEmpSql(String tname, int id) {//admin checked
//
//		conn = MySqlConnector.Connect();
//		try {
//
//			stmt = conn.createStatement();
//			st = "DELETE FROM " + tname + " WHERE id = " + id;
//			stmt.executeUpdate(st);
//
//		} catch (SQLException ex) {
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		} finally {
//			MySqlConnector.closeConnection(conn, stmt, rs);
//		}
//
//	}
	
	public static void deleteEmpSql(String tname, String empEmail) {//admin checked

		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();
			st = "DELETE FROM " + tname + " WHERE email = '" + empEmail + "'";
			System.out.println(st);
			stmt.executeUpdate(st);

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}
	


	public static void getEmpSql(String tName) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT * FROM " + tName;
			// System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
				String gender = (rs.getString("gender"));
				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
				String phoneNum = (rs.getString("phoneNum"));
				String address = (rs.getString("address"));
				String verification = (rs.getString("verification"));
				int groupID = Integer.parseInt(rs.getString("groupID"));
				
				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
				if (role.equals("admin")){
					Employee emp = new Employee(role, empID, password, fName,
							lName, gender, age, email, phoneNum, address,
							verification, groupID);
					Main.Employees.add(emp);
				} else
				
				
				if (role.equals("manager")) {
					String dept = (rs.getString("dept"));
					int numOfEmp = Integer.parseInt(rs.getString("numofemp"));
					Manager man = new Manager(role, empID, password, fName,
							lName, gender, age, email, phoneNum, address,
							verification, groupID,dept, numOfEmp);

					//Manager manBasic = new Manager(role, empID, fName, lName, email, groupID);

					Main.Employees.add(man);
					//Main.EmployeesBasic.add(manBasic);

				} else if (role.equals("developer")) {
					String tech = (rs.getString("tech"));
					int exp = Integer.parseInt(rs.getString("exp"));
					Developer dev = new Developer(role, empID, password, fName,
							lName, gender, age, email, phoneNum, address,
							verification, groupID,tech, exp);

					Main.Employees.add(dev);

				} else if (role.equals("qa")) {
					String tool = (rs.getString("tools"));
					String projectName = (rs.getString("projectname"));
					QA qa = new QA(role, empID, password, fName, lName, gender,
							age, email, phoneNum, address, verification,
							groupID, tool, projectName);

					Main.Employees.add(qa);

				} 
//				else if (role.equals("training")) {
//
//					String projectName = (rs.getString("projectname"));
//					int numOfStudent = Integer.parseInt(rs
//							.getString("numofstudent"));
//					Training tr = new Training(role, empID, password, fName,
//							lName, gender, age, email, phoneNum, address,
//							verification, groupID, projectName, numOfStudent);
//
//					Main.Employees.add(tr);

				//}

			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}

//	public static void updateEmpSql(String tname, int id, String field,//admin checked
//			String newInput) {
//
//		conn = MySqlConnector.Connect();
//		try {
//			stmt = conn.createStatement();
//			if (field.equals("numofemp") || field.equals("experiences")
//					|| field.equals("numofstudent")) {
//				int num = Integer.parseInt(newInput);
//				st = "UPDATE " + tname + " SET " + field + " = '" + num
//						+ "' WHERE id = " + id;
//			} else {
//				st = "UPDATE " + tname + " SET " + field + " = '" + newInput
//						+ "' WHERE id = " + id;
//			}
//			// System.out.println(st);
//			stmt.executeUpdate(st);
//		} catch (SQLException ex) {
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		} finally {
//			MySqlConnector.closeConnection(conn, stmt, rs);
//		}
//	}
	
	public static void updateEmpSql(String tname, String email, String field,//admin checked
			String newInput) {

		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			if (field.equals("numofemp") || field.equals("experiences")
					|| field.equals("numofstudent")) {
				int num = Integer.parseInt(newInput);
				st = "UPDATE " + tname + " SET " + field + " = '" + num
						+ "' WHERE email = '" + email + "'";
			} else {
				st = "UPDATE " + tname + " SET " + field + " = '" + newInput
						+ "' WHERE email = '" + email + "'";
			}
			// System.out.println(st);
			stmt.executeUpdate(st);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}

//	public static void approveEmpSql(String tname, int input, String veri) {//admin checked
//		// TODO Auto-generated method stub
//		conn = MySqlConnector.Connect();
//		try {
//			stmt = conn.createStatement();
//
//			st = "UPDATE " + tname + " SET verification = '" + veri
//					+ "' WHERE id = " + input;
//
//			//System.out.println(st);
//			stmt.executeUpdate(st);
//		} catch (SQLException ex) {
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		} finally {
//			MySqlConnector.closeConnection(conn, stmt, rs);
//		}
//	}

	public static void approveEmpSql(String tname, String empEmail, String veri) {//admin checked
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();

			st = "UPDATE " + tname + " SET verification = '" + veri
					+ "' WHERE email = '" + empEmail + "'";

			//System.out.println(st);
			stmt.executeUpdate(st);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void approveEmpSql(String tname, String veri) {//admin checked
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();

			st = "UPDATE " + tname + " SET verification = '" + veri
					+ "' WHERE verification = '0'";
			System.out.println(st);
			stmt.executeUpdate(st);
			
		
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}

	

	public static void deneyEmpSql(String tname) {//admin checked
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();
			st = "DELETE FROM " + tname + " WHERE verification = '0'";
			stmt.executeUpdate(st);

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}

	
	public static void updateGroupID(String tname, String email, int groupID) {//admin checked
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			
				st = "UPDATE " + tname + " SET groupID = " + groupID + " WHERE email = '" + email + "'";
			// System.out.println(st);
			stmt.executeUpdate(st);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}

	public static void dropGroupID(String tname, int groupID) {   //admin checked
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			
			st = "UPDATE " + tname + " SET groupID = 0  WHERE groupID = " + groupID;
			 //System.out.println(st);
			stmt.executeUpdate(st);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
		
	}
	
	public static void updateURL(String userName, String date, String URL) {
		// TODO Auto-generated method stub
		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();
				st = "UPDATE timesheets SET URL = '" + URL 
						+  "' WHERE email = '" + userName + "' and date = '" + date + "'";
				
				 System.out.println(st);
				stmt.executeUpdate(st);
//			
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void getGroupEmpSql(int startPage, int pageSize, int groupID) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM manager WHERE groupID =" + groupID
					+ " UNION SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM developer WHERE groupID =" + groupID
					+ " UNION SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM qa WHERE groupID =" + groupID
					+ " UNION SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM training WHERE groupID =" + groupID + 
					" limit " + startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
//				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
				String gender = (rs.getString("gender"));
				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
				String phoneNum = (rs.getString("phoneNum"));
				String address = (rs.getString("address"));
//				String verification = (rs.getString("verification"));

				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
		
					Employee emp = new Employee(role, empID, fName,
							lName, gender, age, email, phoneNum, address);
					Main.approvedEmployees.add(emp);
				
				
			
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void addApprovedEmpSql(Employee emp) {
		String role = null;
		int empID = 0;
		String password = null;
		String fName = null;
		String lName = null;
		String gender = null;
		// String ageS = null;
		int age = 0;
		String email = null;
		String phoneNum = null;
		String address = null;
		//String verification = null;
		int groupID = 0;

		String dept = null;
		int numOfEmp = 0;

		String tech = null;
		int exp = 0;

		String tool = null;
		String projectName = null;

		int numOfStudent = 0;

		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();

			role = emp.getRole();
			empID = emp.getId();
			password = emp.getPassword();
			fName = emp.getFName();
			lName = emp.getLName();
			gender = emp.getGender();
			age = emp.getAge();
			email = emp.getEmail();
			phoneNum = emp.getPhoneNum();
			address = emp.getAddress();
			//verification = emp.getVerification();
			groupID = emp.getGroupID();

			switch (role) {
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!1!
			case "admin":
				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','9','"
						+ groupID + "')";
				 System.out.println(st);
				stmt.executeUpdate(st);
				break;
//			
			case "manager":
				Manager man = (Manager) emp;
				dept = man.getDept();
				numOfEmp = man.getNumOfEmp();
				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + dept
						+ "','" + numOfEmp + "','1','"
						+ groupID + "')";
				 System.out.println(st);
				stmt.executeUpdate(st);
				break;

			case "developer":
				Developer dev = (Developer) emp;
				tech = dev.getTech();
				exp = dev.getExp();

				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + tech
						+ "','" + exp + "','2','" + groupID
						+ "')";
				// System.out.println("Sql Statement: " + st);
				stmt.executeUpdate(st);
				System.out.println(st);
				break;

			case "qa":
				QA qa = (QA) emp;
				tool = qa.getTool();
				projectName = qa.getProject();

				st = "INSERT INTO " + role + " VALUES ('" + role + "' , '"
						+ empID + "' , '" + password + "','" + fName + "','"
						+ lName + "','" + gender + "','" + age + "','" + email
						+ "','" + phoneNum + "','" + address + "','" + tool
						+ "','" + projectName + "','3','"
						+ groupID + "')";
				stmt.executeUpdate(st);
				System.out.println(st);
				break;

				
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}
	
	public static void getApprovedEmpSql(int startPage, int pageSize) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT role, empID, fname, lname, email, groupID FROM admin WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM manager WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM developer WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM qa WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM training WHERE verification !=0 limit " 
					+ startPage + " , " + pageSize;
//			st = "SELECT * FROM admin WHERE verification !=0 "
//					+ "UNION SELECT * FROM manager WHERE verification !=0 "
//					+ "UNION SELECT * FROM developer WHERE verification !=0 "
//					+ "UNION SELECT * FROM qa WHERE verification !=0 "
//					+ "UNION SELECT * FROM training WHERE verification !=0 limit" 
//					+ startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
//				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
//				String gender = (rs.getString("gender"));
//				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
//				String phoneNum = (rs.getString("phonenum"));
//				String address = (rs.getString("address"));
//				String verification = (rs.getString("verification"));
				int groupID = Integer.parseInt(rs.getString("groupID"));

				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
		
					Employee emp = new Employee(role, empID, fName,
							lName, email,  groupID);
					Main.approvedEmployees.add(emp);
				
				
			
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void getManagerApprovedEmpSql(int startPage, int pageSize) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT role, empID, fname, lname, email, groupID FROM manager WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM developer WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM qa WHERE verification !=0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM training WHERE verification !=0 limit " 
					+ startPage + " , " + pageSize;
//			st = "SELECT * FROM admin WHERE verification !=0 "
//					+ "UNION SELECT * FROM manager WHERE verification !=0 "
//					+ "UNION SELECT * FROM developer WHERE verification !=0 "
//					+ "UNION SELECT * FROM qa WHERE verification !=0 "
//					+ "UNION SELECT * FROM training WHERE verification !=0 limit" 
//					+ startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
//				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
//				String gender = (rs.getString("gender"));
//				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
//				String phoneNum = (rs.getString("phonenum"));
//				String address = (rs.getString("address"));
//				String verification = (rs.getString("verification"));
				int groupID = Integer.parseInt(rs.getString("groupID"));

				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
		
					Employee emp = new Employee(role, empID, fName,
							lName, email,  groupID);
					Main.approvedEmployees.add(emp);
				
				
			
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void getAvailableEmpSql(int startPage, int pageSize) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM developer WHERE groupID = 0 and verification != 0"
					+ " UNION SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM qa WHERE groupID = 0 and verification != 0"
					+ " UNION SELECT role, empID, fname, lname, gender, age, email, phoneNum, address FROM training WHERE groupID = 0 and verification != 0"
					+ " limit " + startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
//				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
				String gender = (rs.getString("gender"));
				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
				String phoneNum = (rs.getString("phoneNum"));
				String address = (rs.getString("address"));
//				String verification = (rs.getString("verification"));

				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
		
					Employee emp = new Employee(role, empID, fName,
							lName, gender, age, email, phoneNum, address);
					Main.approvedEmployees.add(emp);
				
				
			
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static void getUnapprovedEmpSql(int startPage, int pageSize) {
		conn = MySqlConnector.Connect();
		try {
			stmt = conn.createStatement();
			st = "SELECT role, empID, fname, lname, email, groupID FROM admin WHERE verification =0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM manager WHERE verification =0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM developer WHERE verification =0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM qa WHERE verification =0 "
					+ "UNION SELECT role, empID, fname, lname, email, groupID FROM training WHERE verification =0 limit " 
					+ startPage + " , " + pageSize;
//			st = "SELECT * FROM admin WHERE verification !=0 "
//					+ "UNION SELECT * FROM manager WHERE verification !=0 "
//					+ "UNION SELECT * FROM developer WHERE verification !=0 "
//					+ "UNION SELECT * FROM qa WHERE verification !=0 "
//					+ "UNION SELECT * FROM training WHERE verification !=0 limit" 
//					+ startPage + " , " + pageSize;
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			while (rs.next()) {
				// int numOfEmp = 0;
				String role = (rs.getString("role"));
				int empID = Integer.parseInt(rs.getString("empID"));
//				String password = (rs.getString("password"));
				String fName = (rs.getString("fname"));
				String lName = (rs.getString("lname"));
//				String gender = (rs.getString("gender"));
//				int age = Integer.parseInt(rs.getString("age"));
				String email = (rs.getString("email"));
//				String phoneNum = (rs.getString("phonenum"));
//				String address = (rs.getString("address"));
//				String verification = (rs.getString("verification"));
				int groupID = Integer.parseInt(rs.getString("groupID"));

				
//				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
		
					Employee emp = new Employee(role, empID, fName,
							lName, email,  groupID);
					Main.approvedEmployees.add(emp);
				
				
			
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
	}
	
	public static boolean checkTimesheetExistSql(String email, String date) {
		conn = MySqlConnector.Connect();
		boolean checker = false;
		try {
			
			stmt = conn.createStatement();
			st = "SELECT * FROM timesheets WHERE email = '" + email + "' and date = '" + date + "'";
			 System.out.println(st);
			rs = stmt.executeQuery(st);
			if (rs.next()) {
				checker =  true;
			} else {
				checker =  false;
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}
		System.out.println("in sql !!@@@@!!" + checker);
		return checker;
		
	}
	

	public static void createTimesheetSql(String email, String date, String endDate, String monR, String monOT, 
			String tueR, String tueOT, String wedR, String wedOT, String thuR, String thuOT,
			String friR, String friOT, String satR, String satOT, String sunR, String sunOT, 
			String approve, String URL, String submit, String totalHr, String regularHr, String otHr, String client) {
		
		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();
				st = "INSERT INTO emp.timesheets VALUES ('" + email + "' , '"
						+ date + "','" + endDate +"' , '" + monR + "','" + monOT + "','"
						+ tueR + "','" + tueOT + "','" + wedR + "','" + wedOT
						+ "','" + thuR + "','" + thuOT + "','" + friR
						+ "','" + friOT + "','" + satR + "','" + satOT
						+ "','" + sunR + "','" + sunOT + "','" + approve
						+ "','" + URL + "','" + submit + "','" + totalHr 
						+ "','" + regularHr + "','" + otHr + "','" + client + "')";
				 System.out.println(st);
				stmt.executeUpdate(st);
//			
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}
	
	public static void updateTimesheetSql(String email, String date, String endDate, String monR, String monOT, 
			String tueR, String tueOT, String wedR, String wedOT, String thuR, String thuOT,
			String friR, String friOT, String satR, String satOT, String sunR, String sunOT, 
			String approve, String URL, String submit, String totalHr, String regularHr, String otHr, String client) {
		
		conn = MySqlConnector.Connect();
		try {

			stmt = conn.createStatement();
				st = "UPDATE timesheets SET monR = '" + monR + "' , monOT = '" + monOT 
						+ "' , tueR = '" + tueR + "' , tueOT = '" + tueOT 
						+ "' , wedR = '" + wedR + "' , wedOT = '" + wedOT 
						+ "' , thuR = '" + thuR + "' , thuOT = '" + thuOT 
						+ "' , friR = '" + friR + "' , friOT = '" + friOT 
						+ "' , satR = '" + satR + "' , satOT = '" + satOT 
						+ "' , sunR = '" + sunR + "' , sunOT = '" + sunOT
						+ "' , totalHr = '" + totalHr + "' , regularHr = '" + regularHr
						+ "' , otHr = '" + otHr + "' , client = '" + client
						+ "' WHERE email = '" + email + "' and date = '" + date + "'";
				
				 System.out.println(st);
				stmt.executeUpdate(st);
//			
			
				
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			MySqlConnector.closeConnection(conn, stmt, rs);
		}

	}

	
	
}	
//	 public static String getEmpDetails(int id){
//		        
//		        
//		        JSONObject userDets = new JSONObject();
//		        
//		  try{
//		   PreparedStatement pstmt = null;
//		         String q = "select fname,lname,email from employee where empID=?";
//		         
//		         
//		         pstmt = conn.prepareStatement(q);
//		         pstmt.setInt(1, id);
//		         
//		         ResultSet rs = pstmt.executeQuery();
//
//		         if(rs.next()){
//		        	 userDets.put("id", id);
//		        	 userDets.put("first_name", rs.getString("fname"));
//		        	 userDets.put("last_name", rs.getString("lname"));
//		        	 userDets.put("email", rs.getString("email"));
//		         }
//		         return userDets.toJSONString();
//		         }
//		  catch(Exception ex){
//		   System.out.println(ex.toString());
//		  }
//		  return null;
//		 }

//	public static void getEmpByID(String tName, String email) {
//		// TODO Auto-generated method stub
//		conn = MySqlConnector.Connect();
//		try {
//			stmt = conn.createStatement();
//			st = "SELECT role, email, first, name FROM " + tName + " WHERE email = '" + email + "'";
//			 System.out.println(st);
//			rs = stmt.executeQuery(st);
//			while (rs.next()) {
//				// int numOfEmp = 0;
//				String role = (rs.getString("role"));
//				String fName = (rs.getString("fname"));
//				String lName = (rs.getString("lname"));
//				String email = (rs.getString("email"));
//				
//
//				
////				get admin info from db!!!!!!!!!!!!!!!!!!!!!!
//				if (role.equals("admin")){
//					Employee emp = new Employee(role, empID, password, fName,
//							lName, gender, age, email, phoneNum, address,
//							verification, groupID);
//					Main.Employees.add(emp);
//				} else
//				
//				
//				if (role.equals("manager")) {
//					String dept = (rs.getString("dept"));
//					int numOfEmp = Integer.parseInt(rs.getString("numofemp"));
//					Manager man = new Manager(role, empID, password, fName,
//							lName, gender, age, email, phoneNum, address,
//							verification, groupID, dept, numOfEmp);
//
//					//Manager manBasic = new Manager(role, empID, fName, lName, email, groupID);
//
//					Main.Employees.add(man);
//					//Main.EmployeesBasic.add(manBasic);
//
//				} else if (role.equals("developer")) {
//					String tech = (rs.getString("tech"));
//					int exp = Integer.parseInt(rs.getString("exp"));
//					Developer dev = new Developer(role, empID, password, fName,
//							lName, gender, age, email, phoneNum, address,
//							verification, groupID, tech, exp);
//
//					Main.Employees.add(dev);
//
//				} else if (role.equals("qa")) {
//					String tool = (rs.getString("tools"));
//					String projectName = (rs.getString("projectname"));
//					QA qa = new QA(role, empID, password, fName, lName, gender,
//							age, email, phoneNum, address, verification,
//							groupID, tool, projectName);
//
//					Main.Employees.add(qa);
//
//				} else if (role.equals("training")) {
//
//					String projectName = (rs.getString("projectname"));
//					int numOfStudent = Integer.parseInt(rs
//							.getString("numofstudent"));
//					Training tr = new Training(role, empID, password, fName,
//							lName, gender, age, email, phoneNum, address,
//							verification, groupID, projectName, numOfStudent);
//
//					Main.Employees.add(tr);
//
//				}
//
//			}
//
//		} catch (SQLException ex) {
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		} finally {
//			MySqlConnector.closeConnection(conn, stmt, rs);
//		}
//	}
//
