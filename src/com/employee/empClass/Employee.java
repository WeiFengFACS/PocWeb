package com.employee.empClass;



public class Employee {

	private String role;
	private int id;
	private String password;
	private String fname;
	private String lname;
	private String gender;
	private int age;
	private String email;
	private String phoneNum;
	private String address;
	private String verification;
	private int groupID;
	

	public Employee(){
		role = null;
		id = 0;
		password = null;
		fname = null;
		lname = null;
		gender = null;
		age = 0;
		email = null;
		phoneNum = null;
		address = null;
		verification = null;
		groupID = 0;
		
		
	}
	
//	Employee(String role, int id, String password, String fname, String lname, String gender, 
//			int age, String email, String phoneNum, String address, String verification){
	public Employee(String role, int id, String password, String fname, String lname, String gender, 
			int age, String email, String phoneNum, String address, String verification, int groupID){
		this.role = role;
		this.id = id;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.verification = verification;
		this.groupID = groupID;
		
		
	}
	
	public Employee (String role, int id, String fname, String lname, String email, int groupID){
		this.role = role;
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.groupID = groupID;
	}
	
	public Employee(String role, int id, String fname, String lname,
			String gender, int age, String email, String phoneNum,
			String address) {
		this.role = role;
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		// TODO Auto-generated constructor stub
	}

	

	

	public String getRole() {
		return role;
	}


	public int getId() {
		return id;
	}



	public String getPassword(){
	return password;	
	}
	
	public String getFName() {
		return fname;
	}


	public String getLName() {
		return lname;
	}


	
	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getVerification(){
		return verification;
	}
	
	public int getGroupID(){
		return groupID;
	}

	public String toString() {
		return "\r\n- " + this.getRole().toUpperCase() + " INFORMATION -"
				+ "\r\nRole: " + this.getRole()
				+ "  ||  Employee ID: " + this.getId() 
				+ "  ||  Password: " + this.getPassword()
				+ "\r\nFirst Name: " + this.getFName() 
				+ "  ||  Last Name: " + this.getLName()
				+ "  ||  Gender: " + this.getGender()
				+ "\r\nAge: " + this.getAge()
				+ "  ||  Email: " + this.getEmail()
				+ "  ||  Phone Number: " + this.getPhoneNum()
				+ "\r\nAddress: " + this.getAddress()
				+ "  ||  Group ID: " + this .getGroupID() + "\r\n";
	}
	

}