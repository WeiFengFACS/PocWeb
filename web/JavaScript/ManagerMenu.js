function ManagerMenu($scope, $routeParams, ServletCall, TimesheetServletCall,
		$modal, $log) {
	$scope.params = $routeParams;

	$scope.closeAllView = function() {
		$scope.loading = false;
		$scope.allEmpPart = true;
		$scope.createGroupPart = true;
		$scope.empShow = true;
		$scope.viewAvailablePart = true;
		$scope.noTimesheetPart = true;
		$scope.timesheetInfoShow = true;
		$scope.dropGroupMessageShow = true;
	};

	$scope.closeAllView();

	ServletCall.ckeckLogInFactory($scope.params.userName, $scope);

	$scope.ckeckLogInReturn = function(data) {
		if (data == "false") {
			window.location = "index.html#/Login";
		}
	};

	// !!!!!!!!!!!!!!!!!!Logout Servlet call
	$scope.logOut = function() {
		ServletCall.logOutFactory($scope.params.userName);
	};

	// !!!!! Get Personal Info when first load page
	// !!!!!! getInfoByIDFactory call
	ServletCall.getInfoByIDFactory($scope.params.userName, "default", $scope);

	// !!! Get Employee Info Button Call
	// ===================================================================
	$scope.getPersonalInfo = function() {
		$scope.closeAllView();
		ServletCall.getInfoByIDFactory($scope.params.userName, "default",
				$scope);
	};

	// Get emp info
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
			$scope.managerGroupID = data.groupID;
		}
	};

	// View All Emp Part
	// =====================================================================
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

	// !!!! View All employee button call
	$scope.checkRowPerPage = function(curPage) {
		$scope.closeAllView();
		$scope.currentPageViewAll = curPage;// curPage for viewAll

		$scope.userNameInput = "";
		$scope.resultMessageShow = true;

		ServletCall.getTotalEmpNumberFactory1(curPage,
				$scope.rowPerPage.pageNumber, "managerView", $scope);
	};

	// get total page return
	$scope.getTotalEmpNumberReturn1 = function(curPage, rowPerPage, operation,
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
		ServletCall.managerViewAllEmpFactory(curPage, rowPerPage, $scope);// managerViewAllEmp
	};

	// return viewAllEmpFactory Data
	$scope.managerViewAllEmpReturn = function(data, curPage) {
		$scope.allEmp = []; // get all employee Json data
		$scope.allEmp = data;
		$scope.loading = true;
		$scope.allEmpPart = false;
	};

	$scope.setPage = function(changePage) {
		$scope.checkRowPerPage(changePage);
	};

	// get emp by ID modal view
	$scope.getInfoByIDReturn1 = function(data) {
		if (data.ResponseChecker == "0") {
			$scope.resultMessageShow = false;
			$scope.resultMessage = data.Message;
			$scope.empShow = true;
		} else {
			$scope.userNameInput = "";
			$scope.resultMessageShow = true;
			$scope.user = [];
			$scope.user = data;
			$scope.open1();
		}
	};

	// moadl open code

	$scope.open1 = function() {
		var modalInstance = $modal.open({
			templateUrl : 'myModalContent.html',
			controller : ModalInstanceCtrl,
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

	$scope.viewDetail = function(userName) {
		ServletCall.getInfoByIDFactory(userName, "search", $scope);
	};

	// !!!! View emp by ID in viewAll Page
	$scope.viewEmpByID = function() {
		if (!checkNotNull($scope.userNameInput)) {
			$scope.resultMessageShow = false;
			$scope.resultMessage = "Please enter the email address";
		} else if (!checkIsEmail($scope.userNameInput)) {
			$scope.resultMessageShow = false;
			$scope.resultMessage = "Please enter the valid email address";
		} else {
			ServletCall.getInfoByIDFactory($scope.userNameInput, "search",
					$scope);
		}
	};

	// View Group Info
	// =============================================================
	$scope.pagesGroup = [ {
		pageNumberGroup : '5'
	}, {
		pageNumberGroup : '10'
	}, {
		pageNumberGroup : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPageGroup = $scope.pagesGroup[0];

	// !!!! Delete Employee Button Call
	$scope.checkRowPerPageGroup = function(curPage) {
		$scope.closeAllView();
		$scope.dropGroupMessageShow = true;
		$scope.createGroupMessageShow = true;
		$scope.resultMessageShow = true;
		$scope.groupID = "";
		$scope.currentPageGroup = curPage;// curPage for group
		// alert($scope.currentPageGroup);

		ServletCall.getGroupNumberFactory(curPage,
				$scope.rowPerPageGroup.pageNumberGroup, $scope.params.userName,
				"getNum", $scope);
	};

	$scope.getGroupNumberReturn = function(curPage, rowPerPage, data) {
		$scope.createGroupPart = false;
		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}
		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;

		if (data.totalRecords != 0) {

			ServletCall.getGroupNumberFactory(curPage,
					$scope.rowPerPageGroup.pageNumberGroup,
					$scope.params.userName, "getInfo", $scope);
		} else {
			$scope.noRequestMessage = "No request at this time";
			$scope.showGroupPart = true;
			$scope.noGroupPart = false;
			$scope.noGroupMessageShow = false;
			$scope.createGroupShow = true;
			$scope.loading = true;
		}
	};

	$scope.getGroupNumberDetailReturn = function(curPage, rowPerPage, data) {
		if (data.length == 0 && curPage != 1) {
			$scope.checkRowPerPageGroup(1);
		} else if (data.length != 0) {
			$scope.noGroupPart = true;
			$scope.groupEmp = []; // get all employee Json data
			$scope.groupEmp = data;
			$scope.showGroupPart = false;
			$scope.loading = true;
		}

	};

	$scope.showCreate = function() {
		$scope.createGroupShow = false;
		$scope.noGroupMessageShow = true;
	}

	$scope.createGroupFeature = function() {
		if (!checkNotNull($scope.groupID)) {
			$scope.createGroupMessage = "Please enter the group ID";
			$scope.createGroupMessageShow = false;
		} else if (!checkIsInteger($scope.groupID)) {
			$scope.createGroupMessage = "Please enter the valid group ID";
			$scope.createGroupMessageShow = false;
		} else {
			ServletCall.createGroupFactory($scope.groupID,
					$scope.params.userName, $scope);
		}
	};

	// Get create group data return
	$scope.createGroupReturn = function(data) {
		if (data.Status == 1) {
			// alert("create Successful");
			$scope.managerGroupID = $scope.groupID;
			$scope.checkRowPerPageGroup(1);
			$scope.noGroupPart = true;
			$scope.showGroupPart = false;

		} else {
			$scope.createGroupMessage = data.Message;
			$scope.createGroupMessageShow = false;
		}
	};

	$scope.setPageGroup = function(changePage) {
		$scope.checkRowPerPageGroup(changePage);
	};

	$scope.deleteFromGroup = function(userName) {
		if (confirm("Do you want to delete this employee from group?")) {
			ServletCall.deleteFromGroupFactory(userName, $scope);
		}
	};

	$scope.deleteFromGroupReturn = function(data) {
		if (data.Status == 0) {
			$scope.resultMessage = data.Message;
			$scope.resultMessageShow = false;
		} else {
			//alert(data.Message);
			$scope.checkRowPerPageGroup($scope.currentPageGroup);
		}
	};

	// Drop Group
	$scope.dropGroup = function() {
		if (confirm("Do you like to drop the group?")) {
			ServletCall.dropGroupFactory($scope.params.userName, $scope);
		}
	};

	$scope.dropGroupReturn = function(data) {
		$scope.showGroupPart = true;
		$scope.dropGroupMessage = data.Message;
		$scope.dropGroupMessageShow = false;
		$scope.managerGroupID = 0;

	};

	// View Available Employee
	// ====================================================
	$scope.viewAvailableEmployee = function() {

	};

	$scope.pagesAvailable = [ {
		pageNumberAvailable : '5'
	}, {
		pageNumberAvailable : '10'
	}, {
		pageNumberAvailable : '20'
	} ];

	// !!!! Set default page is 5 records per page
	$scope.rowPerPageAvailable = $scope.pagesAvailable[0];

	// !!!! Delete Employee Button Call
	$scope.checkRowPerPageAvailable = function(curPage) {
//		alert("inside checkRowPerPageAvailable" + curPage);
		if ($scope.managerGroupID == 0) {
			$scope.noRightToAddToGroupShow = false;
		} else {
			$scope.noRightToAddToGroupShow = true;
		}
		$scope.closeAllView();
		$scope.currentPageAvailable = curPage;// curPage for available

		ServletCall.getAvailableEmpFactory(curPage,
				$scope.rowPerPageAvailable.pageNumberAvailable, "getNum",
				$scope);
	};

	$scope.getAvailableEmpReturn = function(curPage, rowPerPage, data) {
		$scope.viewAvailablePart = false;
		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}
		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;

//		alert("total data is " + data.totalRecords);
//		alert("do computation result is " + (curPage - 1) * rowPerPage);
//		alert("check statement is "
//				+ (data.totalRecords == ((curPage - 1) * rowPerPage)));
//		if (data.totalRecords == ((curPage - 1) * rowPerPage)) {
//			alert("do something");
//		} else 
			if (data.totalRecords != 0) {
//			alert("go to get info step!!!");
			ServletCall.getAvailableEmpFactory(curPage,
					$scope.rowPerPageAvailable.pageNumberAvailable, "getInfo",
					$scope);
		} else {
			$scope.noAvailableShow = false;
			$scope.showAvailableEmpPart = true;
			$scope.loading = true;

		}
	};

	$scope.getAvailableEmpDetailReturn = function(curPage, rowPerPage, data) {
		// alert("data return in available emp" + data.length + " currentpage is
		// " + $scope.currentPageAvailable);
		// alert("check statement is " + (data.length == 0 &&
		// $scope.currentPageAvailable != 1));
		// if (data.length == 0 && $scope.currentPageAvailable != 1) {
		// $scope.checkRowPerPageAvailable(1);
		// } else if (data.length != 0) {
		$scope.noAvailableShow = true;
		$scope.availableEmp = []; // get availabel employee Json data
		$scope.availableEmp = data;
		$scope.loading = true;
		$scope.showAvailableEmpPart = false;
		// }
	};

	$scope.setPageAvailable = function(changePage) {
		$scope.checkRowPerPageAvailable(changePage);
	};

	// add to group
	$scope.addToGroup = function(userName) {
		if (confirm("Do you want to add this employee to group?")) {
			ServletCall.addToGroupFactory(userName, $scope.params.userName,
					$scope);
		}
	};

	$scope.addToGroupReturn = function(data) {

		if (data.Status == 0) {
			document.getElementById("addToGroupMessage").style.display = "block";
			document.getElementById("addToGroupMessage").innerText = message;
		} else {
			alert(data.Message);
//			alert("curPage in addtogroupReturn is "
//					+ $scope.currentPageAvailable);
			$scope.checkRowPerPageAvailable($scope.currentPageAvailable);
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
		/*alert($scope.employeeUserName + "/" + $scope.firstName + "/"
				+ $scope.lastName);
		alert("get timesheet by " + userName);*/
		$scope.employeeName = fName + " " + lName;
		$scope.currentPageTimesheet = curPage;// curPage for timesheet
		$scope.loading = false;

		TimesheetServletCall.getNeedApproveTimesheetFactory(userName, curPage,
				$scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTotalNumber", $scope);
	};

	$scope.getNeedApproveTimesheetReturn = function(userName, curPage,
			rowPerPage, operation, data) {
		//alert("inside get total records return");
		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}

		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;

		TimesheetServletCall.getNeedApproveTimesheetFactory(userName, curPage,
				$scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTimesheetByRowPerPage", $scope);
	};

	$scope.getNeedApproveTimesheetByRowPerPageReturn = function(data) {
		$scope.loading = true;

		if (data.length == 0) {
			$scope.noTimesheetPart = false;
			$scope.timesheetInfoShow = true;
			$scope.createGroupPart = true;
		} else {
			$scope.totalTimesheet = [];
			$scope.totalTimesheet = data;
			$scope.timesheetInfoShow = false;
			$scope.noTimesheetPart = true;
			$scope.createGroupPart = true;
		}
	};

	$scope.approveTimesheet = function(employeeName, date) {
		//alert(employeeName + "/" + date);
		$scope.day = new Date(date);
		if (confirm("Do you want to approve the timesheet?")) {
			TimesheetServletCall.submitTimesheetFactory(employeeName,
					$scope.day, "approve", $scope);
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

var ModalInstanceCtrl = function($scope, $modalInstance, users) {

	$scope.user = users;
	if ($scope.user.role == "admin") {
		$scope.showEmpInfo = true;
		$scope.noAdminMessage = "You cannot view admin information";
		$scope.noAdminShow = false;
	} else {
		$scope.noAdminShow = true;
		$scope.showEmpInfo = false;
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
		$scope.showEmpNum = false;
	}
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};
