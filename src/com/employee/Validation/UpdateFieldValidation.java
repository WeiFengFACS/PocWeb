package com.employee.Validation;

public class UpdateFieldValidation {
	
	public static String checkAdminField(String input) {
		String field = null;
		// if (input.matches("e") || input.matches("p") || input.matches("a") ||
		// input.matches("d") || input.matches("n")) {
		// System.out.println("inside vali " + input);
		switch (input) {
		case "w":
			field = "password";
			break;
		case "e":
			field = "email";
			break;
		case "p":
			field = "phonenum";
			break;
		case "a":
			field = "address";
			break;
		case "r":
			field = "return";
			break;
		default:
			field = "null";
			break;
		}
		// } else {
		// System.out.println("---ERROR: Please enter correct options \r\n");
		// field = null;
		// }
		return field;
	}

	public static String checkManagerField(String input) {
		String field = null;
		// if (input.matches("e") || input.matches("p") || input.matches("a") ||
		// input.matches("d") || input.matches("n")) {
		// System.out.println("inside vali " + input);
		switch (input) {
		case "w":
			field = "password";
			break;
		case "e":
			field = "email";
			break;
		case "p":
			field = "phonenum";
			break;
		case "a":
			field = "address";
			break;
		case "d":
			field = "dept";
			break;
		case "n":
			field = "numofemp";
			break;
		case "r":
			field = "return";
			break;
		default:
			field = "null";
			break;
		}
		// } else {
		// System.out.println("---ERROR: Please enter correct options \r\n");
		// field = null;
		// }
		return field;
	}

	public static String checkDeveloperField(String input) {
		String field = null;
		// if (input.matches("e") || input.matches("p") || input.matches("a") ||
		// input.matches("t") || input.matches("x")) {
		switch (input) {
		case "w":
			field = "password";
			break;
		case "e":
			field = "email";
			break;
		case "p":
			field = "phonenum";
			break;
		case "a":
			field = "address";
			break;
		case "t":
			field = "technology";
			break;
		case "x":
			field = "experiences";
			break;
		case "r":
			field = "return";
			break;
		default:
			field = "null";
			break;
		}
		// } else {
		// System.out.println("---ERROR: Please enter correct options \r\n");
		// field = null;
		// }
		return field;
	}
	
	public static String checkQAField(String input) {
		String field = null;
		// if (input.matches("e") || input.matches("p") || input.matches("a") ||
		// input.matches("t") || input.matches("n")) {
		switch (input) {
		case "w":
			field = "password";
			break;
		case "e":
			field = "email";
			break;
		case "p":
			field = "phonenum";
			break;
		case "a":
			field = "address";
			break;
		case "t":
			field = "tools";
			break;
		case "n":
			field = "projectname";
			break;
		case "r":
			field = "return";
			break;
		default:
			field = "null";
			break;
		}
		// } else {
		// System.out.println("---ERROR: Please enter correct options \r\n");
		// field = null;
		// }
		return field;
	}

	public static String checkTrainingField(String input) {
		String field = null;
		// if (input.matches("e") || input.matches("p") || input.matches("a") ||
		// input.matches("t") || input.matches("n")) {
		switch (input) {
		case "w":
			field = "password";
			break;
		case "e":
			field = "email";
			break;
		case "p":
			field = "phonenum";
			break;
		case "a":
			field = "address";
			break;
		case "j":
			field = "projectname";
			break;
		case "n":
			field = "numofstudent";
			break;
		case "r":
			field = "return";
			break;
		default:
			field = "null";
			break;
		}
		// } else {
		// System.out.println("---ERROR: Please enter correct options \r\n");
		// field = null;
		// }
		return field;
	}

}
