function Register($scope, $http, ServletCall) {
	$scope.loading = true;
	$scope.commonInfo = true;
	$scope.managerExtra = true;
	$scope.developerExtra = true;
	$scope.QAExtra = true;
	$scope.TrainingExtra = true;
	$scope.empShow = true;
	$scope.resultM = true;
	$scope.gender = 'Male';

	// alert("call register");
	$scope.roles = [ {
		role : 'Admin'
	}, {
		role : 'Manager'
	}, {
		role : 'Developer'
	}, {
		role : 'QA'
	}, {
		role : 'Training'
	} ];

	$scope.checkRole = function() {

		$scope.commonInfo = false;
		if ($scope.chooseRole.role == "Admin") {
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = true;
			$scope.developerExtra = true;
			$scope.QAExtra = true;
			$scope.TrainingExtra = true;
		} else if ($scope.chooseRole.role == "Manager") {
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = false;
			$scope.developerExtra = true;
			$scope.QAExtra = true;
			$scope.TrainingExtra = true;
		} else if ($scope.chooseRole.role == "Developer") {
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = true;
			$scope.developerExtra = false;
			$scope.QAExtra = true;
			$scope.TrainingExtra = true;
		} else if ($scope.chooseRole.role == "QA") {
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = true;
			$scope.developerExtra = true;
			$scope.QAExtra = false;
			$scope.TrainingExtra = true;
		} else if ($scope.chooseRole.role == "Training") {
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = true;
			$scope.developerExtra = true;
			$scope.QAExtra = true;
			$scope.TrainingExtra = false;
		}
	};

	$scope.goLoginPage = function() {
		window.location = "index.html#/Login";
	};
	
	$scope.reset = function() {
		$scope.userName = "";
		$scope.password = "";
		$scope.fName = "";
		$scope.lName = "";
		$scope.age = "";
		$scope.phoneNum = "";
		$scope.address = "";

		// additional info
		$scope.dept = "";
		$scope.numOfEmp = "";
		$scope.skill = "";
		$scope.yearsOfExp = "";
		$scope.tools = "";
		$scope.projectNameQA = "";
		$scope.projectNameT = "";
		$scope.numOfStudent = "";
	};

	$scope.submitRegister = function() {

		if (!checkNotNull($scope.userName)) {
			$scope.errorMessage = "Please enter the email address";
			$scope.errorMessageShow = false;
	
		} else if (!checkIsEmail($scope.userName)) {
			$scope.errorMessage = "Please enter the valid email address";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.password)) {
			$scope.errorMessage ="Please enter the password";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.fName)) {
			$scope.errorMessage ="Please enter the first name";
			$scope.errorMessageShow = false;

		} else if (!checkIsCharacter($scope.fName)) {
			$scope.errorMessage ="Please enter valid first name";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.lName)) {
			$scope.errorMessage ="Please enter the last name";
			$scope.errorMessageShow = false;

		} else if (!checkIsCharacter($scope.lName)) {
			$scope.errorMessage ="Please enter valid last name";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.age)) {
			$scope.errorMessage ="Please enter age";
			$scope.errorMessageShow = false;

		} else if (!checkAgeRange($scope.age)) {
			$scope.errorMessage ="Please enter valid age";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.phoneNum)) {
			$scope.errorMessage ="Please enter phone number";
			$scope.errorMessageShow = false;

		} else if (!checkIsPhoneNum($scope.phoneNum)) {
			$scope.errorMessage ="Please enter valid phone number";
			$scope.errorMessageShow = false;

		} else if (!checkNotNull($scope.address)) {
			$scope.errorMessage ="Please enter the address";
			$scope.errorMessageShow = false;

		} else if ($scope.chooseRole.role == "Admin") {
			$scope.errorMessageShow = true;
			$scope.loading = false;
			$scope.registerForm = true;
			ServletCall.empRegisterFactory($scope.userName, $scope.password,
					$scope.fName, $scope.lName, $scope.chooseRole.role,
					$scope.gender, $scope.age, $scope.phoneNum, $scope.address,
					"0", "0", $scope);
		} else if ($scope.chooseRole.role == "Manager") {

			if (!checkNotNull($scope.dept)) {
				$scope.errorMessage ="Please enter department name";
				$scope.errorMessageShow = false;

			} else if (!checkNotNull($scope.numOfEmp)) {
				$scope.errorMessage ="Please enter number of employees";
				$scope.errorMessageShow = false;

			} else if (!checkIsInteger($scope.numOfEmp)) {
				$scope.errorMessage ="Please enter valid employee numbers";
				$scope.errorMessageShow = false;

			} else {

				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.empRegisterFactory($scope.userName,
						$scope.password, $scope.fName, $scope.lName,
						$scope.chooseRole.role, $scope.gender, $scope.age,
						$scope.phoneNum, $scope.address, $scope.dept,
						$scope.numOfEmp, $scope);
			}
		} else if ($scope.chooseRole.role == "Developer") {
		
			if (!checkNotNull($scope.skill)) {
				$scope.errorMessage ="Please enter the technology";
				$scope.errorMessageShow = false;

			} else if (!checkNotNull($scope.yearsOfExp)) {
				$scope.errorMessage ="Please enter years of experiences";
				$scope.errorMessageShow = false;

			} else if (!checkExpRange($scope.yearsOfExp)) {
				$scope.errorMessage ="Please enter valid years";
				$scope.errorMessageShow = false;

			} else if ($scope.yearsOfExp > $scope.age) {
				$scope.errorMessage ="Years of experience couldn't larger than age";
				$scope.errorMessageShow = false;

			} else if (($scope.age - $scope.yearsOfExp) < 15) {
				$scope.errorMessage ="please check the years";
				$scope.errorMessageShow = false;
			} else {

				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.empRegisterFactory($scope.userName,
						$scope.password, $scope.fName, $scope.lName,
						$scope.chooseRole.role, $scope.gender, $scope.age,
						$scope.phoneNum, $scope.address, $scope.skill,
						$scope.yearsOfExp, $scope);
			}
		} else if ($scope.chooseRole.role == "QA") {

			if (!checkNotNull($scope.tools)) {
				$scope.errorMessage ="Please enter tool name";
				$scope.errorMessageShow = false;

			} else if (!checkNotNull($scope.projectNameQA)) {
				$scope.errorMessage ="Please enter project name";
				$scope.errorMessageShow = false;

			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.empRegisterFactory($scope.userName,
						$scope.password, $scope.fName, $scope.lName,
						$scope.chooseRole.role, $scope.gender, $scope.age,
						$scope.phoneNum, $scope.address, $scope.tools,
						$scope.projectNameQA, $scope);
			}
		} else if ($scope.chooseRole.role == "Training") {

			if (!checkNotNull($scope.projectNameT)) {
				$scope.errorMessage ="Please enter project name";
				$scope.errorMessageShow = false;

			} else if (!checkNotNull($scope.numOfStudent)) {
				$scope.errorMessage ="Please enter number of students";
				$scope.errorMessageShow = false;

			} else if (!checkIsInteger($scope.numOfStudent)) {
				$scope.errorMessage ="Please enter valid student numbers";
				$scope.errorMessageShow = false;

			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.empRegisterFactory($scope.userName,
						$scope.password, $scope.fName, $scope.lName,
						$scope.chooseRole.role, $scope.gender, $scope.age,
						$scope.phoneNum, $scope.address, $scope.projectNameT,
						$scope.numOfStudent, $scope);
			}
		}
	};

	$scope.empRegisterReturn = function(data) {
		$scope.status = status;
		$scope.result = data.ResponseChecker;
	
		if ($scope.result == 0) {
			$scope.resultM = false;
			$scope.resultMessage = data.Message;
			$scope.loading = true;
			$scope.registerForm = false;
		} else {

			$scope.registerForm = true;
			$scope.loading = true;
			$scope.empShow = false;

			$scope.successMessage = data.Message;
			$scope.email = $scope.userName;
			$scope.showFName = $scope.fName;
			$scope.showLName = $scope.lName;
			$scope.showRole = $scope.chooseRole.role;
			$scope.showGender = $scope.gender;
			$scope.showAge = $scope.age;
			$scope.showPhoneNum = $scope.phoneNum;
			$scope.showAddress = $scope.address;
		}
	};
	
	
}


//function Register($scope, $http, ServletCall) {
//	$scope.commonInfo = true;
//	$scope.managerExtra = true;
//	$scope.developerExtra = true;
//	$scope.QAExtra = true;
//	$scope.TrainingExtra = true;
//	$scope.empShow = true;
//	$scope.resultM = true;
//	$scope.gender = 'Male';
//
//	// alert("call register");
//	$scope.roles = [ {
//		role : 'Admin'
//	}, {
//		role : 'Manager'
//	}, {
//		role : 'Developer'
//	}, {
//		role : 'QA'
//	}, {
//		role : 'Training'
//	} ];
//
//	$scope.checkRole = function() {
//
//		$scope.commonInfo = false;
//		if ($scope.chooseRole.role == "Admin") {
//			$scope.errorMessageShow = true;
//			$scope.resultM = true;
//			$scope.managerExtra = true;
//			$scope.developerExtra = true;
//			$scope.QAExtra = true;
//			$scope.TrainingExtra = true;
//		} else if ($scope.chooseRole.role == "Manager") {
//			$scope.errorMessageShow = true;
//			$scope.resultM = true;
//			$scope.managerExtra = false;
//			$scope.developerExtra = true;
//			$scope.QAExtra = true;
//			$scope.TrainingExtra = true;
//		} else if ($scope.chooseRole.role == "Developer") {
//			$scope.errorMessageShow = true;
//			$scope.resultM = true;
//			$scope.managerExtra = true;
//			$scope.developerExtra = false;
//			$scope.QAExtra = true;
//			$scope.TrainingExtra = true;
//		} else if ($scope.chooseRole.role == "QA") {
//			$scope.errorMessageShow = true;
//			$scope.resultM = true;
//			$scope.managerExtra = true;
//			$scope.developerExtra = true;
//			$scope.QAExtra = false;
//			$scope.TrainingExtra = true;
//		} else if ($scope.chooseRole.role == "Training") {
//			$scope.errorMessageShow = true;
//			$scope.resultM = true;
//			$scope.managerExtra = true;
//			$scope.developerExtra = true;
//			$scope.QAExtra = true;
//			$scope.TrainingExtra = false;
//		}
//	};
//
//	$scope.goLoginPage = function() {
//		window.location = "index.html#/Login";
//	};
//	
//	$scope.reset = function() {
//		$scope.userName = "";
//		$scope.password = "";
//		$scope.fName = "";
//		$scope.lName = "";
//		$scope.age = "";
//		$scope.phoneNum = "";
//		$scope.address = "";
//
//		// additional info
//		$scope.dept = "";
//		$scope.numOfEmp = "";
//		$scope.skill = "";
//		$scope.yearsOfExp = "";
//		$scope.tools = "";
//		$scope.projectNameQA = "";
//		$scope.projectNameT = "";
//		$scope.numOfStudent = "";
//	};
//
//	$scope.submitRegister = function() {
//		// alert("call submit form");
//		if (!checkNotNull($scope.userName)) {
//			$scope.errorMessage = "Please enter the email address";
//			$scope.errorMessageShow = false;
//			//alert("Please enter the email address");
//		} else if (!checkIsEmail($scope.userName)) {
//			$scope.errorMessage = "Please enter the valid email address";
//			$scope.errorMessageShow = false;
////			alert("Please enter the valid email address");
//		} else if (!checkNotNull($scope.password)) {
//			$scope.errorMessage ="Please enter the password";
//			$scope.errorMessageShow = false;
////			alert("Please enter the password");
//		} else if (!checkNotNull($scope.fName)) {
//			$scope.errorMessage ="Please enter the first name";
//			$scope.errorMessageShow = false;
////			alert("Please enter the first name");
//		} else if (!checkIsCharacter($scope.fName)) {
//			$scope.errorMessage ="Please enter valid first name";
//			$scope.errorMessageShow = false;
////			alert("Please enter valid first name");
//		} else if (!checkNotNull($scope.lName)) {
//			$scope.errorMessage ="Please enter the last name";
//			$scope.errorMessageShow = false;
////			alert("Please enter the last name");
//		} else if (!checkIsCharacter($scope.lName)) {
//			$scope.errorMessage ="Please enter valid last name";
//			$scope.errorMessageShow = false;
////			alert("Please enter valid last name");
//		} else if (!checkNotNull($scope.age)) {
//			$scope.errorMessage ="Please enter age";
//			$scope.errorMessageShow = false;
////			alert("Please enter age");
//		} else if (!checkAgeRange($scope.age)) {
//			$scope.errorMessage ="Please enter valid age";
//			$scope.errorMessageShow = false;
////			alert("Please enter valid age");
//		} else if (!checkNotNull($scope.phoneNum)) {
//			$scope.errorMessage ="Please enter phone number";
//			$scope.errorMessageShow = false;
////			alert("Please enter phone number");
//		} else if (!checkIsPhoneNum($scope.phoneNum)) {
//			$scope.errorMessage ="Please enter valid phone number";
//			$scope.errorMessageShow = false;
////			alert("Please enter valid phone number");
//		} else if (!checkNotNull($scope.address)) {
//			$scope.errorMessage ="Please enter the address";
//			$scope.errorMessageShow = false;
////			alert("Please enter the address");
//		} else if ($scope.chooseRole.role == "Admin") {
//			$scope.errorMessageShow = true;
//			ServletCall.empRegisterFactory($scope.userName, $scope.password,
//					$scope.fName, $scope.lName, $scope.chooseRole.role,
//					$scope.gender, $scope.age, $scope.phoneNum, $scope.address,
//					"0", "0", $scope);
//		} else if ($scope.chooseRole.role == "Manager") {
//			// document.getElementById("managerExtra").style.display = "block";
//			if (!checkNotNull($scope.dept)) {
//				$scope.errorMessage ="Please enter department name";
//				$scope.errorMessageShow = false;
////				alert("Please enter department name");
//			} else if (!checkNotNull($scope.numOfEmp)) {
//				$scope.errorMessage ="Please enter number of employees";
//				$scope.errorMessageShow = false;
////				alert("Please enter number of employees");
//			} else if (!checkIsInteger($scope.numOfEmp)) {
//				$scope.errorMessage ="Please enter valid employee numbers";
//				$scope.errorMessageShow = false;
////				alert("Please enter valid employee numbers");
//			} else {
//				// $scope.testcall();
//				$scope.errorMessageShow = true;
//				ServletCall.empRegisterFactory($scope.userName,
//						$scope.password, $scope.fName, $scope.lName,
//						$scope.chooseRole.role, $scope.gender, $scope.age,
//						$scope.phoneNum, $scope.address, $scope.dept,
//						$scope.numOfEmp, $scope);
//			}
//		} else if ($scope.chooseRole.role == "Developer") {
//			// document.getElementById("developerExtra").style.display =
//			// "block";
//			if (!checkNotNull($scope.skill)) {
//				$scope.errorMessage ="Please enter the technology";
//				$scope.errorMessageShow = false;
////				alert("Please enter the technology");
//			} else if (!checkNotNull($scope.yearsOfExp)) {
//				$scope.errorMessage ="Please enter years of experiences";
//				$scope.errorMessageShow = false;
////				alert("Please enter years of experiences");
//			} else if (!checkExpRange($scope.yearsOfExp)) {
//				$scope.errorMessage ="Please enter valid years";
//				$scope.errorMessageShow = false;
////				alert("Please enter valid years");
//			} else if ($scope.yearsOfExp > $scope.age) {
//				$scope.errorMessage ="Years of experience couldn't larger than age";
//				$scope.errorMessageShow = false;
////				alert("Please enter valid years");
//			} else if (($scope.age - $scope.yearsOfExp) < 15) {
//				$scope.errorMessage ="please check the years";
//				$scope.errorMessageShow = false;
//			} else {
//				alert($scope.gender);
//				$scope.errorMessageShow = true;
//				ServletCall.empRegisterFactory($scope.userName,
//						$scope.password, $scope.fName, $scope.lName,
//						$scope.chooseRole.role, $scope.gender, $scope.age,
//						$scope.phoneNum, $scope.address, $scope.skill,
//						$scope.yearsOfExp, $scope);
//			}
//		} else if ($scope.chooseRole.role == "QA") {
//			// document.getElementById("QAExtra").style.display = "block";
//			if (!checkNotNull($scope.tools)) {
//				$scope.errorMessage ="Please enter tool name";
//				$scope.errorMessageShow = false;
////				alert("Please enter tool name");
//			} else if (!checkNotNull($scope.projectNameQA)) {
//				$scope.errorMessage ="Please enter project name";
//				$scope.errorMessageShow = false;
////				alert("Please enter project name");
//			} else {
//				$scope.errorMessageShow = true;
//				ServletCall.empRegisterFactory($scope.userName,
//						$scope.password, $scope.fName, $scope.lName,
//						$scope.chooseRole.role, $scope.gender, $scope.age,
//						$scope.phoneNum, $scope.address, $scope.tools,
//						$scope.projectNameQA, $scope);
//			}
//		} else if ($scope.chooseRole.role == "Training") {
//			// document.getElementById("TrainingExtra").style.display = "block";
//			if (!checkNotNull($scope.projectNameT)) {
//				$scope.errorMessage ="Please enter project name";
//				$scope.errorMessageShow = false;
////				alert("Please enter project name");
//			} else if (!checkNotNull($scope.numOfStudent)) {
//				$scope.errorMessage ="Please enter number of students";
//				$scope.errorMessageShow = false;
////				alert("Please enter number of students");
//			} else if (!checkIsInteger($scope.numOfStudent)) {
//				$scope.errorMessage ="Please enter valid student numbers";
//				$scope.errorMessageShow = false;
////				alert("Please enter valid student numbers");
//			} else {
//				$scope.errorMessageShow = true;
//				ServletCall.empRegisterFactory($scope.userName,
//						$scope.password, $scope.fName, $scope.lName,
//						$scope.chooseRole.role, $scope.gender, $scope.age,
//						$scope.phoneNum, $scope.address, $scope.projectNameT,
//						$scope.numOfStudent, $scope);
//			}
//		}
//	};
//
//	$scope.empRegisterReturn = function(data) {
//		$scope.status = status;
//		$scope.result = data.ResponseChecker;
//		alert("result is " + $scope.result);
//		alert("message is " + data.Message);
//		if ($scope.result == 0) {
//			$scope.resultM = false;
//			$scope.resultMessage = data.Message;
//		} else {
//			$scope.registerForm = true;
//			$scope.empShow = false;
//
//			$scope.successMessage = data.Message;
//			$scope.email = $scope.userName;
//			$scope.showFName = $scope.fName;
//			$scope.showLName = $scope.lName;
//			$scope.showRole = $scope.chooseRole.role;
//			$scope.showGender = $scope.gender;
//			$scope.showAge = $scope.age;
//			$scope.showPhoneNum = $scope.phoneNum;
//			$scope.showAddress = $scope.address;
//		}
//	};
//}

