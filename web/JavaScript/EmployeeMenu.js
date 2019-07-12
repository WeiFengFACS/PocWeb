function EmployeeMenu($scope, $routeParams, ServletCall, TimesheetServletCall,
		$http, $modal) {
	$scope.params = $routeParams;
	$scope.loading = false;
	$scope.timesheetShow = true;

	// !!!!!!!!!!!!!!!!! Check Login Servlet Call

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

	// Return getInfoByIDFactory Data
	$scope.getInfoByIDReturn = function(data) {

		$scope.resultMessageShow = true;
		$scope.timesheetShow = true;
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
		$scope.loading = true;
		$scope.empShow = false;

	};

	// !!! Get Employee Info Button Call
	$scope.getPersonalInfo = function() {
		$scope.loading = false;
		$scope.empShow = true;

		ServletCall.getInfoByIDFactory($scope.params.userName, "default",
				$scope);
	};

	// !!!open timesheet
	$scope.hourCode = [ {
		codes : '--- none ---'
	}, {
		codes : 'Regular Hours'
	}, {
		codes : 'Overtime Hours'
	}, {
		codes : 'vacation'
	}, {
		codes : 'Sick'
	}, {
		codes : 'Paid Time-Off'
	}, {
		codes : 'Unpaid Leave'
	}, {
		codes : 'Week End'
	}, {
		codes : 'Holiday'
	} ];

	$scope.getMonCode = $scope.hourCode[0];
	$scope.getTueCode = $scope.hourCode[0];
	$scope.getWedCode = $scope.hourCode[0];
	$scope.getThuCode = $scope.hourCode[0];
	$scope.getFriCode = $scope.hourCode[0];
	$scope.getSatCode = $scope.hourCode[0];
	$scope.getSunCode = $scope.hourCode[0];

	$scope.getHourCode = function() {
		alert("monday " + $scope.getMonCode.codes + "/ tue "
				+ $scope.getTueCode.codes + "/ wed " + $scope.getWedCode.codes
				+ "/ thu " + $scope.getThuCode.codes + "/ fri "
				+ $scope.getFriCode.codes + "/ sat " + $scope.getSatCode.codes
				+ "/ sun " + $scope.getSunCode.codes);
	};

	$scope.cancelNewTimesheet = function() {

		$scope.checkRowPerPageTimesheet(1);
	};

	$scope.dt = "";
	$scope.checkTimesheetExist = function(dt) {

		if (dt == "") {
			$scope.createTimesheetMessage = "Please choose date";
			$scope.createTimesheetMessageShow = false;
		} else {
			$scope.dt = dt;
			$scope.createTimesheetMessageShow = true;
			TimesheetServletCall.checkTimesheetExistFactory(
					$scope.params.userName, dt, $scope);
		}
	};

	$scope.checkTimesheetExistReturn = function(data) {
		if (data.ResponseChecker == 0) {
			$scope.createTimesheetMessage = data.Message;
			$scope.createTimesheetMessageShow = false;
		} else {

			$scope.getDay($scope.dt);

			ServletCall.saveTimesheetsFactory($scope.params.userName,
					$scope.date_Sunday, $scope.date_Saturday,
					$scope.defaultInfo.monR, $scope.defaultInfo.monOT,
					$scope.defaultInfo.tueR, $scope.defaultInfo.tueOT,
					$scope.defaultInfo.wedR, $scope.defaultInfo.wedOT,
					$scope.defaultInfo.thuR, $scope.defaultInfo.thuOT,
					$scope.defaultInfo.friR, $scope.defaultInfo.friOT,
					$scope.defaultInfo.satR, $scope.defaultInfo.satOT,
					$scope.defaultInfo.sunR, $scope.defaultInfo.sunOT,
					"Not Approve yet", "null", "Not Due Yet", "40", "40", "0",
					$scope.showGroupID, $scope);
			$scope.editNewTimesheet();
		}
	};

	$scope.editNewTimesheet = function() {
//		alert("inside editnewTime SHeet !!!");
		$scope.inputErrorMessageShow = true;
		$scope.editTimesheetShow = false;
		$scope.datePickerShow = true;
		$scope.timesheetInfoShow = true;
		$scope.noEditRightMessageShow = true;
		$scope.detailInfo = $scope.defaultInfo;
		$scope.setTimesheetValue();
		$scope.client = $scope.showGroupID;
	};

	$scope.getTimesheet = function() {
//		alert("call open timesheet");
		$scope.empShow = true;
		$scope.timesheetShow = false;
		$scope.viewTimesheetShow = true;
		$scope.editTimesheetShow = true;
		$scope.timesheetInfoShow = false;
		$scope.datePickerShow = true;
		$scope.framepdf = true;
		$scope.noTimesheetPart = true;
		ServletCall.getTotalTimesheetsFactory($scope.params.userName, $scope);

	};

	$scope.getTotalTimesheetsReturn = function(data) {
		$scope.totalTimesheet = [];
		$scope.totalTimesheet = data;
	};

	$scope.submitTimesheet = function() {
		if (confirm("Do you want to submit the timesheet?")) {
			TimesheetServletCall.submitTimesheetFactory($scope.params.userName,
					$scope.date_Sunday, "submit", $scope);
		}
		
	};

	$scope.submitTimesheetReturn = function(data) {
		$scope.checkRowPerPageTimesheet($scope.currentPage);
	};

	$scope.defaultInfo = {
		monR : 8.00,
		monOT : 0.00,
		monNon : 0.00,
		tueR : 8.00,
		tueOT : 0.00,
		tueNon : 0.00,
		wedR : 8.00,
		wedOT : 0.00,
		wedNon : 0.00,
		thuR : 8.00,
		thuOT : 0.00,
		thuNon : 0.00,
		friR : 8.00,
		friOT : 0.00,
		friNon : 0.00,
		satR : 0.00,
		satOT : 0.00,
		satNon : 0.00,
		sunR : 0.00,
		sunOT : 0.00,
		sunNon : 0.00,
		comment : "none",
		URL : null,
	};

	$scope.viewTimesheet = function(date) {
		// alert(date);
		$scope.timesheetInfoShow = true;
		$scope.viewTimesheetShow = false;
		$scope.editTimesheetShow = true;
		$scope.timesheetTable = false;
		$scope.framepdf = true;
		$scope.saveShow = true;

		ServletCall.getTimesheetsFactory($scope.params.userName, date, "view",
				$scope);
	};

	$scope.closeTimesheet = function() {
		if (confirm("Do you want to close the timesheet?")) {
			$scope.checkRowPerPageTimesheet($scope.currentPage);
		}
		
	};

	$scope.editTimesheet = function(date) {
//		alert("call edit" + date);
		$scope.session = {};
		$scope.viewTimesheetShow = true;

		$scope.inputErrorMessageShow = true;
		$scope.saveMessageShow = true;
		$scope.framepdf = true;
		$scope.saveShow = true;
		$scope.datePickerShow = true;
		$scope.progressVisible = false;

		ServletCall.getTimesheetsFactory($scope.params.userName, date, "edit",
				$scope);
	};

	$scope.getTimesheetsEditReturn = function(data, date) {
		$scope.startDate = date;
//		alert("!!!!!" + data.result);
		if (data.result == 1) {

			$scope.detailInfo = data;
			$scope.generateDate(date);
			$scope.setTimesheetValue();
			$scope.editTimesheetShow = false;
			$scope.timesheetInfoShow = true;
			$scope.noEditRightMessageShow = true;
		} else if (data.result == 0) {
			$scope.editTimesheetShow = true;
			$scope.timesheetInfoShow = false;
			$scope.noEditRightMessageShow = false;
		}

	};

	$scope.getTimesheetsViewReturn = function(data, date) {

		$scope.startDate = date;
		$scope.detailInfo = data;
		$scope.generateDate(date);
		$scope.setTimesheetValue();
		if (data.submit == "Not Due Yet") {
			$scope.editRightButton = false;
		} else {
			$scope.editRightButton = true;
		}
	};

	$scope.setTimesheetValue = function() {

		$scope.sunRValue = $scope.detailInfo.sunR;
		$scope.sunOTValue = $scope.detailInfo.sunOT;
		$scope.sunTotal = (parseInt($scope.sunRValue) + parseInt($scope.sunOTValue));

		$scope.monRValue = $scope.detailInfo.monR;
		$scope.monOTValue = $scope.detailInfo.monOT;
		$scope.monTotal = (parseInt($scope.monRValue) + parseInt($scope.monOTValue));

		$scope.tueRValue = $scope.detailInfo.tueR;
		$scope.tueOTValue = $scope.detailInfo.tueOT;
		$scope.tueTotal = (parseInt($scope.tueRValue) + parseInt($scope.tueOTValue));

		$scope.wedRValue = $scope.detailInfo.wedR;
		$scope.wedOTValue = $scope.detailInfo.wedOT;
		$scope.wedTotal = (parseInt($scope.wedRValue) + parseInt($scope.wedOTValue));

		$scope.thuRValue = $scope.detailInfo.thuR;
		$scope.thuOTValue = $scope.detailInfo.thuOT;
		$scope.thuTotal = (parseInt($scope.thuRValue) + parseInt($scope.thuOTValue));

		$scope.friRValue = $scope.detailInfo.friR;
		$scope.friOTValue = $scope.detailInfo.friOT;
		$scope.friTotal = (parseInt($scope.friRValue) + parseInt($scope.friOTValue));

		$scope.satRValue = $scope.detailInfo.satR;
		$scope.satOTValue = $scope.detailInfo.satOT;
		$scope.satTotal = (parseInt($scope.satRValue) + parseInt($scope.satOTValue));

		$scope.regularTotal = (parseInt($scope.sunRValue)
				+ parseInt($scope.monRValue) + parseInt($scope.tueRValue)
				+ parseInt($scope.wedRValue) + parseInt($scope.thuRValue)
				+ parseInt($scope.friRValue) + parseInt($scope.satRValue));

		$scope.otTotal = (parseInt($scope.sunOTValue)
				+ parseInt($scope.monOTValue) + parseInt($scope.tueOTValue)
				+ parseInt($scope.wedOTValue) + parseInt($scope.thuOTValue)
				+ parseInt($scope.friOTValue) + parseInt($scope.satOTValue));

		$scope.totalHr = $scope.regularTotal + $scope.otTotal;
		$scope.URL = $scope.detailInfo.URL;
		if ($scope.URL != null) {
			$scope.viewPdfButton = false;
		} else {
			$scope.viewPdfButton = true;
		}
		$scope.client = $scope.detailInfo.client;
		$scope.comment = $scope.detailInfo.comment;
	}

	$scope.saveimesheet = function() {
		if (!$scope.inputErrorMessageShow) {
			$scope.saveMessage = "You have errors in you timesheets";
			$scope.saveMessageShow = false;
		} else {
			$scope.saveMessageShow = true;
			ServletCall.saveTimesheetsFactory($scope.params.userName,
					$scope.date_Sunday, $scope.date_Saturday, $scope.monRValue,
					$scope.monOTValue, $scope.tueRValue, $scope.tueOTValue,
					$scope.wedRValue, $scope.wedOTValue, $scope.thuRValue,
					$scope.thuOTValue, $scope.friRValue, $scope.friOTValue,
					$scope.satRValue, $scope.satOTValue, $scope.sunRValue,
					$scope.sunOTValue, "Not Approve yet", "null",
					"Not Due Yet", $scope.totalHr, $scope.regularTotal,
					$scope.otTotal, $scope.showGroupID, $scope);
		}
	};

	$scope.saveTimesheetsReturn = function(data) {
		$scope.save = data.Message;
		$scope.saveShow = false;
	};

	$scope.printPdf = function() {
		var doc = new jsPDF("landscape", "mm", "a4");

		doc.setFontSize(22);
		doc.text(20, 20, "Employee Time Sheet for: " + $scope.showFName + " "
				+ $scope.showLName);
		doc.text(20, 30, "Start Date: " + $scope.date_Sunday);
		doc.text(20, 40, "End Date: " + $scope.date_Saturday);
		doc.line(20, 45, 280, 45);

		doc.setFontSize(18);
		doc.text(20, 60, "Total Regular Hour: " + $scope.regularTotal);
		doc.text(20, 70, "Total OverTime Hour: " + $scope.otTotal);
		doc.text(20, 80, "Total Working Hour: " + $scope.totalHr);
		
		doc.text(70,95, "Regular Hours");
		doc.text(140,95, "Overtime Hours");
		doc.line(20, 100, 280, 100);
		doc.text(20,110, "Sunday");
		doc.text(90,110, $scope.sunRValue);
		doc.text(165,110, $scope.sunOTValue);
		doc.text(20,120, "Monday");
		doc.text(90,120, $scope.monRValue);
		doc.text(165,120, $scope.monOTValue);
		doc.text(20,130, "Tuesday");
		doc.text(90,130, $scope.tueRValue);
		doc.text(165,130, $scope.tueOTValue);
		doc.text(20,140, "Wednesday");
		doc.text(90,140, $scope.wedRValue);
		doc.text(165,140, $scope.wedOTValue);
		doc.text(20,150, "Thursday");
		doc.text(90,150, $scope.thuRValue);
		doc.text(165,150, $scope.thuOTValue);
		doc.text(20,160, "Friday");
		doc.text(90,160, $scope.friRValue);
		doc.text(165,160, $scope.friOTValue);
		doc.text(20,170, "Saturday");
		doc.text(90,170, $scope.satRValue);
		doc.text(165,170, $scope.sunOTValue);
		doc.line(20, 175, 280, 175);

		doc.setFontSize(22);
		doc.text(20, 195, "Manager Signature: ");

		$scope.pdfUrl = doc.output('datauristring');
		$scope.framepdf = false;
	};

	$scope.callChange = function(input) {
		$scope.saveShow = true;
		$scope.saveMessageShow = true;
		switch (input) {
		case 0:
			if ($scope.sunRValue == "") {
				$scope.sunRValue = 0.00;
			} else if ($scope.sunOTValue == "") {
				$scope.sunOTValue = 0.00;
			}
			$scope.checkInput($scope.sunRValue, $scope.sunOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.sunTotal = (parseInt($scope.sunRValue) + parseInt($scope.sunOTValue));
			}
			break;
		case 1:
			if ($scope.monRValue == "") {
				$scope.monRValue = 0.00;
			} else if ($scope.monOTValue == "") {
				$scope.monOTValue = 0.00;
			}
			$scope.checkInput($scope.monRValue, $scope.monOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.monTotal = (parseInt($scope.monRValue) + parseInt($scope.monOTValue));
			}
			break;
		case 2:
			if ($scope.tueRValue == "") {
				$scope.tueRValue = 0.00;
			} else if ($scope.tueOTValue == "") {
				$scope.tueOTValue = 0.00;
			}
			$scope.checkInput($scope.tueRValue, $scope.tueOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.tueTotal = (parseInt($scope.tueRValue) + parseInt($scope.tueOTValue));
			}
			break;
		case 3:
			if ($scope.wedRValue == "") {
				$scope.wedRValue = 0.00;
			} else if ($scope.wedOTValue == "") {
				$scope.wedOTValue = 0.00;
			}
			$scope.checkInput($scope.wedRValue, $scope.wedOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.wedTotal = (parseInt($scope.wedRValue) + parseInt($scope.wedOTValue));
			}
			break;
		case 4:
			if ($scope.thuRValue == "") {
				$scope.thuRValue = 0.00;
			} else if ($scope.thuOTValue == "") {
				$scope.thuOTValue = 0.00;
			}
			$scope.checkInput($scope.thuRValue, $scope.thuOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.thuTotal = (parseInt($scope.thuRValue) + parseInt($scope.thuOTValue));
			}
			break;
		case 5:
			if ($scope.friRValue == "") {
				$scope.friRValue = 0.00;
			} else if ($scope.friOTValue == "") {
				$scope.friOTValue = 0.00;
			}
			$scope.checkInput($scope.friRValue, $scope.friOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.friTotal = (parseInt($scope.friRValue) + parseInt($scope.friOTValue));
			}
			break;
		case 6:
			if ($scope.satRValue == "") {
				$scope.satRValue = 0.00;
			} else if ($scope.satOTValue == "") {
				$scope.satOTValue = 0.00;
			}
			$scope.checkInput($scope.satRValue, $scope.satOTValue);
			if ($scope.checker != false) {
				$scope.inputErrorMessageShow = true;
				$scope.satTotal = (parseInt($scope.satRValue) + parseInt($scope.satOTValue));
			}
			break;
		}
		$scope.regularTotal = (parseInt($scope.sunRValue)
				+ parseInt($scope.monRValue) + parseInt($scope.tueRValue)
				+ parseInt($scope.wedRValue) + parseInt($scope.thuRValue)
				+ parseInt($scope.friRValue) + parseInt($scope.satRValue));

		$scope.otTotal = (parseInt($scope.sunOTValue)
				+ parseInt($scope.monOTValue) + parseInt($scope.tueOTValue)
				+ parseInt($scope.wedOTValue) + parseInt($scope.thuOTValue)
				+ parseInt($scope.friOTValue) + parseInt($scope.satOTValue));

		$scope.totalHr = $scope.regularTotal + $scope.otTotal;

	};

	$scope.checkInput = function(inputR, inputOT) {
		// alert("call validation" + inputR + inputOT);
		if (!checkInputHrRange(inputR)) {
			$scope.inputErrorMessage = "Please enter valid regular hours";
			$scope.inputErrorMessageShow = false;
			// return $scope.inputR = 0.00;
			// return $scope.inputOT = inputOT;
			return $scope.checker = false;
		} else if (!checkInputHrRange(inputOT)) {
			$scope.inputErrorMessage = "Please enter valid overtime hours";
			$scope.inputErrorMessageShow = false;
			// return $scope.inputR = inputR;
			// return $scope.inputOT = 0.00;
			return $scope.checker = false;
		} else if ((parseInt(inputR) + parseInt(inputOT)) > 24) {
			$scope.inputErrorMessage = "Total Hours cannot be larger than 24";
			$scope.inputErrorMessageShow = false;
			return $scope.checker = false;
		} else {
			// return $scope.inputR = inputR;
			// return $scope.inputOT = inputOT;
			return $scope.checker = true;
		}
	};

	$scope.generateDate = function(date) {

		$scope.splitDate = date.split("-");

		$scope.date_Sunday = date;
		var d3 = new Date($scope.splitDate);

		$scope.getDay(d3);
	};

	$scope.getDay = function(date) {
		$scope.getSun(date);
		$scope.getMon(date);
		$scope.getTue(date);
		$scope.getWed(date);
		$scope.getThu(date);
		$scope.getFri(date);
		$scope.getSat(date);
	};

	$scope.getSun = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 0 : 16); // adjust when day is Sunday
		$scope.date_Sunday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Sunday;
	};
	$scope.getMon = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 1 : -6);
		$scope.date_Monday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Monday;
	};

	$scope.getTue = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 2 : -6);
		$scope.date_Tuesday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Tuesday;
	};

	$scope.getWed = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 3 : -6); //
		$scope.date_Wednesday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Wednesday;
	};

	$scope.getThu = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 4 : -6);
		$scope.date_Thursday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Thursday;
	};

	$scope.getFri = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 5 : -6);
		$scope.date_Friday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Friday;
	};

	$scope.getSat = function(d) {
		$scope.d = new Date(d);
		$scope.day = d.getDay(), $scope.diff = $scope.d.getDate() - $scope.day
				+ ($scope.day == 0 ? 6 : -6);
		$scope.date_Saturday = new Date($scope.d.setDate($scope.diff));
		return $scope.date_Saturday;
	};

	//	
	$scope.setFiles = function(element) {
		$scope.$apply(function($scope) {
			console.log('files:', element.files);
			// Turn the FileList object into an Array
			$scope.files = []
			for (var i = 0; i < element.files.length; i++) {
				$scope.files.push(element.files[i])
			}
			$scope.progressVisible = false;
			$scope.uploadButton = false;
		});
	};

	$scope.uploadFile = function() {

		var fd = new FormData();
		for ( var i in $scope.files) {
			fd.append("uploadedFile", $scope.files[i])
		}
		var xhr = new XMLHttpRequest()
		xhr.upload.addEventListener("progress", uploadProgress, false)
		xhr.addEventListener("load", uploadComplete, false)
		xhr.addEventListener("error", uploadFailed, false)
		xhr.addEventListener("abort", uploadCanceled, false)
		xhr.open("POST", "./UploadServletNew")

		$scope.progressVisible = true
		xhr.send(fd)
	};

	function uploadProgress(evt) {
		$scope.$apply(function() {
			if (evt.lengthComputable) {
				$scope.progress = Math.round(evt.loaded * 100 / evt.total)
			} else {
				$scope.progress = 'unable to compute'
			}
		})
	}

	function uploadComplete(evt) {
		/* This event is raised when the server send back a response */
		// alert(evt.target.responseText)
		$scope.URL = evt.target.responseText;
		if ($scope.URL != null) {
			$scope.viewPdfButton = false;
		} else {
			$scope.viewPdfButton = true;
		}
		ServletCall.updateURLFactory($scope.params.userName, $scope.startDate,
				evt.target.responseText, $scope);
		$scope.uploadButton = true;
	}

	function uploadFailed(evt) {
		alert("There was an error attempting to upload the file.")
	}

	function uploadCanceled(evt) {
		$scope.$apply(function() {
			$scope.progressVisible = false
			$scope.uploadButton = true;
		})
		alert("The upload has been canceled by the user or the browser dropped the connection.")
	}

	//	

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

	$scope.checkRowPerPageTimesheet = function(curPage) {
		$scope.empShow = true;
		$scope.timesheetShow = false;
		$scope.viewTimesheetShow = true;
		$scope.editTimesheetShow = true;
		$scope.timesheetInfoShow = false;
		$scope.datePickerShow = true;
		$scope.framepdf = true;
		$scope.noTimesheetPart = true;
		$scope.noEditRightMessageShow = true;

		$scope.currentPage = curPage;
	
		$scope.loading = false;

		TimesheetServletCall.getTotalTimesheetFactory($scope.params.userName,
				curPage, $scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTotalNumber", $scope);
	};

	$scope.getTotalTimesheetReturn = function(userName, curPage, rowPerPage,
			operation, data) {
//		alert("inside get total records return");
		if (rowPerPage == 5) {
			$scope.bigTotalItems = data.totalRecords * 2;
		} else if (rowPerPage == 10) {
			$scope.bigTotalItems = data.totalRecords;
		} else {
			$scope.bigTotalItems = data.totalRecords / 2;
		}

		$scope.bigCurrentPage = curPage;
		$scope.maxSize = 10;

		TimesheetServletCall.getTotalTimesheetFactory($scope.params.userName,
				curPage, $scope.rowPerPageTimesheet.pageNumberTimesheet,
				"getTimesheetByRowPerPage", $scope);
	};

	$scope.getTimesheetByRowPerPageReturn = function(data) {
		$scope.loading = true;

		if (data.length == 0) {
			$scope.noTimesheetPart = false;
			$scope.timesheetInfoShow = true;
		} else {
			$scope.totalTimesheet = [];
			$scope.totalTimesheet = data;
		}
	};

	$scope.createNewTimesheet = function() {
//		alert("create new timesheet");
		$scope.datePickerShow = false;
		$scope.timesheetInfoShow = true;
		$scope.createTimesheetMessageShow = true;
		$scope.noTimesheetPart = true;
	};

	$scope.disabled = function(date, mode) {
		return (mode === 'day' && (date.getDay() === 1 || date.getDay() === 2
				|| date.getDay() === 3 || date.getDay() === 4
				|| date.getDay() === 5 || date.getDay() === 6));
	};

	$scope.toggleMin = function() {
		$scope.minDate = new Date();
		$scope.minDate
				.setTime($scope.minDate.getTime() - 20 * 24 * 3600 * 1000);
	};
	$scope.toggleMin();

	$scope.toggleMax = function() {
		$scope.maxDate = new Date();
		$scope.maxDate.setTime($scope.maxDate.getTime() + 7 * 24 * 3600 * 1000);
	};
	$scope.toggleMax();

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
