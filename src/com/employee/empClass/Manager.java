package com.employee.empClass;

import com.employee.empClass.Employee;

public class Manager extends Employee {

	public Manager(String role, int id, String password, String fname, String lname, 
			String gender, int age, String email, String phoneNum, String address, 
			String verification, int groupID, String dept, int numOfEmp) {
		super(role, id, password, fname, lname, gender, age, email, phoneNum, address, verification, groupID);
		this.dept = dept;
		this.numOfEmp = numOfEmp;
		// TODO Auto-generated constructor stub
	}

	private String dept;
	private int numOfEmp;

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getNumOfEmp() {
		return numOfEmp;
	}

	public void setNumOfEmp(int numOfEmp) {
		this.numOfEmp = numOfEmp;
	}

	public String toString() {
		
		return super.toString() + "  ||  Department Name: " + this.getDept()
				+ "  ||  Number of Employee: " + this.getNumOfEmp() + "\r\n";
	}
	
	public String toString(String para) {
		return super.toString();
	}

}

