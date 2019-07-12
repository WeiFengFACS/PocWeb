package com.employee.empClass;

import com.employee.empClass.Employee;

public class Developer extends Employee {
	public Developer(String role, int id, String password, String fname, String lname, 
			String gender, int age, String email, String phoneNum, String address, 
			String verification, int groupID, String tech, int exp) {
		super(role, id, password, fname, lname, gender, age, email, phoneNum, address, verification, groupID);
		this.tech = tech;
		this.exp = exp;
		// TODO Auto-generated constructor stub
	}

	private String tech;
	private int exp;

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	//
	public String toString() {
		
		return super.toString() + "  ||  Technology: " + this.getTech()
				+ "  ||  Years of Experiences: " + this.getExp() + "\r\n";
	}


}
