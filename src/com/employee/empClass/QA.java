package com.employee.empClass;

import com.employee.empClass.Employee;

public class QA extends Employee {
	
	public QA(String role, int id, String password, String fname, String lname, 
			String gender, int age, String email, String phoneNum, String address, 
			String verification, int groupID, String tools, String projectName) {
		super(role, id, password, fname, lname, gender, age, email, phoneNum, address, verification, groupID);
		this.tools = tools;
		this.projectName = projectName;
		// TODO Auto-generated constructor stub
	}

	private String tools;
	private String projectName;

	public String getTool() {
		return tools;
	}

	public void setTool(String tools) {
		this.tools = tools;
	}
	
	public String getProject() {
		return projectName;
	}

	public void setproject(String projectName) {
		this.projectName = projectName;
	}
	
	public String toString() {

		return super.toString() + "  ||  Tool Name: " + this.getTool()
				+ "  ||  Current Project Name: " + this.getProject() + "\r\n";
	}

	public String toString(String para) {
		return super.toString();
	}
}
