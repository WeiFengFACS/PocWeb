function AdminMenu($scope, $routeParams, ServletCall, TimesheetServletCall,
		$modal, $log) {

	$scope.params = $routeParams;
	$scope.loading = false;
	$scope.empShow = true;
	$scope.allEmpPart = true;
	$scope.addEmpPart = true;
	$scope.registerShow = true;
	$scope.groupInfoPart = true;
	$scope.groupDetail = true;
	$scope.deleteEmpPart = true;
	$scope.empRequestPart = true;
	$scope.requestMessage = true;
	$scope.groupMessage = true;
	$scope.timesheetInfoShow = true;
	$scope.noTimesheetPart = true;
	// alert($scope.params.userName);

	// !!!!!!!!!!!!!!!!! Check Login Servlet Call
	// $scope.ckeckLogIn($scope.params.userName);
	ServletCall.ckeckLogInFactory($scope.params.userName, $scope);

	$scope.ckeckLogInReturn = function(data) {
		if (data == "false") {
			window.location = "index.html#/Login";
		} else {
			$scope.homeButton = true;
			$scope.adminButton = false;
		}
		;
	};

	// !!!!!!!!!!!!!!!!!!Logout Servlet call
	$scope.logOut = function() {
		ServletCall.logOutFactory($scope.params.userName);
	};

	// Return getInfoByIDFactory Data

	// After change
	$scope.getInfoByIDReturn1 = function(data) {
		if (data.ResponseChecker == "0") {
			$scope.resultMessageShow = false;
			$scope.resultMessage = data.Message;
			$scope.empShow = true;
		} else {
			$scope.resultMessageShow = true;
			$scope.userNameInput = "";
			$scope.user = [];
			$scope.user = data;
			$scope.open1();
		}
	};

	$scope.getInfoByIDReturn = function(data) {
		if (data.ResponseChecker == "0") {
			$scope.resultMessageShow = false;
			$scope.resultMessage = data.Message;
			$scope.empShow = true;
		} else {
			$scope.loading = true;
			$scope.empShow = false;
			$scope.resultMessageShow = true;
			$scope.email = data.userName;
			$scope.showFName = data.fName;
			$scope.showLName = data.lName;
			$scope.showRole = data.role;
			$scope.showEmpNum = data.empNum;
			$scope.showGender = data.gender;
			$scope.showAge = data.age;
			$scope.showPhoneNum = data.phoneNum;
			$scope.showAddress = data.address;
			$scope.showGroupID = data.groupID;
		}
	};

	$scope.open1 = function() {
		var modalInstance = $modal.open({
			templateUrl : 'myModalContentAdmin.html',
			controller : ModalInstanceCtrlAdmin,
			resolve : {
				users : function() {
					return $scope.user;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	// !!!!! Get Personal Info when first load page
	// !!!!!! getInfoByIDFactory call
	ServletCall.getInfoByIDFactory($scope.params.userName, "default", $scope);

	// !!! Get Employee Info Button Call
	$scope.getPersonalInfo = function() {
		$scope.loading = false;
		$scope.empShow = true;
		$scope.allEmpPart = true;
		$scope.addEmpPart = true;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = true;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;

		ServletCall.getInfoByIDFactory($scope.params.userName, "default",
				$scope);
	};

	// !!!! Dropdown List for choose records per page
	$scope.pages = [ {
		pageNumber : '5'
	}, {
		pageNumber : '10'
	}, {
		pageNumber : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPage = $scope.pages[0];

	$scope.setPage = function(changePage) {
		$scope.checkRowPerPage(changePage);
	};

	$scope.setPageDelete = function(changePage) {
		$scope.checkRowPerPageDelete(changePage);
	};

	// !!!! View All employee button call
	$scope.checkRowPerPage = function(curPage) {

		$scope.currentPageViewAll = curPage;
//		alert("current page is " + $scope.currentPageViewAll);
		$scope.loading = false;
		$scope.addEmpPart = true;
		$scope.empShow = true;
		$scope.allEmpPart = false;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = true;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.resultMessageShow = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;

		ServletCall.getTotalEmpNumberFactory(curPage,
				$scope.rowPerPage.pageNumber, "viewAll", $scope);
	};
	$scope.items = [ 'Item 1', 'Item 2', 'Item 3' ];
	// !!!! Dropdown List for choose records per page in delete
	$scope.pagesDelete = [ {
		pageNumberDelete : '5'
	}, {
		pageNumberDelete : '10'
	}, {
		pageNumberDelete : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPageDelete = $scope.pagesDelete[0];

	// !!!! Delete Employee Button Call
	$scope.checkRowPerPageDelete = function(curPage) {
		$scope.currentPageDelete = curPage;
//		alert("curPage in checkRowPerPageDelete is " + $scope.currentPageDelete);
		$scope.loading = false;
		$scope.empShow = true;
		$scope.allEmpPart = true;
		$scope.addEmpPart = true;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = false;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.deleteMessageShow = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;

		ServletCall.getTotalEmpNumberFactory(curPage,
				$scope.rowPerPageDelete.pageNumberDelete, "delete", $scope);
	};

	$scope.getTotalEmpNumberReturn = function(curPage, rowPerPage, operation,
			data) {
		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}
		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;
		if (operation == "request") {
			ServletCall.empRequestFactory(curPage, rowPerPage, $scope);
		} else {
			ServletCall.viewAllEmpFactory(curPage, rowPerPage, operation, $scope);
		}
	};

	
	
	$scope.viewAllEmpDeleteReturn = function(data, curPage) {
//		alert("after get data curpage is " + curPage);
//		alert("date back from view all is " + data.length);
//		alert("check statement is "+ (data.length == 0));
		if (data.length == 0) {
			$scope.checkRowPerPageDelete(1);

		} else {
			$scope.allEmp = []; // get all employee Json data
			$scope.allEmp = data;
			$scope.loading = true;
		}
	};
	
	$scope.viewAllEmpReturn = function(data, curPage) {
			$scope.allEmp = []; // get all employee Json data
			$scope.allEmp = data;
			$scope.loading = true;
	};

	// !!!! View Deatil button call in viewAll Page
	$scope.viewDetail = function(userName) {
		
		ServletCall.getInfoByIDFactory(userName, "search", $scope);
	};

	// !!!! View emp by ID in viewAll Page
	$scope.viewEmpByID = function() {
		if (!checkNotNull($scope.userNameInput)) {
			$scope.resultMessage = "Please enter the email address";
			$scope.resultMessageShow = false;
		} else if (!checkIsEmail($scope.userNameInput)) {
			$scope.resultMessage = "Please enter valid email address";
			$scope.resultMessageShow = false;
		} else {
//			alert($scope.userNameInput);
			ServletCall.getInfoByIDFactory($scope.userNameInput, "search",
					$scope);
		}
	};

	// !!!! Delete Emp by ID in Delete emp page
	$scope.deleteEmployeeById = function() {
		if (!checkNotNull($scope.deleteUsername)) {
			$scope.deleteMessage = "Please enter the email address";
			$scope.deleteMessageShow = false;
		} else if (!checkIsEmail($scope.deleteUsername)) {
			$scope.deleteMessage = "Please enter valid email address";
			$scope.deleteMessageShow = false;
		} else {
			if (confirm("Do you want to delete this employee?")) {
				ServletCall.removeEmpFactory($scope.deleteUsername, "enter",
						$scope);
			}
		}
	};

	$scope.removeEmpByEmterReturn = function(data) {
		if (data.Status == 0) {
			$scope.deleteMessageShow = false;
			$scope.deleteMessage = data.Message;
		} else {
			$scope.deleteMessageShow = true;
			//alert(data.Message);
			$scope.checkRowPerPageDelete(1);
		}
	};

	$scope.removeEmpByClickReturn = function(data) {
		if (data.Status == 0) {
			$scope.deleteMessageShow = false;
			$scope.deleteMessage = data.Message;
		} else {
			//alert(data.Message);
			$scope.checkRowPerPageDelete($scope.currentPageDelete);
		}
	};

	// !!!! Delete emp button call in Delete emp Page
	$scope.deleteEmployee = function(userName) {
		
		if (confirm("Do you want to delete this employee?")) {
			ServletCall.removeEmpFactory(userName, "click", $scope);
		}
	};

	// !!!! Add emp button call
	$scope.roles = [ {
		role : 'None'
	}, {
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

	$scope.addEmp = function() {
		$scope.loading = true;
		$scope.empShow = true;
		$scope.allEmpPart = true;
		$scope.addEmpPart = false;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = true;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;
		$scope.registerForm = false;
		$scope.chooseRole = $scope.roles[0];

		$scope.commonInfo = true;
		$scope.managerExtra = true;
		$scope.developerExtra = true;
		$scope.QAExtra = true;
		$scope.TrainingExtra = true;
		$scope.registerShow = true;
		$scope.resultM = true;
		$scope.genderAdd = 'Male';
		$scope.reset();
	};

	$scope.reset = function() {
		$scope.userNameAdd = "";
		$scope.passwordAdd = "";
		$scope.fNameAdd = "";
		$scope.lNameAdd = "";
		$scope.ageAdd = "";
		$scope.phoneNumAdd = "";
		$scope.addressAdd = "";

		// additional info
		$scope.deptAdd = "";
		$scope.numOfEmpAdd = "";
		$scope.skillAdd = "";
		$scope.yearsOfExpAdd = "";
		$scope.toolsAdd = "";
		$scope.projectNameQAAdd = "";
		$scope.projectNameTAdd = "";
		$scope.numOfStudentAdd = "";
	};

	// Employee Role data

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
		} else {
			$scope.commonInfo = true;
			$scope.errorMessageShow = true;
			$scope.resultM = true;
			$scope.managerExtra = true;
			$scope.developerExtra = true;
			$scope.QAExtra = true;
			$scope.TrainingExtra = true;
		}
	};

	$scope.submitRegister = function() {
		if (!checkNotNull($scope.userNameAdd)) {
			$scope.errorMessage = "Please enter the email address";
			$scope.errorMessageShow = false;
		} else if (!checkIsEmail($scope.userNameAdd)) {
			$scope.errorMessage = "Please enter the valid email address";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.passwordAdd)) {
			$scope.errorMessage = "Please enter the password";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.fNameAdd)) {
			$scope.errorMessage = "Please enter the first name";
			$scope.errorMessageShow = false;
		} else if (!checkIsCharacter($scope.fNameAdd)) {
			$scope.errorMessage = "Please enter valid first name";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.lNameAdd)) {
			$scope.errorMessage = "Please enter the last name";
			$scope.errorMessageShow = false;
		} else if (!checkIsCharacter($scope.lNameAdd)) {
			$scope.errorMessage = "Please enter valid last name";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.ageAdd)) {
			$scope.errorMessage = "Please enter age";
			$scope.errorMessageShow = false;
		} else if (!checkAgeRange($scope.ageAdd)) {
			$scope.errorMessage = "Please enter valid age";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.phoneNumAdd)) {
			$scope.errorMessage = "Please enter phone number";
			$scope.errorMessageShow = false;
		} else if (!checkIsPhoneNum($scope.phoneNumAdd)) {
			$scope.errorMessage = "Please enter valid phone number";
			$scope.errorMessageShow = false;
		} else if (!checkNotNull($scope.addressAdd)) {
			$scope.errorMessage = "Please enter the address";
			$scope.errorMessageShow = false;
		} else if ($scope.chooseRole.role == "Admin") {
			$scope.errorMessageShow = true;
			$scope.loading = false;
			$scope.registerForm = true;
			ServletCall.adminRegisterFactory($scope.userNameAdd,
					$scope.passwordAdd, $scope.fNameAdd, $scope.lNameAdd,
					$scope.chooseRole.role, $scope.genderAdd, $scope.ageAdd,
					$scope.phoneNumAdd, $scope.addressAdd, "0", "0", $scope);
		} else if ($scope.chooseRole.role == "Manager") {
			if (!checkNotNull($scope.deptAdd)) {
				$scope.errorMessage = "Please enter department name";
				$scope.errorMessageShow = false;
			} else if (!checkNotNull($scope.numOfEmpAdd)) {
				$scope.errorMessage = "Please enter number of employees";
				$scope.errorMessageShow = false;
			} else if (!checkIsInteger($scope.numOfEmpAdd)) {
				$scope.errorMessage = "Please enter valid employee numbers";
				$scope.errorMessageShow = false;
			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.adminRegisterFactory($scope.userNameAdd,
						$scope.passwordAdd, $scope.fNameAdd, $scope.lNameAdd,
						$scope.chooseRole.role, $scope.genderAdd,
						$scope.ageAdd, $scope.phoneNumAdd, $scope.addressAdd,
						$scope.deptAdd, $scope.numOfEmpAdd, $scope);
			}
		} else if ($scope.chooseRole.role == "Developer") {
			if (!checkNotNull($scope.skillAdd)) {
				$scope.errorMessage = "Please enter the technology";
				$scope.errorMessageShow = false;
			} else if (!checkNotNull($scope.yearsOfExpAdd)) {
				$scope.errorMessage = "Please enter years of experiences";
				$scope.errorMessageShow = false;
			} else if (!checkExpRange($scope.yearsOfExpAdd)) {
				$scope.errorMessage = "Please enter valid years";
				$scope.errorMessageShow = false;
			} else if ($scope.yearsOfExpAdd > $scope.ageAdd) {
				$scope.errorMessage = "Years of experience couldn't larger than age";
				$scope.errorMessageShow = false;
			} else if (($scope.ageAdd - $scope.yearsOfExpAdd) < 15) {
				$scope.errorMessage = "please check the years";
				$scope.errorMessageShow = false;
			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.adminRegisterFactory($scope.userNameAdd,
						$scope.passwordAdd, $scope.fNameAdd, $scope.lNameAdd,
						$scope.chooseRole.role, $scope.genderAdd,
						$scope.ageAdd, $scope.phoneNumAdd, $scope.addressAdd,
						$scope.skillAdd, $scope.yearsOfExpAdd, $scope);
			}
		} else if ($scope.chooseRole.role == "QA") {
			if (!checkNotNull($scope.toolsAdd)) {
				$scope.errorMessage = "Please enter tool name";
				$scope.errorMessageShow = false;
			} else if (!checkNotNull($scope.projectNameQAAdd)) {
				$scope.errorMessage = "Please enter project name";
				$scope.errorMessageShow = false;

			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.adminRegisterFactory($scope.userNameAdd,
						$scope.passwordAdd, $scope.fNameAdd, $scope.lNameAdd,
						$scope.chooseRole.role, $scope.genderAdd,
						$scope.ageAdd, $scope.phoneNumAdd, $scope.addressAdd,
						$scope.toolsAdd, $scope.projectNameQAAdd, $scope);
			
			}
		} else if ($scope.chooseRole.role == "Training") {

			if (!checkNotNull($scope.projectNameTAdd)) {
				$scope.errorMessage = "Please enter project name";
				$scope.errorMessageShow = false;

			} else if (!checkNotNull($scope.numOfStudentAdd)) {
				$scope.errorMessage = "Please enter number of students";
				$scope.errorMessageShow = false;
	
			} else if (!checkIsInteger($scope.numOfStudentAdd)) {
				$scope.errorMessage = "Please enter valid student numbers";
				$scope.errorMessageShow = false;

			} else {
				$scope.errorMessageShow = true;
				$scope.loading = false;
				$scope.registerForm = true;
				ServletCall.adminRegisterFactory($scope.userNameAdd,
						$scope.passwordAdd, $scope.fNameAdd, $scope.lNameAdd,
						$scope.chooseRole.role, $scope.genderAdd,
						$scope.ageAdd, $scope.phoneNumAdd, $scope.addressAdd,
						$scope.projectNameTAdd, $scope.numOfStudentAdd, $scope);
			}
		}
	};
	// !!!! return adminRegisterFactory data
	$scope.adminRegisterReturn = function(data) {
		$scope.result = data.ResponseChecker;

		if ($scope.result == 0) {
			$scope.resultM = false;
			$scope.resultMessage = data.Message;
			$scope.loading = true;
			$scope.registerForm = false;
		} else {
			$scope.loading = true;
			$scope.registerForm = true;
			$scope.registerShow = false;

			$scope.successMessage = data.Message;
			$scope.addemail = $scope.userNameAdd;
			$scope.addFName = $scope.fNameAdd;
			$scope.addLName = $scope.lNameAdd;
			$scope.addRole = $scope.chooseRole.role;
			$scope.addGender = $scope.genderAdd;
			$scope.addAge = $scope.ageAdd;
			$scope.addPhoneNum = $scope.phoneNumAdd;
			$scope.addAddress = $scope.addressAdd;
		}
	};

	// !!!! Dropdown List for choose records per page
	$scope.pagesRequest = [ {
		pageNumberRequest : '5'
	}, {
		pageNumberRequest : '10'
	}, {
		pageNumberRequest : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPageRequest = $scope.pagesRequest[0];

	// !!!! Emp Request Button Call
	$scope.checkRowPerPageApproval = function(curPage, totalRecord, totalPage) {


		$scope.loading = false;
		$scope.empShow = true;
		$scope.allEmpPart = true;
		$scope.addEmpPart = true;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = true;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;

		$scope.requestMessage = true;
		$scope.currentRequestPage = curPage;


		ServletCall.getTotalEmpNumberFactory(curPage,
				$scope.rowPerPageDelete.pageNumberDelete, "request", $scope);

	};

	$scope.setPageRequest = function(changePage) {

		$scope.checkRowPerPageApproval(changePage, 0, 0);
	};

	// Return getTotalPageFactoryt Data

	$scope.getTotalPageReturn = function(curPage, rowPerPage, data) {
		$scope.totalPageRequest = [];// Get request total page number

		$scope.totalPageRequest = data;
		ServletCall.empRequestFactory(curPage, rowPerPage, $scope);
	};

	// Return Factory empRequest Data

	$scope.empRequestReturn = function(data, curPage) {

		if (data.length == 0 && curPage == 1) {

			$scope.noRequestMessage = "No request at this time";
			$scope.requestMessage = false;
			$scope.loading = true;
		} else if (data.length != 0) {
			$scope.empRequestInfo = [];// Get request emp Info
			$scope.empRequestPart = false;
			$scope.empRequestInfo = data;
			$scope.loading = true;
		} else {
			$scope.checkRowPerPageApproval(1, 0, 0);
		}
	};

	// !!!! Approve one button call
	$scope.approveOneEmp = function(userName) {

		if (confirm("Do you want to approve this request?")) {
			ServletCall.approveOneFactory(userName, $scope);

		}
	};

	// Return approveOneFactory Data
	$scope.approveOneReturn = function(data) {
		//alert(data.Message);

		$scope.checkRowPerPageApproval($scope.currentRequestPage, 0, 0);

	};

	// !!!! Deny one button call
	$scope.denyOneEmp = function(userName) {
		
		if (confirm("Do you want to deny this request?")) {
			ServletCall.denyOneFactory(userName, $scope);
		}
	};

	// Return denyOneFactory Data
	$scope.denyOneReturn = function(data) {
		//alert(data.Message);
		// alert($scope.currentRequestPage);
		$scope.checkRowPerPageApproval($scope.currentRequestPage, 0, 0);
	};

	// !!!! Approve All button Call + approve all servlet call
	$scope.approveAll = function() {

		if (confirm("Do you want to approve all requests?")) {
			ServletCall.approveAllFactory($scope);
		}
	};

	// Return approveAllFactory Data
	$scope.approveAllReturn = function(data) {
		//alert(data.Message);
		$scope.checkRowPerPageApproval(1, 0, 0);
	};

	// !!!! Deny All button Call + deny all servlet call
	$scope.denyAll = function() {

		if (confirm("Do you want to approve all requests?")) {
			ServletCall.denyAllFactory($scope);
		}
	};

	$scope.denyAllReturn = function(data) {
		//alert(data.Message);
		$scope.checkRowPerPageApproval(1, 0, 0);
	};

	// !!!! View Group Manger Info Servlet Call / View Group Info Button Call

	$scope.groupInfo = function() {

		$scope.loading = false;
		$scope.empShow = true;
		$scope.allEmpPart = true;
		$scope.addEmpPart = true;
		$scope.registerShow = true;
		$scope.groupInfoPart = true;
		$scope.groupDetail = true;
		$scope.deleteEmpPart = true;
		$scope.empRequestPart = true;
		$scope.requestMessage = true;
		$scope.groupMessage = true;
		$scope.timesheetInfoShow = true;
		$scope.noTimesheetPart = true;

		ServletCall.viewGroupFactory($scope);

	};

	$scope.viewGroupReturn = function(data) {
		if (data.length == 0) {
	
			$scope.noGroupMessage = "No group at this time";
			$scope.groupMessage = false;
			$scope.loading = true;
		} else {
			$scope.groupManager = []; // Get group manger info json data
			$scope.groupManager = data;
			$scope.loading = true;
			$scope.groupInfoPart = false;
		}
	};
	// !!!! View group Employee servlet call

	$scope.viewGroupDetail = function() {
		$scope.loading = false;

		var e = e || window.event;
		var groupID = [];

		var target = e.srcElement || e.target;
		while (target && target.nodeName !== "TR") {
			target = target.parentNode;
		}
		if (target) {
			var cells = target.getElementsByTagName("td");
			groupID.push(cells[4].innerHTML);
		}



		ServletCall.viewGroupDetailFactory(groupID, $scope);
	};

	$scope.viewGroupDetailReturn = function(data) {
		$scope.totalGroupEmp = data.length;
		$scope.groupEmp = [];// Get Group Emp info json data
		$scope.groupEmp = data;
		$scope.loading = true;
		$scope.groupInfoPart = false;
		$scope.groupDetail = false;
	};
	// New added

	// Code for view group accordion
	$scope.oneAtATime = true; // for checkbox

	$scope.tableRowExpanded = false;
	$scope.tableRowIndexExpandedCurr = "";
	$scope.tableRowIndexExpandedPrev = "";
	$scope.storeIdExpanded = "";

	$scope.dayDataCollapseFn = function() {
		$scope.dayDataCollapse = [];
		for (var i = 0; i < $scope.groupManager.length; i += 1) {
			$scope.dayDataCollapse.push(false);
		}
	};

	$scope.selectTableRow = function(index, storeId) {
		if (typeof $scope.dayDataCollapse === 'undefined') {
			$scope.dayDataCollapseFn();
		}

		if ($scope.tableRowExpanded === false
				&& $scope.tableRowIndexExpandedCurr === ""
				&& $scope.storeIdExpanded === "") {
			$scope.tableRowIndexExpandedPrev = "";
			$scope.tableRowExpanded = true;
			$scope.tableRowIndexExpandedCurr = index;
			$scope.storeIdExpanded = storeId;
			ServletCall.viewGroupDetailFactory($scope.storeIdExpanded, $scope);
			$scope.dayDataCollapse[index] = true;
		} else if ($scope.tableRowExpanded === true) {
			if ($scope.tableRowIndexExpandedCurr === index
					&& $scope.storeIdExpanded === storeId) {
				$scope.tableRowExpanded = false;
				$scope.tableRowIndexExpandedCurr = "";
				$scope.storeIdExpanded = "";
				$scope.dayDataCollapse[index] = false;
			} else {
				$scope.tableRowIndexExpandedPrev = $scope.tableRowIndexExpandedCurr;
				$scope.tableRowIndexExpandedCurr = index;
				$scope.storeIdExpanded = storeId;
				ServletCall.viewGroupDetailFactory($scope.storeIdExpanded,
						$scope);
				$scope.dayDataCollapse[$scope.tableRowIndexExpandedPrev] = false;
				$scope.dayDataCollapse[$scope.tableRowIndexExpandedCurr] = true;
			}
		}

	};

	// View Timesheet Part Code
	$scope.pagesTimesheet = [ {
		pageNumberTimesheet : '5'
	}, {
		pageNumberTimesheet : '10'
	}, {
		pageNumberTimesheet : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPageTimesheet = $scope.pagesTimesheet[0];

	$scope.setPageTimesheet = function(changePage) {
		$scope.checkRowPerPageTimesheet(changePage);
	};

	$scope.checkRowPerPageTimesheet = function(curPage, userName, fName, lName) {
		$scope.employeeUserName = userName;
		$scope.firstName = fName;
		$scope.lastName = lName;

		$scope.employeeName = fName + " " + lName;
		$scope.currentPageTimesheet = curPage;
		$scope.loading = false;

		TimesheetServletCall.getSubmittedTimesheetFactory(userName, curPage,
				$scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTotalNumber", $scope);
	};

	$scope.getSubmittedTimesheetReturn = function(userName, curPage,
			rowPerPage, operation, data) {

		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}

		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;


		TimesheetServletCall.getSubmittedTimesheetFactory(userName, curPage,
				$scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTimesheetByRowPerPage", $scope);
	};

	$scope.getSubmittedTimesheetByRowPerPageReturn = function(data) {

		$scope.loading = true;

		if (data.length == 0) {
			$scope.allEmpPart = true;
			$scope.noTimesheetPart = false;
			$scope.timesheetInfoShow = true;
			$scope.createGroupPart = true;
		} else {
			$scope.totalTimesheet = [];
			$scope.totalTimesheet = data;
			$scope.timesheetInfoShow = false;
			$scope.noTimesheetPart = true;
			$scope.allEmpPart = true;
		}
	};

	$scope.approveTimesheet = function(employeeName, date) {

		$scope.day = new Date(date);
		if (confirm("Do you want to approve this timesheet?")) {
			TimesheetServletCall.submitTimesheetFactory(employeeName,
					$scope.day, "approve", $scope);
		}
	};

	$scope.disapproveTimesheet = function(employeeName, date) {

		$scope.day = new Date(date);
		if (confirm("Do you want to deny this timesheet?")) {
			TimesheetServletCall.submitTimesheetFactory(employeeName,
					$scope.day, "disapprove", $scope);
		}
	};

	$scope.submitTimesheetReturn = function(data) {
		$scope.checkRowPerPageTimesheet(1, $scope.employeeUserName,
				$scope.firstName, $scope.lastName);
	};

	$scope.viewPDF = function(PdfURL) {
		$scope.viewPDFURL = "http://localhost:8080/timesheetPDF/" + PdfURL;
	
		var modalInstance = $modal.open({
			templateUrl : 'OpenPdfModal.html',
			controller : OpenPdfModal,
			resolve : {
				src : function() {
					return $scope.viewPDFURL;
				}
			}
		});

	};

}

var OpenPdfModal = function($scope, $modalInstance, src) {

	$scope.viewPDFURL = src;
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var ModalInstanceCtrlAdmin = function($scope, $modalInstance, users) {

	$scope.user = users;

	$scope.email = $scope.user.userName;
	$scope.showFName = $scope.user.fName;
	$scope.showLName = $scope.user.lName;
	$scope.showRole = $scope.user.role;
	$scope.showEmpNum = $scope.user.empNum;
	$scope.showGender = $scope.user.gender;
	$scope.showAge = $scope.user.age;
	$scope.showPhoneNum = $scope.user.phoneNum;
	$scope.showAddress = $scope.user.address;
	$scope.showGroupID = $scope.user.groupID;

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

function checkIsEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function checkNotNull(ch) {
	if (ch != null && ch != "")
		return true;
}

function checkIsRole(ch) {
	var chLower = ch.toLowerCase();
	if (chLower == 'a' || chLower == 'm' || chLower == 'd' || chLower == 'q'
			|| chLower == 't')
		return true;
}

function checkIsCharacter(ch) {
	var c = /^[ A-Za-z]*$/;
	return c.test(ch);
}

function checkIsInteger(ch) {
	var i = /^[0-9]*[1-9][0-9]*$/;
	return i.test(ch);
}

function checkIsGender(ch) {
	var newGender = ch.toLowerCase();
	if (newGender == 'female' || newGender == "male")
		return true;
}

function checkInputRange(ch) {
	if (ch > 0 && ch <= 100)
		return true;
}

function checkInputHrRange(ch) {
	if (ch >= 0 && ch <= 24)
		return true;
}

function checkAgeRange(ch) {
	if (ch >= 16 && ch <= 65)
		return true;
}

function checkExpRange(ch) {
	if (ch >= 0 && ch <= 49)
		return true;
}

function checkIsPhoneNum(phoneNum) {
	var ph = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	return ph.test(phoneNum);

}
