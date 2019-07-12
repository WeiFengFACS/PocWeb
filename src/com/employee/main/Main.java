package com.employee.main;

import java.util.ArrayList;
import java.util.List;

import com.employee.empClass.Employee;

public class Main {

	public static List<Employee> Employees = new ArrayList<Employee>();

	public static List<Employee> approvedEmployees = new ArrayList<Employee>();
	
	public static void main(String[] args) {

		Menu.mainMenu();
	}

}
